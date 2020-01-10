package com.example.ligaappapi.view.leagues

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ligaappapi.R
import com.example.ligaappapi.model.League
import com.example.ligaappapi.view.detailLeague.LeagueDetailActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class LeaguesAdapter (private val leagues: List<League>)
    : RecyclerView.Adapter<LeagueViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(LeagueUI().createView(AnkoContext.Companion.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(leagues[position])
    }
}

class LeagueUI : AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

                imageView{
                    id = R.id.league_Badge
                }.lparams{
                    height = dip(150)
                    width = matchParent
                }

                textView{
                    id = R.id.league_Name
                    textSize = 16f
                    this.gravity = Gravity.CENTER
                }.lparams {
                    margin = dip(15)
                }
            }
        }
    }
}

class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val leagueBadge: ImageView = view.find(R.id.league_Badge)
    private val leagueName: TextView = view.find(R.id.league_Name)

    fun bindItem(league: League){
        if(league.leagueBadge == null){
            leagueBadge.setImageResource(R.drawable.ic_image_black_24dp)
        }else {
            Picasso.get().load(league.leagueBadge).fit().into(leagueBadge)
        }
        leagueName.text = league.leagueName
        itemView.setOnClickListener { itemView.context.startActivity<LeagueDetailActivity>(LeagueDetailActivity.EXTRA_LEAGUE to league) }
    }
}