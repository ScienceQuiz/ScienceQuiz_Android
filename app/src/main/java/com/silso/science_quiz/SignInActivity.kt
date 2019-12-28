package com.silso.science_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.startActivity

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        go_to_signup.setOnClickListener{
            startActivity<SignUpActivity>()
        }

        signin_complete_btn.setOnClickListener {
            startActivity<MainActivity>()
            finish()
        }
    }
}
