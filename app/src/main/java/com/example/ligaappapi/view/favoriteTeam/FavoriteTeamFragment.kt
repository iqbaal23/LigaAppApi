package com.example.ligaappapi.view.favoriteTeam


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ligaappapi.R
import com.example.ligaappapi.adapter.FavoriteTeamAdapter
import com.example.ligaappapi.db.FavoriteTeam
import com.example.ligaappapi.db.database
import com.example.ligaappapi.view.detailTeam.DetailTeamActivity
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment : Fragment() {
    private var favorites: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoriteTeamAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavoriteTeamAdapter(favorites, requireContext()){
            context?.startActivity<DetailTeamActivity>("idTeam" to it.teamId)
        }
        rvTeam.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvTeam.adapter = adapter
        showFavorite()
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

}
