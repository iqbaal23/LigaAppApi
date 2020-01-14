package com.example.ligaappapi.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ligaappapi.R
import com.example.ligaappapi.db.FavoriteTeam
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_team_item.view.*

class FavoriteTeamAdapter (private val favorite: List<FavoriteTeam>, private val context: Context?, private val listener: (FavoriteTeam) -> Unit)
    : RecyclerView.Adapter<FavoriteTeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamViewHolder {
        return FavoriteTeamViewHolder(LayoutInflater.from(context).inflate(R.layout.list_team_item, parent, false))
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

}

class FavoriteTeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(favorite: FavoriteTeam, listener: (FavoriteTeam) -> Unit){
        itemView.clubName.text = favorite.teamName
        itemView.leagueName.text = favorite.teamLeague
        Picasso.get().load(favorite.teamBadge).fit().into(itemView.teamBadge)
        itemView.setOnClickListener{ listener(favorite) }
    }
}
