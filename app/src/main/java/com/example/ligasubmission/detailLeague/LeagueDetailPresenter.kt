package com.example.ligasubmission.detailLeague

import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.model.LeaguesResponse
import com.example.ligasubmission.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LeagueDetailPresenter(
    private val view: LeagueDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLeagueDetail(idLeague: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(ApiRepository.TheSportDBApi.getDetailLeague(idLeague)).await(),
                LeaguesResponse::class.java
            )
            view.hideLoading()
            view.showLeagueDetail(data?.leagues)
        }
    }

}