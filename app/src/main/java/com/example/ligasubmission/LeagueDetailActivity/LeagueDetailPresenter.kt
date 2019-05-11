package com.example.ligasubmission.LeagueDetailActivity

import android.util.Log
import com.example.ligasubmission.API.ApiRepository
import com.example.ligasubmission.ListLeagueActivity.ListLeagueListFragment
import com.example.ligasubmission.Model.EventsResponse
import com.example.ligasubmission.Model.LeaguesResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class LeagueDetailPresenter(private val view: LeagueDetailActivity,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson
) {

    fun getLeagueDetail(idEvent: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getDetailLeague(idEvent)),
                LeaguesResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showLeagueDetail(data?.leagues)
            }
        }
    }

}