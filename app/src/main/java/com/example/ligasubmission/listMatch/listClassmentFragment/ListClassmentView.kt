package com.example.ligasubmission.listMatch.listClassmentFragment

import com.example.ligasubmission.model.Classment

interface ListClassmentView {
    fun showLoading()
    fun hideLoading()
    fun showClassment(data: List<Classment>?)
}