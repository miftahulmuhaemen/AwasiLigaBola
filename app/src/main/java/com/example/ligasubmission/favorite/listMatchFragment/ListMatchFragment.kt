package com.example.ligasubmission.favorite.listMatchFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.example.ligasubmission.detailMatch.DetailMatchActivity
import com.example.ligasubmission.model.Event
import com.example.ligasubmission.R
import com.example.ligasubmission.util.DatabaseConstMatches.TABLE_FAVORITE_NEXT
import com.example.ligasubmission.util.DatabaseConstMatches.TABLE_FAVORITE_PAST
import com.example.ligasubmission.util.Util.DETAIL_TRANSACTION
import com.example.ligasubmission.util.Util.LIST_LEAGUE_FRAGS
import com.example.ligasubmission.util.Util.LIST_LEAGUE_NEXT_MATCH
import com.example.ligasubmission.util.Util.LIST_LEAGUE_PREV_MATCH
import com.example.ligasubmission.util.invisible
import com.example.ligasubmission.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class ListMatchFragment : Fragment(), ListMatchView {

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPastLeague(data: List<Event>?) {
        swipeRefresh.isRefreshing = false
        pastEvent.clear()
        data?.let { pastEvent.addAll(it) }
        mAdapter.notifyDataSetChanged()
        if (mAdapter.itemCount == 0) blank.visible() else blank.invisible()
    }

    override fun showNextLeague(data: List<Event>?) {
        swipeRefresh.isRefreshing = false
        nextEvent.clear()
        data?.let { nextEvent.addAll(it) }
        mAdapter.notifyDataSetChanged()
        if (mAdapter.itemCount == 0) blank.visible() else blank.invisible()
    }

    private lateinit var listTeam: RecyclerView
    private var pastEvent: MutableList<Event> = mutableListOf()
    private var nextEvent: MutableList<Event> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var mAdapter: FavoriteListAdapter
    private lateinit var presenter: ListMatchPresenter
    private lateinit var blank: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return UI {
            swipeRefresh = swipeRefreshLayout {
                relativeLayout {
                    padding = dip(16)
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(activity)
                    }.lparams {
                        below(R.id.search)
                    }
                    progressBar = progressBar {
                        visibility = View.INVISIBLE
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
            }
        }.view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = ListMatchPresenter(this)

        val fragmentSwitch = arguments?.getString(LIST_LEAGUE_FRAGS)
        when (fragmentSwitch) {
            LIST_LEAGUE_PREV_MATCH -> {
                mAdapter = FavoriteListAdapter(pastEvent) {
                    activity?.startActivity<DetailMatchActivity>(
                        DETAIL_TRANSACTION to it,
                        LIST_LEAGUE_FRAGS to TABLE_FAVORITE_PAST
                    )
                }
                presenter.getFavoritePast()
            }
            LIST_LEAGUE_NEXT_MATCH -> {
                mAdapter = FavoriteListAdapter(nextEvent) {
                    activity?.startActivity<DetailMatchActivity>(
                        DETAIL_TRANSACTION to it,
                        LIST_LEAGUE_FRAGS to TABLE_FAVORITE_NEXT
                    )
                }
                presenter.getFavoriteNext()
            }
        }

        listTeam.adapter = mAdapter

        swipeRefresh.onRefresh {
            when (fragmentSwitch) {
                LIST_LEAGUE_PREV_MATCH ->
                    presenter.getFavoritePast()
                LIST_LEAGUE_NEXT_MATCH ->
                    presenter.getFavoriteNext()

            }
        }
    }

}