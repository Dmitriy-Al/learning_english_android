package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.presenter.LearningHandler
import aldmitry.dev.learningenglish.presenter.Lesson
import aldmitry.dev.learningenglish.ui.theme.Blue10
import aldmitry.dev.learningenglish.ui.theme.Blue15
import aldmitry.dev.learningenglish.ui.theme.Blue50
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TrainingScreen(learningHandler: LearningHandler) {

    val savedLesson = remember { // урок
        mutableStateOf(Lesson(learningHandler))
    }

    val savedPreviousEnText = remember { // сохранение старого текста тренировки для сравнения с новым текстом
        mutableStateOf("")
    }

    val keyBoardField = remember { // выбор поля keyboard view
        mutableStateOf("KEYBOARD")
    }

    val inputtedText = remember { // введеный с клавиатуры текст
        mutableStateOf("")
    }

    val flag = remember { // флаг для загрузки нового урока
        mutableStateOf(true)
    }

    val answerText = remember { // текст для экрана при правильноом/неправильном ответе
        mutableStateOf("")
    }

    val underScreenText = remember { // второй текст сообщения главного экрана
        mutableStateOf("")
    }

    val screenText = remember { // первый текст сообщения главного экрана
        mutableStateOf("")
    }

    val textRedactorCount = remember { // счетчик нажатых клавиш
        mutableStateOf("")
    }

    val counter = remember { // счетчик
        mutableStateOf("")
    }


    when {
        // если флаг == true, загружается новый урок
        flag.value ->  {
            savedLesson.value.receiveLesson()
            var lessonUnit = savedLesson.value.enText

            while (lessonUnit == savedPreviousEnText.value) {
                savedLesson.value = Lesson(learningHandler) // TODO можно сделать без вызова нового объекта
                savedLesson.value.receiveLesson()
                lessonUnit = savedLesson.value.enText
            }
            savedPreviousEnText.value = ""

            screenText.value = "Введите перевод текста:" // Текст для перевода на экране
            underScreenText.value = savedLesson.value.forTranslateText // Текст для перевода на экране
            flag.value = false
        }

        // если введенный текст отсутствует и поле ввода показывает ответ, запускается таймер показа экрана
        inputtedText.value.isEmpty() && keyBoardField.value == "ANSWER" -> {
            val timeUp = LocalTime.now().plusSeconds(2) // TODO задержка показа ответа
            while (!timeUp.isBefore(LocalTime.now())) keyBoardField.value = "KEYBOARD" // таймер показа экрана
            screenText.value = "" // Текст для перевода на экране
            underScreenText.value = ""
            flag.value = true
        }

        // если введенный текст свпадает по количеству слов с английским текстом
        inputtedText.value.trim().split(" ").size >= savedLesson.value.enText.split(" ").size -> {
            if (inputtedText.value.trim() == savedLesson.value.enText) {
                screenText.value = "" // Текст для перевода на экране
                underScreenText.value = savedLesson.value.enText // оригинальный eng текст на экране
                answerText.value = "Правильно !" // return
            }  else {
                screenText.value = "Правильно: ${savedLesson.value.enText}" // оригинальный eng текст на экране
                underScreenText.value = "❌ ${inputtedText.value}" // user eng текст на экране
                answerText.value = "Неправильно" // return
            }
            savedPreviousEnText.value = savedLesson.value.enText
            savedLesson.value = Lesson(learningHandler)
            inputtedText.value = ""
            textRedactorCount.value = ""
            keyBoardField.value = "ANSWER" // "KEYBOARD"
        }


    }


    Column(
        modifier = Modifier
            .background(Blue15)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom // Bottom
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = screenText.value, // "Введите перевод текста:
            style = TextStyle(color = Color.White, fontSize = 20.sp)
        )

        Text(
            modifier = Modifier.padding(10.dp),
            text = underScreenText.value, // "Введите перевод текста:
            style = TextStyle(color = Color.White, fontSize = 23.sp)
        )

        Column(
            modifier = Modifier
                .background(Blue15)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            //// verticalArrangement = Arrangement.Top //////////////////////////////////////
        ) {
            Text(
                modifier = Modifier.padding(top = 60.dp, start = 25.dp, end = 25.dp),
                text = inputtedText.value,
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )
        }

        Spacer(
            modifier = Modifier
                .background(Blue15)
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        )


// if(savedLessonUnit.value.buttonText.isNotEmpty()) TrainingKeyboard(savedLessonUnit, inputtedText)


        when (keyBoardField.value) {

            "KEYBOARD" -> {
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
                                val letterList = ("FIRST_LETTER " + inputtedText.value).trim().split(" ") as MutableList // введенный текст в лист
                                val lastInputLetter = letterList.removeAt(letterList.size - 1) // из листа с введенным текстом забирается и удаляется последний элемент
                                inputtedText.value = letterList.toString().replace("[FIRST_LETTER, ","").replace("[FIRST_LETTER","").replace("]","").replace(",","")// введенный текст заменяется новым без последнего элемента

                                val lastIndex = textRedactorCount.value.takeLast(1) // строка с индексами листа
                                textRedactorCount.value = textRedactorCount.value.replace(lastIndex, "") // удаление последнего индекса из строки с индексами листа
                                savedLesson.value.buttonText[lastIndex.toInt()] = lastInputLetter // добавление слова в лист урока
                                }
                                      },
                            modifier = Modifier
                                .padding(10.dp)
                                .background(Blue10),
                            colors = ButtonDefaults.textButtonColors(containerColor = Blue50),
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
                                    inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) " " + savedLesson.value.buttonText[0] else ""
                                    savedLesson.value.buttonText[0] = ""
                                    textRedactorCount.value += "0"
                                },
                            text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[0] else "",
                            style = TextStyle(color = Color.White, fontSize = 20.sp)
                        )

                        Text(
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable {
                                    inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) " " + savedLesson.value.buttonText[1] else ""
                                    savedLesson.value.buttonText[1] = ""
                                    textRedactorCount.value += "1"
                                },
                            text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[1] else "",
                            style = TextStyle(color = Color.White, fontSize = 20.sp)
                        )

                        Text(
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable {
                                    inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) " " + savedLesson.value.buttonText[2] else ""
                                    savedLesson.value.buttonText[2] = ""
                                    textRedactorCount.value += "2"
                                },
                            text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[2] else "",
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
                                    inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) " " + savedLesson.value.buttonText[3] else ""
                                    savedLesson.value.buttonText[3] = ""
                                    textRedactorCount.value += "3"
                                },
                            text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[3] else "",
                            style = TextStyle(color = Color.White, fontSize = 20.sp)
                        )

                        Text(
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable {
                                    inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) " " + savedLesson.value.buttonText[4] else ""
                                    savedLesson.value.buttonText[4] = ""
                                    textRedactorCount.value += "4"
                                },
                            text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[4] else "",
                            style = TextStyle(color = Color.White, fontSize = 20.sp)
                        )

                        Text(
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable {
                                    inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) " " + savedLesson.value.buttonText[5] else ""
                                    savedLesson.value.buttonText[5] = ""
                                    textRedactorCount.value += "5"
                                },
                            text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[5] else "",
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
                                    inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) " " + savedLesson.value.buttonText[6] else ""
                                    savedLesson.value.buttonText[6] = ""
                                    textRedactorCount.value += "6"
                                },
                            text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[6] else "",
                            style = TextStyle(color = Color.White, fontSize = 20.sp)
                        )

                        Text(
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable {
                                    inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) " " + savedLesson.value.buttonText[7] else ""
                                    savedLesson.value.buttonText[7] = ""
                                    textRedactorCount.value += "7"
                                },
                            text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[7] else "",
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
                                    inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) " " + savedLesson.value.buttonText[8] else ""
                                    savedLesson.value.buttonText[8] = ""
                                    textRedactorCount.value += "8"
                                },
                            text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[8] else "",
                            style = TextStyle(color = Color.White, fontSize = 20.sp)
                        )

                        Text(
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable {
                                    inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) " " + savedLesson.value.buttonText[9] else ""
                                    savedLesson.value.buttonText[9] = ""
                                    textRedactorCount.value += "9"
                                },
                            text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[9] else "",
                            style = TextStyle(color = Color.White, fontSize = 20.sp)
                        )
                    }
                }
            } // TODO

            "ANSWER" -> {
                Column(
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .background(Blue15)
                        .fillMaxWidth(),
                       // .height(377.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                   Text(
                       modifier = Modifier
                           .clickable {  } // TODO
                           .padding(30.dp),
                       text = answerText.value,
                       style = TextStyle(color = Color.White, fontSize = 30.sp)
                   )
                    /*  */
               }
           }


       }





    }


}


