package com.example.ligasubmission.mainActivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.ProgressBar
import com.example.ligasubmission.listLeagueActivity.ListLeagueActivity
import com.example.ligasubmission.model.InitialLeague
import com.example.ligasubmission.util.Util.DETIL_TRANSACTION
import com.example.ligasubmission.util.invisible
import com.example.ligasubmission.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity(), MainView {


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<InitialLeague>) {
        leagues.clear()
        leagues.addAll(data)
        mAdapter.notifyDataSetChanged()
    }

    private var leagues: MutableList<InitialLeague> = mutableListOf()
    private lateinit var mAdapter: MainActivityListAdapter
    private lateinit var presenter: MainPresenter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAdapter = MainActivityListAdapter(leagues) {
            startActivity<ListLeagueActivity>(DETIL_TRANSACTION to it)
        }

        relativeLayout {
            padding = dip(16)
            lparams(width = matchParent, height = wrapContent)
            recyclerView {
                lparams(width = matchParent, height = wrapContent)
                layoutManager = GridLayoutManager(this@MainActivity, 2)
                adapter = mAdapter
            }

            progressBar = progressBar {
            }.lparams {
                centerHorizontally()
            }
        }

        presenter = MainPresenter(this)
        presenter.initData()

    }


}
