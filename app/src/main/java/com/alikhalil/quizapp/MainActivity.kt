package com.alikhalil.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alikhalil.quizapp.ui.theme.QuizAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Quiz()
                    }
                }
            }
        }
    }
}

@Composable
fun Quiz(
    viewModel: QuizViewModel = viewModel()
) {
    LazyColumn {
        items(viewModel.quizList) { quizItem ->
            val selectedOption = rememberSaveable    { mutableStateOf("") }
            Text(text = quizItem.question)
            quizItem.answersList.forEach { answer ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .clickable {
                            selectedOption.value = answer
                            viewModel.checkAnswer(quizItem, answer)
                        }, verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = answer == selectedOption.value, onClick = {
                        selectedOption.value = answer
                        viewModel.checkAnswer(quizItem, answer)
                    })
                    Text(text = answer)
                }
            }
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(onClick = { viewModel.onSubmit() }) {
                    Text(text = "Submit")
                }

            }

        }
        item{
            Text(text = viewModel.score.value)
        }
    }
}