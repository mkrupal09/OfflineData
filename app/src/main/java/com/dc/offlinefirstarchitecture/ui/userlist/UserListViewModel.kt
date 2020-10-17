package com.dc.offlinefirstarchitecture.ui.userlist

import com.dc.offlinefirstarchitecture.MainApplication
import com.dc.offlinefirstarchitecture.data.remote.ApiClient
import com.dc.offlinefirstarchitecture.ui.core.BaseViewModel

class UserListViewModel : BaseViewModel() {


    fun fetchUserList() {
        makeNetworkCall(ApiClient.service.getUserList(), true) {
            val list = it.arrayList!!
            MainApplication.database.userDao().insertAllUsers(list)
            postResult(Result.success(list))
        }
    }

}