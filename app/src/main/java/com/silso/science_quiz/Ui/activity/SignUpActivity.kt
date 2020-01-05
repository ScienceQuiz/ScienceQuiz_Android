package com.silso.science_quiz.Ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.silso.science_quiz.R
import com.silso.science_quiz.server.Retrofit
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signup_complete_btn.setOnClickListener {
            if (isNicknameEmpty() && isIdEmpty() && isPasswordEqual() && isSchoolEmpty()) {
                sendSignup()
                finish()
            }
        }
    }

    private fun isNicknameEmpty(): Boolean {
        return signup_nick.text.isNotEmpty()
    }

    private fun isIdEmpty(): Boolean {
        return signup_id.text.isNotEmpty()
    }

    private fun isPasswordEqual(): Boolean {
        return signup_password.text.isNotEmpty() && signup_password_check.text.isNotEmpty() && signup_password.text.toString() == signup_password_check.text.toString()
    }

    private fun isSchoolEmpty(): Boolean {
        return signup_school.text.isNotEmpty()
    }

    private fun sendSignup() {
        val userNick = signup_nick.text.toString()
        val userId = signup_id.text.toString()
        val userPw = signup_password.text.toString()
        val userSchool = signup_school.text.toString()

        val call: Call<Void> = Retrofit().service.postSignup(userNick,userId,userPw,userSchool)
        call.enqueue(object :Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("signUp_error",t.message.toString())
            }
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("signUp_response",response.code().toString())
                if(response.code() == 201){
                    finish()
                }
            }

        })
    }
}
