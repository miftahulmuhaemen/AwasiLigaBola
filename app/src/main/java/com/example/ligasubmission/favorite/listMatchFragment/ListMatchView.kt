package com.example.ligasubmission.favorite.listMatchFragment

import com.example.ligasubmission.model.Event

interface ListMatchView {
    fun showLoading()
    fun hideLoading()
    fun showPastLeague(data: List<Event>?)
    fun showNextLeague(data: List<Event>?)
}