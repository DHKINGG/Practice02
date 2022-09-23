package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName

data class Participants(
 @SerializedName("win") var win: Boolean,
 @SerializedName("kills") var kills: Int,
 @SerializedName("deaths") var deaths: Int,
 @SerializedName("challenges") var challenges: Challenges,
 @SerializedName("summonerId") var summonerId: String,
 @SerializedName("championName") var championId: Int,
 @SerializedName("doubleKills") var doubleKills: Int,
 @SerializedName("tripleKills") var tripleKills: Int,
 @SerializedName("quadraKills") var quadraKills: Int,
 @SerializedName("pentaKills") var pentaKills: Int,
 @SerializedName("item0") var item0: Int,
 @SerializedName("item1") var item1: Int,
 @SerializedName("item2") var item2: Int,
 @SerializedName("item3") var item3: Int,
 @SerializedName("item4") var item4: Int,
 @SerializedName("item5") var item5: Int,
 @SerializedName("item6") var item6: Int

)