package com.example.ligaappapi.view.detailTeam

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.ligaappapi.R
import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.db.FavoriteTeam
import com.example.ligaappapi.db.database
import com.example.ligaappapi.model.Team
import com.example.ligaappapi.util.invisible
import com.example.ligaappapi.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {
    private lateinit var presenter: DetailTeamPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var teams: Team
    private lateinit var idTeam: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        idTeam = intent.getStringExtra("idTeam")
        favoriteState()

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailTeamPresenter(this, request, gson)
        presenter.getDetailTeam(idTeam)

    }

    override fun showDetailTeamList(team: List<Team>) {
        teams = team[0]

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = teams.teamName
        description.text = teams.teamDescription
        Picasso.get().load(teams.teamBadge).fit().into(imageTeam)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite){
                    removeFromFavorite()
                } else{
                    addToFavorite()
                }

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite(){
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to teams.teamId,
                    FavoriteTeam.TEAM_NAME to teams.teamName,
                    FavoriteTeam.TEAM_LEAGUE to teams.teamLeague,
                    FavoriteTeam.TEAM_BADGE to teams.teamBadge)
            }
            toast(getString(R.string.add_message))
        } catch(e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {idTeam})",
                    "idTeam" to idTeam)
            }
            toast(getString(R.string.remove_message))
        }catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                .whereArgs("TEAM_ID = {idTeam}",
                    "idTeam" to idTeam)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if(favorite.isNotEmpty()) isFavorite = true
        }
    }
}
