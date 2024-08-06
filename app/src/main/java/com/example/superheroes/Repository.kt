package com.example.superheroes

import retrofit2.Retrofit

class Repository (private val apiClient: ApiClient) {

    suspend fun getSuperheroes():List<Superhero>{
        val apiInterface = apiClient.client.create(ApiInterface::class.java)
        return apiInterface.getSuperheroes()
    }
}
