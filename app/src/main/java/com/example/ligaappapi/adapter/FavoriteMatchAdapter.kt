package com.example.ligaappapi.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ligaappapi.R
import com.example.ligaappapi.db.FavoriteMatch
import kotlinx.android.synthetic.main.list_match_item.view.*

class FavoriteMatchAdapter(private val favorite: List<FavoriteMatch>, private val context:Context?, private val listener: (FavoriteMatch) -> Unit)
    : RecyclerView.Adapter<FavoriteMatchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(LayoutInflater.from(context).inflate(R.layout.list_match_item, parent, false))
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteMatchViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

}

class FavoriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(favorite: FavoriteMatch, listener: (FavoriteMatch) -> Unit){
        itemView.homeNameTv.text = favorite.homeName
        itemView.awayNameTv.text = favorite.awayName
        itemView.homeScoreTv.text = favorite.homeScore
        itemView.awayScoreTv.text = favorite.awayScore
        itemView.dateScheduleTv.text = favorite.dateEvent
        itemView.setOnClickListener { listener(favorite) }
    }
}
