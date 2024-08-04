package com.example.superheroes

import android.app.Application
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {
    lateinit var repo: Repository
    override fun onCreate() {
        super.onCreate()
        instance = this
        repo = Repository(getApiClient())
    }
    private fun getApiClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://akabab.github.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    companion object {
        private lateinit var instance: MyApplication
        fun getApp() = instance
    }
}

