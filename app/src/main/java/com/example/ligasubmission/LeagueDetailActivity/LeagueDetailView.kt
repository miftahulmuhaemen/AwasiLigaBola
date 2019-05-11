package com.example.ligasubmission.LeagueDetailActivity

import com.example.ligasubmission.Model.InitialLeague
import com.example.ligasubmission.Model.League

interface LeagueDetailView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueDetail(data: List<League>?)
}