package com.example.ligaappapi.view.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ligaappapi.R
import com.example.ligaappapi.adapter.ViewPagerAdapter
import com.example.ligaappapi.view.favoriteMatch.FavoriteMatchFragment
import com.example.ligaappapi.view.favoriteTeam.FavoriteTeamFragment
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = getString(R.string.favorites)
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.populateFragment(FavoriteMatchFragment(), getString(R.string.matchs))
        adapter.populateFragment(FavoriteTeamFragment(), getString(R.string.teams))
        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)
    }


}
