package com.example.ligasubmission.ListDetailMatchActivity

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.example.ligasubmission.Model.Event
import com.example.ligasubmission.Model.InitialLeague
import com.example.ligasubmission.R
import com.example.ligasubmission.Util.Util
import com.example.ligasubmission.Util.Util.Companion.Margin_16dp
import com.example.ligasubmission.Util.Util.Companion.Margin_8dp
import org.jetbrains.anko.*
import org.jetbrains.anko.db.TEXT

class ListDetailMatchActivity : AppCompatActivity() {

    private lateinit var strScore: TextView
    private lateinit var strHomeGoal: TextView
    private lateinit var strAwayGoal: TextView
    private lateinit var strHomeRedCards: TextView
    private lateinit var strAwayRedCards: TextView
    private lateinit var strHomeTeam: TextView
    private lateinit var strAwayTeam: TextView
    private lateinit var strHomeYellowCards: TextView
    private lateinit var strAwayYellowCards: TextView
    private lateinit var strHomeLineupGoalkeeper: TextView
    private lateinit var strAwayLineupGoalkeeper: TextView
    private lateinit var strHomeLineupMidfield: TextView
    private lateinit var strAwayLineupMidfield: TextView
    private lateinit var strHomeLineupSubstitutes: TextView
    private lateinit var strAwayLineupSubstitutes: TextView
    private lateinit var strHomeFormation: TextView
    private lateinit var strAwayFormation: TextView
    private lateinit var strHomeLineupDefense: TextView
    private lateinit var strAwayLineupDefense: TextView
    private lateinit var strHomeLineupForward: TextView
    private lateinit var strAwayLineupForward: TextView
    private lateinit var intHomeShots: TextView
    private lateinit var intAwayShots: TextView
    private lateinit var dateEvent: TextView
    private lateinit var strTime: TextView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        scrollView {
            relativeLayout {
                padding = dip(Margin_16dp)
                strScore = textView {
                    id = R.id.Score
                    textSize = 20f
                    setTypeface(typeface, Typeface.BOLD)
                }.lparams {
                    centerHorizontally()
                }

                strHomeTeam = textView {
                    textSize = 20f
                    gravity = Gravity.START
                    maxWidth = dip(140)
                }.lparams {
                    leftOf(R.id.Marker)
                    rightMargin = dip(24)
                }
                strAwayTeam = textView {
                    textSize = 20f
                    gravity = Gravity.END
                    maxWidth = dip(140)
                }.lparams {
                    rightOf(R.id.Marker)
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

                strHomeGoal = textView {
                    id = R.id.strHomeGoal
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Marker)
                }

                strAwayGoal = textView {
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


                strHomeRedCards = textView {
                    id = R.id.strHomeRedCards
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.RedCards)
                }

                strAwayRedCards = textView {
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

                strHomeYellowCards = textView {
                    id = R.id.strHomeYellowCards
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.YellowCard)
                }

                strAwayYellowCards = textView {
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

                strHomeLineupGoalkeeper = textView {
                    id = R.id.strHomeLineupGoalkeeper
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.GoalKeeper)
                }

                strAwayLineupGoalkeeper = textView {
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

                strHomeLineupMidfield = textView {
                    id = R.id.strHomeLineupMidfield
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Midfield)
                }

                strAwayLineupMidfield = textView {
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

                strHomeLineupSubstitutes = textView {
                    id = R.id.strHomeLineupSubstitutes
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Subtitute)
                }

                strAwayLineupSubstitutes = textView {
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

                strHomeFormation = textView {
                    id = R.id.strHomeFormation
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Formation)
                }

                strAwayFormation = textView {
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

                strHomeLineupDefense = textView {
                    id = R.id.strHomeLineupDefense
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Defense)
                }

                strAwayLineupDefense = textView {
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

                strHomeLineupForward = textView {
                    id = R.id.strHomeLineupForward
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Forward)
                }

                strAwayLineupForward = textView {
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

                intHomeShots = textView {
                    id = R.id.intHomeShots
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Shots)
                }

                intAwayShots = textView {
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

                dateEvent = textView {
                    gravity = Gravity.END
                }.lparams {
                    leftOf(R.id.Marker)
                    below(R.id.Date)
                }

                strTime = textView {
                    gravity = Gravity.START
                }.lparams {
                    rightOf(R.id.Marker)
                    below(R.id.Date)
                }
            }
        }


        val intent = intent
        val event: Event = intent.getParcelableExtra(Util.DETIL_TRANSACTION)

        strScore.text = event.intHomeScore + " : " + event.intAwayScore
        strHomeTeam.text = event.strHomeTeam
        strAwayTeam.text = event.strAwayTeam
        strHomeGoal.text = event.strHomeGoalDetails
        strAwayGoal.text = event.strAwayGoalDetails
        strHomeRedCards.text = event.strHomeRedCards
        strAwayRedCards.text = event.strAwayRedCards
        strHomeYellowCards.text = event.strHomeYellowCards
        strAwayYellowCards.text = event.strAwayYellowCards
        strHomeLineupGoalkeeper.text = event.strHomeLineupGoalkeeper
        strAwayLineupGoalkeeper.text = event.strAwayLineupGoalkeeper
        strHomeLineupMidfield.text = event.strHomeLineupMidfield
        strAwayLineupMidfield.text = event.strAwayLineupMidfield
        strHomeLineupSubstitutes.text = event.strHomeLineupSubstitutes
        strAwayLineupSubstitutes.text = event.strAwayLineupSubstitutes
        strHomeFormation.text = event.strHomeFormation
        strAwayFormation.text = event.strAwayFormation
        strHomeLineupDefense.text = event.strAwayLineupDefense
        strAwayLineupDefense.text = event.strAwayLineupDefense
        strHomeLineupForward.text = event.strHomeLineupForward
        strAwayLineupForward.text = event.strAwayLineupForward
        intHomeShots.text = event.intHomeShots
        intAwayShots.text = event.intAwayShots
        dateEvent.text = event.dateEvent
        strTime.text = event.strTime


    }

}
