package com.silso.science_quiz.Ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.silso.science_quiz.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_quiz_btn.setOnClickListener {
            startActivity<TestActivity>()
        }
        rank_school_image.setOnClickListener {
            startActivity<RankActivity>()
        }
    }
}
