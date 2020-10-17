package com.dc.offlinefirstarchitecture

import android.app.Application
import androidx.room.Room
import com.dc.offlinefirstarchitecture.data.local.AppDatabase
import com.facebook.stetho.Stetho

class MainApplication : Application() {

    companion object {
        lateinit var application: Application
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        database =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "offlineFirstArchitecture")
                .build();
        Stetho.initializeWithDefaults(this)
    }

}