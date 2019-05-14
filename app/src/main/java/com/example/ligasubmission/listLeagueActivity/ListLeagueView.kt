package com.example.ligasubmission.listLeagueActivity

import com.example.ligasubmission.model.Event

interface ListLeagueView {
    fun showLoading()
    fun hideLoading()
    fun showPastLeague(data: List<Event>?)
    fun showNextLeague(data: List<Event>?)
}