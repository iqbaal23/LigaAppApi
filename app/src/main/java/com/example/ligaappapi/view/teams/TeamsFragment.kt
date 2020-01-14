package com.example.ligaappapi.view.teams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ligaappapi.R
import com.example.ligaappapi.adapter.TeamAdapter
import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.model.League
import com.example.ligaappapi.model.Team
import com.example.ligaappapi.util.invisible
import com.example.ligaappapi.util.visible
import com.example.ligaappapi.view.detailTeam.DetailTeamActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 */
class TeamsFragment : Fragment(), TeamsView {
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamAdapter
    private val teams: MutableList<Team> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val league = arguments?.getParcelable("league") as League

        adapter = TeamAdapter(teams, context){
            context?.startActivity<DetailTeamActivity>("idTeam" to it.teamId)
        }
        rvTeam.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)
        presenter.getTeamList(league.leagueId)
    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        if (progressBar != null){
            progressBar.invisible()
        }
    }

    override fun showLeagueList(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showEmptyMessage() {
        if (emptyMessage != null){
            emptyMessage.text = getString(R.string.empty_message)
        }
    }

}
