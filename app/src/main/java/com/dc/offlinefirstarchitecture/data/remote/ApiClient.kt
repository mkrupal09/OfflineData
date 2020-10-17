package com.dc.offlinefirstarchitecture.data.remote

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private var apiService: ApiService? = null
    private const val CONNECTION_TIMEOUT = 120L
    private const val READ_TIMEOUT = 120L
    private const val WRITE_TIMEOUT = 120L

    val service: ApiService
        get() {
            if (apiService == null) {
                apiService = buildApiService()
            }
            return apiService!!
        }

    private fun buildApiService(): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor { message ->
                Log.e(
                    "WebServiceResponse",
                    message
                )
            })
            .build()

        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}