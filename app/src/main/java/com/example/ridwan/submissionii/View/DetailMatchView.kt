package com.example.ridwan.submissionii.View

import com.example.ridwan.submissionii.Model.DetailTeam
import com.example.ridwan.submissionii.Model.DetailMatch

interface DetailMatchView{
    fun showMatchList(data : ArrayList<DetailMatch>, data2 : ArrayList<DetailTeam>, data3: ArrayList<DetailTeam>)
}