package com.example.ligasubmission.favorite.listTeamFragment

import com.example.ligasubmission.model.Team

interface ListTeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeams(data: List<Team>?)
}