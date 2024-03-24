package com.alikhalil.quizapp.model

data class QuizItem(
    val id: Int,
    val question: String,
    val answersList: List<String>,
    val correctChoice: String,
    var isAnswerCorrect: Boolean = false
)