package com.example.ligasubmission.favorite.listTeamFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import com.example.ligasubmission.R
import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.detailTeam.DetailTeamActivity
import com.example.ligasubmission.model.Team
import com.example.ligasubmission.util.Util
import com.example.ligasubmission.util.invisible
import com.example.ligasubmission.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onQueryTextListener
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class ListTeamFragment : Fragment(), ListTeamView {


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeams(data: List<Team>?) {
        swipeRefresh.isRefreshing = false
        team.clear()
        data?.let { team.addAll(it) }
        mAdapter.notifyDataSetChanged()
        if (mAdapter.itemCount == 0) blank.visible() else blank.invisible()

    }

    private lateinit var blank: TextView
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: ListTeamPresenter
    private lateinit var mAdapter: ListTeamAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var team: MutableList<Team> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return UI {
            swipeRefresh = swipeRefreshLayout {
                relativeLayout {
                    padding = dip(16)
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = R.id.recylerVieww
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(activity)
                    }.lparams {
                        below(R.id.search)
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
            }
        }.view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = ListTeamPresenter(this)
        presenter.getTeams()

        mAdapter = ListTeamAdapter(team) {
            activity?.startActivity<DetailTeamActivity>(Util.DETAIL_TRANSACTION to it)
        }
        listTeam.adapter = mAdapter


        swipeRefresh.onRefresh {
            presenter.getTeams()
        }

    }


}