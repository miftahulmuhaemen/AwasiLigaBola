package com.example.ligasubmission.listMatch.listClassmentFragment

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ligasubmission.R.id.*
import com.example.ligasubmission.model.Classment
import org.jetbrains.anko.*


class ListClassmentAdapter(private var classment: List<Classment>) :
    RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            ItemViewUI().createView(
                AnkoContext.create(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(classment[position])
    }

    override fun getItemCount(): Int = classment.size

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val mName: TextView = view.find(name)
    private val mWin: TextView = view.find(win)
    private val mDraw: TextView = view.find(draw)
    private val mLoss: TextView = view.find(loss)
    private val mTotal: TextView = view.find(total)

    @SuppressLint("SetTextI18n")
    fun bindItem(items: Classment) {
        mName.text = items.name
        mWin.text = items.win
        mDraw.text = items.draw
        mLoss.text = items.loss
        mTotal.text = items.total
    }
}


class ItemViewUI : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            padding = dip(16)

            textView {
                id = name
                textSize = 16f
                maxWidth = dip(180)
            }

            textView {
                id = win
                textSize = 16f
                width = dip(40)
                gravity = Gravity.CENTER
            }.lparams {
                leftOf(draw)
            }

            textView {
                id = draw
                textSize = 16f
                width = dip(40)
                gravity = Gravity.CENTER
            }.lparams {
                leftOf(loss)
            }

            textView {
                id = loss
                textSize = 16f
                width = dip(40)
                gravity = Gravity.CENTER
            }.lparams {
                leftOf(total)
            }

            textView {
                id = total
                textSize = 16f
                width = dip(40)
                gravity = Gravity.CENTER
            }.lparams {
                alignParentRight()
            }
        }
    }
}