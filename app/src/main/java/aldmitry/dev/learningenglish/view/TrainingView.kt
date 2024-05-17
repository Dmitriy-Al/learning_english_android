package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.presenter.LearningProcessor
import aldmitry.dev.learningenglish.presenter.LessonUnit
import aldmitry.dev.learningenglish.ui.theme.Blue10
import aldmitry.dev.learningenglish.ui.theme.Blue15
import aldmitry.dev.learningenglish.ui.theme.Green30
import aldmitry.dev.learningenglish.ui.theme.Green50
import aldmitry.dev.learningenglish.ui.theme.Red30
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


const val answer_field = "ANSWER"
const val input_field = "INPUT_FIELD"
const val keyboard_field = "KEYBOARD_FIELD"
const val topText_field = "Переведите текст:\n"


@Composable
fun TrainingView(lessonUnits: List<LessonUnit>) {

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

    val bottomScreenText = remember { // второй текст сообщения главного экрана
        mutableStateOf("")
    }

    val answerCounter = remember { // счетчик
        mutableStateOf(0)
    }


    LaunchedEffect(topScreenText.value, bottomScreenText.value) {
        CoroutineScope(Dispatchers.IO).launch {
            LearningProcessor(
                lessonUnit,
                keyBoardField,
                isAnswer,
                answerCounter,
                topScreenText,
                bottomScreenText,
                lessonUnits
            ).process()
        }
    }


    Column(
        modifier = Modifier
            .background(Blue15)
            .fillMaxSize()
            .clickable(
                MutableInteractionSource(),
                indication = rememberRipple(
                    bounded = false,
                    radius = 0.dp,
                    color = Blue15
                ),
                onClick = {
                    if(isAnswer.value) topScreenText.value = ""
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 5.dp, start = 20.dp),
                text = "Ответов: ${answerCounter.value}",
                style = TextStyle(color = Color.White, fontSize = 18.sp),
                textAlign = TextAlign.Start
            )
        }

        Column(
            modifier = Modifier
                //.background(Blue15)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 10.dp, start = 30.dp, end = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = topScreenText.value,
                    style = TextStyle(
                        color = if (keyBoardField.value == answer_field) Green30 else Color.White,
                        fontSize = 23.sp
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp, start = 30.dp, end = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = bottomScreenText.value,
                    style = TextStyle(
                        color = if (isAnswer.value) Red30 else Color.White,
                        fontSize = 23.sp
                    )
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                //.background(Blue15)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            when (keyBoardField.value) {
                keyboard_field -> Keyboard(lessonUnit.value.keyButtonsWords, bottomScreenText)
                input_field -> InputLesson(topScreenText, bottomScreenText)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputLesson(forTranslateText: MutableState<String>, inputtedText: MutableState<String>) {

    val textFromField = remember {
        mutableStateOf("")
    }

    val isTextValid = textFromField.value.length < 70


    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(Blue15)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        TextButton(
            onClick = {
                if (isTextValid) inputtedText.value = textFromField.value
            },
            modifier = Modifier
                .padding(bottom = 10.dp, top = 30.dp)
                .background(Blue15),
            colors = ButtonDefaults.textButtonColors(containerColor = if (textFromField.value.isEmpty() || !isTextValid) Blue10 else Green50),
            border = BorderStroke(2.dp, Color.White)
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Проверить",
                style = TextStyle(color = Color.White, fontSize = 18.sp)
            )
        }
    }

    Column(
        modifier = Modifier
            .background(Blue10)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        TextField(
            placeholder = {
                Text(
                    text = "✎ Введите перевод текста: ${forTranslateText.value.replace(topText_field, "")}",
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
            },
            value = textFromField.value,
            onValueChange = { textFromField.value = if (isTextValid) it else it.dropLast(1) },
            modifier = Modifier
                .background(Blue10)
                .border(10.dp, Blue10)
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 30.dp),
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = if (isTextValid) Color.White else Red30
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(Blue10)
        )
    }
    Spacer(
        modifier = Modifier.padding(bottom = 10.dp)
    )
}


@Composable
fun Keyboard(keyList: List<String>, inputtedText: MutableState<String>) {

    val buttonsSet = remember {
        mutableStateListOf(
            keyList[0],
            keyList[1],
            keyList[2],
            keyList[3],
            keyList[4],
            keyList[5],
            keyList[6],
            keyList[7],
            keyList[8],
            keyList[9]
        )
    }

    val buttonIndexCounter = remember { // счетчик нажатых клавиш
        mutableStateListOf<Int>()
    }


    Column(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(
            onClick = {
                if (buttonIndexCounter.isNotEmpty()) {
                    val lastIndex = buttonIndexCounter.removeLast()
                    inputtedText.value = inputtedText.value.replace(" ${keyList[lastIndex]}", "")
                    buttonsSet[lastIndex] = keyList[lastIndex]
                }
            },
            modifier = Modifier
                .padding(5.dp)
                .background(Blue15),
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

    Column(
        modifier = Modifier
            .background(Blue15) // Blue10
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

    ) {

        Row(
            modifier = Modifier
                .padding(top = 20.dp, start = 3.dp, end = 3.dp, bottom = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier
                    .padding(1.dp)
                    .clickable(
                        MutableInteractionSource(),
                        indication = rememberRipple(
                            bounded = false,
                            radius = 40.dp,
                            color = Blue10
                        ),
                        onClick = {
                            if (buttonsSet[0].isNotEmpty()) {
                                inputtedText.value += " ${buttonsSet[0]}"
                                buttonsSet[0] = ""
                                buttonIndexCounter.add(0)
                            }
                        }),
                text = buttonsSet[0],
                style = TextStyle(
                    color = Color.White,
                    fontSize = if (buttonsSet[0].length > 12) 12.sp else 20.sp
                )
            )

            Text(
                modifier = Modifier
                    .padding(1.dp)
                    .clickable(
                        MutableInteractionSource(),
                        indication = rememberRipple(
                            bounded = false,
                            radius = 40.dp,
                            color = Blue10
                        ),
                        onClick = {
                            if (buttonsSet[1].isNotEmpty()) {
                                inputtedText.value += " ${buttonsSet[1]}"
                                buttonsSet[1] = ""
                                buttonIndexCounter.add(1)
                            }
                        }),
                text = buttonsSet[1],
                style = TextStyle(
                    color = Color.White,
                    fontSize = if (buttonsSet[1].length > 12) 12.sp else 20.sp
                )
            )
            Text(
                modifier = Modifier
                    .padding(1.dp)
                    .clickable(
                        MutableInteractionSource(),
                        indication = rememberRipple(
                            bounded = false,
                            radius = 40.dp,
                            color = Blue10
                        ),
                        onClick = {
                            if (buttonsSet[2].isNotEmpty()) {
                                inputtedText.value += " ${buttonsSet[2]}"
                                buttonsSet[2] = ""
                                buttonIndexCounter.add(2)
                            }
                        }),
                text = buttonsSet[2],
                style = TextStyle(
                    color = Color.White,
                    fontSize = if (buttonsSet[2].length > 12) 12.sp else 20.sp
                )
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 10.dp, start = 3.dp, end = 3.dp, bottom = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                modifier = Modifier
                    .padding(1.dp)
                    .clickable(
                        MutableInteractionSource(),
                        indication = rememberRipple(
                            bounded = false,
                            radius = 40.dp,
                            color = Blue10
                        ),
                        onClick = {
                            if (buttonsSet[3].isNotEmpty()) {
                                inputtedText.value += " ${buttonsSet[3]}"
                                buttonsSet[3] = ""
                                buttonIndexCounter.add(3)
                            }
                        }),
                text = buttonsSet[3],
                style = TextStyle(
                    color = Color.White,
                    fontSize = if (buttonsSet[3].length > 12) 12.sp else 20.sp
                )
            )

            Text(
                modifier = Modifier
                    .padding(1.dp)
                    .clickable(
                        MutableInteractionSource(),
                        indication = rememberRipple(
                            bounded = false,
                            radius = 40.dp,
                            color = Blue10
                        ),
                        onClick = {
                            if (buttonsSet[4].isNotEmpty()) {
                                inputtedText.value += " ${buttonsSet[4]}"
                                buttonsSet[4] = ""
                                buttonIndexCounter.add(4)
                            }
                        }),
                text = buttonsSet[4],
                style = TextStyle(
                    color = Color.White,
                    fontSize = if (buttonsSet[4].length > 12) 12.sp else 20.sp
                )
            )

            Text(
                modifier = Modifier
                    .padding(1.dp)
                    .clickable(
                        MutableInteractionSource(),
                        indication = rememberRipple(
                            bounded = false,
                            radius = 40.dp,
                            color = Blue10
                        ),
                        onClick = {
                            if (buttonsSet[5].isNotEmpty()) {
                                inputtedText.value += " ${buttonsSet[5]}"
                                buttonsSet[5] = ""
                                buttonIndexCounter.add(5)
                            }
                        }),
                text = buttonsSet[5],
                style = TextStyle(
                    color = Color.White,
                    fontSize = if (buttonsSet[5].length > 12) 12.sp else 20.sp
                )
            )

        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp, start = 3.dp, end = 3.dp, bottom = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                modifier = Modifier
                    .padding(1.dp)
                    .clickable(
                        MutableInteractionSource(),
                        indication = rememberRipple(
                            bounded = false,
                            radius = 40.dp,
                            color = Blue10
                        ),
                        onClick = {
                            if (buttonsSet[6].isNotEmpty()) {
                                inputtedText.value += " ${buttonsSet[6]}"
                                buttonsSet[6] = ""
                                buttonIndexCounter.add(6)
                            }
                        }),
                text = buttonsSet[6],
                style = TextStyle(
                    color = Color.White,
                    fontSize = if (buttonsSet[6].length > 12) 12.sp else 20.sp
                )
            )

            Text(
                modifier = Modifier
                    .padding(1.dp)
                    .clickable(
                        MutableInteractionSource(),
                        indication = rememberRipple(
                            bounded = false,
                            radius = 40.dp,
                            color = Blue10
                        ),
                        onClick = {
                            if (buttonsSet[7].isNotEmpty()) {
                                inputtedText.value += " ${buttonsSet[7]}"
                                buttonsSet[7] = ""
                                buttonIndexCounter.add(7)
                            }
                        }),
                text = buttonsSet[7],
                style = TextStyle(
                    color = Color.White,
                    fontSize = if (buttonsSet[7].length > 12) 12.sp else 20.sp
                )
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 10.dp, start = 3.dp, end = 3.dp, bottom = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier
                    .padding(1.dp)
                    .clickable(
                        MutableInteractionSource(),
                        indication = rememberRipple(
                            bounded = false,
                            radius = 40.dp,
                            color = Blue10
                        ),
                        onClick = {
                            if (buttonsSet[8].isNotEmpty()) {
                                inputtedText.value += " ${buttonsSet[8]}"
                                buttonsSet[8] = ""
                                buttonIndexCounter.add(8)
                            }
                        }),
                text = buttonsSet[8],
                style = TextStyle(
                    color = Color.White,
                    fontSize = if (buttonsSet[8].length > 12) 12.sp else 20.sp
                )
            )
            Text(
                modifier = Modifier   // .indication(InteractionSource(), Indication())          //.hoverable(MutableInteractionSource(), false)
                    .padding(1.dp)
                    .clickable(
                        MutableInteractionSource(),
                        indication = rememberRipple(
                            bounded = false,
                            radius = 40.dp,
                            color = Blue10
                        ),
                        onClick = {
                            if (buttonsSet[9].isNotEmpty()) {
                                inputtedText.value += " ${buttonsSet[9]}"
                                buttonsSet[9] = ""
                                buttonIndexCounter.add(9)
                            }
                        }),
                text = buttonsSet[9],
                style = TextStyle(
                    color = Color.White,
                    fontSize = if (buttonsSet[9].length > 12) 12.sp else 20.sp
                )
            )
        }
    }
}