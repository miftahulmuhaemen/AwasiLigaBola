package com.example.ligasubmission.detailMatch

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.example.ligasubmission.R
import com.example.ligasubmission.R.drawable.ic_add_to_favorites
import com.example.ligasubmission.R.drawable.ic_added_to_favorites
import com.example.ligasubmission.R.menu.detail_menu
import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.model.Event
import com.example.ligasubmission.model.Team
import com.example.ligasubmission.util.AnkoLayoutConst.Margin_16dp
import com.example.ligasubmission.util.AnkoLayoutConst.Margin_8dp
import com.example.ligasubmission.util.Util
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class DetailMatchActivity : AppCompatActivity(), AnkoLogger, DetailMatchView {

    override fun showTeamLogo(homeTeam: List<Team>?, awayTeam: List<Team>?) {
        Picasso.get().load(homeTeam?.last()?.strTeamBadge).into(mHomeTeamImage)
        Picasso.get().load(awayTeam?.last()?.strTeamBadge).into(mAwayTeamImage)
    }

    override fun favoriteState(state: Boolean) {
        isFavorite = !state
    }

    override fun message(query: String) {
        toast(query)
    }

    private lateinit var mHomeTeamImage: ImageView
    private lateinit var mAwayTeamImage: ImageView
    private lateinit var event: Event
    private lateinit var presenter: DetailMatchPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite)
                    presenter.removeFromFavorite()
                else
                    presenter.addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val league: String = intent.getStringExtra(Util.LIST_LEAGUE_FRAGS)
        event = intent.getParcelableExtra(Util.DETAIL_TRANSACTION)
        val request = ApiRepository()
        val gson = Gson()

        presenter = DetailMatchPresenter(this, this, event, league, request, gson)
        presenter.favoriteState()
        presenter.getTeamPicture()


        scrollView {
            relativeLayout {
                padding = dip(Margin_16dp)

                mHomeTeamImage = imageView {
                    id = R.id.homeTeamImage
                }.lparams {
                    leftOf(R.id.Marker)
                    rightMargin = dip(24)
                    width = dip(100)
                    height = dip(100)
                }

                mAwayTeamImage = imageView {
                }.lparams {
                    rightOf(R.id.Marker)
                    leftMargin = dip(24)
                    width = dip(100)
                    height = dip(100)
                }

                textView {
                    id = R.id.Score
                    text = event.intHomeScore + " : " + event.intAwayScore
                    textSize = 20f
                    setTypeface(typeface, Typeface.BOLD)
                }.lparams {
                    centerHorizontally()
                    below(R.id.homeTeamImage)
                }

                textView {
                    text = event.strHomeTeam
                    textSize = 20f
                    gravity = Gravity.START
                    maxWidth = dip(140)
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.homeTeamImage)
                    rightMargin = dip(24)
                }

                textView {
                    text = event.strAwayTeam
                    textSize = 20f
                    gravity = Gravity.END
                    maxWidth = dip(140)
                }.lparams {
                    rightOf(R.id.Marker)
                    below(R.id.homeTeamImage)
                    leftMargin = dip(24)
                }

                textView {
                    text = context.getString(R.string.goal)
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }.lparams {
                    centerHorizontally()
                    topMargin = dip(Margin_16dp)
                    below(R.id.Score)
                }

                textView {
                    id = R.id.Marker
                }.lparams {
                    below(R.id.Score)
                    centerHorizontally()
                    horizontalMargin = dip(Margin_8dp)
                    bottomMargin = dip(Margin_16dp)
                }

                textView {
                    text = event.strHomeGoalDetails
                    id = R.id.strHomeGoal
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Marker)
                }

                textView {
                    text = event.strAwayGoalDetails
                    gravity = Gravity.START
                }.lparams {
                    rightOf(R.id.Marker)
                    below(R.id.Marker)
                }

                textView {
                    id = R.id.RedCards
                    text = context.getString(R.string.redcard)
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }.lparams {
                    centerHorizontally()
                    topMargin = dip(Margin_16dp)
                    below(R.id.strHomeGoal)
                }

                textView {
                    text = event.strHomeRedCards
                    id = R.id.strHomeRedCards
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.RedCards)
                }

                textView {
                    text = event.strAwayRedCards
                    gravity = Gravity.START
                }.lparams {
                    rightOf(R.id.Marker)
                    below(R.id.RedCards)
                }

                textView {
                    id = R.id.YellowCard
                    text = context.getString(R.string.yellow_card)
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }.lparams {
                    centerHorizontally()
                    topMargin = dip(Margin_16dp)
                    below(R.id.strHomeRedCards)
                }

                textView {
                    text = event.strHomeYellowCards
                    id = R.id.strHomeYellowCards
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.YellowCard)
                }

                textView {
                    text = event.strAwayYellowCards
                    gravity = Gravity.START
                }.lparams {
                    rightOf(R.id.Marker)
                    below(R.id.YellowCard)
                }

                textView {
                    id = R.id.GoalKeeper
                    text = context.getString(R.string.goalkeeper)
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }.lparams {
                    centerHorizontally()
                    topMargin = dip(Margin_16dp)
                    below(R.id.strHomeYellowCards)
                }

                textView {
                    text = event.strHomeLineupGoalkeeper
                    id = R.id.strHomeLineupGoalkeeper
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.GoalKeeper)
                }

                textView {
                    text = event.strAwayLineupGoalkeeper
                    gravity = Gravity.START
                }.lparams {
                    rightOf(R.id.Marker)
                    below(R.id.GoalKeeper)
                }

                textView {
                    id = R.id.Midfield
                    text = context.getString(R.string.Midfield)
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }.lparams {
                    centerHorizontally()
                    topMargin = dip(Margin_16dp)
                    below(R.id.strHomeLineupGoalkeeper)
                }

                textView {
                    text = event.strHomeLineupMidfield
                    id = R.id.strHomeLineupMidfield
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Midfield)
                }

                textView {
                    text = event.strAwayLineupMidfield
                    gravity = Gravity.START
                }.lparams {
                    rightOf(R.id.Marker)
                    below(R.id.Midfield)
                }

                textView {
                    id = R.id.Subtitute
                    text = context.getString(R.string.substitute)
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }.lparams {
                    centerHorizontally()
                    topMargin = dip(Margin_16dp)
                    below(R.id.strHomeLineupMidfield)
                }

                textView {
                    text = event.strHomeLineupSubstitutes
                    id = R.id.strHomeLineupSubstitutes
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Subtitute)
                }

                textView {
                    text = event.strAwayLineupSubstitutes
                    gravity = Gravity.START
                }.lparams {
                    rightOf(R.id.Marker)
                    below(R.id.Subtitute)
                }

                textView {
                    id = R.id.Formation
                    text = context.getString(R.string.formation)
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }.lparams {
                    centerHorizontally()
                    topMargin = dip(Margin_16dp)
                    below(R.id.strHomeLineupSubstitutes)
                }

                textView {
                    text = event.strHomeFormation
                    id = R.id.strHomeFormation
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Formation)
                }

                textView {
                    text = event.strAwayFormation
                    gravity = Gravity.START
                }.lparams {
                    rightOf(R.id.Marker)
                    below(R.id.Formation)
                }

                textView {
                    id = R.id.Defense
                    text = context.getString(R.string.defense)
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }.lparams {
                    centerHorizontally()
                    topMargin = dip(Margin_16dp)
                    below(R.id.strHomeFormation)
                }

                textView {
                    text = event.strHomeLineupDefense
                    id = R.id.strHomeLineupDefense
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Defense)
                }

                textView {
                    text = event.strAwayLineupDefense
                    gravity = Gravity.START
                }.lparams {
                    rightOf(R.id.Marker)
                    below(R.id.Defense)
                }

                textView {
                    id = R.id.Forward
                    text = context.getString(R.string.forward)
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }.lparams {
                    centerHorizontally()
                    topMargin = dip(Margin_16dp)
                    below(R.id.strHomeLineupDefense)
                }

                textView {
                    text = event.strHomeLineupForward
                    id = R.id.strHomeLineupForward
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Forward)
                }

                textView {
                    text = event.strAwayLineupForward
                    gravity = Gravity.START
                }.lparams {
                    rightOf(R.id.Marker)
                    below(R.id.Forward)
                }

                textView {
                    id = R.id.Shots
                    text = context.getString(R.string.shots)
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }.lparams {
                    centerHorizontally()
                    topMargin = dip(Margin_16dp)
                    below(R.id.strHomeLineupForward)
                }

                textView {
                    text = event.intHomeShots
                    id = R.id.intHomeShots
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Shots)
                }

                textView {
                    text = event.intAwayShots
                    gravity = Gravity.START
                }.lparams {
                    rightOf(R.id.Marker)
                    below(R.id.Shots)
                }

                textView {
                    id = R.id.Date
                    text = context.getString(R.string.date)
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }.lparams {
                    centerHorizontally()
                    topMargin = dip(Margin_16dp)
                    below(R.id.intHomeShots)
                }

                textView {
                    text = event.dateEvent
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Date)
                }

                textView {
                    text = event.strTime
                    gravity = Gravity.START
                }.lparams {
                    rightOf(R.id.Marker)
                    below(R.id.Date)
                }
            }
        }

    }

}
