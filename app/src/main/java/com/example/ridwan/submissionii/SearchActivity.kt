package com.example.ridwan.submissionii

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.example.ridwan.submissionii.Api.ApiRepository
import com.example.ridwan.submissionii.Model.Match
import com.example.ridwan.submissionii.Presenter.MatchPresenter
import com.example.ridwan.submissionii.R.id.menu_search
import com.example.ridwan.submissionii.R.menu.search_bar
import com.example.ridwan.submissionii.View.LastMatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.intentFor

class SearchActivity : AppCompatActivity(), LastMatchView{

    private lateinit var adapter: RecyclerViewAdapter
    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.title = "Search Result"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = RecyclerViewAdapter(this, matches) {
            startActivity(
                intentFor<DetailMatchActivity>(
                    "eventId" to it.eventId,
                    "homeId" to it.homeId, "awayId" to it.awayId
                )
            )
        }

        list_search_match.layoutManager = LinearLayoutManager(this)
        list_search_match.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this,request,gson)
        val extra = intent.extras
        presenter.searchMatch(extra.getString("query"))

    }

    override fun showMatchList(data: List<Match>) {
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(search_bar,menu)
        val menu_search = menu?.findItem(menu_search)
        if(menu_search != null){
            val searchView = menu_search.actionView as SearchView
            val editext = searchView.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
            editext.hint = "Find Club..."

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(query: String?): Boolean {
                    if(query!!.isNotEmpty()){
                        presenter.searchMatch(query)
                    }

                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }
            })

            searchView?.setOnCloseListener(object: SearchView.OnCloseListener{
                override fun onClose(): Boolean {
                    finish()
                    return true
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }
}
