package com.silso.science_quiz.Ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.silso.science_quiz.R

class Solution: Fragment() {
    lateinit var btn: ArrayList<Button>

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_solution, null)
        val bunArrStr = arguments?.getStringArray("solution")

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
        btn[0].setOnClickListener {
            Log.e("a", "1")
        }

        btn[1].setOnClickListener {
            Log.e("a", "2")
        }

        btn[2].setOnClickListener {
            Log.e("a", "3")
        }

        btn[3].setOnClickListener {
            Log.e("a", "4")
        }
    }
}