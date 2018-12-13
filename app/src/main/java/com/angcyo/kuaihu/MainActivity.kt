package com.angcyo.kuaihu

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import com.angcyo.http.Http
import com.angcyo.http.HttpSubscriber
import com.angcyo.kuaihu.http.AesEncryptionUtil
import com.angcyo.kuaihu.http.Constant
import com.angcyo.kuaihu.http.HttpDNS
import com.angcyo.kuaihu.http.PostFormBuilder
import com.angcyo.kuaihu.http.bean.HttpBean
import com.angcyo.kuaihu.http.bean.UserBean
import com.angcyo.kuaihu.http.bean.VideoDetailBean
import com.angcyo.kuaihu.http.bean.VideoListBean
import com.angcyo.uiview.less.base.BaseAppCompatActivity
import com.angcyo.uiview.less.kotlin.fromJsonList
import com.angcyo.uiview.less.kotlin.toJson
import com.angcyo.uiview.less.recycler.RBaseItemDecoration
import com.angcyo.uiview.less.recycler.RBaseViewHolder
import com.angcyo.uiview.less.recycler.RRecyclerView
import com.angcyo.uiview.less.recycler.adapter.RBaseAdapter
import com.angcyo.uiview.less.utils.RLogFile
import com.angcyo.uiview.less.utils.RUtils
import com.angcyo.uiview.less.utils.T_
import com.angcyo.uiview.less.utils.utilcode.utils.ClipboardUtils
import com.orhanobut.hawk.Hawk
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import java.util.*
import java.util.Arrays.asList


class MainActivity : BaseAppCompatActivity() {

    companion object {
        var DOMAINLIST: ArrayList<String> = ArrayList(asList("api.kuaihuapi.com", "api.khuapi.com"))
        var ip: String = ""
        var userBean: UserBean? = null
    }

    var refreshLayout: SmartRefreshLayout? = null
    var recyclerView: RRecyclerView? = null
    var adapter: MainActivity.Adapter = MainActivity.Adapter(this)

    var page = 1

    override fun enableLayoutFull(): Boolean {
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        refreshLayout = findViewById(R.id.refresh_layout)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView?.adapter = adapter
        recyclerView?.addItemDecoration(
            RBaseItemDecoration(
                resources.getDimensionPixelOffset(R.dimen.base_hdpi),
                Color.TRANSPARENT
            ).apply {
                setDrawLastHLine(true)
            }
        )

        refreshLayout?.let {
            it.setOnRefreshListener {
                if (userBean == null) {
                    getHostIpAndLogin()
                } else {
                    getDomain(1)
                }
            }

            it.setOnLoadMoreListener {
                if (userBean == null) {
                    getHostIpAndLogin()
                } else {
                    getDomain(page + 1)
                }
            }

            val allData = Hawk.get("all_data", "")
            if (TextUtils.isEmpty(allData) || BuildConfig.DEBUG) {
                it.autoRefresh()
            } else {
                val list = allData.fromJsonList(VideoListBean.DataBean.ListBean::class.java)
                adapter.resetData(list)
            }

        }

        checkPermissions()

        title = "${getString(R.string.app_name)} ${RUtils.getAppVersionName()}"
    }

    fun getHostIpAndLogin() {
        Http.create(Api::class.java)
            .getHostIp("${HttpDNS.m_httpdns_url}${DOMAINLIST[0]}")
            .compose(Http.transformerString())
            .subscribe(object : HttpSubscriber<String>() {
                override fun onEnd(data: String?, error: Throwable?) {
                    super.onEnd(data, error)
                    data?.let { it ->
                        RLogFile.log(data)

                        ip = it.split(";").first()

                        login()
                    }

                    error(error)
                }
            })
    }

    override fun onResume() {
        super.onResume()
        //L.i(Json.to(Http.map("uid:123", "page:2")))
    }

    fun error(error: Throwable?) {
        error?.let {
            T_.error(it.message)
            refreshLayout?.finishRefresh()
            refreshLayout?.finishLoadMore()
        }
    }

    /**登录接口*/
    fun login() {
        Http.create(Api::class.java)
            .post(
                Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_LOGIN,
                PostFormBuilder().addParams(
                    "data",
                    AesEncryptionUtil.encrypt(
                        Http.mapJson("email:angcyo@126.com", "password:angcyo"),
                        Constant.AES_PWD,
                        Constant.AES_IV
                    )
                ).buildParams()
            )
            .compose(Http.transformerBean(UserBean::class.java) {
                val decrypt = AesEncryptionUtil.decrypt(it, Constant.AES_PWD, Constant.AES_IV)
                RLogFile.log(decrypt)
                decrypt
            })
            .subscribe(object : HttpSubscriber<UserBean>() {

                override fun onEnd(data: UserBean?, error: Throwable?) {
                    super.onEnd(data, error)

                    data?.let {
                        userBean = it
                        getDomain(page)
                    }

                    error(error)
                }
            })
    }

    /**获取domain, 用来获取ip*/
    fun getDomain(page: Int) {
        Http.create(Api::class.java)
            .getDomain(
                Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_DOMAIN,
                PostFormBuilder().addParams(
                    "data",
                    AesEncryptionUtil.encrypt(
                        Http.mapJson("type:1"),
                        Constant.AES_PWD,
                        Constant.AES_IV
                    )
                ).buildParams()
            )
            .compose(Http.transformerBean(HttpBean::class.java) {
                val decrypt = AesEncryptionUtil.decrypt(it, Constant.AES_PWD, Constant.AES_IV)
                RLogFile.log(decrypt)
                decrypt
            })
            .subscribe(object : HttpSubscriber<HttpBean>() {
                override fun onEnd(data: HttpBean?, error: Throwable?) {
                    super.onEnd(data, error)
                    //{"code":0,"data":["api.kuaihuapi.com","api.khuapi.com"],"message":""}
                    //L.e("$data")
                    getHotData(page)

                    error(error)
                }
            })
    }

