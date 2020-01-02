package com.silso.science_quiz.Ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.silso.science_quiz.R
import com.silso.science_quiz.Ui.fragment.Question
import com.silso.science_quiz.Ui.fragment.Solution
import com.silso.science_quiz.util.SendAnswer

class TestActivity : AppCompatActivity(), SendAnswer {
    lateinit var questFragObj: Question
    lateinit var soluFragObj: Solution

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        setBundle()
    }

    fun setBundle(){
        val bundle = Bundle()
        bundle.putString("quest", "문제여")

        questFragObj = Question()
        questFragObj.arguments = bundle

        val bundle1 = Bundle().apply {
            putStringArray("solution", arrayOf("one", "two", "three", "four"))
            putInt("key", 1)
        }

        soluFragObj = Solution()
        soluFragObj.arguments = bundle1
        startTransaction()
    }

    fun startTransaction(){
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.question_fragment,
                questFragObj
            )

            replace(
                R.id.btn_fragment,
                soluFragObj
            )
        }.commit()
    }

    override fun sendEvent(check: Boolean) {
        Log.e("Успешно", check.toString())
    }
}
