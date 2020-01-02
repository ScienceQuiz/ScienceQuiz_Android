package com.silso.science_quiz.Ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.silso.science_quiz.R
import com.silso.science_quiz.util.SendAnswer

class Solution: Fragment() {
    lateinit var btn: ArrayList<Button>
    lateinit var sa: SendAnswer
    var key: Int = 0
    var choice: Int = 0

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_solution, null)
        val bunArrStr = arguments?.getStringArray("solution")
        key = arguments?.getInt("key", 0)!!
        sa = activity as SendAnswer

        btn = ArrayList<Button>().apply {
            add(view.findViewById(R.id.btn1))
            add(view.findViewById(R.id.btn2))
            add(view.findViewById(R.id.btn3))
            add(view.findViewById(R.id.btn4))
        }

        for(i in 0..3) btn[i].text = bunArrStr?.get(i) ?: "none"
        BCE()

        return view
    }

    fun BCE() {
        for(i in 0..3) {
            btn[i].setOnClickListener {
                choice = i + 1
                check()
            }
        }
    }

    fun check(){
        when(choice){
            key -> {sa.sendEvent(true)}
            else -> {sa.sendEvent(false)}
        }
    }
}