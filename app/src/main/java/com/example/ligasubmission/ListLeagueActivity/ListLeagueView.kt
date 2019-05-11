package com.example.ligasubmission.ListLeagueActivity

import com.example.ligasubmission.Model.Event

interface ListLeagueView {
    fun showLoading()
    fun hideLoading()
    fun showPastLeague(data: List<Event>?)
    fun showNextLeague(data: List<Event>?)
}