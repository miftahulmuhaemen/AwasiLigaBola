package com.example.ligasubmission.favorite.listMatchFragment

import com.example.ligasubmission.database.database
import com.example.ligasubmission.model.Event
import com.example.ligasubmission.util.DatabaseConstMatches.TABLE_FAVORITE_NEXT
import com.example.ligasubmission.util.DatabaseConstMatches.TABLE_FAVORITE_PAST
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ListMatchPresenter(
    private val view: ListMatchFragment
) {

    fun getFavoriteNext() {
        view.showLoading()
        view.activity?.database?.use {
            val result = select(TABLE_FAVORITE_NEXT)
            val favorite = result.parseList(classParser<Event>())
            view.showNextLeague(favorite)
            view.hideLoading()
        }
    }

    fun getFavoritePast() {
        view.activity?.database?.use {
            view.showLoading()
            val result = select(TABLE_FAVORITE_PAST) //it's still NEXT
            val favorite = result.parseList(classParser<Event>())
            view.showPastLeague(favorite)
            view.hideLoading()
        }
    }

}