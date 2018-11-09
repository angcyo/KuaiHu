package com.angcyo.kuaihu

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
import com.angcyo.uiview.less.recycler.RBaseAdapter
import com.angcyo.uiview.less.recycler.RBaseItemDecoration
import com.angcyo.uiview.less.recycler.RBaseViewHolder
import com.angcyo.uiview.less.recycler.RRecyclerView
import com.angcyo.uiview.less.utils.RUtils
import com.angcyo.uiview.less.utils.utilcode.utils.ClipboardUtils
import com.angcyo.uiview.less.widget.rsen.RefreshLayout
import com.angcyo.uiview.less.widget.rsen.RefreshLayout.BOTTOM
import com.angcyo.uiview.less.widget.rsen.RefreshLayout.TOP
import com.orhanobut.hawk.Hawk
import rx.Observable
import java.util.*
import java.util.Arrays.asList


class MainActivity : AppCompatActivity() {

    companion object {
        var DOMAINLIST: ArrayList<String> = ArrayList(asList("api.kuaihuapi.com", "api.khuapi.com"))
        var ip: String = ""
        var userBean: UserBean? = null
    }

    var refreshLayout: RefreshLayout? = null
    var recyclerView: RRecyclerView? = null
    var adapter: MainActivity.Adapter = MainActivity.Adapter(this)

    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refreshLayout = findViewById(R.id.refresh_layout)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView?.adapter = adapter
        recyclerView?.addItemDecoration(
            RBaseItemDecoration(
                resources.getDimensionPixelOffset(R.dimen.base_mdpi),
                Color.TRANSPARENT
            )
        )

        refreshLayout?.let {
            it.addOnRefreshListener { it ->
                if (it == TOP) {
                    if (userBean == null) {
                        Http.create(Api::class.java)
                            .getHostIp("${HttpDNS.m_httpdns_url}${DOMAINLIST[0]}")
                            .compose(Http.transformerString())
                            .subscribe(object : HttpSubscriber<String>() {
                                override fun onSucceed(data: String?) {
                                    super.onSucceed(data)
                                    data?.let { it ->
                                        ip = it

                                        login()
                                    }
                                }
                            })
                    } else {
                        getDomain(1)
                    }
                } else if (it == BOTTOM) {
                    getDomain(page + 1)
                }
            }
            //it.setRefreshDirection(TOP)
            it.setRefreshState(TOP)
        }
    }

    override fun onResume() {
        super.onResume()
        //L.i(Json.to(Http.map("uid:123", "page:2")))
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
                AesEncryptionUtil.decrypt(it, Constant.AES_PWD, Constant.AES_IV)
            })
            .subscribe(object : HttpSubscriber<UserBean>() {
                override fun onSucceed(data: UserBean?) {
                    super.onSucceed(data)
                    data?.let {
                        userBean = it
                    }

                    getDomain(page)
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
                AesEncryptionUtil.decrypt(it, Constant.AES_PWD, Constant.AES_IV)
            })
            .subscribe(object : HttpSubscriber<HttpBean>() {
                override fun onSucceed(data: HttpBean?) {
                    super.onSucceed(data)
                    //{"code":0,"data":["api.kuaihuapi.com","api.khuapi.com"],"message":""}
                    //L.e("$data")
                    getHotData(page)
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
                AesEncryptionUtil.decrypt(it, Constant.AES_PWD, Constant.AES_IV)
            })
            .flatMap {
                Observable.from(it.data.list)
            }
            .flatMap { listBean ->
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
                    AesEncryptionUtil.decrypt(it, Constant.AES_PWD, Constant.AES_IV)
                }).onErrorResumeNext {
                    it.printStackTrace()
                    Observable.just(VideoDetailBean())
                }.filter {
                    it.data != null
                }.flatMap {
                    listBean.videoDetailBean = it
                    Observable.just(listBean)
                }
            }
            .toList()
            .subscribe(object : HttpSubscriber<List<VideoListBean.DataBean.ListBean>>() {
                override fun onSucceed(data: List<VideoListBean.DataBean.ListBean>?) {
                    super.onSucceed(data)
                    this@MainActivity.page = page
                    refreshLayout?.setRefreshEnd()
                    data?.let {
                        if (page <= 1) {
                            adapter.resetData(it)
                        } else {
                            adapter.appendData(it)

                            recyclerView?.let {
                                it.smoothScrollBy(0, resources.getDimensionPixelOffset(R.dimen.base_xxxhdpi))
                            }
                        }
                    }
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
                holder.giv(R.id.image_view).apply {
                    reset()
                    url = it.mv_img_url
                }

                holder.tv(R.id.title_view).text = "${bean.mv_title} - ${bean.mu_name}"
                holder.tv(R.id.time_view).text = "${bean.videoDetailBean.data.mv_updated}"

                holder.click(R.id.copy_button) {
                    ClipboardUtils.copyText(bean.videoDetailBean.data.mv_play_url)

                    Hawk.put("copy", "${Hawk.get("copy", "")},${bean.videoDetailBean.data.mv_play_url}")

                    notifyItemChanged(position)
                }

                holder.click(R.id.download_button) {
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