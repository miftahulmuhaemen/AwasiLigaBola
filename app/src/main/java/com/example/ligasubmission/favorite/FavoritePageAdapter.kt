package com.example.ligasubmission.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.ligasubmission.favorite.listMatchFragment.ListMatchFragment
import com.example.ligasubmission.favorite.listTeamFragment.ListTeamFragment
import com.example.ligasubmission.util.Util

class FavoritePageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        val favoriteFrags = ListMatchFragment()

        when (position) {
            0 -> {
                favoriteFrags.arguments = Bundle().apply {
                    putString(Util.LIST_LEAGUE_FRAGS, Util.LIST_LEAGUE_NEXT_MATCH)
                }
                return favoriteFrags
            }
            1 -> {
                favoriteFrags.arguments = Bundle().apply {
                    putString(Util.LIST_LEAGUE_FRAGS, Util.LIST_LEAGUE_PREV_MATCH)
                }
                return favoriteFrags
            }
            else -> {
                return ListTeamFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> Util.LIST_LEAGUE_NEXT_MATCH
            1 -> Util.LIST_LEAGUE_PREV_MATCH
            else -> Util.LIST_TEAM_FRAGS
        }
    }
}