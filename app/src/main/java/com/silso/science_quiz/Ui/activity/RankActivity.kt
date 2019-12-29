package com.silso.science_quiz.Ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.silso.science_quiz.R
import com.silso.science_quiz.adapter.RankAdapter
import com.silso.science_quiz.data.RankUser
import kotlinx.android.synthetic.main.activity_rank.*

class RankActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)

        val rankAdapter = RankAdapter()
        rank_list.adapter = rankAdapter

        val lm =LinearLayoutManager(applicationContext)
        rank_list.layoutManager = lm

        rankAdapter.add(
            RankUser(
                "tjrwns8024",
                "211100"
            )
        )
        rankAdapter.add(
            RankUser(
                "tjrwns8024",
                "50213"
            )
        )
        rankAdapter.add(
            RankUser(
                "fsdadfas",
                "211100"
            )
        )
        rankAdapter.add(
            RankUser(
                "tjrwns8024",
                "211100"
            )
        )
        rankAdapter.add(
            RankUser(
                "tjr02asfasd4",
                "211100"
            )
        )
        rankAdapter.add(
            RankUser(
                "tjrwns8024",
                "211100"
            )
        )
        rankAdapter.add(
            RankUser(
                "tjrasdfas4",
                "211100"
            )
        )
        rankAdapter.add(RankUser("3165", "211100"))
        rankAdapter.add(
            RankUser(
                "tjrwns8024",
                "211100"
            )
        )

    }
}
