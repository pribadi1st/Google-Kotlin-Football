package com.example.ridwan.submissionii

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.example.ridwan.submissionii.Api.ApiRepository
import com.example.ridwan.submissionii.DB.Favorite
import com.example.ridwan.submissionii.DB.database
import com.example.ridwan.submissionii.Model.DetailMatch
import com.example.ridwan.submissionii.Model.DetailTeam
import com.example.ridwan.submissionii.Presenter.DetailMatchPresenter
import com.example.ridwan.submissionii.R.drawable.ic_add_to_favorite
import com.example.ridwan.submissionii.R.drawable.ic_added_to_favorite
import com.example.ridwan.submissionii.View.DetailMatchView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

import kotlinx.android.synthetic.main.activity_detail_match.*
import com.example.ridwan.submissionii.R.menu.detail_menu
import com.example.ridwan.submissionii.R.id.add_to_favorite
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.design.snackbar
import java.lang.Exception

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {

    private lateinit var presenter : DetailMatchPresenter
    private var detailMatch: ArrayList<DetailMatch> = ArrayList()
    private var homeTeam: ArrayList<DetailTeam> = ArrayList()
    private var awayTeam: ArrayList<DetailTeam> = ArrayList()
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var eventId: String
    private lateinit var homeId : String
    private lateinit var awayId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailMatchPresenter(this, request, gson)

        val extra = intent.extras
        eventId = extra.getString("eventId")
        homeId = extra.getString("homeId")
        awayId = extra.getString("awayId")
        favoriteState()

        presenter.getMatchDetail(eventId,homeId,awayId)
    }

    override fun showMatchList(data: ArrayList<DetailMatch>, data2: ArrayList<DetailTeam>, data3 : ArrayList<DetailTeam>) {
        detailMatch.clear()
        detailMatch.addAll(data)
        homeTeam.clear()
        homeTeam.addAll(data2)
        awayTeam.clear()
        awayTeam.addAll(data3)
        detail_match_date.text = detailMatch[0].date
        homegoals.text = detailMatch[0].homeGoals?.replace(";","\n")
        awaygoals.text = detailMatch[0].awayGoals?.replace(";","\n")
        home_shot.text = detailMatch[0].homeShots
        away_shot.text = detailMatch[0].awayShots
        home_GK.text = detailMatch[0].homeGK?.replace(";","\n")
        away_GK.text = detailMatch[0].awayGK?.replace(";","\n")
        home_DF.text = detailMatch[0].homeDF?.replace(";","\n")
        away_DF.text = detailMatch[0].awayDF?.replace(";","\n")
        home_MF.text = detailMatch[0].homeMF?.replace(";","\n")
        away_MF.text = detailMatch[0].awayMF?.replace(";","\n")
        home_FW.text = detailMatch[0].homeFW?.replace(";","\n")
        away_FW.text = detailMatch[0].awayFW?.replace(";","\n")
        home_sub.text = detailMatch[0].homeSB?.replace(";","\n")
        away_sub.text = detailMatch[0].awaySB?.replace(";","\n")

        Picasso.get().load(homeTeam[0].badge).resize(300,300).into(home_badge)
        Picasso.get().load(awayTeam[0].badge).resize(300,300).into(away_badge)
//        home_badge.image?.let { Picasso.get().load(homeTeam[0].badge).resize(300,300).into(image) }
    }

    private fun addToFavorite(){
        try{
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to eventId,
                    Favorite.HOME_ID to homeId,
                    Favorite.AWAY_ID to awayId,
                    Favorite.HOME_SCORE to detailMatch[0].homeScore,
                    Favorite.AWAY_SCORE to detailMatch[0].awayScore,
                    Favorite.HOME_NAME to homeTeam[0].name,
                    Favorite.AWAY_NAME to awayTeam[0].name,
                    Favorite.DATE to detailMatch[0].date
                )
            }
            snackbar(view,"Added to Favourite").show()
        }catch (e:Exception){
            println("ERROR nya : "+e.toString())
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                    "id" to eventId)
            }
            snackbar(view, "Removed from favourite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(view, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorite)
            println("SET TO FAVORITE")
        }
        else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorite)
            println("UNSET TO FAVORITE")
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(EVENT_ID = {id})",
                    "id" to eventId)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
            println("ISFAVORITE "+ isFavorite.toString())
            setFavorite()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu,menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }

            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                println(isFavorite.toString() + " FAVORITE")
                isFavorite = !isFavorite
                println(isFavorite.toString() + " FAVORITE")
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
