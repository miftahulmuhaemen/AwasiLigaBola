package com.example.ligasubmission.leagueDetailActivity

import com.example.ligasubmission.model.League

interface LeagueDetailView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueDetail(data: List<League>?)
}