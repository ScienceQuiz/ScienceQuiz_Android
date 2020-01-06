package com.silso.science_quiz.Ui.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.silso.science_quiz.R
import com.silso.science_quiz.Ui.fragment.Question
import com.silso.science_quiz.Ui.fragment.Solution
import com.silso.science_quiz.data.GetQusetion
import com.silso.science_quiz.data.Science
import com.silso.science_quiz.model.SignIn
import com.silso.science_quiz.server.Retrofit
import com.silso.science_quiz.util.SendAnswer
import com.silso.science_quiz.util.UtilClass
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class TestActivity : AppCompatActivity(), SendAnswer {
    private lateinit var questFragObj: Question
    private lateinit var soluFragObj: Solution
    private lateinit var data: Array<Science>
    private lateinit var btns: Array<String>
    private var count = 9
    private var correct = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val call: Call<GetQusetion> = Retrofit().service.getScience()

        call.enqueue(object : Callback<GetQusetion> {
            override fun onFailure(call: Call<GetQusetion>, t: Throwable) {
                Log.e("ddd", t.message.toString())
            }

            override fun onResponse(call: Call<GetQusetion>, response: Response<GetQusetion>) {
                data = response.body()?.science!!
                turningTest()
            }
        })
    }

    //받은 데이터로 문제 표시
    private fun turningTest(){
        Log.e("count", count.toString())
        if(count == -1){
            startActivity<ResultActivity>(
                "correct" to correct,
                "count" to 10
            )
            finish()
        }else {
            corrctCount.text = correct.toString()
            wrongCount.text = (10 - count - correct - 1).toString()
            data[count].btns.apply {
                btns = arrayOf(b1, b2, b3, b4)
            }
            setBundle()
        }
        count--
    }

    private fun setBundle(){
        val bundle = Bundle()
        bundle.putString("quest", data[count].question)

        questFragObj = Question()
        questFragObj.arguments = bundle

        val bundle1 = Bundle().apply {
            putStringArray("solution", btns)
            putInt("key", data[count].key)
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