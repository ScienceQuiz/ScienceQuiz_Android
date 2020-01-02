package com.silso.science_quiz.data

data class Science(var question: String, var btns: Btns, var key: Int)
data class Btns (var b1: String, var b2: String, var b3: String, var b4: String)
data class GetQusetion(var science: Array<Science>)