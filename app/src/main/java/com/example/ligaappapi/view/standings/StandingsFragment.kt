package com.example.ligaappapi.view.standings


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ligaappapi.R
import com.example.ligaappapi.adapter.StandingAdapter
import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.model.League
import com.example.ligaappapi.model.Standing
import com.example.ligaappapi.util.invisible
import com.example.ligaappapi.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_standings.*

/**
 * A simple [Fragment] subclass.
 */
class StandingsFragment : Fragment(), StandingsView {

    private lateinit var presenter: StandingsPresenter
    private lateinit var adapter: StandingAdapter
    private val standings: MutableList<Standing> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_standings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val league = arguments?.getParcelable("league") as League

        adapter = StandingAdapter(standings, context)
        rvStanding.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvStanding.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = StandingsPresenter(this, request, gson)
        presenter.getStandingList(league.leagueId)
    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        if (progressBar != null){
            progressBar.invisible()
        }
    }

    override fun showStandingsList(data: List<Standing>) {
        standings.clear()
        standings.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showEmptyMessage() {
        if (emptyMessage != null){
            emptyMessage.text = getString(R.string.empty_message)
        }
    }
}
