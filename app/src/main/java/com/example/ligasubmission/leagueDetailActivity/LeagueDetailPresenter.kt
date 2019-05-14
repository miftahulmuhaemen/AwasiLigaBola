package com.example.ligasubmission.leagueDetailActivity

import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.model.LeaguesResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LeagueDetailPresenter(
    private val view: LeagueDetailActivity,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getLeagueDetail(idEvent: String?) {
        GlobalScope.launch(Dispatchers.Main){
            view.showLoading()
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(ApiRepository.TheSportDBApi.getDetailLeague(idEvent)).await(),
                LeaguesResponse::class.java
            )
            view.hideLoading()
            view.showLeagueDetail(data?.leagues)
        }
    }

}