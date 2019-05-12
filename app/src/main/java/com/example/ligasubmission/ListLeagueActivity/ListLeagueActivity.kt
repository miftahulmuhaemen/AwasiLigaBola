package com.example.ligasubmission.ListLeagueActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.SearchView
import com.example.ligasubmission.LeagueDetailActivity.LeagueDetailActivity
import com.example.ligasubmission.Model.InitialLeague
import com.example.ligasubmission.R
import com.example.ligasubmission.Util.Util.Companion.DETIL_TRANSACTION
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onQueryTextListener
import org.jetbrains.anko.support.v4.viewPager

class ListLeagueActivity : AppCompatActivity() {

    private lateinit var myTabLayout: TabLayout
    private lateinit var myViewPager: ViewPager
    lateinit var gambar: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val league: InitialLeague = intent.getParcelableExtra(DETIL_TRANSACTION)
        val mAdapter = ListLeaguePageAdapter(supportFragmentManager, league.id.toString())


        coordinatorLayout {
            lparams(matchParent, matchParent)

            appBarLayout {
                lparams(matchParent, wrapContent)

                gambar = imageView {
                    onClick {
                        startActivity<LeagueDetailActivity>(DETIL_TRANSACTION to league)
                    }
                }.lparams {
                    width = dip(100)
                    height = dip(100)
                    setMargins(0, dip(8), 0, 0)
                    gravity = Gravity.CENTER
                }

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
        league.img?.let { Picasso.get().load(it).into(gambar) }
    }
}
