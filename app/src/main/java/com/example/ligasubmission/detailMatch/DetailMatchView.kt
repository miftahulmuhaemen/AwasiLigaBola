package com.example.ligasubmission.detailMatch

import com.example.ligasubmission.model.Team

interface DetailMatchView {
    fun message(query: String)
    fun favoriteState(state: Boolean)
    fun showTeamLogo(homeTeam: List<Team>?, awayTeam: List<Team>?)
}