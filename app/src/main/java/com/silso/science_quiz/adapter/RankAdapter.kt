package com.silso.science_quiz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.silso.science_quiz.R
import com.silso.science_quiz.data.RankUser

class RankAdapter:RecyclerView.Adapter<RankAdapter.Holder>() {
    private val rankList = ArrayList<RankUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ranking, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = rankList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.connect(rankList[position])
    }
    fun add(data: RankUser){
        rankList.add(data)
        notifyDataSetChanged()
    }
    inner class Holder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val userNick =itemView.findViewById<TextView>(R.id.rank_nick)
        private val userScore = itemView.findViewById<TextView>(R.id.rank_score)

        fun connect(data: RankUser){
            userNick.text = data.userNick
            userScore.text = data.userScore
        }
    }
}