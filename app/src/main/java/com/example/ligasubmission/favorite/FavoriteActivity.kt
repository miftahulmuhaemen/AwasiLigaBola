package com.example.ligasubmission.favorite

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.ligasubmission.R
import com.example.ligasubmission.util.Util
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.info
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.viewPager
import org.jetbrains.anko.wrapContent

class FavoriteActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var myTabLayout: TabLayout
    private lateinit var myViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val league: String? = intent?.getStringExtra(Util.LIST_LEAGUE_FRAGS)
        info(league)

        val mAdapter = FavoritePageAdapter(supportFragmentManager)

        coordinatorLayout {
            lparams(matchParent, matchParent)

            appBarLayout {
                lparams(matchParent, wrapContent)

                myTabLayout = themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                    id = R.id.ListLeagueTabLayout
                    lparams(matchParent, wrapContent)
                    {
                        tabGravity = TabLayout.GRAVITY_FILL
                        tabMode = TabLayout.MODE_FIXED
                    }
                }
            }
            myViewPager = viewPager {
                id = R.id.ListLeagueViewpager
                adapter = mAdapter
            }.lparams(matchParent, wrapContent)
            (myViewPager.layoutParams as CoordinatorLayout.LayoutParams).behavior = AppBarLayout.ScrollingViewBehavior()
        }

        myTabLayout.setupWithViewPager(myViewPager)
    }
}
