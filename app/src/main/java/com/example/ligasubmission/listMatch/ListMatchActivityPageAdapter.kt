package com.example.ligasubmission.listMatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.ligasubmission.listMatch.listClassmentFragment.ListClassmentFragment
import com.example.ligasubmission.listMatch.listMatchFragment.ListMatchFragment
import com.example.ligasubmission.listMatch.listTeamFragment.ListTeamFragment
import com.example.ligasubmission.util.Util.LIST_CLASSMENT_FRAGS
import com.example.ligasubmission.util.Util.LIST_LEAGUE_FRAGS
import com.example.ligasubmission.util.Util.LIST_LEAGUE_ID
import com.example.ligasubmission.util.Util.LIST_LEAGUE_NEXT_MATCH
import com.example.ligasubmission.util.Util.LIST_LEAGUE_PREV_MATCH
import com.example.ligasubmission.util.Util.LIST_TEAM_FRAGS

class ListMatchActivityPageAdapter(fm: FragmentManager, val id_league: String) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {


        when(position) {
            0 -> {
                val listLeague = ListMatchFragment()
                listLeague.arguments = Bundle().apply {
                    putString(LIST_LEAGUE_FRAGS, LIST_LEAGUE_NEXT_MATCH)
                    putString(LIST_LEAGUE_ID, id_league)
                }
                return listLeague
            }
            1 -> {
                val listLeague = ListMatchFragment()
                listLeague.arguments = Bundle().apply {
                    putString(LIST_LEAGUE_FRAGS, LIST_LEAGUE_PREV_MATCH)
                    putString(LIST_LEAGUE_ID, id_league)
                }
                return listLeague
            }
            2 -> {
                val listClassment = ListClassmentFragment()
                listClassment.arguments = Bundle().apply {
                    putString(LIST_LEAGUE_ID, id_league)
                }
                return listClassment
            }
            else -> {
                val listTeamFragment = ListTeamFragment()
                listTeamFragment.arguments = Bundle().apply {
                    putString(LIST_LEAGUE_ID, id_league)
                }
                return listTeamFragment
            }
        }

    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> LIST_LEAGUE_NEXT_MATCH
            1 -> LIST_LEAGUE_PREV_MATCH
            2 -> LIST_CLASSMENT_FRAGS
            else -> LIST_TEAM_FRAGS
        }
    }
}