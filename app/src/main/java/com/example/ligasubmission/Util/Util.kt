package com.example.ligasubmission.Util

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.GONE
}

class Util {
    companion object {
        val DETIL_TRANSACTION = "detil"
        val LIST_LEAGUE_FRAGS = "listleague"
        val LIST_LEAGUE_ID = "id_league"
        val LIST_LEAGUE_NEXT_MATCH = "Next Match"
        val LIST_LEAGUE_PREV_MATCH = "Previous Match"

        val Margin_16dp = 16
        val Margin_8dp = 8
    }
}