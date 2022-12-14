package com.example.myapplication.Api

import com.example.myapplication.Activity.MyApplication
import com.example.myapplication.Model.Summoner
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SummonerApi {
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    fun getSummoner(
        @Path("summonerName") summonerName: String,
        @Query("api_key") apiKey: String
    ): Call<Summoner>

    companion object {

        fun create(): SummonerApi {
            return Retrofit.Builder()
                .baseUrl(MyApplication.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SummonerApi::class.java)
        }
    }
}