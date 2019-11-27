package com.example.ligaappapi.view.DetailMatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.example.ligaappapi.R
import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.model.Match
import com.example.ligaappapi.model.Team
import com.example.ligaappapi.view.SearchMatches.SearchMatchActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.startActivity

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {
    private lateinit var presenter: MatchDetailPresenter

    companion object{
        const val EXTRA_MATCH_DETAIL = "extra_match_detail"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        
        val match = intent.getParcelableExtra(EXTRA_MATCH_DETAIL) as Match
        supportActionBar?.title = match.strMatch
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        homeNameTv.text = match.homeTeam
        awayNameTv.text = match.awayTeam
        homeScoreTv.text = match.homeScore
        awayScoreTv.text = match.awayScore
        dateScheduleTv.text = match.dateMatch
        homeScorerTv.text = match.homeGoalDetail
        awayScorerTv.text = match.awayGoalDetails
        gkHomeTv.text = match.homeLineupGoalkeeper
        gkAwayTv.text = match.awayLineupGoalkeeper
        defHomeTv.text = match.homeLineupDefense
        defAwayTv.text = match.awayLineupDefense
        midHomeTv.text = match.homeLineupMidfield
        midAwayTv.text = match.awayLineupMidfield
        forHomeTv.text = match.homeLineupForward
        forAwayTv.text = match.awayLineupForward
        subHomeTv.text = match.homeLineupSubstitutes
        subAwayTv.text = match.awayLineupSubstitutes

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchDetailPresenter(this, request, gson)
        presenter.getTeamDetail(match.idHomeTeam, match.idAwayTeam)
    }

    override fun showTeamDetailList(homeTeam: List<Team>, awayTeam: List<Team>) {
        val homeTeamData = homeTeam.get(0)
        val awayTeamData = awayTeam.get(0)
        Picasso.get().load(homeTeamData.teamBadge).into(homeImg)
        Picasso.get().load(awayTeamData.teamBadge).into(awayImg)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView?
        searchView?.queryHint = "Search Matches"

        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                applicationContext?.startActivity<SearchMatchActivity>("query" to query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
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
