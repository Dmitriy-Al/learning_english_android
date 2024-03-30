package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.presenter.LessonUnit
import aldmitry.dev.learningenglish.ui.theme.Blue15
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TrainingScreen() {

    val lessonUnit = remember { // урок
        mutableStateOf(LessonUnit("", "", mutableListOf()))
    }

    val keyBoardField = remember { // выбор поля keyboard view
        mutableStateOf(if (lessonUnit.value.keyButtonsWords.isEmpty()) input_field else keyboard_field)
    }

    val inputtedText = remember { // введеный с клавиатуры текст
        mutableStateOf("")
    }

    val answerText = remember { // текст для экрана при правильноом/неправильном ответе
        mutableStateOf("")
    }

    val screenText = remember { // первый текст сообщения главного экрана
        mutableStateOf("")
    }

    val underScreenText = remember { // второй текст сообщения главного экрана
        mutableStateOf("")
    }

    val answerCounter = remember { // счетчик
        mutableStateOf(0)
    }


    val previewLessonText = remember { // урок
        mutableStateOf(lessonUnit.value.englishText)
    }


    when {
        lessonUnit.value.keyButtonsWords.isEmpty() && inputtedText.value.isNotEmpty() || inputtedText.value.isNotEmpty() &&
                lessonUnit.value.englishText.split(" ").size == inputtedText.value.trim().split(" ").size -> {
            if (inputtedText.value.trim().equals(lessonUnit.value.englishText, ignoreCase = true)) {
                answerCounter.value ++
                screenText.value = "" // Текст для перевода на экране
                underScreenText.value = lessonUnit.value.englishText // оригинальный eng текст на экране
                answerText.value = "Правильно !" // return

                var newLessonUnit = LessonUnit("", "", mutableListOf())
                while (newLessonUnit.englishText == previewLessonText.value) {
                    newLessonUnit = LessonUnit("", "", mutableListOf())
                }
                lessonUnit.value = newLessonUnit
            }  else {
                screenText.value = "Правильно: ${lessonUnit.value.englishText}" // оригинальный eng текст на экране
                underScreenText.value = "❌ ${inputtedText.value}" // user eng текст на экране
                answerText.value = "Неправильно" // return
            }
            inputtedText.value = ""
            keyBoardField.value = answer_field
        }

        keyBoardField.value == keyboard_field || keyBoardField.value == input_field -> {
            screenText.value = "Введите перевод текста:"
            underScreenText.value = lessonUnit.value.russianText
        }

        // если введенный текст отсутствует и поле ввода показывает ответ, запускается таймер показа экрана
        inputtedText.value.isEmpty() && keyBoardField.value == answer_field -> {
            if (lessonUnit.value.keyButtonsWords.isEmpty()) {
                keyBoardField.value = input_field
            } else {
                keyBoardField.value = keyboard_field
            }


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
                .background(Blue15)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Bottom
        ) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
                    text = "Ответов: 3", // "Введите перевод текста:
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    textAlign = TextAlign.Start
                )
            }

            Text(
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                text = "Text 1", // "Введите перевод текста:
                style = TextStyle(color = Color.White, fontSize = 23.sp)
            )

            Text(
                modifier = Modifier.padding(30.dp),
                text = "Text 2",
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

            Text(
                modifier = Modifier.padding(30.dp),
                text = "Text 3",
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

        }


        Column(
            modifier = Modifier
                .weight(0.5F)
                .padding(bottom = 30.dp)
                .background(Blue15)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(
                modifier = Modifier
                    .clickable { } // TODO
                    .padding(30.dp),
                text = "Text 4",
                style = TextStyle(color = Color.White, fontSize = 30.sp)
            )
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