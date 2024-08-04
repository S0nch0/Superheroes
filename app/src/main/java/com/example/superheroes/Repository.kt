package com.example.superheroes

import retrofit2.Retrofit

class Repository (private val client: Retrofit) {

    suspend fun getSuperheroes():List<Superhero>{
        val apiInterface = client.create(ApiInterface::class.java)
        return apiInterface.getSuperheroes()
    }
}
