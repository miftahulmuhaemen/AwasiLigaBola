package com.example.ligasubmission.detailTeam

import com.example.ligasubmission.model.Player

interface DetailTeamView {
    fun message(query: String)
    fun favoriteState(state: Boolean)
    fun showPlayers(data: List<Player>?)
}