package com.example.ligaappapi.view.detailMatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.ligaappapi.R
import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.db.FavoriteMatch
import com.example.ligaappapi.db.database
import com.example.ligaappapi.model.Match
import com.example.ligaappapi.model.Team
import com.example.ligaappapi.util.invisible
import com.example.ligaappapi.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {
    private lateinit var presenter: MatchDetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var matchs: Match
    private lateinit var idEvent: String
    private lateinit var idHome: String
    private lateinit var idAway: String

    companion object{
        const val EXTRA_MATCH_DETAIL = "extra_match_detail"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        idEvent = intent.getStringExtra("idEvent")
        idHome = intent.getStringExtra("idHome")
        idAway = intent.getStringExtra("idAway")
        favoriteState()

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchDetailPresenter(this, request, gson)
        presenter.getMatchDetail(idEvent, idHome, idAway)

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetailList(match: List<Match>, homeTeam: List<Team>, awayTeam: List<Team>) {
        matchs = match[0]

        val matchData = match[0]
        val homeTeamData = homeTeam[0]
        val awayTeamData = awayTeam[0]

        supportActionBar?.title = matchData.strMatch

        homeNameTv.text = matchData.homeTeam
        awayNameTv.text = matchData.awayTeam
        homeScoreTv.text = matchData.homeScore
        awayScoreTv.text = matchData.awayScore
        dateScheduleTv.text = matchData.dateMatch
        homeScorerTv.text = matchData.homeGoalDetail
        awayScorerTv.text = matchData.awayGoalDetails
        gkHomeTv.text = matchData.homeLineupGoalkeeper
        gkAwayTv.text = matchData.awayLineupGoalkeeper
        defHomeTv.text = matchData.homeLineupDefense
        defAwayTv.text = matchData.awayLineupDefense
        midHomeTv.text = matchData.homeLineupMidfield
        midAwayTv.text = matchData.awayLineupMidfield
        forHomeTv.text = matchData.homeLineupForward
        forAwayTv.text = matchData.awayLineupForward
        subHomeTv.text = matchData.homeLineupSubstitutes
        subAwayTv.text = matchData.awayLineupSubstitutes
        Picasso.get().load(homeTeamData.teamBadge).into(homeImg)
        Picasso.get().load(awayTeamData.teamBadge).into(awayImg)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite){
                    removeFromFavorite()
                } else {
                    addToFavorite()
                }

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteMatch.TABLE_FAVORITE_MATCH,
                    FavoriteMatch.EVENT_ID to matchs.idMatch,
                    FavoriteMatch.HOME_ID to matchs.idHomeTeam,
                    FavoriteMatch.AWAY_ID to matchs.idAwayTeam,
                    FavoriteMatch.HOME_NAME to matchs.homeTeam,
                    FavoriteMatch.AWAY_NAME to matchs.awayTeam,
                    FavoriteMatch.HOME_SCORE to matchs.homeScore,
                    FavoriteMatch.AWAY_SCORE to matchs.awayScore,
                    FavoriteMatch.DATE_EVENT to matchs.dateMatch)
            }
            toast(getString(R.string.add_message))
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE_MATCH, "(EVENT_ID = {idEvent})",
                    "idEvent" to idEvent)
            }
            toast(getString(R.string.remove_message))
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                .whereArgs("EVENT_ID = {idEvent}",
                    "idEvent" to idEvent)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