@Composable
fun TrainingKeyboard(
    savedLesson: MutableState<Lesson>,
    inputtedText: MutableState<String>
) { // inputtedText

    Column(
        modifier = Modifier
            .background(Blue10)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
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
                        inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[0] + " " else ""
                        savedLesson.value.buttonText[0] = ""
                    },
                text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[0] else "",
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[1] + " " else ""
                        savedLesson.value.buttonText[1] = ""
                    },
                // text = savedLessonUnit.value.buttonText[1],
                text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[1] else "",
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[2] + " " else ""
                        savedLesson.value.buttonText[2] = ""
                    },
                text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[2] else "",
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
                        inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[3] + " " else ""
                        savedLesson.value.buttonText[3] = ""
                    },
                text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[3] else "",
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[4] + " " else ""
                        savedLesson.value.buttonText[4] = ""
                    },
                text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[4] else "",
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[5] + " " else ""
                        savedLesson.value.buttonText[5] = ""
                    },
                text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[5] else "",
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
                        inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[6] + " " else ""
                        savedLesson.value.buttonText[6] = ""
                    },
                text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[6] else "",
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[7] + " " else ""
                        savedLesson.value.buttonText[7] = ""
                    },
                text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[7] else "",
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
                        inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[8] + " " else ""
                        savedLesson.value.buttonText[8] = ""
                    },
                text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[8] else "",
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        inputtedText.value += if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[9] + " " else ""
                        savedLesson.value.buttonText[9] = ""
                    },
                text = if (savedLesson.value.buttonText.isNotEmpty()) savedLesson.value.buttonText[9] else "",
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )
        }
    }

}


