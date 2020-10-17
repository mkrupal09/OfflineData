package com.dc.offlinefirstarchitecture.data.remote

import com.google.gson.annotations.SerializedName


class WebServiceResponse<T> {

    @SerializedName("info")
    var webServiceInfo:WebServiceInfo?=null

    @SerializedName("results")
    var arrayList:ArrayList<T>?=null
}