package com.silso.science_quiz.Ui.activity

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
    private val requiredPermissions = arrayOf(Manifest.permission.INTERNET)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        giveGrant()

        go_to_signup.setOnClickListener {
            startActivity<SignUpActivity>()
        }

        signin_complete_btn.setOnClickListener {
            if(isIdEmpty() && isPwEmpty()){
                sendSignin()
                finish()
            }else{
                toast("빈칸을 채워주시길 바랍니다.")
            }
        }
    }

    //권한 부여
    fun giveGrant(){
        val rejectedPermissionList = ArrayList<String>()
        for(permission in requiredPermissions){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                rejectedPermissionList.add(permission)
        }

        if(rejectedPermissionList.isNotEmpty()){
            val array = arrayOfNulls<String>(rejectedPermissionList.size)
            ActivityCompat.requestPermissions(this, rejectedPermissionList.toArray(array), 1)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1 && grantResults.isNotEmpty())
            for((i, permission) in permissions.withIndex()) {
                if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    //권한 획득 실패
                    Log.i("TAG", "The user has denied to $permission")
                    Log.i("TAG", "I can't work for you anymore then. ByeBye!")
                }
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
                when {
                    response.code() == 200 -> {
                        toast("로그인 성공")
                        val jwt: String = response.body()!!.token
                        UtilClass.saveToken(applicationContext, jwt)
                        startActivity<MainActivity>()
                    }
                    response.code() == 471 -> toast("아이디 비밀번호가 일치하지 않습니다")
                    response.code() == 470 -> toast("계정이 존재하지 않습니다.")
                }
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
