package com.example.ligaappapi.view.searchMatches

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.example.ligaappapi.R
import com.example.ligaappapi.adapter.SearchMatchAdapter
import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.model.Match
import com.example.ligaappapi.util.invisible
import com.example.ligaappapi.util.visible
import com.example.ligaappapi.view.detailMatch.MatchDetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.startActivity

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {
    private lateinit var presenter: SearchMatchPresenter
    private lateinit var adapter: SearchMatchAdapter
    private var matchs: MutableList<Match> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        val query = intent.getStringExtra("query")
        supportActionBar?.title = query
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = SearchMatchAdapter(matchs, applicationContext){
            applicationContext?.startActivity<MatchDetailActivity>("idEvent" to it.idMatch, "idHome" to it.idHomeTeam, "idAway" to it.idAwayTeam)
        }
        rvFootballSearch.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rvFootballSearch.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchMatchPresenter(this, request, gson)
        presenter.searchMatchList(query)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showSearchMatchList(data: List<Match>) {
        val dataFilter: List<Match> = data.filter { it.strSport == "Soccer" }
        if (dataFilter.isEmpty()){
            showEmptyMessage()
        }
        matchs.clear()
        matchs.addAll(dataFilter)
        adapter.notifyDataSetChanged()
    }

    override fun showEmptyMessage() {
        emptyMessage.text = getString(R.string.empty_message)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView?
        searchView?.queryHint = getString(R.string.search_matches)

        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.searchMatchList(newText)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
