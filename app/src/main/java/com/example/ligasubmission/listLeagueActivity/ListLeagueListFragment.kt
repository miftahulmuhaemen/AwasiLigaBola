package com.example.ligasubmission.listLeagueActivity


import android.os.Bundle
import android.support.v4.app.Fragment
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
import com.example.ligasubmission.listDetailMatchActivity.ListDetailMatchActivity
import com.example.ligasubmission.model.Event
import com.example.ligasubmission.util.DatabaseConst.TABLE_FAVORITE_NEXT
import com.example.ligasubmission.util.DatabaseConst.TABLE_FAVORITE_PAST
import com.example.ligasubmission.util.EspressoIdlingResource
import com.example.ligasubmission.util.Util.DETIL_TRANSACTION
import com.example.ligasubmission.util.Util.LIST_LEAGUE_FRAGS
import com.example.ligasubmission.util.Util.LIST_LEAGUE_ID
import com.example.ligasubmission.util.Util.LIST_LEAGUE_NEXT_MATCH
import com.example.ligasubmission.util.Util.LIST_LEAGUE_PREV_MATCH
import com.example.ligasubmission.util.invisible
import com.example.ligasubmission.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onQueryTextListener
import org.jetbrains.anko.support.v4.UI

class ListLeagueListFragment : Fragment(), ListLeagueView {

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPastLeague(data: List<Event>?) {
        if (!EspressoIdlingResource.idlingresource.isIdleNow)
            EspressoIdlingResource.decrement()

        pastEvent.clear()
        data?.let { pastEvent.addAll(it) }
        mAdapter.notifyDataSetChanged()
        if (mAdapter.itemCount == 0) blank.visible() else blank.invisible()
    }

    override fun showNextLeague(data: List<Event>?) {
        if (!EspressoIdlingResource.idlingresource.isIdleNow)
            EspressoIdlingResource.decrement()

        nextEvent.clear()
        data?.let { nextEvent.addAll(it) }
        mAdapter.notifyDataSetChanged()
        if (mAdapter.itemCount == 0) blank.visible() else blank.invisible()
    }

    private lateinit var listTeam: RecyclerView
    private var pastEvent: MutableList<Event> = mutableListOf()
    private var nextEvent: MutableList<Event> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var mAdapter: ListLeagueActivityListAdapter
    private lateinit var presenter: ListLeaguePresenter
    private lateinit var search: SearchView
    private lateinit var blank: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return UI {
            relativeLayout {
                padding = dip(16)
                lparams(width = matchParent, height = wrapContent)
                search = searchView {
                    id = R.id.search
                }.lparams(width = matchParent, height = wrapContent)
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
        }.view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = ListLeaguePresenter(this, request, gson)

        EspressoIdlingResource.increment()

        val fragmentSwitch = arguments?.getString(LIST_LEAGUE_FRAGS)
        val idLeague = arguments?.getString(LIST_LEAGUE_ID)
        when (fragmentSwitch) {
            LIST_LEAGUE_PREV_MATCH -> {
                presenter.getPreviousMatch(idLeague)
                mAdapter = ListLeagueActivityListAdapter(pastEvent) {
                    activity?.startActivity<ListDetailMatchActivity>(
                        DETIL_TRANSACTION to it,
                        LIST_LEAGUE_FRAGS to TABLE_FAVORITE_PAST
                    )
                }
            }
            LIST_LEAGUE_NEXT_MATCH -> {
                presenter.getNextMatch(idLeague)
                mAdapter = ListLeagueActivityListAdapter(nextEvent) {
                    activity?.startActivity<ListDetailMatchActivity>(
                        DETIL_TRANSACTION to it,
                        LIST_LEAGUE_FRAGS to TABLE_FAVORITE_NEXT
                    )
                }
            }
        }

        search.onQueryTextListener {
            onQueryTextChange { query ->
                mAdapter.filter.filter(query)
                false
            }
        }

        listTeam.adapter = mAdapter
    }

}
