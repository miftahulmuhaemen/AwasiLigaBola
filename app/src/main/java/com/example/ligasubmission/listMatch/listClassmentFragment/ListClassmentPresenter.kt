package com.example.ligasubmission.listMatch.listClassmentFragment

import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.model.ClassmentResponse
import com.example.ligasubmission.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListClassmentPresenter(
    private val view: ListClassmentView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getClassment(league: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(ApiRepository.TheSportDBApi.getClassment(league)).await(),
                ClassmentResponse::class.java
            )

            view.hideLoading()
            view.showClassment(data.table)
        }
    }


}