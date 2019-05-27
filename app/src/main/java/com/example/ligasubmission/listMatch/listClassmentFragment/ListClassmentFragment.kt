package com.example.ligasubmission.listMatch.listClassmentFragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.example.ligasubmission.R
import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.model.Classment
import com.example.ligasubmission.util.Util
import com.example.ligasubmission.util.invisible
import com.example.ligasubmission.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI


class ListClassmentFragment : Fragment(), ListClassmentView {

    override fun showClassment(data: List<Classment>?) {
        classment.clear()
        data?.let { classment.addAll(it) }
        mAdapter.notifyDataSetChanged()
        if (mAdapter.itemCount == 0) blank.visible() else blank.invisible()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    private lateinit var blank: TextView
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: ListClassmentPresenter
    private lateinit var mAdapter: ListClassmentAdapter
    private  var classment: MutableList<Classment> = mutableListOf()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return UI {
            relativeLayout {
                padding = dip(16)
                lparams(width = matchParent, height = wrapContent)

                listTeam = recyclerView {
                    id = R.id.recylerVieww
                    layoutManager = LinearLayoutManager(activity)
                }.lparams {
                    below(R.id.header)
                }

                relativeLayout {
                    id = R.id.header
                    padding = dip(16)

                    textView {
                        id = R.id.name
                        text = "Team Name"
                        textSize = 16f
                        maxWidth = dip(180)
                    }

                    textView {
                        id = R.id.win
                        text = "Win"
                        textSize = 16f
                        width = dip(40)
                        gravity = Gravity.CENTER
                    }.lparams {
                        leftOf(R.id.draw)
                    }

                    textView {
                        id = R.id.draw
                        text = "Draw"
                        textSize = 16f
                        width = dip(40)
                        gravity = Gravity.CENTER
                    }.lparams {
                        leftOf(R.id.loss)
                    }

                    textView {
                        id = R.id.loss
                        text = "Loss"
                        textSize = 16f
                        width = dip(40)
                        gravity = Gravity.CENTER
                    }.lparams {
                        leftOf(R.id.total)
                    }

                    textView {
                        id = R.id.total
                        text = "Total"
                        textSize = 16f
                        width = dip(40)
                        gravity = Gravity.CENTER
                    }.lparams {
                        alignParentRight()
                    }
                }

                progressBar = progressBar {
                }.lparams {
                    centerInParent()
                }

                blank = textView {
                    text = context.getString(R.string.no_data)
                    invisible()
                }.lparams {
                    centerInParent()
                }
            }
        }.view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = ListClassmentPresenter(this, request, gson)
        val idLeague = arguments?.getString(Util.LIST_LEAGUE_ID)

        presenter.getClassment(idLeague)
        mAdapter = ListClassmentAdapter(classment)
        listTeam.adapter = mAdapter

    }

}
