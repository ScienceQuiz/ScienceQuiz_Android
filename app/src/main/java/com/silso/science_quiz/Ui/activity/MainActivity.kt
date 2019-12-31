package com.silso.science_quiz.Ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.silso.science_quiz.R
import com.silso.science_quiz.model.GetNick
import com.silso.science_quiz.server.Retrofit
import com.silso.science_quiz.util.UtilClass
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNick()

        start_quiz_btn.setOnClickListener {
            startActivity<TestActivity>()
        }
        rank_school_image.setOnClickListener {
            startActivity<RankActivity>()
        }
    }
    private fun getNick(){
        val call: Call<GetNick> = Retrofit().service.getNick(UtilClass.getToken(applicationContext))
        call.enqueue(object: Callback<GetNick>{
            override fun onFailure(call: Call<GetNick>, t: Throwable) {
                Log.e("getNick_error",t.message.toString())
            }

            override fun onResponse(call: Call<GetNick>, response: Response<GetNick>) {
                Log.d("getNick_response",response.code().toString())
                if(response.code() == 200){
                    nickname_textView.text = response.body()?.userNick
                }
            }
        })
    }
}
