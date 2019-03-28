package com.example.ridwan.submissionii

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.example.ridwan.submissionii.Api.ApiRepository
import com.example.ridwan.submissionii.DB.FavoriteTeam
import com.example.ridwan.submissionii.DB.database
import com.example.ridwan.submissionii.Fragment.DescriptionFragment
import com.example.ridwan.submissionii.Fragment.ViewPagerTeamAdapter
import com.example.ridwan.submissionii.Model.DetailTeam
import com.example.ridwan.submissionii.Presenter.DetailTeamPresenter
import com.example.ridwan.submissionii.R.id.add_to_favorite
import com.example.ridwan.submissionii.R.menu.detail_menu
import com.example.ridwan.submissionii.View.DetailTeamView
import com.example.ridwan.submissionii.R.drawable.ic_add_to_favorite
import com.example.ridwan.submissionii.R.drawable.ic_added_to_favorite
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.fragment_description.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import java.lang.Exception

class TeamDetailActivity : AppCompatActivity(),DetailTeamView {

    private lateinit var presenter : DetailTeamPresenter
    private lateinit var teamId : String
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var teams : ArrayList<DetailTeam> = ArrayList()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu,menu)
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
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extra = intent.extras
        teamId= extra.getString("id")

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailTeamPresenter(this,request,gson)

        presenter.getTeamDetail(teamId)

        view_pager_team.adapter = ViewPagerTeamAdapter(supportFragmentManager, teamId)
        tabLayout.setupWithViewPager(view_pager_team)
    }

    override fun showDetailTeam(data: ArrayList<DetailTeam>) {
        teams.clear()
        teams.addAll(data)
        Picasso.get().load(teams[0].badge).resize(300,300).into(team_logo)
        team_name.text = teams[0].name
        team_founded.text = teams[0].formed
        team_alias.text = teams[0].stadium
        description_text.text= teams[0].description
    }

    private fun addToFavorite(){
        try{
            database.use {
                insert(
                    FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to teamId,
                    FavoriteTeam.TEAM_NAME to teams[0].name,
                    FavoriteTeam.TEAM_BADGE to teams[0].badge
                )
            }
            snackbar(view_team,"Added to Favourite").show()
        }catch (e : Exception){
            println(e.toString()+ " ERROR FAVORITE TEAM")
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorite)
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                    "id" to teamId)
            }
            snackbar(view_team, "Removed from favourite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(view_team,e.localizedMessage).show()
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to teamId)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

}
