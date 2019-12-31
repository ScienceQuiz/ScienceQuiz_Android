package com.silso.science_quiz.server

import com.silso.science_quiz.model.SignIn
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface NetRetrofit {
    @POST("/api/users/signup")
    fun postSignup(@Query("nickname")nickname:String,
                   @Query("userId")id:String,
                   @Query("userPw")password:String,
                   @Query("school")school:String): Call<Void>

    @POST("/api/users/login")
    fun postSignin(@Body signIn: SignIn):Call<SignIn>
}