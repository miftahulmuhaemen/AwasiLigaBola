package com.example.ligasubmission.mainActivity

import com.example.ligasubmission.model.InitialLeague


interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<InitialLeague>)
}