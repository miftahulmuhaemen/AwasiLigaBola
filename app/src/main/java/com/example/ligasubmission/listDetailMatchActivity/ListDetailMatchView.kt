package com.example.ligasubmission.listDetailMatchActivity

import com.example.ligasubmission.model.Team

interface ListDetailMatchView {
    fun showLeagueDetail(query: String)
    fun favoriteState(state: Boolean)
    fun showTeamLogo(homeTeam: List<Team>?, awayTeam: List<Team>?)
}