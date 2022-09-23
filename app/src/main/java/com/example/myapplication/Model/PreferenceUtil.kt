package com.example.myapplication.Model

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.Activity.Url
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE)


    fun getSummonerList(key: String): MutableList<Summoner> {
        val json = prefs.getString(key, "").toString() // sp에서 꺼내오는
        val gson = Gson()
        val token: TypeToken<MutableList<Summoner>> = object : TypeToken<MutableList<Summoner>>() {} // 어떤타입의 토큰을 가져올건지
        val summonerList: MutableList<Summoner>? = gson.fromJson(json, token.type)   // gson을 사용해서 json을 object로 바꿈


        return summonerList ?: mutableListOf()
    }

    fun setSummonerList(key: String, listObject: MutableList<Summoner>) {
    val gson = Gson()
    val json: String = gson.toJson(listObject)
    prefs.edit().putString(key,json).apply()
    }

    fun clearSummonerList() {
        prefs.edit().remove(Url.summonerListPrefKey).commit()
    }
}

