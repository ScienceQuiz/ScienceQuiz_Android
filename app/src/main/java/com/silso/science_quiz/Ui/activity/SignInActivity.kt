package com.silso.science_quiz.Ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.silso.science_quiz.R
import com.silso.science_quiz.model.SignIn
import com.silso.science_quiz.server.Retrofit
import com.silso.science_quiz.util.UtilClass
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        go_to_signup.setOnClickListener {
            startActivity<SignUpActivity>()
        }

        signin_complete_btn.setOnClickListener {
            if(isIdEmpty() && isPwEmpty()){
                sendSignin()
            }
            finish()
        }
    }

    private fun sendSignin() {
        val userId = signin_id.text.toString()
        val userPw = signin_password.text.toString()
        val call: Call<SignIn> = Retrofit().service.postSignin(SignIn(userId, userPw))

        call.enqueue(object : Callback<SignIn> {
            override fun onFailure(call: Call<SignIn>, t: Throwable) {
                Log.e("SignIn_errpr", t.message.toString())
            }

            override fun onResponse(call: Call<SignIn>, response: Response<SignIn>) {
                if (response.code() == 200) {
                    toast("로그인 성공")
                    val jwt: String = response.body()!!.token
                    UtilClass.saveToken(applicationContext, jwt)
                    startActivity<MainActivity>()
                }
                else if(response.code() == 471) toast("아이디 비밀번호가 일치하지 않습니다")
                else if(response.code() == 470) toast("계정이 존재하지 않습니다.")
            }

        })
    }

    private fun isIdEmpty(): Boolean {
        return signin_id.text.isNotEmpty()
    }

    private fun isPwEmpty(): Boolean {
        return signin_password.text.isNotEmpty()
    }
}
