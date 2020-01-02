package com.silso.science_quiz.Ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.silso.science_quiz.R
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.image

class ResultActivity : AppCompatActivity() {
    var reciece = 0
    var count = 0
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setScore()
        setImage()
    }

    fun setScore(){
        reciece = intent.getIntExtra("correct", 0)
        count = intent.getIntExtra("count", 0)

        resultOCnt.text = reciece.toString()
        resultXCnt.text = (count - reciece).toString()
        score = (reciece * 100 + (count - reciece) * 50)
        resultScoreCnt.text = score.toString()
    }

    fun setImage() {
        resultImg.image = when {
            score >= count * 75 -> getDrawable(R.drawable.result_happy_face)
            score >= count * 40 -> getDrawable(R.drawable.result_ok_face)
            else -> getDrawable(R.drawable.result_sad_face)
        }
    }
}
