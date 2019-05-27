package com.example.ligasubmission.detailTeam

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ligasubmission.R
import com.example.ligasubmission.model.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*


class DetailTeamAdapter(private var player: List<Player>, private val listener: (Player) -> Unit) :
    RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            ItemViewUI().createView(
                AnkoContext.create(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(player[position], listener)
    }

    override fun getItemCount(): Int = player.size

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val mImage: ImageView = view.find(R.id.image)
    private val mBox: RelativeLayout = view.find(R.id.clickbox)

    @SuppressLint("SetTextI18n")
    fun bindItem(items: Player, listener: (Player) -> Unit) {
        Picasso.get().load(items.strThumb).into(mImage)
        mBox.setOnClickListener { listener(items) }
    }
}


class ItemViewUI : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            id = R.id.clickbox
            padding = dip(16)

            imageView{
                id = R.id.image
            }.lparams {
                width = dip (60)
                height = dip (60)
            }
        }
    }
}