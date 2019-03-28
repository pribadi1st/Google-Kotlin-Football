package com.example.ridwan.submissionii

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ridwan.submissionii.DB.FavoriteTeam
import com.example.ridwan.submissionii.R.id.team_badge
import com.example.ridwan.submissionii.R.id.team_name
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class FavoriteTeamAdapter(private val favorite: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit)
    : RecyclerView.Adapter<FavoriteTeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamViewHolder {
        return FavoriteTeamViewHolder(FavTeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size

}

class FavTeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout{
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = team_badge
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = team_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

            }
        }
    }
}

class FavoriteTeamViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val teamBadge: ImageView = view.find(team_badge)
    private val teamName: TextView = view.find(team_name)

    fun bindItem(favorite: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
        Picasso.get().load(favorite.teamBadge).into(teamBadge)
        teamName.text = favorite.teamName
        itemView.setOnClickListener { listener(favorite) }
    }
}