package com.example.ligasubmission.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.ligasubmission.util.DatabaseConst.TABLE_FAVORITE_NEXT
import com.example.ligasubmission.util.DatabaseConst.TABLE_FAVORITE_PAST
import com.example.ligasubmission.util.DatabaseConst.dateEvent
import com.example.ligasubmission.util.DatabaseConst.idAwayTeam
import com.example.ligasubmission.util.DatabaseConst.idEvent
import com.example.ligasubmission.util.DatabaseConst.idHomeTeam
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
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)