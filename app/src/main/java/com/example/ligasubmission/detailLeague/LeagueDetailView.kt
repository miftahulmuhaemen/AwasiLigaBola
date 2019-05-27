package com.example.ligasubmission.detailLeague

import com.example.ligasubmission.model.League

interface LeagueDetailView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueDetail(data: List<League>?)
}