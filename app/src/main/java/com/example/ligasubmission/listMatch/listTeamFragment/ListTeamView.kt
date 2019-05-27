package com.example.ligasubmission.listMatch.listTeamFragment

import com.example.ligasubmission.model.Team

interface ListTeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeams(data: List<Team>?)
}