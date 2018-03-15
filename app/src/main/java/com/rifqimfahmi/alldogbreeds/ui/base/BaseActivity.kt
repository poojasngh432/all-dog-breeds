package com.rifqimfahmi.alldogbreeds.ui.base

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import com.rifqimfahmi.alldogbreeds.MyApplication
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.di.component.ActivityComponent
import com.rifqimfahmi.alldogbreeds.di.component.DaggerActivityComponent
import com.rifqimfahmi.alldogbreeds.di.module.ActivityModule
import com.rifqimfahmi.alldogbreeds.dialog.CommonLoadingDialog
import com.rifqimfahmi.alldogbreeds.util.CommonUtils

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

abstract class BaseActivity : AppCompatActivity(), MvpView, BaseFragment.Callback  {

    private var mCommonLoadingDialog: CommonLoadingDialog? = null

    lateinit var mActivityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as MyApplication).mAppComponent)
                .build()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
            requestPermissions(permissions, requestCode)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun showLoading() {
        mCommonLoadingDialog = CommonUtils.showLoadingDialog(supportFragmentManager, null)
    }

    override fun showLoadingWithText(message: String) {
        mCommonLoadingDialog = CommonUtils.showLoadingDialog(supportFragmentManager, message)
    }

    override fun showLoadingWithText(@StringRes message: Int) {
        mCommonLoadingDialog = CommonUtils.showLoadingDialog(supportFragmentManager, getString(message))
    }

    override fun hideLoading() {
        mCommonLoadingDialog?.dismiss()
    }

    private fun showSnackBar(message: String) {
        val snackbar: Snackbar = Snackbar.make(
                findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val sbView: View = snackbar.view
        val textView: TextView = sbView.findViewById(android.support.design.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        snackbar.show()
    }

    override fun onError(message: String?) {
        if (message != null) {
            showSnackBar(message)
        } else {
            showSnackBar("Some error occurred!")
        }
    }

    override fun onError(@StringRes resId: Int) {
        onError(getString(resId))
    }

    override fun showMessage(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Some error occured", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showMessage(@StringRes resId: Int) {
        showMessage(getString(resId))
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }

    abstract fun setup()
}