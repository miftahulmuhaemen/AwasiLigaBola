package com.example.ligasubmission.MainActivity

import com.example.ligasubmission.Model.InitialLeague
import com.example.ligasubmission.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainActivity
) {

    fun initData(){
        doAsync {
            view.showLoading()
            val leagues: MutableList<InitialLeague> = mutableListOf()
            val id = view.resources.getStringArray(R.array.club_id)
            val name = view.resources.getStringArray(R.array.club_name)
            val image = view.resources.obtainTypedArray(R.array.club_image)

            for (i in name.indices) {
                leagues.add(
                    InitialLeague(
                        id[i].toInt(), name[i],
                        image.getResourceId(i, 0)
                    )
                )
            }
            image.recycle()

            uiThread {
                view.hideLoading()
                view.showTeamList(leagues)
            }
        }
    }

}