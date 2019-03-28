package com.example.ridwan.submissionii

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ridwan.submissionii.Model.Player
import com.example.ridwan.submissionii.R.id.player_badge
import com.example.ridwan.submissionii.R.id.player_name
import com.example.ridwan.submissionii.R.id.player_position
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class ListPlayerAdapter(private val players: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position],listener)
    }

    override fun getItemCount(): Int = players.size

}

class PlayerUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.player_badge
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.player_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

                textView {
                    id = R.id.player_position
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }
            }
        }
    }

}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val playerBadge: ImageView = view.find(player_badge)
    private val playerName: TextView = view.find(player_name)
    private val playerPosition: TextView = view.find(player_position)

    fun bindItem(players: Player, listener: (Player) -> Unit) {
        Picasso.get().load(players.playerBadge).into(playerBadge)
        playerName.text = players.playerName
        playerPosition.text = players.playerPosition

        itemView.onClick {
            listener(players)
        }
    }
}