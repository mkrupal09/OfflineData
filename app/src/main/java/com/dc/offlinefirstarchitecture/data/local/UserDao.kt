package com.dc.offlinefirstarchitecture.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dc.offlinefirstarchitecture.data.entity.User
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("select * from user")
    fun getAllUsers(): Maybe<List<User>>

    @Query("Delete from user")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUsers(arrayList: List<User>)
}