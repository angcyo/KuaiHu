package com.angcyo.kuaihu

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
import com.angcyo.kuaihu.http.bean.VideoListBean
import com.angcyo.lib.L
import com.orhanobut.hawk.Hawk
import java.util.*
import java.util.Arrays.asList


class MainActivity : AppCompatActivity() {

    companion object {
        var DOMAINLIST: ArrayList<String> = ArrayList(asList("api.kuaihuapi.com", "api.khuapi.com"))
        var ip: String = ""
        var userBean: UserBean? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

                    getDomain()
                }
            })
    }

    /**获取domain, 用来获取ip*/
    fun getDomain() {
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
                    L.e("$data")
                    getHotData()
                }
            })
    }


    /**视频列表*/
    fun getHotData() {
        Http.create(Api::class.java)
            .post(
                Constant.HOST_HTTP + ip + Constant.HOST_PORT + Constant.API_VIDEO_HOT,
                PostFormBuilder().addParams(
                    "data",
                    AesEncryptionUtil.encrypt(
                        Http.mapJson("page:1", "perPage:14", "uId:0"),
                        Constant.AES_PWD,
                        Constant.AES_IV
                    )
                ).buildParams()
            )
            .compose(Http.transformerBean(VideoListBean::class.java) {
                AesEncryptionUtil.decrypt(it, Constant.AES_PWD, Constant.AES_IV)
            })
            .subscribe(object : HttpSubscriber<VideoListBean>() {
                override fun onSucceed(data: VideoListBean?) {
                    super.onSucceed(data)
                    //{"code":0,"data":["api.kuaihuapi.com","api.khuapi.com"],"message":""}
//                    L.e("$data")
                    //getHotData()
                    data?.let {
                        getVideoDetail(it.data.list[0].mv_id)
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

}