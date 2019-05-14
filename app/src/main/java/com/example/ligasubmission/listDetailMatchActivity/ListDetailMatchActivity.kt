package com.example.ligasubmission.listDetailMatchActivity

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
import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.leagueDetailActivity.ListDetailMatchPresenter
import com.example.ligasubmission.leagueDetailActivity.ListDetailMatchView
import com.example.ligasubmission.model.Event
import com.example.ligasubmission.R
import com.example.ligasubmission.R.drawable.ic_add_to_favorites
import com.example.ligasubmission.R.drawable.ic_added_to_favorites
import com.example.ligasubmission.R.menu.detail_menu
import com.example.ligasubmission.util.Util
import com.example.ligasubmission.util.Util.Companion.Margin_16dp
import com.example.ligasubmission.util.Util.Companion.Margin_8dp
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class ListDetailMatchActivity : AppCompatActivity(), AnkoLogger, ListDetailMatchView {

    override fun showTeamLogo(homeTeam: String, awayTeam: String) {
        homeTeam.let {
            Picasso.get().load(it).into(mHomeTeamImage)
        }

        awayTeam.let {
            Picasso.get().load(it).into(mAwayTeamImage)
        }
    }

    override fun favoriteState(state: Boolean) {
        isFavorite = !state
    }

    override fun showLeagueDetail(query: String) {
        toast(query)
    }

    private lateinit var mStrScore: TextView
    private lateinit var mStrHomeGoal: TextView
    private lateinit var mStrAwayGoal: TextView
    private lateinit var mHomeTeamImage: ImageView
    private lateinit var mAwayTeamImage: ImageView
    private lateinit var mStrHomeRedCards: TextView
    private lateinit var mStrAwayRedCards: TextView
    private lateinit var mStrHomeTeam: TextView
    private lateinit var mStrAwayTeam: TextView
    private lateinit var mStrHomeYellowCards: TextView
    private lateinit var mStrAwayYellowCards: TextView
    private lateinit var mStrHomeLineupGoalkeeper: TextView
    private lateinit var mStrAwayLineupGoalkeeper: TextView
    private lateinit var mStrHomeLineupMidfield: TextView
    private lateinit var mStrAwayLineupMidfield: TextView
    private lateinit var mStrHomeLineupSubstitutes: TextView
    private lateinit var mStrAwayLineupSubstitutes: TextView
    private lateinit var mStrHomeFormation: TextView
    private lateinit var mStrAwayFormation: TextView
    private lateinit var mStrHomeLineupDefense: TextView
    private lateinit var mStrAwayLineupDefense: TextView
    private lateinit var mStrHomeLineupForward: TextView
    private lateinit var mStrAwayLineupForward: TextView
    private lateinit var mIntHomeShots: TextView
    private lateinit var mIntAwayShots: TextView
    private lateinit var mDateEvent: TextView
    private lateinit var mStrTime: TextView

    private lateinit var event: Event
    private lateinit var presenter: ListDetailMatchPresenter
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
        event = intent.getParcelableExtra(Util.DETIL_TRANSACTION)
        val request = ApiRepository()
        val gson = Gson()

        presenter = ListDetailMatchPresenter(this, event, league, request, gson)
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
                    width = dip (100)
                    height = dip (100)
                }

                mAwayTeamImage = imageView {
                }.lparams {
                    rightOf(R.id.Marker)
                    leftMargin = dip(24)
                    width = dip (100)
                    height = dip (100)
                }

                mStrScore = textView {
                    id = R.id.Score
                    textSize = 20f
                    setTypeface(typeface, Typeface.BOLD)
                }.lparams {
                    centerHorizontally()
                    below(R.id.homeTeamImage)
                }

                mStrHomeTeam = textView {
                    textSize = 20f
                    gravity = Gravity.START
                    maxWidth = dip(140)
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.homeTeamImage)
                    rightMargin = dip(24)
                }
                mStrAwayTeam = textView {
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

                mStrHomeGoal = textView {
                    id = R.id.strHomeGoal
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Marker)
                }

                mStrAwayGoal = textView {
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


                mStrHomeRedCards = textView {
                    id = R.id.strHomeRedCards
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.RedCards)
                }

                mStrAwayRedCards = textView {
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

                mStrHomeYellowCards = textView {
                    id = R.id.strHomeYellowCards
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.YellowCard)
                }

                mStrAwayYellowCards = textView {
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

                mStrHomeLineupGoalkeeper = textView {
                    id = R.id.strHomeLineupGoalkeeper
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.GoalKeeper)
                }

                mStrAwayLineupGoalkeeper = textView {
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

                mStrHomeLineupMidfield = textView {
                    id = R.id.strHomeLineupMidfield
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Midfield)
                }

                mStrAwayLineupMidfield = textView {
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

                mStrHomeLineupSubstitutes = textView {
                    id = R.id.strHomeLineupSubstitutes
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Subtitute)
                }

                mStrAwayLineupSubstitutes = textView {
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

                mStrHomeFormation = textView {
                    id = R.id.strHomeFormation
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Formation)
                }

                mStrAwayFormation = textView {
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

                mStrHomeLineupDefense = textView {
                    id = R.id.strHomeLineupDefense
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Defense)
                }

                mStrAwayLineupDefense = textView {
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

                mStrHomeLineupForward = textView {
                    id = R.id.strHomeLineupForward
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Forward)
                }

                mStrAwayLineupForward = textView {
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

                mIntHomeShots = textView {
                    id = R.id.intHomeShots
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Shots)
                }

                mIntAwayShots = textView {
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

                mDateEvent = textView {
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Date)
                }

                mStrTime = textView {
                    gravity = Gravity.START
                }.lparams {
                    rightOf(R.id.Marker)
                    below(R.id.Date)
                }
            }
        }


        mStrScore.text = event.intHomeScore + " : " + event.intAwayScore
        mStrHomeTeam.text = event.strHomeTeam
        mStrAwayTeam.text = event.strAwayTeam
        mStrHomeGoal.text = event.strHomeGoalDetails
        mStrAwayGoal.text = event.strAwayGoalDetails
        mStrHomeRedCards.text = event.strHomeRedCards
        mStrAwayRedCards.text = event.strAwayRedCards
        mStrHomeYellowCards.text = event.strHomeYellowCards
        mStrAwayYellowCards.text = event.strAwayYellowCards
        mStrHomeLineupGoalkeeper.text = event.strHomeLineupGoalkeeper
        mStrAwayLineupGoalkeeper.text = event.strAwayLineupGoalkeeper
        mStrHomeLineupMidfield.text = event.strHomeLineupMidfield
        mStrAwayLineupMidfield.text = event.strAwayLineupMidfield
        mStrHomeLineupSubstitutes.text = event.strHomeLineupSubstitutes
        mStrAwayLineupSubstitutes.text = event.strAwayLineupSubstitutes
        mStrHomeFormation.text = event.strHomeFormation
        mStrAwayFormation.text = event.strAwayFormation
        mStrHomeLineupDefense.text = event.strAwayLineupDefense
        mStrAwayLineupDefense.text = event.strAwayLineupDefense
        mStrHomeLineupForward.text = event.strHomeLineupForward
        mStrAwayLineupForward.text = event.strAwayLineupForward
        mIntHomeShots.text = event.intHomeShots
        mIntAwayShots.text = event.intAwayShots
        mDateEvent.text = event.dateEvent
        mStrTime.text = event.strTime


    }

}
