package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.presenter.LessonUnit
import aldmitry.dev.learningenglish.ui.theme.Blue15
import aldmitry.dev.learningenglish.ui.theme.Green30
import aldmitry.dev.learningenglish.ui.theme.Red30
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun TrainingScreen() {

    val lessonUnit = remember { // урок
        mutableStateOf(LessonUnit("", "", mutableListOf()))
    }

    val keyBoardField = remember { // выбор поля keyboard view
        mutableStateOf(if (lessonUnit.value.keyButtonsWords.isEmpty()) input_field else keyboard_field)
    }

    val isAnswer = remember { // текст для экрана при правильноом/неправильном ответе // TODO
        mutableStateOf(false)
    }

    val topScreenText = remember { // первый текст сообщения главного экрана
        mutableStateOf("")
    }

    val midScreenText = remember { // второй текст сообщения главного экрана
        mutableStateOf("")
    }

    val inputtedText = remember { // введеный с клавиатуры текст
        mutableStateOf("")
    }


    val answerCounter = remember { // счетчик
        mutableStateOf(0)
    }


    val previewLessonText = remember { // урок
        mutableStateOf(lessonUnit.value.englishText)
    }

    when {
        lessonUnit.value.keyButtonsWords.isEmpty() && inputtedText.value.isNotEmpty() && !isAnswer.value || inputtedText.value.isNotEmpty() &&
                lessonUnit.value.englishText.split(" ").size == inputtedText.value.trim().split(" ").size -> {

            if (inputtedText.value.trim().equals(lessonUnit.value.englishText, ignoreCase = true)) {
                answerCounter.value ++
                topScreenText.value = rightAnswer_text // return
                midScreenText.value = "✔ ${lessonUnit.value.englishText}" // оригинальный eng текст на экране
                inputtedText.value = ""

                var newLessonUnit = LessonUnit("", "", mutableListOf())
                while (newLessonUnit.englishText == previewLessonText.value) {
                    newLessonUnit = LessonUnit("", "", mutableListOf())
                }
                lessonUnit.value = newLessonUnit
            }  else {
                topScreenText.value = wrongAnswer_text // оригинальный eng текст на экране
                midScreenText.value = "✔ ${lessonUnit.value.englishText}" // оригинальный eng текст на экране
                inputtedText.value = "✘ ${inputtedText.value}" // user eng текст на экране
            }
            isAnswer.value = true
            keyBoardField.value = answer_field
        }

        keyBoardField.value == keyboard_field || keyBoardField.value == input_field -> {
            topScreenText.value = "Введите перевод для текста:"
            midScreenText.value = lessonUnit.value.russianText
        }

        // если введенный текст отсутствует и поле ввода показывает ответ, запускается таймер показа экрана
        //lowScreenText.value.isEmpty() && keyBoardField.value == answer_field -> {
        isAnswer.value && keyBoardField.value == answer_field -> {
            if (lessonUnit.value.keyButtonsWords.isEmpty()) {
                keyBoardField.value = input_field
            } else {
                keyBoardField.value = keyboard_field
            }

            val timeUp = LocalTime.now().plusSeconds(2) // TODO задержка показа ответа
            while (!timeUp.isBefore(LocalTime.now())) // таймер показа экрана
                inputtedText.value = ""
            topScreenText.value = "" // Текст для перевода на экране
            midScreenText.value = ""
            isAnswer.value = false
        }
    }

    Column(
        modifier = Modifier
            .background(Blue15)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Column(
            modifier = Modifier
                .weight(0.5F)
                .background(Blue15)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom// if (isAnswer.value) Arrangement.Bottom else Arrangement.Top // TODO
        ) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
                    text = "Ответов: ${answerCounter.value}", // "Введите перевод текста:
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    textAlign = TextAlign.Start
                )
            }
            Text(
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                text = topScreenText.value, // "Введите перевод текста:
                style = TextStyle(color = Color.White, fontSize = if (isAnswer.value) 25.sp else 21.sp)
            )

            Text(
                modifier = Modifier.padding(20.dp),
                text = "midScreenText.value",
                style = TextStyle(color = if (isAnswer.value) Green30 else Color.White, fontSize = 23.sp) // if (isAnswer.value) 23.sp else 22.sp)
            )

            Text(
                modifier = Modifier.padding(20.dp),
                text = "inputtedText.value",
                style = TextStyle(color = if (isAnswer.value) Red30 else Color.White, fontSize = 23.sp) // if (isAnswer.value) 23.sp else 22.sp)
            )

        }


        Column(
            modifier = Modifier
                .weight(0.5F)
                .padding(bottom = 20.dp)
                .background(Blue15)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            when (keyBoardField.value) {
                keyboard_field -> Keyboard(lessonUnit.value.keyButtonsWords, inputtedText)
                input_field -> InputLesson(inputtedText)
            }
        }
    }
}



/*
TextButton(
onClick = { chooseLearningTypeSection.value = LearningTypeSection.USERS_TEXTS },
modifier = Modifier
.padding(start = 3.dp, end = 3.dp)
.background(Blue15),
colors = ButtonDefaults.textButtonColors(containerColor = if (chooseLearningTypeSection.value == LearningTypeSection.USERS_TEXTS) Blue30 else Blue15),
border = BorderStroke(1.dp, if (chooseLearningTypeSection.value == LearningTypeSection.USERS_TEXTS) Color.White else Blue15) // Blue30  Blue10  Color.White

) {
    Text(
        modifier = Modifier.padding(5.dp),
        text = LearningTypeSection.USERS_TEXTS.title, // "Добавить собственный текст"
        style = TextStyle(color = Color.White, fontSize = 18.sp)
    )
}

 */