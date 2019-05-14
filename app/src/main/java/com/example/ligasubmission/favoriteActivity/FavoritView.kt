package com.example.ligasubmission.favoriteActivity

import com.example.ligasubmission.model.Event

interface FavoritView {
    fun showLoading()
    fun hideLoading()
    fun showPastLeague(data: List<Event>?)
    fun showNextLeague(data: List<Event>?)
}