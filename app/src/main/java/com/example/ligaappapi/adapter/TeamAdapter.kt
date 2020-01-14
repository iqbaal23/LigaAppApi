package com.example.ligaappapi.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ligaappapi.R
import com.example.ligaappapi.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_team_item.view.*

class TeamAdapter(private val team: List<Team>, val context: Context?, private val listener: (Team) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.list_team_item, parent, false))
    }

    override fun getItemCount() = team.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(team[position], listener)
    }

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){
    fun bindItem(team: Team, listener: (Team) -> Unit){
        itemView.clubName.text = team.teamName
        itemView.leagueName.text = team.teamLeague
        Picasso.get().load(team.teamBadge).fit().into(itemView.teamBadge)
        itemView.setOnClickListener{ listener(team) }
    }
}