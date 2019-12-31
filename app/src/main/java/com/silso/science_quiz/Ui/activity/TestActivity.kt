package com.silso.science_quiz.Ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.silso.science_quiz.R
import com.silso.science_quiz.Ui.fragment.Question
import com.silso.science_quiz.Ui.fragment.Solution

class TestActivity : AppCompatActivity() {
    lateinit var questFragObj: Question
    lateinit var soluFragObj: Solution

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        setBundle()
        startTransaction()
    }

    fun setBundle(){
        val bundle = Bundle()
        bundle.putString("quest", "문제여")

        questFragObj = Question()
        questFragObj.arguments = bundle

        val bundle1 = Bundle()
        bundle1.putStringArray("solution", arrayOf("one", "two", "three", "four"))

        soluFragObj = Solution()
        soluFragObj.arguments = bundle1
    }

    fun startTransaction(){
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.question_fragment,
                questFragObj
            )
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.btn_fragment,
                soluFragObj
            )
            .commit()
    }
}