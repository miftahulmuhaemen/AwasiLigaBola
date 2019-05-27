package com.example.ligasubmission.mainMenu

import com.example.ligasubmission.model.InitialLeague


interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<InitialLeague>)
}