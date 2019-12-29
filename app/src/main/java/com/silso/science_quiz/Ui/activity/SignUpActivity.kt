package com.silso.science_quiz.Ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.silso.science_quiz.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signup_complete_btn.setOnClickListener{
            if (isNicknameEmpty() && isIdEmpty() && isPasswordEqual() && isSchoolEmpty()){

                finish()
            }
        }
    }
    private fun isNicknameEmpty():Boolean{
        return signup_name.text.isNotEmpty()
    }
    private fun isIdEmpty():Boolean{
        return signup_id.text.isNotEmpty()
    }
    private fun isPasswordEqual():Boolean{
        return signup_password.text.isNotEmpty() && signup_password_check.text.isNotEmpty() && signup_password_check.text == signup_password_check.text
    }
    private fun isSchoolEmpty():Boolean{
        return signup_school.text.isNotEmpty()
    }
}
