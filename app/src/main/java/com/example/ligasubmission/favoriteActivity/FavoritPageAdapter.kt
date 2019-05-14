package com.example.ligasubmission.favoriteActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.ligasubmission.util.Util

class FavoritPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        val favoritFrags = FavoritFragment()
        favoritFrags.arguments = Bundle().apply {
            when (position) {
                0 -> putString(Util.LIST_LEAGUE_FRAGS, Util.LIST_LEAGUE_NEXT_MATCH)
                else -> putString(Util.LIST_LEAGUE_FRAGS, Util.LIST_LEAGUE_PREV_MATCH)
            }
        }
        return favoritFrags
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> Util.LIST_LEAGUE_NEXT_MATCH
            else -> Util.LIST_LEAGUE_PREV_MATCH
        }
    }
}