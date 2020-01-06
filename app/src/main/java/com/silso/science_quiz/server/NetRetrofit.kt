package com.silso.science_quiz.server

import com.silso.science_quiz.data.GetQusetion
import com.silso.science_quiz.model.GetNick
import com.silso.science_quiz.model.SignIn
import retrofit2.Call
import retrofit2.http.*

interface NetRetrofit {
    @POST("/api/signup")
    fun postSignup(@Query("nickname")nickname:String,
                   @Query("userId")id:String,
                   @Query("userPw")password:String,
                   @Query("school")school:String): Call<Void>

    @POST("/api/login")
    fun postSignin(@Body signIn: SignIn):Call<SignIn>

    @GET("/api/users/my")
    fun getNick(@Header("Authorization") token:String):Call<GetNick>

    @GET("/api/quiz")
    fun getScience(): Call<GetQusetion>
}