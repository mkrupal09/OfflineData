package com.dc.offlinefirstarchitecture.ui.userlist

import com.dc.offlinefirstarchitecture.MainApplication
import com.dc.offlinefirstarchitecture.data.entity.User
import com.dc.offlinefirstarchitecture.data.remote.ApiClient
import com.dc.offlinefirstarchitecture.ui.core.BaseViewModel
import com.dc.offlinefirstarchitecture.util.extension.isNetworkAvailable
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription

class UserListViewModel : BaseViewModel() {


    /**
     * This will fetch users only from API
     */
    fun fetchUserList() {
        makeNetworkCall(ApiClient.service.getUserList(), true) {
            val list = it.arrayList!!

            //First Delete old users then insert newly fetched users
            Single.defer {
                Single.just(MainApplication.database.userDao().deleteAll())
            }
                .flatMap {
                    Single.defer {
                        return@defer Single.just(
                            MainApplication.database.userDao().insertAllUsers(list)
                        )
                    }
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()

            postResult(Result.success(list))
        }
    }

    /**
     * This will fetch users from database
     */
    fun fetchUsersFromDatabase() {
        MainApplication.database.userDao().getAllUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : MaybeObserver<List<User>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: List<User>) {
                    postResult(Result.success(t))
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {
                    TODO("Not yet implemented")
                }

            })

    }


    /**
     * This will call api as well as data from local storage
     */
    fun fetchUsersFromDatabaseAndRemove() {
        val localUsersObservable = MainApplication.database.userDao().getAllUsers()
        val networkUsersObservable = ApiClient.service.getUserList().map {
            it.arrayList
        }.filter { MainApplication.application.isNetworkAvailable() }

        addDisposable(
            Maybe.concat(arrayListOf(localUsersObservable, networkUsersObservable))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                postResult(Result.success(it!!))
            })
    }

}