package com.silso.science_quiz.Ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.silso.science_quiz.R
import kotlinx.android.synthetic.main.fragment_question.*

class Question: Fragment(){

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_question, null)
        val bunStr = arguments?.getString("quest", "default")

        val text = view.findViewById<TextView>(R.id.question)
        text.text = bunStr

        return view
    }
}