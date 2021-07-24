package com.stellar.iss.interfaces

import android.content.Context
import android.webkit.JavascriptInterface
import com.sundar.shopping.classes.HelperClass


class WebAppInterface(private val mContext: Context) {

    /** Show a toast from the web page  */
    @JavascriptInterface
    fun showToast(toast: String) {
        HelperClass.toast(mContext, "Clicked")
    }

    @JavascriptInterface
    fun formSubmited() {
        HelperClass.toast(mContext, "Submit button clicked")
    }
}