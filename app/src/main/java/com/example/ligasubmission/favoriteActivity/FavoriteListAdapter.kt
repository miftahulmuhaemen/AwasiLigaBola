package com.example.ligasubmission.favoriteActivity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.ligasubmission.model.Event
import com.example.ligasubmission.R.id.*
import org.jetbrains.anko.*


class FavoriteListAdapter(private var events: List<Event>, private val listener: (Event) -> Unit) :
    RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            ItemViewUI().createView(
                AnkoContext.create(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

    override fun getItemCount(): Int = events.size
}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val score: TextView = view.find(Score)
    private val home: TextView = view.find(HomeScore)
    private val away: TextView = view.find(AwayScore)
    private val kotak: RelativeLayout = view.find(clickbox)

    @SuppressLint("SetTextI18n")
    fun bindItem(items: Event, listener: (Event) -> Unit) {
        score.text = items.intHomeScore + " : " + items.intAwayScore
        home.text = items.strHomeTeam
        away.text = items.strAwayTeam

        kotak.setOnClickListener { listener(items) }
    }
}


class ItemViewUI : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            id = clickbox
            padding = dip(16)
            textView {
                id = Score
                textSize = 20f
                setTypeface(typeface, Typeface.BOLD)
            }.lparams {
                centerInParent()
            }
            textView {
                id = HomeScore
                textSize = 20f
                gravity = Gravity.START
                maxWidth = dip(140)
            }.lparams {
                marginEnd = dip(20)
                marginStart = dip(20)
                alignParentLeft()
            }
            textView {
                id = AwayScore
                textSize = 20f //sp
                gravity = Gravity.END
                maxWidth = dip(140)
            }.lparams {
                marginEnd = dip(20)
                marginStart = dip(20)
                alignParentRight()
            }
        }
    }
}