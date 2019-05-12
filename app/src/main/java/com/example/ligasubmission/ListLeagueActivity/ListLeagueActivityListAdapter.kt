package com.example.ligasubmission.ListLeagueActivity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ligasubmission.Model.Event
import com.example.ligasubmission.R.id.*
import org.jetbrains.anko.*


class ListLeagueActivityListAdapter(private var events: List<Event>, private val listener: (Event) -> Unit) : RecyclerView.Adapter<TeamViewHolder>(), Filterable {

    private val originalItem: List<Event> = events

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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    events = originalItem
                } else {
                    val filteredList = ArrayList<Event>()
                    for (row in events) {
                        if (row.strHomeTeam!!.toLowerCase().contains(charString.toLowerCase()) || row.strAwayTeam!!.contains(charSequence)) {
                            filteredList.add(row)
                        }
                    }
                    events = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = events
                return filterResults
            }
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                events = filterResults.values as ArrayList<Event>
                notifyDataSetChanged()
            }
        }
    }
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
    override fun createView(ui: AnkoContext<Context>): View = with(ui){
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