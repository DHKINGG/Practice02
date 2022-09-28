package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.MultiAdapter
import com.example.myapplication.Api.MatchApi
import com.example.myapplication.Api.MatchByPuuidApi
import com.example.myapplication.Api.SummonerApi
import com.example.myapplication.Model.Match
import com.example.myapplication.Model.SeasonTier
import com.example.myapplication.Model.Summoner
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySummonerMatchHistoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SummonerMatchHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySummonerMatchHistoryBinding
    private var adapter = MultiAdapter()
    private lateinit var summoner: Summoner
    private lateinit var summonerName: String
    private lateinit var summonerId: String
    private var summonerLevel: Long = 0
    private var matchIds = arrayListOf<String>()
    private var apiCall = 0
    private var matches = arrayListOf<Match>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummonerMatchHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.rvMatchHistory.layoutManager = LinearLayoutManager(this)
        adapter.setContext(this)
        binding.rvMatchHistory.adapter = adapter

        setSeasonTier()

    }

    override fun onStart() {
        super.onStart()

        var summonerName = ""
        if (intent.hasExtra("champ_id")) {
            summonerName = intent.getStringExtra("champ_id").toString()
        }
        getSummoner(summonerName)
    }

    private fun getSummoner(pSummonerName: String) {
        val api = SummonerApi.create()
        api.getSummoner(pSummonerName, MyApplication.apiKey).enqueue(object : Callback<Summoner> {
            override fun onResponse(
                call: Call<Summoner>,
                response: Response<Summoner>
            ) { Log.d("data1", "불러오기 성공")
                // 데이터 받아오는게 성공시 이 함수 실행
                val responseSummoner = response.body()

                if (responseSummoner != null) {
                    summoner = responseSummoner
                    summonerName = responseSummoner.name
                    summonerId = responseSummoner.id
                    summonerLevel = responseSummoner.summonerLevel

                    var summonerList = MyApplication.prefs.getSummonerList(MyApplication.summonerListPrefKey)

                    if (summonerList == null) {
                        summonerList = mutableListOf()
                    }
                    summonerList.add(summoner)
                    MyApplication.prefs.setSummonerList(
                        MyApplication.summonerListPrefKey,
                        summonerList
                    )


                    val puuid = responseSummoner.puuid
                    getMatchByPuuid(puuid)

                }
            }

            override fun onFailure(call: Call<Summoner>, t: Throwable) {
                Log.d("data1", "불러오기 실패")
            }

        })
    }

    private fun getMatchByPuuid(puuid: String) {
        val api = MatchByPuuidApi.create()
        api.getMatchByPuuid(puuid, MyApplication.apiKey, 20)
            .enqueue(object : Callback<ArrayList<String>> {
                override fun onResponse(
                    call: Call<ArrayList<String>>,
                    response: Response<ArrayList<String>>
                ) { Log.d("data1", "불러오기 성공")
                    // 데이터 받아오는게 성공시 이 함수 실행
                    val responseMatches = response.body()

                    if (responseMatches != null) {
                        matchIds.addAll(responseMatches)
                        for (match in responseMatches) {
                            getMatch(match)
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                    Log.d("data1", "불러오기 실패")
                }
            })
    }

    private fun getMatch(matchId: String) {
        val api = MatchApi.create()
        api.getMatch(matchId, MyApplication.apiKey).enqueue(object : Callback<Match> {
            override fun onResponse(
                call: Call<Match>,
                response: Response<Match>
            ) { Log.d("data1", "불러오기 성공")
                // 데이터 받아오는게 성공시 이 함수 실행
                val responseMatch = response.body()

                apiCall++
                if (responseMatch != null) {
                    for (participant in responseMatch.info.participants) {
                        if (participant.summonerId == summonerId) {
                            matches.add(responseMatch)
                            sortMatches()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Match>, t: Throwable) { // 데이터 받아오는게 실패시 이 함수 실행
                Log.d("data1", "불러오기 실패")
            }

        })
    }

    private fun sortMatches() {
        if (apiCall == matchIds.size) {
            matches.sortByDescending { it.info.gameCreation }
            adapter.setContext(this)
            adapter.matchDataList = matches
            adapter.summonerId = summonerId
            adapter.topInfoData = summoner
            adapter.notifyDataSetChanged()
        }
    }

    private fun setSeasonTier() {
        var seasonTier = mutableListOf<SeasonTier>()
        seasonTier.add(SeasonTier("S2021", "GOLD 3"))
        seasonTier.add(SeasonTier("S2020", "GOLD 4"))
        seasonTier.add(SeasonTier("S9", "PLATINUM 4"))
        seasonTier.add(SeasonTier("S8", "GOLD 5"))
        seasonTier.add(SeasonTier("S7", "GOLD 3"))
        seasonTier.add(SeasonTier("S6", "GOLD 4"))
        seasonTier.add(SeasonTier("S5", "GOLD 5"))
        seasonTier.add(SeasonTier("S4", "SILVER 2"))

        adapter.seasonTierList = seasonTier
        adapter.notifyDataSetChanged()
    }
}
