package com.example.ligasubmission.leagueDetailActivity

interface ListDetailMatchView {
    fun showLeagueDetail(query: String)
    fun favoriteState(state: Boolean)
    fun showTeamLogo(homeTeam: String, awayTeam: String)
}