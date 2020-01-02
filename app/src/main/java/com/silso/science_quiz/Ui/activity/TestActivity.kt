package com.silso.science_quiz.Ui.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.silso.science_quiz.R
import com.silso.science_quiz.Ui.fragment.Question
import com.silso.science_quiz.Ui.fragment.Solution
import com.silso.science_quiz.data.Science
import com.silso.science_quiz.server.Retrofit
import com.silso.science_quiz.util.SendAnswer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity

class TestActivity : AppCompatActivity(), SendAnswer {
    private lateinit var questFragObj: Question
    private lateinit var soluFragObj: Solution
    private lateinit var data: Array<Science>
    private lateinit var btns: Array<String>
    private var count = 10
    private var correct = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        GlobalScope.launch(Dispatchers.IO) {
            val res = Retrofit().service.getSience()
            Log.d("retrofit", res.code().toString())
            if(res.isSuccessful) {
                data = res.body()!!.science
                turningTest()
            }
        }
    }

    //받은 데이터로 문제 표시
    private fun turningTest(){
        Log.e("count", count.toString())
        if(count == 0){
            startActivity<ResultActivity>(
                "correct" to correct,
                "count" to 10
            )
            finish()
        }else {
            count--
            data[10 - count].btns.apply {
                btns = arrayOf(b1, b2, b3, b4)
            }
            setBundle()
        }
    }

    private fun setBundle(){
        val bundle = Bundle()
        bundle.putString("quest", data[11 - count].question)

        questFragObj = Question()
        questFragObj.arguments = bundle

        val bundle1 = Bundle().apply {
            putStringArray("solution", btns)
            putInt("key", data[10 - count].key)
        }

        soluFragObj = Solution()
        soluFragObj.arguments = bundle1
        startTransaction()
    }

    private fun startTransaction(){
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
        var sText = "아쉽습니다...."
        var sColor = "#EB4460"

        if (check){
            sText = "정답"
            sColor = "#2DB400"
            correct += 1
        }

        Snackbar.make(window.decorView.rootView, sText, 1000).apply {
            view.setBackgroundColor(Color.parseColor(sColor))
        }.show()

        turningTest()
    }
}