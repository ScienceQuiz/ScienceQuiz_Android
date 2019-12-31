package com.silso.science_quiz.model

import com.google.gson.annotations.SerializedName

class GetNick {
    @SerializedName("nickname")
    lateinit var userNick:String
}