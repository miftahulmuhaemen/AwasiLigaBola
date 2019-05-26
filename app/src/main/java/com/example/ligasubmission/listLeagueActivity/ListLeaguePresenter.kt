package com.example.ligasubmission.listLeagueActivity

import android.annotation.SuppressLint
import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.model.Event
import com.example.ligasubmission.model.EventsResponse
import com.example.ligasubmission.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListLeaguePresenter(
    private val view: ListLeagueView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getPreviousMatch(league: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(ApiRepository.TheSportDBApi.getPreviousMatch(league)).await(),
                EventsResponse::class.java
            )

            view.hideLoading()
            view.showPastLeague(Filter(data.events))
        }

    }

    fun getNextMatch(league: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(ApiRepository.TheSportDBApi.getNextMatch(league)).await(),
                EventsResponse::class.java
            )

            view.hideLoading()
            view.showNextLeague(Filter(data.events))
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun Filter(data: List<Event>?): List<Event>? {
        data?.mapIndexed { _, event ->
            if (event.intAwayScore.isNullOrBlank() && event.intHomeScore.isNullOrBlank()) {
                event.intAwayScore = "-"
                event.intHomeScore = "-"
            }
        }
        return data
    }
}