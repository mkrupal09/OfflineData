package com.dc.offlinefirstarchitecture.ui.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dc.offlinefirstarchitecture.MainApplication
import com.dc.offlinefirstarchitecture.util.extension.isNetworkAvailable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class BaseViewModel : ViewModel() {

    val networkLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val resultLiveData: MutableLiveData<Result<Any>> = MutableLiveData()
    val messageLiveData: MutableLiveData<String> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun postResult(result: Result<Any>) {
        resultLiveData.postValue(result)
    }

    fun postMessage(message: String) {
        messageLiveData.postValue(message)
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }

    private fun validateNetwork(onNetworkConnected: () -> Unit) {
        if (MainApplication.application.applicationContext.isNetworkAvailable()) {
            onNetworkConnected()
        } else {
            networkLiveData.postValue(false)
        }
    }

    fun <T> makeNetworkCall(observable: Single<T>, withLoading: Boolean, callback: (T) -> Unit) {
        validateNetwork {
            if (withLoading) {
                invalidateLoading(true)
            }
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<T> {
                    override fun onSubscribe(d: Disposable) {
                        addDisposable(d)
                    }

                    override fun onError(e: Throwable) {
                        if (withLoading) {
                            invalidateLoading(false)
                        }
                    }

                    override fun onSuccess(t: T) {
                        if (withLoading) {
                            invalidateLoading(false)
                        }
                        callback(t)
                    }
                })
        }
    }

    fun invalidateLoading(show: Boolean) {
        loadingLiveData.postValue(show)
    }

    override fun onCleared() {
        super.onCleared()
        clearDisposable()
    }

}