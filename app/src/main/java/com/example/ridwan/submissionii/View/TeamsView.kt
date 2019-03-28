package com.example.ridwan.submissionii.View

import com.example.ridwan.submissionii.Model.Teams

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Teams>)
}