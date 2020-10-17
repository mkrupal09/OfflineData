package com.dc.offlinefirstarchitecture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dc.offlinefirstarchitecture.data.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}