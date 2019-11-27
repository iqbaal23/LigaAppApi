package com.example.ligaappapi.view.LastMatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ligaappapi.R
import com.example.ligaappapi.adapter.MatchAdapter
import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.model.League
import com.example.ligaappapi.model.Match
import com.example.ligaappapi.util.invisible
import com.example.ligaappapi.util.visible
import com.example.ligaappapi.view.DetailMatch.MatchDetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_last_match.*
import org.jetbrains.anko.startActivity

class LastMatchFragment : Fragment(), LastMatchView {
    private lateinit var presenter: LastMatchPresenter
    private lateinit var adapter: MatchAdapter
    private var matchs: MutableList<Match> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val league = arguments?.getParcelable("league") as League

        adapter = MatchAdapter(matchs, context){
            context?.startActivity<MatchDetailActivity>("idEvent" to it.idMatch, "idHome" to it.idHomeTeam, "idAway" to it.idAwayTeam)
        }
        rvFootballLast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvFootballLast.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = LastMatchPresenter(this, request, gson)
        presenter.getLastMatchList(league.leagueId)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLastMatchList(data: List<Match>) {
        matchs.clear()
        matchs.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
