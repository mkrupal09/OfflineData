package com.dc.offlinefirstarchitecture.util.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.loadImage(url: String, placeHolder: Int = -1) {
    val requestManager = Glide.with(this).load(url)
    if (placeHolder != -1) {
        requestManager.apply(RequestOptions().placeholder(placeHolder).error(placeHolder))
            .into(this)
    } else {
        requestManager.into(this)
    }
}
