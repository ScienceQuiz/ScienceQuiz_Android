package com.silso.science_quiz

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.silso.science_quiz.Ui.activity.SignInActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long=6000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val lottie: LottieAnimationView = findViewById(R.id.splash_lottie)
        lottie.setAnimation("splash_lottie.json")
        lottie.playAnimation()
        lottie.addAnimatorListener(object: Animator.AnimatorListener{
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationStart(p0: Animator?) {
            }

        })

        Handler().postDelayed({
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        },SPLASH_TIME_OUT)

//            val lottie: LottieAnimationView = findViewById(R.id.splash_lottie)
//            lottie.playAnimation()
//
//            val intent = Intent(this, SignInActivity::class.java)
//            startActivity(intent)
//            finish()

    }
}
