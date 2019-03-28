package com.example.ridwan.submissionii

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ridwan.submissionii.DB.Favorite
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavoriteAdapter(private val context: Context, private val favorite: List<Favorite>, private val listener: (Favorite)->Unit):
    RecyclerView.Adapter<FavoriteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.list_match, parent, false))

    override fun getItemCount() = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }
}

class FavoriteViewHolder(val view: View):RecyclerView.ViewHolder(view) {

    private val home = view.findViewById<TextView>(R.id.first_team)
    private val away = view.findViewById<TextView>(R.id.second_team)
    private val date = view.findViewById<TextView>(R.id.schedule_match)
    private val score = view.findViewById<TextView>(R.id.divider)

    fun bindItem(match : Favorite,listener: (Favorite) -> Unit){
        home.text = match.homeName
        away.text = match.awayName
        date.text = match.date
        if(match.homeScore !== null)
            score.text = match.homeScore + " VS " + match.awayScore
        else
            score.text = "VS"
        itemView.onClick { listener(match) }
    }
}
