package com.rifqimfahmi.alldogbreeds.ui.base

import android.support.annotation.StringRes

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

interface MvpView {
    fun showLoading()

    fun hideLoading()

    fun onError(message: String?)

    fun onError(@StringRes resId: Int)

    fun showMessage(message: String?)

    fun showMessage(@StringRes resId: Int)
}