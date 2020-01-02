package com.silso.science_quiz.Ui.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.silso.science_quiz.R
import com.silso.science_quiz.Ui.fragment.Question
import com.silso.science_quiz.Ui.fragment.Solution
import com.silso.science_quiz.data.TestData
import com.silso.science_quiz.util.SendAnswer

class TestActivity : AppCompatActivity(), SendAnswer {
    lateinit var questFragObj: Question
    lateinit var soluFragObj: Solution
    lateinit var data: TestData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        recieveData()
    }

    fun recieveData(){
        data = TestData("다음 중 1인 것을 고르시오", arrayOf("Первый", "Uno", "first", "첫번째"), 2)
        setBundle()
    }

    fun setBundle(){
        val bundle = Bundle()
        bundle.putString("quest", data.quest)

        questFragObj = Question()
        questFragObj.arguments = bundle

        val bundle1 = Bundle().apply {
            putStringArray("solution", data.btns)
            putInt("key", data.key)
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
        Snackbar.make(window.decorView.rootView, "띄움", 1000).apply {
            if(check) view.setBackgroundColor(Color.parseColor("#2DB400"))
            else view.setBackgroundColor(Color.parseColor("#EB4460"))
        }.show()
        recieveData()
    }
}
