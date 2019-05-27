package com.example.ligasubmission.detailTeam

import android.database.sqlite.SQLiteConstraintException
import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.database.database
import com.example.ligasubmission.model.Event
import com.example.ligasubmission.model.PlayerResponse
import com.example.ligasubmission.model.Team
import com.example.ligasubmission.model.TeamResponse
import com.example.ligasubmission.util.CoroutineContextProvider
import com.example.ligasubmission.util.DatabaseConstTeam.TABLE_TEAM
import com.example.ligasubmission.util.DatabaseConstTeam.idTeam
import com.example.ligasubmission.util.DatabaseConstTeam.intFormedYear
import com.example.ligasubmission.util.DatabaseConstTeam.intStadiumCapacity
import com.example.ligasubmission.util.DatabaseConstTeam.strAlternate
import com.example.ligasubmission.util.DatabaseConstTeam.strDescriptionEN
import com.example.ligasubmission.util.DatabaseConstTeam.strManager
import com.example.ligasubmission.util.DatabaseConstTeam.strStadium
import com.example.ligasubmission.util.DatabaseConstTeam.strStadiumDescription
import com.example.ligasubmission.util.DatabaseConstTeam.strStadiumLocation
import com.example.ligasubmission.util.DatabaseConstTeam.strTeam
import com.example.ligasubmission.util.DatabaseConstTeam.strTeamBadge
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


class DetailTeamPresenter(
    private val activity: DetailTeamActivity,
    private val view: DetailTeamView,
    private val team: Team,
    private val apiRepository: ApiRepository = ApiRepository(),
    private val gson: Gson = Gson(),
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) : AnkoLogger {

    fun getPlayers(){
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(ApiRepository.TheSportDBApi.getPlayers(team.idTeam)).await(),
                PlayerResponse::class.java
            )
            view.showPlayers(data?.player)
        }
    }

    fun addToFavorite() {
        try {
            activity.database.use {
                insert(
                    TABLE_TEAM,
                    idTeam to team.idTeam,
                    strTeamBadge to team.strTeamBadge,
                    strTeam to team.strTeam,
                    strAlternate to team.strAlternate,
                    intFormedYear to team.intFormedYear,
                    strManager to team.strManager,
                    strStadium to team.strStadium,
                    strStadiumDescription to team.strStadiumDescription,
                    strStadiumLocation to team.strStadiumLocation,
                    intStadiumCapacity to team.intStadiumCapacity,
                    strDescriptionEN to team.strDescriptionEN
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
                    TABLE_TEAM, "(idTeam = {id})",
                    "id" to team.idTeam.toString()
                )
            }
            activity.message("Removed from Favorite")
        } catch (e: SQLiteConstraintException) {
            activity.message(e.localizedMessage)
        }
    }

    fun favoriteState() {
        activity.database.use {
            val result = select(TABLE_TEAM)
                .whereArgs(
                    "(idTeam = {id})",
                    "id" to team.idTeam.toString()
                )
            val favorite = result.parseList(classParser<Team>())
            activity.favoriteState(favorite.isEmpty())
        }
    }


}