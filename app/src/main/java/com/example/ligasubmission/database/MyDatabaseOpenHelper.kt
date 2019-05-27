package com.example.ligasubmission.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.ligasubmission.util.DatabaseConstMatches.TABLE_FAVORITE_NEXT
import com.example.ligasubmission.util.DatabaseConstMatches.TABLE_FAVORITE_PAST
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
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteEvent.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            TABLE_FAVORITE_NEXT, true,
            idEvent to TEXT + PRIMARY_KEY,
            strEvent to TEXT,
            strHomeTeam to TEXT,
            strAwayTeam to TEXT,
            intHomeScore to TEXT,
            intRound to TEXT,
            intAwayScore to TEXT,
            intSpectators to TEXT,
            strHomeGoalDetails to TEXT,
            strHomeRedCards to TEXT,
            strHomeYellowCards to TEXT,
            strHomeLineupGoalkeeper to TEXT,
            strHomeLineupDefense to TEXT,
            strHomeLineupForward to TEXT,
            strHomeLineupSubstitutes to TEXT,
            strHomeFormation to TEXT,
            strAwayGoalDetails to TEXT,
            strAwayRedCards to TEXT,
            strAwayYellowCards to TEXT,
            strAwayLineupGoalkeeper to TEXT,
            strAwayLineupDefense to TEXT,
            strAwayLineupForward to TEXT,
            strAwayLineupSubstitutes to TEXT,
            strAwayFormation to TEXT,
            strAwayLineupMidfield to TEXT,
            strHomeLineupMidfield to TEXT,
            intHomeShots to TEXT,
            intAwayShots to TEXT,
            dateEvent to TEXT,
            strDate to TEXT,
            strTime to TEXT,
            strThumb to TEXT,
            idHomeTeam to TEXT,
            idAwayTeam to TEXT
        )

        db.createTable(TABLE_TEAM, true,
            idTeam to TEXT + PRIMARY_KEY,
            strTeamBadge to TEXT,
            strTeam to TEXT,
            strAlternate to TEXT,
            intFormedYear to TEXT,
            strManager to TEXT,
            strStadium to TEXT,
            strStadiumDescription to TEXT,
            strStadiumLocation to TEXT,
            intStadiumCapacity to TEXT,
            strDescriptionEN to TEXT
            )

        db.createTable(
            TABLE_FAVORITE_PAST, true,
            idEvent to TEXT + PRIMARY_KEY,
            strEvent to TEXT,
            strHomeTeam to TEXT,
            strAwayTeam to TEXT,
            intHomeScore to TEXT,
            intRound to TEXT,
            intAwayScore to TEXT,
            intSpectators to TEXT,
            strHomeGoalDetails to TEXT,
            strHomeRedCards to TEXT,
            strHomeYellowCards to TEXT,
            strHomeLineupGoalkeeper to TEXT,
            strHomeLineupDefense to TEXT,
            strHomeLineupForward to TEXT,
            strHomeLineupSubstitutes to TEXT,
            strHomeFormation to TEXT,
            strAwayGoalDetails to TEXT,
            strAwayRedCards to TEXT,
            strAwayYellowCards to TEXT,
            strAwayLineupGoalkeeper to TEXT,
            strAwayLineupDefense to TEXT,
            strAwayLineupForward to TEXT,
            strAwayLineupSubstitutes to TEXT,
            strAwayFormation to TEXT,
            strAwayLineupMidfield to TEXT,
            strHomeLineupMidfield to TEXT,
            intHomeShots to TEXT,
            intAwayShots to TEXT,
            dateEvent to TEXT,
            strDate to TEXT,
            strTime to TEXT,
            strThumb to TEXT,
            idHomeTeam to TEXT,
            idAwayTeam to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(TABLE_FAVORITE_NEXT, true)
        db.dropTable(TABLE_FAVORITE_PAST, true)
        db.dropTable(TABLE_TEAM, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)