package com.angcyo.kuaihu

import com.angcyo.kuaihu.http.AppUtils
import com.angcyo.uiview.less.RApplication

/**
 *
 * Email:angcyo@126.com
 * @author angcyo
 * @date 2018/11/01
 */
class App : RApplication() {
    override fun onInit() {
        super.onInit()
        AppUtils.init(applicationContext)
    }
}