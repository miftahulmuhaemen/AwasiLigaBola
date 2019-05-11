package com.example.ligasubmission.ListLeagueActivity


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.ligasubmission.API.ApiRepository
import com.example.ligasubmission.LeagueDetailActivity.LeagueDetailActivity
import com.example.ligasubmission.ListDetailMatchActivity.ListDetailMatchActivity
import com.example.ligasubmission.Model.Event
import com.example.ligasubmission.Util.Util.Companion.DETIL_TRANSACTION
import com.example.ligasubmission.Util.Util.Companion.LIST_LEAGUE_FRAGS
import com.example.ligasubmission.Util.Util.Companion.LIST_LEAGUE_ID
import com.example.ligasubmission.Util.Util.Companion.LIST_LEAGUE_NEXT_MATCH
import com.example.ligasubmission.Util.Util.Companion.LIST_LEAGUE_PREV_MATCH
import com.example.ligasubmission.Util.invisible
import com.example.ligasubmission.Util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI

class ListLeagueListFragment : Fragment(), ListLeagueView {


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPastLeague(data: List<Event>?) {
        pastEvent.clear()
        data?.let { pastEvent.addAll(it) }
        mAdapter.notifyDataSetChanged()
    }

    override fun showNextLeague(data: List<Event>?) {
        nextEvent.clear()
        data?.let { nextEvent.addAll(it) }
        mAdapter.notifyDataSetChanged()
    }

    private lateinit var listTeam : RecyclerView
    private var pastEvent: MutableList<Event> = mutableListOf()
    private var nextEvent: MutableList<Event> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var mAdapter: ListLeagueActivityListAdapter
    private lateinit var presenter: ListLeaguePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return UI {
            relativeLayout{
                padding = dip(16)
                lparams (width = matchParent, height = wrapContent)
                listTeam = recyclerView{
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(activity)
                }
                progressBar = progressBar {
                }.lparams{
                    centerHorizontally()
                }
            }
        }.view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = ListLeaguePresenter(this, request, gson)

        val fragmentSwitch = arguments?.getString(LIST_LEAGUE_FRAGS)
        val idLeague = arguments?.getString(LIST_LEAGUE_ID)
        when(fragmentSwitch){
            LIST_LEAGUE_PREV_MATCH ->{
                presenter.getPreviousMatch(idLeague)
                mAdapter = ListLeagueActivityListAdapter(pastEvent) {
                    activity?.startActivity<ListDetailMatchActivity>(DETIL_TRANSACTION to it)
                }
            }
            LIST_LEAGUE_NEXT_MATCH -> {
                presenter.getNextMatch(idLeague)
                mAdapter = ListLeagueActivityListAdapter(nextEvent) {
                    activity?.startActivity<ListDetailMatchActivity>(DETIL_TRANSACTION to it)
                }
            }
        }

        listTeam.adapter = mAdapter
    }

}
