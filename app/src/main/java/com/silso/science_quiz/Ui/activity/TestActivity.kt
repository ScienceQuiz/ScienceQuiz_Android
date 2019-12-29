package com.silso.science_quiz.Ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.silso.science_quiz.R
import com.silso.science_quiz.Ui.fragment.Question
import com.silso.science_quiz.Ui.fragment.Solution

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.question_fragment,
                Question()
            )
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.btn_fragment,
                Solution()
            )
            .commit()
    }
}
