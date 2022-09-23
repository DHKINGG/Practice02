package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("info") var info : Info
)