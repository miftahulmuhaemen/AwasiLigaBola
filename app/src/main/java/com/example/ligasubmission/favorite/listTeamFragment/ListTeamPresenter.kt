package com.example.ligasubmission.favorite.listTeamFragment

import com.example.ligasubmission.database.database
import com.example.ligasubmission.model.Team
import com.example.ligasubmission.util.CoroutineContextProvider
import com.example.ligasubmission.util.DatabaseConstTeam.TABLE_TEAM
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ListTeamPresenter(
    private val view: ListTeamFragment,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeams() {
        view.showLoading()
        GlobalScope.launch(context.main) {
            view.activity?.database?.use {
                val result = select(TABLE_TEAM)
                val team = result.parseList(classParser<Team>())
                view.showTeams(team)
                view.hideLoading()
            }
        }
    }

}
