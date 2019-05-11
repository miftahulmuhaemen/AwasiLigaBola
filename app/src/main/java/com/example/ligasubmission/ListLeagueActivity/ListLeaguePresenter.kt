package com.example.ligasubmission.ListLeagueActivity

import com.example.ligasubmission.API.ApiRepository
import com.example.ligasubmission.Model.EventsResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ListLeaguePresenter(private val view: ListLeagueListFragment,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson
) {

    fun getPreviousMatch(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getPreviousMatch(league)),
                EventsResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showPastLeague(data.events)
            }
        }
    }

    fun getNextMatch(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getNextMatch(league)),
                EventsResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showNextLeague(data.events)
            }
        }
    }

}