    /**视频列表*/
    fun getHotData(page: Int) {
        Http.create(Api::class.java)
            .post(
                Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_VIDEO_HOT,
                PostFormBuilder().addParams(
                    "data",
                    AesEncryptionUtil.encrypt(
                        Http.mapJson("page:$page", "perPage:0", "uId:0"),
                        Constant.AES_PWD,
                        Constant.AES_IV
                    )
                ).buildParams()
            )
            .compose(Http.transformerBean(VideoListBean::class.java) {
                val decrypt = AesEncryptionUtil.decrypt(it, Constant.AES_PWD, Constant.AES_IV)
                RLogFile.log(decrypt)
                decrypt
            })
            .concatMap {
                Observable.from(it.data.list)
            }
            .concatMap { listBean ->
                Http.create(Api::class.java).post(
                    Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_VIDEO_DETAIL,
                    PostFormBuilder().addParams(
                        "data",
                        AesEncryptionUtil.encrypt(
                            Http.mapJson(
                                "mvId:${listBean.mv_id}",
                                "type:${Hawk.get("line", "2")}",
                                "uId:${userBean?.data?.mu_id}"
                            ),
                            Constant.AES_PWD,
                            Constant.AES_IV
                        )
                    ).buildParams()
                ).compose(Http.transformerBean(VideoDetailBean::class.java) {
                    val decrypt = AesEncryptionUtil.decrypt(it, Constant.AES_PWD, Constant.AES_IV)
                    RLogFile.log(decrypt)
                    decrypt
                }).onErrorResumeNext {
                    it.printStackTrace()
                    Observable.just(VideoDetailBean())
                }.filter {
                    it.data != null
                }.concatMap {
                    listBean.videoDetailBean = it
                    Observable.just(listBean)
                }
            }
            .toList()
            .subscribe(object : HttpSubscriber<List<VideoListBean.DataBean.ListBean>>() {
                override fun onEnd(data: List<VideoListBean.DataBean.ListBean>?, error: Throwable?) {
                    super.onEnd(data, error)
                    this@MainActivity.page = page
                    refreshLayout?.finishRefresh()
                    refreshLayout?.finishLoadMore()
                    data?.let {
                        if (page <= 1) {
                            adapter.resetData(it)
                        } else {
                            adapter.appendData(it)

//                            recyclerView?.let {
//                                it.smoothScrollBy(0, resources.getDimensionPixelOffset(R.dimen.base_xxxhdpi))
//                            }
                        }

                        Hawk.put("all_data", adapter.allDatas.toJson())
                    }

                    error(error)
                }
            })
    }

    /**视频详情*/
    fun getVideoDetail(mvId: String) {
        Http.create(Api::class.java)
            .post(
                Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_VIDEO_DETAIL,
                PostFormBuilder().addParams(
                    "data",
                    AesEncryptionUtil.encrypt(
                        Http.mapJson("mvId:$mvId", "type:${Hawk.get("line", "2")}", "uId:${userBean?.data?.mu_id}"),
                        Constant.AES_PWD,
                        Constant.AES_IV
                    )
                ).buildParams()
            )
            .compose(Http.transformerBean(HttpBean::class.java) {
                AesEncryptionUtil.decrypt(it, Constant.AES_PWD, Constant.AES_IV)
            })
            .subscribe(object : HttpSubscriber<HttpBean>() {
                override fun onSucceed(data: HttpBean?) {
                    super.onSucceed(data)
                }
            })
    }

    class Adapter(context: Context) : RBaseAdapter<VideoListBean.DataBean.ListBean>(context) {
        override fun getItemLayoutId(viewType: Int): Int {
            return R.layout.item_video_layout
        }

        override fun onBindView(holder: RBaseViewHolder, position: Int, bean: VideoListBean.DataBean.ListBean?) {
            bean?.let {
                if (!BuildConfig.DEBUG) {
                    holder.giv(R.id.image_view).apply {
                        reset()
                        url = it.mv_img_url
                    }
                }

                holder.tv(R.id.title_view).text = "${bean.mv_title} - ${bean.mu_name}"
                holder.tv(R.id.time_view).text = "${bean.videoDetailBean.data.mv_updated}"

                holder.clickItem {
                    holder.clickView(R.id.copy_button)
                }

                holder.click(R.id.copy_button) {
                    if (BuildConfig.FLAVOR.toLowerCase() != "apk") {
                        T_.show("受限制的功能")
                        return@click
                    }

                    ClipboardUtils.copyText("clean")

                    ClipboardUtils.copyText(bean.videoDetailBean.data.mv_play_url)

                    Hawk.put("copy", "${Hawk.get("copy", "")},${bean.videoDetailBean.data.mv_play_url}")

                    notifyItemChanged(position)
                }

                holder.click(R.id.download_button) {
                    if (BuildConfig.FLAVOR.toLowerCase() != "release") {
                        T_.show("受限制的功能")
                        return@click
                    }

                    holder.clickView(R.id.copy_button)
                    RUtils.startApp(mContext, "com.xunlei.downloadprovider")
                }

                if (Hawk.get("copy", "").contains(bean.videoDetailBean.data.mv_play_url)) {
                    holder.tv(R.id.copy_button).text = "已复制"
                } else {
                    holder.tv(R.id.copy_button).text = "复制"
                }
            }
        }

    }
}