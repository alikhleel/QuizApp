package com.alikhalil.quizapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alikhalil.quizapp.model.QuizItem

class QuizViewModel : ViewModel() {
    var quizList: MutableList<QuizItem> = mutableListOf(
        QuizItem(
            0,
            question = "What is the capital of France?",
            answersList = listOf("Paris", "London", "Berlin", "Madrid"),
            correctChoice = "Paris",
            isAnswerCorrect = false
        ),
        QuizItem(
            1,
            question = "What is the capital of Germany?",
            answersList = listOf("Paris", "London", "Berlin", "Madrid"),
            correctChoice = "Berlin",
            isAnswerCorrect = false
        ),
        QuizItem(
            2,
            question = "What is the capital of Spain?",
            answersList = listOf("Paris", "London", "Berlin", "Madrid"),
            correctChoice = "Madrid",
            isAnswerCorrect = false
        ),
        QuizItem(
            3,
            question = "What is the capital of England?",
            answersList = listOf("Paris", "London", "Berlin", "Madrid"),
            correctChoice = "London",
            isAnswerCorrect = false
        )
    )

    val score = mutableStateOf("")

    fun checkAnswer(quizItem: QuizItem, answer: String) {
        quizList = quizList.map {
            if (it.id == quizItem.id && answer == it.correctChoice) {
                it.copy(isAnswerCorrect = true)
            } else if (it.id == quizItem.id) {
                it.copy(isAnswerCorrect = false)
            } else {
                it
            }
        }.toMutableList()
    }

    fun onSubmit() {
        val numOfCorrectAnswers = quizList.count { it.isAnswerCorrect }
        score.value = "you got ${numOfCorrectAnswers} out of ${quizList.size}"
    }
}