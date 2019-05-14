package com.example.ligasubmission.listLeagueActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.ligasubmission.util.Util.LIST_LEAGUE_FRAGS
import com.example.ligasubmission.util.Util.LIST_LEAGUE_ID
import com.example.ligasubmission.util.Util.LIST_LEAGUE_NEXT_MATCH
import com.example.ligasubmission.util.Util.LIST_LEAGUE_PREV_MATCH

class ListLeaguePageAdapter(fm: FragmentManager, val id_league: String) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {

        val ListLeague = ListLeagueListFragment()
        ListLeague.arguments = Bundle().apply {
            when (position) {
                0 -> putString(LIST_LEAGUE_FRAGS, LIST_LEAGUE_NEXT_MATCH)
                else -> putString(LIST_LEAGUE_FRAGS, LIST_LEAGUE_PREV_MATCH)
            }
            putString(LIST_LEAGUE_ID, id_league)
        }
        return ListLeague
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> LIST_LEAGUE_NEXT_MATCH
            else -> LIST_LEAGUE_PREV_MATCH
        }
    }
}