/*
  val buttonData1 = remember { mutableStateOf("") }
val buttonData2 = remember { mutableStateOf("") }
val buttonData3 = remember { mutableStateOf("") }
val buttonData4 = remember { mutableStateOf("") }
val buttonData5 = remember { mutableStateOf("") }
val buttonData6 = remember { mutableStateOf("") }
val buttonData7 = remember { mutableStateOf("") }
val buttonData8 = remember { mutableStateOf("") }
val buttonData9 = remember { mutableStateOf("") }
val buttonData10 = remember { mutableStateOf("") }

if (flag.value) {
    learningProcessor.randomizer()

    buttonData1.value = learningProcessor.buttonText[0]
    buttonData2.value = learningProcessor.buttonText[1]
    buttonData3.value = learningProcessor.buttonText[2]
    buttonData4.value = learningProcessor.buttonText[3]
    buttonData5.value = learningProcessor.buttonText[4]
    buttonData6.value = learningProcessor.buttonText[5]
    buttonData7.value = learningProcessor.buttonText[6]
    buttonData8.value = learningProcessor.buttonText[7]
    buttonData9.value = learningProcessor.buttonText[8]
    buttonData10.value = learningProcessor.buttonText[9]
}


when {
    textForScreen.value.length > 7 -> {
        flag.value = true
        textForScreen.value = ""
        learningProcessor.randomizer()

        buttonData1.value = learningProcessor.buttonText[0]
        buttonData2.value = learningProcessor.buttonText[1]
        buttonData3.value = learningProcessor.buttonText[2]
        buttonData4.value = learningProcessor.buttonText[3]
        buttonData5.value = learningProcessor.buttonText[4]
        buttonData6.value = learningProcessor.buttonText[5]
        buttonData7.value = learningProcessor.buttonText[6]
        buttonData8.value = learningProcessor.buttonText[7]
        buttonData9.value = learningProcessor.buttonText[8]
        buttonData10.value = learningProcessor.buttonText[9]
    }

    textForScreen.value.isNotEmpty() -> flag.value = false

}


*/