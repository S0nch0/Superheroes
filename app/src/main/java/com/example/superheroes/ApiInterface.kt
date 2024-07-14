package com.example.superheroes

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET("superhero-api/api/all.json")
    fun getSuperheroes(): Single<List<Superhero>>
}