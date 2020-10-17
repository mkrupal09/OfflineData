package com.dc.offlinefirstarchitecture.ui.userlist

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dc.offlinefirstarchitecture.R
import com.dc.offlinefirstarchitecture.data.entity.User
import com.dc.offlinefirstarchitecture.databinding.ActivityUserListBinding
import com.dc.offlinefirstarchitecture.ui.core.BaseVMBindingActivity

class UserListActivity :
    BaseVMBindingActivity<ActivityUserListBinding, UserListViewModel>(UserListViewModel::class.java) {

    private val adapter = UserListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingView(R.layout.activity_user_list)

        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.addItemDecoration(object : RecyclerView.ItemDecoration() {

            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.left = 20
                outRect.right = 20
                outRect.top = 10
                outRect.bottom = 10
                super.getItemOffsets(outRect, view, parent, state)
            }
        })

        binding.rvUsers.adapter = adapter

        viewModel.resultLiveData.observe(this, Observer {

            if (it.isSuccess) {
                val listOfUsers = it.getOrNull() as ArrayList<User>
                adapter.clear(true)
                adapter.addAll(listOfUsers, false)
                adapter.notifyDataSetChanged()
            }
        })
        viewModel.fetchUsersFromDatabaseAndRemove()


    }
}