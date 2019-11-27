package com.example.ligaappapi.view.Leagues


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.LinearLayout
import android.widget.ProgressBar

import com.example.ligaappapi.R
import com.example.ligaappapi.api.ApiRepository
import com.example.ligaappapi.model.League
import com.example.ligaappapi.util.invisible
import com.example.ligaappapi.util.visible
import com.example.ligaappapi.view.SearchMatches.SearchMatchActivity
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * A simple [Fragment] subclass.
 */
class LeaguesFragment : Fragment(), AnkoComponent<Context>, LeaguesView {
    private lateinit var listLeague: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var presenter: LeaguesPresenter
    private lateinit var adapter: LeaguesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.Companion.create(requireContext()))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        activity!!.setTitle(getString(R.string.leagues))
        adapter = LeaguesAdapter(leagues)
        listLeague.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = LeaguesPresenter(this, request, gson)
        presenter.getLeagueList()

        swipeRefresh.onRefresh {
            presenter.getLeagueList()
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listLeague = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = GridLayoutManager(context, 2)
                    }

                    progressBar = progressBar {}.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLeagueList(data: List<League>) {
        swipeRefresh.isRefreshing = false
        leagues.clear()
        leagues.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView?
        searchView?.queryHint = getString(R.string.search_matches)

        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                context?.startActivity<SearchMatchActivity>("query" to query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }


}
