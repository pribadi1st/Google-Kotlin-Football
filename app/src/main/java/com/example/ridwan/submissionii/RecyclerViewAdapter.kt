package com.example.ridwan.submissionii

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ridwan.submissionii.Model.Match

class RecyclerViewAdapter(private val context:Context, private val matches: List<Match>,
                          private val listener: (Match) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_match,parent,false))

    override fun getItemCount() : Int{

        return matches.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(matches[position],listener)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val home = view.findViewById<TextView>(R.id.first_team)
        private val away = view.findViewById<TextView>(R.id.second_team)
        private val date = view.findViewById<TextView>(R.id.schedule_match)
        private val score = view.findViewById<TextView>(R.id.divider)

        fun bindItem(match : Match, listener: (Match) -> Unit) {
            home.text = match.home
            away.text = match.away
            
            date.text = match.eventDate
            if(match.homeScore !== null)
                score.text = match.homeScore + " VS " + match.awayScore
            else
                score.text = "VS"

            itemView.setOnClickListener{
                listener(match)
            }
        }
    }
}