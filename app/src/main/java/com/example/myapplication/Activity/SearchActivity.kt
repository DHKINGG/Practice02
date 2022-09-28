package com.example.myapplication.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.SearchAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val adapter = SearchAdapter()

        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvSearchMain.layoutManager = LinearLayoutManager(this)
        adapter.setContext(this)
        var summonerList = MyApplication.prefs.getSummonerList(MyApplication.summonerListPrefKey)
        if (summonerList == null) {
            summonerList = mutableListOf()
        }
        adapter.list = summonerList

        binding.rvSearchMain.adapter = adapter

        binding.ivLeftArrow.setOnClickListener {
            finish()
        }

        binding.edtSearch.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                val nextIntent = Intent(this, SummonerMatchHistoryActivity::class.java)
                Log.d("data", "작동하였습니다")
                nextIntent.putExtra("champ_id", binding.edtSearch.text.toString())
                Log.d("data", "작동하였습니다")
                startActivity(nextIntent)
                Log.d("data", "작동하였습니다")
            }

            true
        }

    }

    }
