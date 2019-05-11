package com.example.ligasubmission.ListDetailMatchActivity

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.example.ligasubmission.R
import com.example.ligasubmission.Util.Util.Companion.Margin_16dp
import com.example.ligasubmission.Util.Util.Companion.Margin_8dp
import org.jetbrains.anko.*
import org.jetbrains.anko.db.TEXT

class ListDetailMatchActivity : AppCompatActivity() {

    private lateinit var strHomeGoal: TextView
    private lateinit var strAwayGoal: TextView
    private lateinit var strHomeRedCards: TextView
    private lateinit var strAwayRedCards: TextView
    private lateinit var strHomeTeam: TextView
    private lateinit var strHomeYellowCards: TextView
    private lateinit var strHomeLineupGoalkeeper: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout {
            padding = dip(Margin_16dp)
            textView {
                id = R.id.Score
                textSize = 20f
                text = "0 : 0"
                setTypeface(typeface, Typeface.BOLD)
            }.lparams {
                centerHorizontally()
            }
            textView {
                text = "Aselole FC"
                textSize = 20f
                gravity = Gravity.START
                maxWidth = dip(140)
            }.lparams {
                leftOf(R.id.Marker)
                rightMargin = dip(32)
            }
            textView {
                text = "Aseludin FC"
                textSize = 20f
                gravity = Gravity.END
                maxWidth = dip(140)
            }.lparams {
                rightOf(R.id.Marker)
                leftMargin = dip(32)
            }

            textView {
                text = "GOAL"
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
                gravity = Gravity.RIGHT
                text =
                    "Kyle Walker; Aymeric Laporte; Vincent Kompany; Oleksandr Zinchenko; Kyle Walker; Aymeric Laporte; Vincent Kompany; Oleksandr Zinchenko; "
            }.lparams {
                leftOf(R.id.Marker)
                below(R.id.Marker)
            }

            strAwayGoal = textView {
                gravity = Gravity.LEFT
                text =
                    "Kyle Walker; Aymeric Laporte; Vincent Kompany; Oleksandr Zinchenko; Kyle Walker; Aymeric Laporte; Vincent Kompany; Oleksandr Zinchenko; "
            }.lparams {
                rightOf(R.id.Marker)
                below(R.id.Marker)
            }

            textView {
                id = R.id.RedCards
                text = "RED CARD"
                setTypeface(typeface, Typeface.BOLD_ITALIC)
            }.lparams {
                centerHorizontally()
                topMargin = dip(Margin_16dp)
                below(R.id.strHomeGoal)
            }


            strHomeRedCards = textView {
                id = R.id.strHomeRedCards
                gravity = Gravity.RIGHT
                text =
                    "Kyle Walker; Aymeric Laporte; Vincent Kompany; Oleksandr Zinchenko; Kyle Walker; Aymeric Laporte; Vincent Kompany; Oleksandr Zinchenko; "
            }.lparams {
                leftOf(R.id.Marker)
                below(R.id.RedCards)
            }

            strAwayRedCards = textView {
                gravity = Gravity.LEFT
                text = "Kyle Walker; Aymeric Laporte; Vincent Kompany; Oleksandr Zinchenko; Kyle Walker; Aymeric Laporte; Vincent Kompany; Oleksandr Zinchenko; "
            }.lparams {
                rightOf(R.id.Marker)
                below(R.id.RedCards)
            }


        }

    }

}
