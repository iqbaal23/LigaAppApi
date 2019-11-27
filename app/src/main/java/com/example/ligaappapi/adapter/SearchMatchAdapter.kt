package com.example.ligaappapi.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ligaappapi.R
import com.example.ligaappapi.model.Match
import com.example.ligaappapi.view.DetailMatch.MatchDetailActivity
import kotlinx.android.synthetic.main.list_match_item.view.*
import org.jetbrains.anko.startActivity

class SearchMatchAdapter (private val matchs: List<Match>, val context: Context?)
    : RecyclerView.Adapter<SearchMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMatchViewHolder {
        return SearchMatchViewHolder(LayoutInflater.from(context).inflate(R.layout.list_match_item, parent, false))
    }

    override fun getItemCount() = matchs.size

    override fun onBindViewHolder(holder: SearchMatchViewHolder, position: Int) {
        holder.bindItem(matchs[position])
    }

}

class SearchMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(match: Match){
        if(match.awayScore == null){ match.homeScore = "-" }
        if(match.awayScore == null){ match.awayScore = "-" }
        itemView.homeNameTv.text = match.homeTeam
        itemView.awayNameTv.text = match.awayTeam
        itemView.homeScoreTv.text = match.homeScore
        itemView.awayScoreTv.text = match.awayScore
        itemView.dateScheduleTv.text = match.dateMatch
        itemView.setOnClickListener { itemView.context.startActivity<MatchDetailActivity>(MatchDetailActivity.EXTRA_MATCH_DETAIL to match) }
    }
}
