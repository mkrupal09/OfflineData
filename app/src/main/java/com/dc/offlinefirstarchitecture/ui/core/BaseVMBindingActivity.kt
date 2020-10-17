package com.dc.offlinefirstarchitecture.ui.core

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

abstract class BaseVMBindingActivity<T : ViewDataBinding, VM : BaseViewModel>(private var viewModelClass: Class<VM>) :
    BaseBindingActivity<T>() {

    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(viewModelClass)

        viewModel.loadingLiveData.observe(this, Observer<Boolean> {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })

        viewModel.networkLiveData.observe(this, Observer<Boolean> {
            if (!it) {
                showNetworkNotConnected()
            }
        })

        viewModel.messageLiveData.observe(this, Observer<String> {
            showMessage(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposable()
    }
}