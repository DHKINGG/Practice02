package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Model.Summoner
import com.example.myapplication.R
import com.example.myapplication.databinding.IvSearchSummonerBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.Holder>() {
    lateinit var adapterContext: Context
    var list = mutableListOf<Summoner>()

    inner class Holder(private val binding: IvSearchSummonerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Summoner, position: Int) {
            Glide.with(adapterContext)
                .load("${com.example.myapplication.Activity.MyApplication.profileIconUrl}${item.profileIconId}.png")
                .into(binding.ivSearchSummonerIcon)


            binding.tvSearchSummonerId.text = item.name

            binding.btnSearchDelete.setOnClickListener {
                list.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, list.size)

                var summonerList =
                    com.example.myapplication.Activity.MyApplication.prefs.getSummonerList(com.example.myapplication.Activity.MyApplication.summonerListPrefKey)
                if (summonerList == null) summonerList = mutableListOf()
                summonerList.removeAt(position)
                com.example.myapplication.Activity.MyApplication.prefs.setSummonerList(
                    com.example.myapplication.Activity.MyApplication.summonerListPrefKey,
                    summonerList
                )
            }
            binding.btnSearchFavorite.setOnClickListener {
                if (item.isLike) {
                    list[position].isLike = false
                    binding.btnSearchFavorite.setImageResource(R.drawable.search_summoner_like_icon)
                } else {
                    list[position].isLike = true
                    binding.btnSearchFavorite.setImageResource(R.drawable.search_favorit_blue_icon)
                }

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.Holder {
        return Holder(
            IvSearchSummonerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: SearchAdapter.Holder,
        position: Int
    ) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setContext(context: Context) {
        adapterContext = context

    }

}