package com.silso.science_quiz.Ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.silso.science_quiz.R
import kotlinx.android.synthetic.main.fragment_solution.*

class Solution: Fragment() {
    lateinit var btn: Button

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_solution, null)
        val bunArrStr = arguments?.getStringArray("solution")

        btn = view.findViewById(R.id.btn1)
        btn.text = bunArrStr?.get(0) ?: "none"
        btn = view.findViewById(R.id.btn2)
        btn.text = bunArrStr?.get(1) ?: "none"
        btn = view.findViewById(R.id.btn3)
        btn.text = bunArrStr?.get(2) ?: "none"
        btn = view.findViewById(R.id.btn4)
        btn.text = bunArrStr?.get(3) ?: "none"

        return view
    }
}