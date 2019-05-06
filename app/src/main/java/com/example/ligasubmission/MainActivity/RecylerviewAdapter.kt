package com.example.ligasubmission.MainActivity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.ligasubmission.Model.Item
import com.squareup.picasso.Picasso
import com.example.ligasubmission.R.id.*
import org.jetbrains.anko.*


class RecyclerViewAdapter(private val items: List<Item>, private val listener: (Item) -> Unit) : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            ItemViewUI().createView(
                AnkoContext.create(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size
}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val nama: TextView = view.find(name)
    private val gambar: ImageView = view.find(imagez)
    private val kotak: RelativeLayout = view.find(clickbox)

    fun bindItem(items: Item, listener: (Item) -> Unit) {
        nama.text = items.name
        items.img?.let { Picasso.get().load(it).into(gambar) }
        kotak.setOnClickListener { listener(items) }
    }
}


class ItemViewUI : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        relativeLayout {
            id = clickbox
            padding = dip(8)
            imageView{
                id = imagez
            }.lparams{
                width = dip(100)
                height = dip(100)
                centerInParent()
            }

            textView{
                textSize = sp(6).toFloat()
                id = name
            }.lparams{
                width = wrapContent
                height = wrapContent
                below(imagez)
                setMargins(0, dip (16), dip(16), 0)
                centerHorizontally()
            }
        }
    }
}