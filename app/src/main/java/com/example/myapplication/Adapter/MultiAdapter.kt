package com.example.myapplication.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Activity.MyApplication
import com.example.myapplication.Model.Match
import com.example.myapplication.Model.SeasonTier
import com.example.myapplication.Model.Summoner
import com.example.myapplication.databinding.IvMatchMultiBinding
import com.example.myapplication.databinding.IvSeasonTierMultiBinding
import com.example.myapplication.databinding.IvTopInfoBinding

class MultiAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var matchDataList = mutableListOf<Match>()
    var topInfoData = Summoner("", 0, 0, "", "", "", 0, false)
    lateinit var adapterContext: Context
    var summonerId: String = ""
    var seasonTierList = mutableListOf<SeasonTier>()

    inner class TopInfoHolder(private val binding: IvTopInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Summoner) {
            binding.tvLevel.text = item.summonerLevel.toString()
            binding.tvSummonerNickname.text = item.name
            Glide.with(adapterContext)
                .load("${MyApplication.profileIconUrl}${item.profileIconId}.png")
                .into(binding.ivSummonerProfile)
            Glide.with(adapterContext)
                .load("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg")
                .into(binding.ivBackground)
        }
    }

    inner class MatchHistoryHolder(private val binding : IvMatchMultiBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(item: MutableList<Match>){
            binding.rvGameResultMain.layoutManager =
                LinearLayoutManager(adapterContext, LinearLayoutManager.VERTICAL, false)
            val recyclerAdapter = MatchAdapter()
            recyclerAdapter.list = item
            recyclerAdapter.summonerId = summonerId
            recyclerAdapter.setContext(adapterContext)
            binding.rvGameResultMain.adapter = recyclerAdapter
        Log.d("data2" , "불러왔다 게이야" )
        }
    }

    inner class SeasonTierHolder(private val binding:IvSeasonTierMultiBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(item: MutableList<SeasonTier>){
                binding.rvSeasonTierMain.layoutManager =
                    LinearLayoutManager(adapterContext, LinearLayoutManager.HORIZONTAL, false)
                val recyclerAdapter = SeasonTierAdapter()
                recyclerAdapter.list = item
                recyclerAdapter.setContext(adapterContext)
                binding.rvSeasonTierMain.adapter = recyclerAdapter

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                return TopInfoHolder(
                    IvTopInfoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            1 -> {
                return SeasonTierHolder(
                    IvSeasonTierMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return MatchHistoryHolder(
                    IvMatchMultiBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> (holder as TopInfoHolder).bind(topInfoData)
            1 -> (holder as SeasonTierHolder).bind(seasonTierList)
            else -> (holder as MatchHistoryHolder).bind(matchDataList)
        }
        holder.setIsRecyclable(false)
    }


    override fun getItemCount(): Int {
        return 3
    }

    fun setContext(context: Context) {
        adapterContext = context
    }
}