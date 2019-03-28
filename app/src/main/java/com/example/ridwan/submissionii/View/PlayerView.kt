package com.example.ridwan.submissionii.View

import com.example.ridwan.submissionii.Model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Player>)
}