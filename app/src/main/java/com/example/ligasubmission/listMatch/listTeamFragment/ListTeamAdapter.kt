package com.example.ligasubmission.listMatch.listTeamFragment

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ligasubmission.R
import com.example.ligasubmission.model.Event
import com.example.ligasubmission.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*


class ListTeamAdapter(private var teams: List<Team>, private val listener: (Team) -> Unit) :
    RecyclerView.Adapter<TeamViewHolder>(), Filterable {

    private val originalItem: List<Team> = teams

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            ItemViewUI().createView(
                AnkoContext.create(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.size


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    teams = originalItem
                } else {
                    val filteredList = ArrayList<Team>()
                    for (row in teams) {
                        if (row.strTeam!!.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    teams = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = teams
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                teams = filterResults.values as ArrayList<Team>
                notifyDataSetChanged()
            }
        }
    }

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val mImage: ImageView = view.find(R.id.image)
    private val mName: TextView = view.find(R.id.name)
    private val mBox: RelativeLayout = view.find(R.id.checkbox)

    @SuppressLint("SetTextI18n")
    fun bindItem(items: Team, listener: (Team) -> Unit) {
        Picasso.get().load(items.strTeamBadge).into(mImage)
        mName.text = items.strTeam
        mBox.setOnClickListener { listener(items) }
    }
}

class ItemViewUI : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            id = R.id.checkbox
            padding = dip(16)

            imageView {
                id = R.id.image
            }.lparams {
                width = dip(60)
                height = dip(60)
            }

            textView {
                id = R.id.name
                textSize = 16f
                gravity = Gravity.CENTER
            }.lparams {
                rightOf(R.id.image)
                centerVertically()
                leftMargin = dip(16)
            }

        }
    }
}