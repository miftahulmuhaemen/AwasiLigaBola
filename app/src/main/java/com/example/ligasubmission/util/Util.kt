package com.example.ligasubmission.util

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.GONE
}

object Util {
    const val DETIL_TRANSACTION = "detil"
    const val LIST_LEAGUE_FRAGS = "listleague"
    const val LIST_LEAGUE_ID = "id_league"
    const val LIST_LEAGUE_NEXT_MATCH = "Next Match"
    const val LIST_LEAGUE_PREV_MATCH = "Previous Match"
}

object AnkoLayoutConst {
    const val Margin_16dp = 16
    const val Margin_8dp = 8

}

object DatabaseConst {
        const val TABLE_FAVORITE_NEXT: String = "TABLE_FAVORITE_NEXT"
        const val TABLE_FAVORITE_PAST: String = "TABLE_FAVORITE_PAST"
        const val idEvent: String = "idEvent"
        const val strEvent: String = "strEvent"
        const val strHomeTeam: String = "strHomeTeam"
        const val strAwayTeam: String = "strAwayTeam"
        const val intHomeScore: String = "intHomeScore"
        const val intRound: String = "intRound"
        const val intAwayScore: String = "intAwayScore"
        const val intSpectators: String = "intSpectators"
        const val strHomeGoalDetails: String = "strHomeGoalDetails"
        const val strHomeRedCards: String = "strHomeRedCards"
        const val strHomeYellowCards: String = "strHomeYellowCards"
        const val strHomeLineupGoalkeeper: String = "strHomeLineupGoalkeeper"
        const val strHomeLineupDefense: String = "strHomeLineupDefense"
        const val strHomeLineupForward: String = "strHomeLineupForward"
        const val strHomeLineupSubstitutes: String = "strHomeLineupSubstitutes"
        const val strHomeFormation: String = "strHomeFormation"
        const val strAwayRedCards: String = "strAwayRedCards"
        const val strAwayYellowCards: String = "strAwayYellowCards"
        const val strAwayGoalDetails: String = "strAwayGoalDetails"
        const val strAwayLineupGoalkeeper: String = "strAwayLineupGoalkeeper"
        const val strAwayLineupDefense: String = "strAwayLineupDefense"
        const val strAwayLineupForward: String = "strAwayLineupForward"
        const val strAwayLineupSubstitutes: String = "strAwayLineupSubstitutes"
        const val strHomeLineupMidfield: String = "strHomeLineupMidfield"
        const val strAwayLineupMidfield: String = "strAwayLineupMidfield"
        const val strAwayFormation: String = "strAwayFormation"
        const val intHomeShots: String = "intHomeShots"
        const val intAwayShots: String = "intAwayShots"
        const val dateEvent: String = "dateEvent"
        const val strDate: String = "strDate"
        const val strTime: String = "strTime"
        const val strThumb: String = "strThumb"
}