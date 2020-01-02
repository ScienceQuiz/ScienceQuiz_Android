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
import com.silso.science_quiz.server.Retrofit
import com.silso.science_quiz.util.SendAnswer
import kotlinx.coroutines.*
import org.jetbrains.anko.startActivity
import retrofit2.HttpException

class TestActivity : AppCompatActivity(), SendAnswer {
    lateinit var questFragObj: Question
    lateinit var soluFragObj: Solution
    lateinit var data: TestData
    var count = 10
    var correct = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

//        CoroutineScope(Dispatchers.Default).launch {
//            val response = Retrofit().service.getData()
//            withContext(Dispatchers.Main) {
//                try {
//                    if (response.isSuccessful) {
                        turningTest()
//                    } else {
//                        Log.e("retroNo", response.code().toString())
//                    }
//                } catch (e: HttpException) {
//                    Log.e("HttpE", "Exception ${e.message}")
//                } catch (e: Throwable) {
//                    Log.e("Throwable","Something else went wrong")
//                }
//            }
//        }
    }

    //받은 데이터로 문제 표시
    fun turningTest(){
        Log.e("count", count.toString())
        if(count == 0){
            startActivity<ResultActivity>(
                "correct" to correct,
                "count" to 10
            )
            finish()
        }else {
            count--
            data = TestData("다음 중 1인 것을 고르시오", arrayOf("Первый", "Uno", "first", "첫번째"), 2)
            setBundle()
        }
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