package com.dc.offlinefirstarchitecture.ui.userlist

import com.dc.offlinefirstarchitecture.R
import com.dc.offlinefirstarchitecture.data.entity.User
import com.dc.offlinefirstarchitecture.databinding.ItemUserBinding
import com.dc.offlinefirstarchitecture.util.extension.loadImage
import easyadapter.dc.com.library.EasyAdapter

class UserListAdapter : EasyAdapter<User, ItemUserBinding>(R.layout.item_user) {


    override fun onBind(binding: ItemUserBinding, model: User) {


        binding.tvName.text = "${model.name?.first}   ${model.name?.last}"
        binding.ivProfilePicture.loadImage(model.picture?.largeImage ?: "",R.drawable.placeholder)
    }


}