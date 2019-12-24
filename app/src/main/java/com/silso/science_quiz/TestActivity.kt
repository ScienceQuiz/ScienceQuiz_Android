package com.silso.science_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragment, Question())
            .commit()
    }
}
