package com.example.ligaappapi.view.favoriteMatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ligaappapi.R
import com.example.ligaappapi.adapter.FavoriteMatchAdapter
import com.example.ligaappapi.db.FavoriteMatch
import com.example.ligaappapi.db.database
import com.example.ligaappapi.view.detailMatch.MatchDetailActivity
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMatchFragment : Fragment() {
    private var favorites: MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.setTitle(getString(R.string.favorites))
        adapter = FavoriteMatchAdapter(favorites, requireContext()){
            context?.startActivity<MatchDetailActivity>("idEvent" to it.eventId, "idHome" to it.homeId, "idAway" to it.awayId)
        }

        rvFootballMatch.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvFootballMatch.adapter = adapter
        showFavorite()

    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

}
