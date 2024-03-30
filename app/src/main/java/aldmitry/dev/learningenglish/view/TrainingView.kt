package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.presenter.LessonUnit
import aldmitry.dev.learningenglish.ui.theme.Blue10
import aldmitry.dev.learningenglish.ui.theme.Blue15
import aldmitry.dev.learningenglish.ui.theme.Green30
import aldmitry.dev.learningenglish.ui.theme.Green50
import aldmitry.dev.learningenglish.ui.theme.Red30
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalTime


const val answer_field = "ANSWER"
const val input_field = "INPUT_FIELD"
const val keyboard_field = "KEYBOARD_FIELD"

const val rightAnswer_text = "Правильно!"
const val wrongAnswer_text = "Неправильно"

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TrainingScreen(lessonUnits : List<LessonUnit>) {

    val lessonUnit = remember { // урок
        mutableStateOf(lessonUnits[(lessonUnits.indices).random()])
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

   // midScreenText.value = "✔ ${lessonUnit.value.englishText}" // оригинальный eng текст на экране
    // lowScreenText.value = "✘ ${lowScreenText.value}" // user eng текст на экране

    when {
        lessonUnit.value.keyButtonsWords.isEmpty() && inputtedText.value.isNotEmpty() && !isAnswer.value || inputtedText.value.isNotEmpty() &&
                lessonUnit.value.englishText.split(" ").size == inputtedText.value.trim().split(" ").size -> {

            if (inputtedText.value.trim().equals(lessonUnit.value.englishText, ignoreCase = true)) {
                answerCounter.value ++
                topScreenText.value = rightAnswer_text // return
                midScreenText.value = "✔ ${lessonUnit.value.englishText}" // оригинальный eng текст на экране
                inputtedText.value = ""

                var newLessonUnit = lessonUnits[(lessonUnits.indices).random()]
                while (newLessonUnit.englishText == previewLessonText.value) {
                    newLessonUnit = lessonUnits[(lessonUnits.indices).random()]
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
                .background(Blue15)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if (isAnswer.value) Arrangement.Bottom else Arrangement.Top // TODO
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
                text = midScreenText.value,
                style = TextStyle(color = if (isAnswer.value) Green30 else Color.White, fontSize = 23.sp) // if (isAnswer.value) 23.sp else 22.sp)
            )

            Text(
                modifier = Modifier.padding(20.dp),
                text = inputtedText.value,
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputLesson(inputtedText: MutableState<String>) { // keyList: List<String>, inputtedText: MutableState<String>

    val textFromField = remember {
        mutableStateOf("")
    }

    val isTextValid = textFromField.value.length < 70

    Column(
        modifier = Modifier
            .padding(bottom = 30.dp)
            .background(Blue10)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        TextButton(
            onClick = {
                if (isTextValid) inputtedText.value = textFromField.value
            },
            modifier = Modifier
                .padding(bottom = 20.dp, top = 30.dp)
                .background(Blue10),
            colors = ButtonDefaults.textButtonColors(containerColor = if (textFromField.value.isEmpty() || !isTextValid) Blue10 else Green50),
            border = BorderStroke(2.dp, Color.White) // Blue30  Blue10  Color.White
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Проверить",
                style = TextStyle(color = Color.White, fontSize = 18.sp)
            )
        }

        TextField(
            placeholder = {
                Text(
                    text = "✎ Введите текст",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            },
            value = textFromField.value,
            onValueChange = { textFromField.value = if (isTextValid) it else it.dropLast(1) },
            modifier = Modifier
                .background(Blue10)
                .border(10.dp, Blue10)
                .fillMaxWidth()
                .padding(bottom = 50.dp, top = 20.dp),
            textStyle = TextStyle(fontSize = 18.sp, color = if (isTextValid) Color.White else  Red30),
            colors = TextFieldDefaults.outlinedTextFieldColors(Blue10)
        )
    }
}


@Composable
fun Keyboard(keyList: List<String>, inputtedText: MutableState<String>) {

    val button0 = remember {
        mutableStateOf(keyList[0])
    }

    val button1= remember {
        mutableStateOf(keyList[1])
    }

    val button2 = remember {
        mutableStateOf(keyList[2])
    }

    val button3 = remember {
        mutableStateOf(keyList[3])
    }

    val button4 = remember {
        mutableStateOf(keyList[4])
    }

    val button5 = remember {
        mutableStateOf(keyList[5])
    }

    val button6 = remember {
        mutableStateOf(keyList[6])
    }

    val button7 = remember {
        mutableStateOf(keyList[7])
    }

    val button8 = remember {
        mutableStateOf(keyList[8])
    }

    val button9 = remember {
        mutableStateOf(keyList[9])
    }

    val buttonIndexCounter = remember { // счетчик нажатых клавиш
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .padding(bottom = 30.dp)
            .background(Blue10)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        Row(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            TextButton(
                onClick = {
                    if (inputtedText.value.isNotEmpty()){
                        val lastIndex = buttonIndexCounter.value.takeLast(1).toInt()
                        buttonIndexCounter.value = buttonIndexCounter.value.dropLast(1)

                        when (lastIndex) {
                            0 -> {
                                inputtedText.value = inputtedText.value.replace(" ${keyList[0]}", "")
                                button0.value = keyList[0]
                            }
                            1 -> {
                                inputtedText.value = inputtedText.value.replace(" ${keyList[1]}", "")
                                button1.value = keyList[1]
                            }
                            2 -> {
                                inputtedText.value = inputtedText.value.replace(" ${keyList[2]}", "")
                                button2.value = keyList[2]
                            }
                            3 -> {
                                inputtedText.value = inputtedText.value.replace(" ${keyList[3]}", "")
                                button3.value = keyList[3]
                            }
                            4 -> {
                                inputtedText.value = inputtedText.value.replace(" ${keyList[4]}", "")
                                button4.value = keyList[4]
                            }
                            5 -> {
                                inputtedText.value = inputtedText.value.replace(" ${keyList[5]}", "")
                                button5.value = keyList[5]
                            }
                            6 -> {
                                inputtedText.value = inputtedText.value.replace(" ${keyList[6]}", "")
                                button6.value = keyList[6]
                            }
                            7 -> {
                                inputtedText.value = inputtedText.value.replace(" ${keyList[7]}", "")
                                button7.value = keyList[7]
                            }
                            8 -> {
                                inputtedText.value = inputtedText.value.replace(" ${keyList[8]}", "")
                                button8.value = keyList[8]
                            }
                            9 -> {
                                inputtedText.value = inputtedText.value.replace(" ${keyList[9]}", "")
                                button9.value = keyList[9]
                            }
                        }
                    }
                },
                modifier = Modifier
                    .padding(10.dp)
                    .background(Blue10),
                colors = ButtonDefaults.textButtonColors(containerColor = Green50),
                border = BorderStroke(2.dp, Color.White) // Blue30  Blue10  Color.White
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Редактировать текст",
                    style = TextStyle(color = Color.White, fontSize = 18.sp)
                )
            }

        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += " ${button0.value}"
                        button0.value = ""
                        buttonIndexCounter.value += "0"
                    },
                text = button0.value,
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += " ${button1.value}"
                        button1.value = ""
                        buttonIndexCounter.value += "1"
                    },
                text =  button1.value,
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += " ${button2.value}"
                        button2.value = ""
                        buttonIndexCounter.value += "2"
                    },
                text =  button2.value,
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )
        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += " ${button3.value}"
                        button3.value = ""
                        buttonIndexCounter.value += "3"
                    },
                text =  button3.value,
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += " ${button4.value}"
                        button4.value = ""
                        buttonIndexCounter.value += "4"
                    },
                text =  button4.value,
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += " ${button5.value}"
                        button5.value = ""
                        buttonIndexCounter.value += "5"
                    },
                text =  button5.value,
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += " ${button6.value}"
                        button6.value = ""
                        buttonIndexCounter.value += "6"
                    },
                text =  button6.value,
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += " ${button7.value}"
                        button7.value = ""
                        buttonIndexCounter.value += "7"
                    },
                text =  button7.value,
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )
        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += " ${button8.value}"
                        button8.value = ""
                        buttonIndexCounter.value += "8"
                    },
                text =  button8.value,
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += " ${button9.value}"
                        button9.value = ""
                        buttonIndexCounter.value += "9"
                    },
                text =  button9.value,
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )
        }
    }

}