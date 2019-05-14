package com.example.ligasubmission.leagueDetailActivity

import android.database.sqlite.SQLiteConstraintException
import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.database.database
import com.example.ligasubmission.listDetailMatchActivity.ListDetailMatchActivity
import com.example.ligasubmission.model.Event
import com.example.ligasubmission.model.TeamResponse
import com.example.ligasubmission.util.DatabaseConst.dateEvent
import com.example.ligasubmission.util.DatabaseConst.idEvent
import com.example.ligasubmission.util.DatabaseConst.intAwayScore
import com.example.ligasubmission.util.DatabaseConst.intAwayShots
import com.example.ligasubmission.util.DatabaseConst.intHomeScore
import com.example.ligasubmission.util.DatabaseConst.intHomeShots
import com.example.ligasubmission.util.DatabaseConst.intRound
import com.example.ligasubmission.util.DatabaseConst.intSpectators
import com.example.ligasubmission.util.DatabaseConst.strAwayFormation
import com.example.ligasubmission.util.DatabaseConst.strAwayGoalDetails
import com.example.ligasubmission.util.DatabaseConst.strAwayLineupDefense
import com.example.ligasubmission.util.DatabaseConst.strAwayLineupForward
import com.example.ligasubmission.util.DatabaseConst.strAwayLineupGoalkeeper
import com.example.ligasubmission.util.DatabaseConst.strAwayLineupMidfield
import com.example.ligasubmission.util.DatabaseConst.strAwayLineupSubstitutes
import com.example.ligasubmission.util.DatabaseConst.strAwayRedCards
import com.example.ligasubmission.util.DatabaseConst.strAwayTeam
import com.example.ligasubmission.util.DatabaseConst.strAwayYellowCards
import com.example.ligasubmission.util.DatabaseConst.strDate
import com.example.ligasubmission.util.DatabaseConst.strEvent
import com.example.ligasubmission.util.DatabaseConst.strHomeFormation
import com.example.ligasubmission.util.DatabaseConst.strHomeGoalDetails
import com.example.ligasubmission.util.DatabaseConst.strHomeLineupDefense
import com.example.ligasubmission.util.DatabaseConst.strHomeLineupForward
import com.example.ligasubmission.util.DatabaseConst.strHomeLineupGoalkeeper
import com.example.ligasubmission.util.DatabaseConst.strHomeLineupMidfield
import com.example.ligasubmission.util.DatabaseConst.strHomeLineupSubstitutes
import com.example.ligasubmission.util.DatabaseConst.strHomeRedCards
import com.example.ligasubmission.util.DatabaseConst.strHomeTeam
import com.example.ligasubmission.util.DatabaseConst.strHomeYellowCards
import com.example.ligasubmission.util.DatabaseConst.strThumb
import com.example.ligasubmission.util.DatabaseConst.strTime
import com.example.ligasubmission.util.Util
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ListDetailMatchPresenter(
    private val view: ListDetailMatchActivity,
    private val event: Event,
    private val status: String,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getTeamPicture() {
        GlobalScope.launch(Dispatchers.Main) {
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
            away.teams.last().strTeamBadge?.let {
                home.teams.last().strTeamBadge?.let { it1 ->
                    view.showTeamLogo(
                        it1,
                        it
                    )
                }
            }
        }
    }

    fun addToFavorite() {
        try {
            view.database.use {
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
                    strThumb to event.strThumb
                )
            }
            view.showLeagueDetail("Match added to Favorite")
        } catch (e: SQLiteConstraintException) {
            view.showLeagueDetail(e.localizedMessage)
        }
    }

    fun removeFromFavorite() {
        try {
            view.database.use {
                delete(
                    status, "(idEvent = {id})",
                    "id" to event.idEvent.toString()
                )
            }
            view.showLeagueDetail("Removed from Favorite")
        } catch (e: SQLiteConstraintException) {
            view.showLeagueDetail(e.localizedMessage)
        }
    }

    fun favoriteState() {
        view.database.use {
            val result = select(status)
                .whereArgs(
                    "(idEvent = {id})",
                    "id" to event.idEvent.toString()
                )
            val favorite = result.parseList(classParser<Event>())
            view.favoriteState(favorite.isEmpty())
        }
    }


}