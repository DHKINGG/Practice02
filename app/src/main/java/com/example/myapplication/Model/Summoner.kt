package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName


data class Summoner(
    @SerializedName("accountId") var accountId: String,
    @SerializedName("profileIconId") var profileIconId: Int,
    @SerializedName("revisionDate") var revisionDate: Long,
    @SerializedName("name") var name: String,
    @SerializedName("id") var id: String,
    @SerializedName("puuid") var puuid: String,
    @SerializedName("summonerLevel") var summonerLevel: Long,
    var isLike: Boolean
)