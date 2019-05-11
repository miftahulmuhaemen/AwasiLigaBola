package com.example.ligasubmission.LeagueDetailActivity

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.ligasubmission.API.ApiRepository
import com.example.ligasubmission.ListLeagueActivity.ListLeaguePresenter
import com.example.ligasubmission.Model.InitialLeague
import com.example.ligasubmission.Model.League
import com.example.ligasubmission.R
import com.example.ligasubmission.Util.Util.Companion.DETIL_TRANSACTION
import com.example.ligasubmission.Util.invisible
import com.example.ligasubmission.Util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.viewPager

class LeagueDetailActivity : AppCompatActivity(), LeagueDetailView, AnkoLogger {


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    @SuppressLint("SetTextI18n")
    override fun showLeagueDetail(data: List<League>?) {
        data?.first()?.strFanart1?.let { Picasso.get().load(it).into(img) }
        field1.text = data?.first()?.strLeague
        field2.text = data?.first()?.strLeagueAlternate
        field3.text = data?.first()?.intFormedYear + " | " + data?.first()?.strCountry + " | " + data?.first()?.strGender
        field4.text = data?.first()?.strWebsite
        field5.text = data?.first()?.strDescriptionEN
    }

    lateinit var field1: TextView
    lateinit var field2: TextView
    lateinit var field3: TextView
    lateinit var field4: TextView
    lateinit var field5: TextView
    lateinit var img: ImageView
    lateinit var progressBar: ProgressBar
    private lateinit var presenter: LeagueDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scrollView {
            verticalLayout {
                lparams(matchParent, matchParent)

                img = imageView {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }.lparams {
                    width = matchParent
                    height = dip(250)
                }

                progressBar = progressBar {
                }.lparams {
                    width = dip(60)
                    height = dip(60)
                    gravity = Gravity.CENTER
                }

                field1 = textView {
                    textSize = sp(20).toFloat()
                }.lparams {
                    marginEnd = dip(16)
                    marginStart = dip(16)
                }

                field2 = textView {
                    textSize = 14f
                }.lparams {
                    marginEnd = dip(16)
                    marginStart = dip(16)
                }

                field3 = textView {
                    textSize = 12f
                }.lparams {
                    marginEnd = dip(16)
                    marginStart = dip(16)
                }

                field4 = textView().lparams {
                    marginEnd = dip(16)
                    marginStart = dip(16)
                }

                field5 = textView {
                    textSize = 14f
                }.lparams {
                    marginEnd = dip(16)
                    marginStart = dip(16)
                    topMargin = dip(16)
                    bottomMargin = dip( 16)
                }
            }
        }

        val intent = intent
        val leagues: InitialLeague = intent.getParcelableExtra(DETIL_TRANSACTION)

        val request = ApiRepository()
        val gson = Gson()
        presenter = LeagueDetailPresenter(this, request, gson)
        presenter.getLeagueDetail(leagues.id.toString())
    }


}
