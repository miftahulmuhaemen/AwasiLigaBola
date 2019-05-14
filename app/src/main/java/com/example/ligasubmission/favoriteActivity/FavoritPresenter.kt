package com.example.ligasubmission.favoriteActivity

import android.content.Context
import com.example.ligasubmission.database.MyDatabaseOpenHelper
import com.example.ligasubmission.model.Event
import com.example.ligasubmission.util.DatabaseConst.TABLE_FAVORITE_NEXT
import com.example.ligasubmission.util.DatabaseConst.TABLE_FAVORITE_PAST
import com.example.ligasubmission.util.Util
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritPresenter(
    private val view: FavoritFragment

) {

    val Context.database: MyDatabaseOpenHelper
        get() = MyDatabaseOpenHelper.getInstance(applicationContext)

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