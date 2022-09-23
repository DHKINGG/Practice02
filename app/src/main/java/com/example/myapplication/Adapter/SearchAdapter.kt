package com.example.myapplication.Adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.Summoner
import com.example.myapplication.databinding.IvSearchSummonerBinding

class SearchAdapter {
    lateinit var adapterContext: Context
    val list = mutableListOf<Summoner>()

    inner class Holder(val binding: IvSearchSummonerBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item: Summoner, position: Int){

            }
        }

}