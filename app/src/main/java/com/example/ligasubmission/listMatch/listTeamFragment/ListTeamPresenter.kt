package com.example.ligasubmission.listMatch.listTeamFragment

import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.model.TeamResponse
import com.example.ligasubmission.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListTeamPresenter(
    private val view: ListTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeams(league: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(ApiRepository.TheSportDBApi.getTeamOnLeague(league)).await(),
                TeamResponse::class.java
            )

            view.hideLoading()
            view.showTeams(data.teams)
        }
    }
}
