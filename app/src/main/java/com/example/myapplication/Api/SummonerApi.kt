package com.example.myapplication.Api

import com.example.myapplication.Activity.Url
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MatchByPuuidApi {
    @GET("/lol/match/v5/matches/by-puuid/{puuid}/ids")
    fun getMatchByPuuid(
        @Path("puuid") puuid: String,
        @Query("api_key") apiKey: String,
        @Query("count") count: Int
    ): Call<ArrayList<String>>

    companion object {

        fun create(): MatchByPuuidApi {
            return Retrofit.Builder()
                .baseUrl(Url.asiaBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MatchByPuuidApi::class.java)
        }
    }
}