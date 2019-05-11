package com.example.ligasubmission.MainActivity

import com.example.ligasubmission.Model.InitialLeague


interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<InitialLeague>)
}