package com.dc.offlinefirstarchitecture.data.remote

import com.dc.offlinefirstarchitecture.data.entity.User
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?results=100")
    fun getUserList() : Single<WebServiceResponse<User>>
}