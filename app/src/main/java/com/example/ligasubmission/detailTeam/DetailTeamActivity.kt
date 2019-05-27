package com.example.ligasubmission.detailTeam

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.example.ligasubmission.R
import com.example.ligasubmission.api.ApiRepository
import com.example.ligasubmission.detailPlayer.DetailPlayerActivity
import com.example.ligasubmission.model.Event
import com.example.ligasubmission.model.Player
import com.example.ligasubmission.model.Team
import com.example.ligasubmission.util.AnkoLayoutConst.Margin_16dp
import com.example.ligasubmission.util.Util
import com.example.ligasubmission.util.Util.DETAIL_TRANSACTION
import com.example.ligasubmission.util.invisible
import com.example.ligasubmission.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {

    override fun showPlayers(data: List<Player>?) {
        player.clear()
        data?.let { player.addAll(it) }
        mAdapter.notifyDataSetChanged()
    }

    override fun favoriteState(state: Boolean) {
        isFavorite = !state
    }

    override fun message(query: String) {
        toast(query)
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite)
                    presenter.removeFromFavorite()
                else
                    presenter.addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private lateinit var listTeam: RecyclerView
    private lateinit var presenter: DetailTeamPresenter
    private lateinit var mAdapter: DetailTeamAdapter
    private var player: MutableList<Player> = mutableListOf()
    private lateinit var team: Team
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        team = intent.getParcelableExtra(DETAIL_TRANSACTION)

        presenter = DetailTeamPresenter(this, this, team)
        presenter.favoriteState()
        presenter.getPlayers()

        mAdapter = DetailTeamAdapter(player) {
            startActivity<DetailPlayerActivity>(DETAIL_TRANSACTION to it)
        }

        scrollView {
            verticalLayout {
                padding = dip(Margin_16dp)
                Picasso.get().load(team.strTeamBadge).into(imageView().lparams{
                    width = matchParent
                    height = dip (100)
                })

                textView {
                    text = team.strTeam + " / " + team.strAlternate
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }

                textView {
                    text = "Formed : " + team.intFormedYear
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }

                textView {
                    text = "Manager : " + team.strManager
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }

                textView {
                    text = team.strStadium + " / " + team.strStadiumLocation + " / " + team.intStadiumCapacity + " people"
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }

                textView{
                    text = "Stadium Description"
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }

                textView(team.strStadiumDescription)

                textView{
                    text = "Team Description"
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }

                textView(team.strDescriptionEN)

                textView{
                    text = "Players"
                    setTypeface(typeface, Typeface.BOLD_ITALIC)
                }

                listTeam = recyclerView {
                    id = R.id.recylerVieww
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(this@DetailTeamActivity, LinearLayoutManager.HORIZONTAL, false)
                    adapter = mAdapter
                }
            }
        }

    }
}
