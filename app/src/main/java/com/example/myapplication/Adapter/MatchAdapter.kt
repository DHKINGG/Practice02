package com.example.myapplication.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Activity.MyApplication
import com.example.myapplication.Model.Match
import com.example.myapplication.Model.Participants
import com.example.myapplication.R
import com.example.myapplication.databinding.IvMatchBinding
import java.time.Duration
import kotlin.math.roundToInt

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.Holder>() {
    var list = mutableListOf<Match>()
    var summonerId: String = ""
    private lateinit var adapterContext: Context

    inner class Holder(private val binding: IvMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Match, position: Int) {
            for (participants in item.info.participants) {
                Log.d("data3", "불러오기 완료")
                if (summonerId == participants.summonerId) binding.tvMatchHistoryIsWin.text =
                    if (participants.win) "승" else "패"
                Log.d("data3", "불러오기 완료")

                if (participants.win) binding.viewSummonerLose.visibility = View.VISIBLE
                Log.d("data3", "불러오기 완료")
                binding.vTimeBar.setBackgroundColor(
                    ContextCompat.getColor(
                        adapterContext,
                        if (participants.win) R.color.blue else R.color.red
                    )
                )

                binding.tvSummonerKda.text =
                    "${participants.kills} / ${participants.deaths} / ${participants.assists}"

                binding.tvKillParticipation.text =
                    calculateKillParticipation(participants.challenges.killParticipation)

                if (doMultiKill(participants) == "") View.GONE
                else binding.tvMultiKill.visibility = View.VISIBLE
                binding.tvMultiKill.text = doMultiKill(participants)

                Glide.with(adapterContext)
                    .load("${MyApplication.championUrl}${participants.championName}.png")
                    .into(binding.ivSummonerChampIcon)

                Glide.with(adapterContext)
                    .load("${MyApplication.itemUrl}${participants.item0}.png")
                    .error(R.drawable.empty_slot)
                    .into(binding.ivSummonerItemSlot1)
                Glide.with(adapterContext)
                    .load("${MyApplication.itemUrl}${participants.item1}.png")
                    .error(R.drawable.empty_slot)
                    .into(binding.ivSummonerItemSlot2)
                Glide.with(adapterContext)
                    .load("${MyApplication.itemUrl}${participants.item2}.png")
                    .error(R.drawable.empty_slot)
                    .into(binding.ivSummonerItemSlot3)
                Glide.with(adapterContext)
                    .load("${MyApplication.itemUrl}${participants.item3}.png")
                    .error(R.drawable.empty_slot)
                    .into(binding.ivSummonerItemSlot4)
                Glide.with(adapterContext)
                    .load("${MyApplication.itemUrl}${participants.item4}.png")
                    .error(R.drawable.empty_slot)
                    .into(binding.ivSummonerItemSlot5)
                Glide.with(adapterContext)
                    .load("${MyApplication.itemUrl}${participants.item5}.png")
                    .error(R.drawable.empty_slot)
                    .into(binding.ivSummonerItemSlot6)
                Glide.with(adapterContext)
                    .load("${MyApplication.itemUrl}${participants.item6}.png")
                    .error(R.drawable.empty_slot)
                    .into(binding.ivSummonerSubItem)
            }
            binding.tvFightTime.text = calculateDuration(item.info.gameDuration)
        }

    }


    private fun calculateDuration(gameDuration: Long): String {
        val minute = gameDuration / 60
        val sec = gameDuration % 60

        var returnValue = ""
        if (minute < 10) {
            returnValue += "0"
        }
        returnValue += minute.toString()
        returnValue += ":"

        if (sec < 10) {
            returnValue += "0"
        }
        returnValue += sec.toString()
        return returnValue
    }

    private fun doMultiKill(participants: Participants): String {
        return if (participants.pentaKills > 0) "펜타킬"
        else if (participants.quadraKills > 0) "쿼드라킬"
        else if (participants.tripleKills > 0) "트리플킬"
        else if (participants.doubleKills > 0) "더블킬킬"
        else ""
    }

    private fun calculateKillParticipation(killParticipants: Float): String {
        var returnValue = (killParticipants * 100).roundToInt()
        return "킬관여 ${returnValue.toString()}%"
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MatchAdapter.Holder {
        return Holder(
            IvMatchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MatchAdapter.Holder,
        position: Int
    ) {
        holder.bind(list[position], position)
    }


    override fun getItemCount(): Int {
        // 4. model리스트의 사이즈만큼 리턴
        return list.size
    }

    fun setContext(context: Context) {
        adapterContext = context
    }

}