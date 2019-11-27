package com.example.ligaappapi.view.detailLeague

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ligaappapi.R
import com.example.ligaappapi.adapter.ViewPagerAdapter
import com.example.ligaappapi.model.League
import com.example.ligaappapi.view.LastMatch.LastMatchFragment
import com.example.ligaappapi.view.NextMatch.NextMatchFragment
import com.example.ligaappapi.view.SearchMatches.SearchMatchActivity
import kotlinx.android.synthetic.main.activity_detail_league.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.wrapContent

class LeagueDetailActivity : AppCompatActivity(){
    companion object{
        const val EXTRA_LEAGUE = "extra_league"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)
        setSupportActionBar(toolbar)

        val league = intent.getParcelableExtra(EXTRA_LEAGUE) as League

        val bundle = Bundle()
        bundle.putParcelable("league", league)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        val lastMatchFragment = LastMatchFragment()
        val nextMatchFragment = NextMatchFragment()
        lastMatchFragment.arguments = bundle
        nextMatchFragment.arguments = bundle
        adapter.populateFragment(lastMatchFragment, getString(R.string.last_match))
        adapter.populateFragment(nextMatchFragment, getString(R.string.next_match))
        viewpagerLeague.adapter = adapter
        tabs.setupWithViewPager(viewpagerLeague)

        supportActionBar?.title = league.leagueName
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_layout.setExpandedTitleColor(Color.GRAY)

        if(league.leagueBadge == null){
            imageLeague.setImageResource(R.drawable.ic_image_black_24dp)
        }else{
            Glide.with(applicationContext)
                .load(league.leagueBadge)
                .apply(RequestOptions().override(wrapContent, wrapContent))
                .into(imageLeague)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView?
        searchView?.queryHint = getString(R.string.search_matches)

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