package com.example.ligasubmission.mainActivity

import com.example.ligasubmission.model.InitialLeague
import com.example.ligasubmission.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(
    private val view: MainActivity
) {

    fun initData() {
        GlobalScope.launch(Dispatchers.Main){
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
            view.hideLoading()
            view.showTeamList(leagues)
        }
    }

}