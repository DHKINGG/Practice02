package com.example.myapplication.Activity

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Model.PreferenceUtil
import com.example.myapplication.R

class MyApplication : Application() {
    companion object {  // 어떤 파일에서도 쓸수있게
        lateinit var prefs: PreferenceUtil
        const val summonerListPrefKey = "summoner_list"
        const val baseUrl = "https://kr.api.riotgames.com"
        const val asiaBaseUrl = "https://asia.api.riotgames.com"
        const val apiKey = "RGAPI-21fc33c4-6a27-483f-8607-16dbb4aa9571" //  만료시 다시 받아와야함 Api_key
        const val profileIconUrl = "http://ddragon.leagueoflegends.com/cdn/12.16.1/img/profileicon/"
        const val championUrl = "http://ddragon.leagueoflegends.com/cdn/12.16.1/img/champion/"
        const val itemUrl = "http://ddragon.leagueoflegends.com/cdn/12.16.1/img/item/"
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}