package com.example.ligasubmission.detailMatch

import android.database.sqlite.SQLiteConstraintException
import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.database.database
import com.example.ligasubmission.model.Event
import com.example.ligasubmission.model.TeamResponse
import com.example.ligasubmission.util.CoroutineContextProvider
import com.example.ligasubmission.util.DatabaseConstMatches.dateEvent
import com.example.ligasubmission.util.DatabaseConstMatches.idAwayTeam
import com.example.ligasubmission.util.DatabaseConstMatches.idEvent
import com.example.ligasubmission.util.DatabaseConstMatches.idHomeTeam
import com.example.ligasubmission.util.DatabaseConstMatches.intAwayScore
import com.example.ligasubmission.util.DatabaseConstMatches.intAwayShots
import com.example.ligasubmission.util.DatabaseConstMatches.intHomeScore
import com.example.ligasubmission.util.DatabaseConstMatches.intHomeShots
import com.example.ligasubmission.util.DatabaseConstMatches.intRound
import com.example.ligasubmission.util.DatabaseConstMatches.intSpectators
import com.example.ligasubmission.util.DatabaseConstMatches.strAwayFormation
import com.example.ligasubmission.util.DatabaseConstMatches.strAwayGoalDetails
import com.example.ligasubmission.util.DatabaseConstMatches.strAwayLineupDefense
import com.example.ligasubmission.util.DatabaseConstMatches.strAwayLineupForward
import com.example.ligasubmission.util.DatabaseConstMatches.strAwayLineupGoalkeeper
import com.example.ligasubmission.util.DatabaseConstMatches.strAwayLineupMidfield
import com.example.ligasubmission.util.DatabaseConstMatches.strAwayLineupSubstitutes
import com.example.ligasubmission.util.DatabaseConstMatches.strAwayRedCards
import com.example.ligasubmission.util.DatabaseConstMatches.strAwayTeam
import com.example.ligasubmission.util.DatabaseConstMatches.strAwayYellowCards
import com.example.ligasubmission.util.DatabaseConstMatches.strDate
import com.example.ligasubmission.util.DatabaseConstMatches.strEvent
import com.example.ligasubmission.util.DatabaseConstMatches.strHomeFormation
import com.example.ligasubmission.util.DatabaseConstMatches.strHomeGoalDetails
import com.example.ligasubmission.util.DatabaseConstMatches.strHomeLineupDefense
import com.example.ligasubmission.util.DatabaseConstMatches.strHomeLineupForward
import com.example.ligasubmission.util.DatabaseConstMatches.strHomeLineupGoalkeeper
import com.example.ligasubmission.util.DatabaseConstMatches.strHomeLineupMidfield
import com.example.ligasubmission.util.DatabaseConstMatches.strHomeLineupSubstitutes
import com.example.ligasubmission.util.DatabaseConstMatches.strHomeRedCards
import com.example.ligasubmission.util.DatabaseConstMatches.strHomeTeam
import com.example.ligasubmission.util.DatabaseConstMatches.strHomeYellowCards
import com.example.ligasubmission.util.DatabaseConstMatches.strThumb
import com.example.ligasubmission.util.DatabaseConstMatches.strTime
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailMatchPresenter(
    private val activity: DetailMatchActivity,
    private val view: DetailMatchView,
    private val event: Event,
    private val status: String,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) : AnkoLogger {

    fun getTeamPicture() {
        GlobalScope.launch(context.main) {

            val home = gson.fromJson(
                apiRepository
                    .doRequestAsync(ApiRepository.TheSportDBApi.getTeamDetail(event.idHomeTeam)).await(),
                TeamResponse::class.java
            )

            val away = gson.fromJson(
                apiRepository
                    .doRequestAsync(ApiRepository.TheSportDBApi.getTeamDetail(event.idAwayTeam)).await(),
                TeamResponse::class.java
            )

            view.showTeamLogo(home.teams, away.teams)
        }
    }

    fun addToFavorite() {
        try {
            activity.database.use {
                insert(
                    status,
                    idEvent to event.idEvent,
                    strEvent to event.strEvent,
                    strHomeTeam to event.strHomeTeam,
                    strAwayTeam to event.strAwayTeam,
                    intHomeScore to event.intHomeScore,
                    intRound to event.intRound,
                    intAwayScore to event.intAwayScore,
                    intSpectators to event.intSpectators,
                    strHomeGoalDetails to event.strHomeGoalDetails,
                    strHomeRedCards to event.strHomeRedCards,
                    strHomeYellowCards to event.strHomeYellowCards,
                    strHomeLineupGoalkeeper to event.strHomeLineupGoalkeeper,
                    strHomeLineupDefense to event.strHomeLineupDefense,
                    strHomeLineupForward to event.strHomeLineupForward,
                    strHomeLineupSubstitutes to event.strHomeLineupSubstitutes,
                    strHomeFormation to event.strHomeFormation,
                    strAwayRedCards to event.strAwayRedCards,
                    strAwayYellowCards to event.strAwayYellowCards,
                    strAwayGoalDetails to event.strAwayGoalDetails,
                    strAwayLineupGoalkeeper to event.strAwayLineupGoalkeeper,
                    strAwayLineupDefense to event.strAwayLineupDefense,
                    strAwayLineupForward to event.strAwayLineupForward,
                    strAwayLineupSubstitutes to event.strAwayLineupSubstitutes,
                    strHomeLineupMidfield to event.strHomeLineupMidfield,
                    strAwayLineupMidfield to event.strAwayLineupMidfield,
                    strAwayFormation to event.strAwayFormation,
                    intHomeShots to event.intHomeShots,
                    intAwayShots to event.intAwayShots,
                    dateEvent to event.dateEvent,
                    strDate to event.strDate,
                    strTime to event.strTime,
                    strThumb to event.strThumb,
                    idHomeTeam to event.idHomeTeam,
                    idAwayTeam to event.idAwayTeam
                )
            }
            activity.message("Match added to Favorite")
        } catch (e: SQLiteConstraintException) {
            activity.message(e.localizedMessage)
        }
    }

    fun removeFromFavorite() {
        try {
            activity.database.use {
                delete(
                    status, "(idEvent = {id})",
                    "id" to event.idEvent.toString()
                )
            }
            activity.message("Removed from Favorite")
        } catch (e: SQLiteConstraintException) {
            activity.message(e.localizedMessage)
        }
    }

    fun favoriteState() {
        activity.database.use {
            val result = select(status)
                .whereArgs(
                    "(idEvent = {id})",
                    "id" to event.idEvent.toString()
                )
            val favorite = result.parseList(classParser<Event>())
            activity.favoriteState(favorite.isEmpty())
        }
    }


}