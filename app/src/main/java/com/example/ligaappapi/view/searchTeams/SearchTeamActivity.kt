package com.example.ligaappapi.view.searchTeams

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.example.ligaappapi.R
import com.example.ligaappapi.adapter.TeamAdapter
import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.model.Team
import com.example.ligaappapi.util.invisible
import com.example.ligaappapi.util.visible
import com.example.ligaappapi.view.detailTeam.DetailTeamActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_team.*
import org.jetbrains.anko.startActivity

class SearchTeamActivity : AppCompatActivity(), SearchTeamView {
    private lateinit var presenter: SearchTeamPresenter
    private lateinit var adapter: TeamAdapter
    private var teams: MutableList<Team> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        supportActionBar?.title = getString(R.string.search_team)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = TeamAdapter(teams, applicationContext){
            applicationContext?.startActivity<DetailTeamActivity>("idTeam" to it.teamId)
        }

        rvTeamSearch.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rvTeamSearch.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchTeamPresenter(this, request, gson)
        presenter.searchTeamList("")
    }

    override fun showSearchTeamList(data: List<Team>) {
        val dataFilter: List<Team> = data.filter { it.strSport == "Soccer" }
        if (dataFilter.isEmpty()){
            showEmptyMessage()
        }
        teams.clear()
        teams.addAll(dataFilter)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEmptyMessage() {
        emptyMessage.text = getString(R.string.empty_message)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_team)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.searchTeamList(newText)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
