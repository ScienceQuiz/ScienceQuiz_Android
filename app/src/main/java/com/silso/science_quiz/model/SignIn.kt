package com.silso.science_quiz.model

import com.google.gson.annotations.SerializedName

class SignIn(
    @SerializedName("userId")
    val userId:String,
    @SerializedName("userPw")
    val userPw:String
) {
    @SerializedName("access_token")
    lateinit var token:String
}