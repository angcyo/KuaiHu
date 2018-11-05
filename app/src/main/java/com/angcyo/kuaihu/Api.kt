package com.angcyo.kuaihu

import okhttp3.ResponseBody
import retrofit2.http.*
import rx.Observable

/**
 *
 * Email:angcyo@126.com
 * @author angcyo
 * @date 2018/11/01
 */
interface Api {

    @FormUrlEncoded
    @POST
    fun getDomain(@Url url: String, @FieldMap params: Map<String, String>): Observable<ResponseBody>

    @FormUrlEncoded
    @POST
    fun post(@Url url: String, @FieldMap params: Map<String, String>): Observable<ResponseBody>

    /*获取服务接口ip*/
    @GET
    fun getHostIp(@Url url: String): Observable<ResponseBody>
}