package com.example.ligaappapi.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ligaappapi.R
import com.example.ligaappapi.model.Standing
import kotlinx.android.synthetic.main.list_standing_item.view.*

class StandingAdapter(private val standing: List<Standing>, val context: Context?)
    : RecyclerView.Adapter<StandingViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingViewHolder {
        return StandingViewHolder(LayoutInflater.from(context).inflate(R.layout.list_standing_item, parent, false))
    }

    override fun getItemCount() = standing.size

    override fun onBindViewHolder(holder: StandingViewHolder, position: Int) {
        holder.bindItem(standing[position])
    }

}

class StandingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(standing: Standing){
        itemView.name.text = standing.name
        itemView.played.text = standing.played
        itemView.goalsFor.text = standing.goalsfor
        itemView.goalsAgainst.text = standing.goalsagainst
        itemView.goalsDiff.text = standing.goalsdifference
        itemView.win.text = standing.win
        itemView.draw.text = standing.draw
        itemView.loss.text = standing.loss
        itemView.total.text = standing.total
    }
}
