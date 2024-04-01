package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.database.LessonDatabase
import aldmitry.dev.learningenglish.database.UserLesson
import aldmitry.dev.learningenglish.presenter.DbTextEditor
import aldmitry.dev.learningenglish.ui.theme.Blue10
import aldmitry.dev.learningenglish.ui.theme.Blue15
import aldmitry.dev.learningenglish.ui.theme.Blue30
import aldmitry.dev.learningenglish.ui.theme.Red30
import aldmitry.dev.learningenglish.ui.theme.Yellow30
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextEditionView(lessonTitle: String, database: LessonDatabase) { //

    val flagOfDelete = remember {
        mutableStateOf(false)
    }

    val originIdText = remember {
        mutableStateOf("")
    }

    val russianText = remember {
        mutableStateOf("")
    }

    val englishText = remember {
        mutableStateOf("")
    }

    val lessonList = remember {
        mutableStateOf<List<UserLesson>>(listOf())
    }

    val isValidText = englishText.value.length < 70 && !englishText.value.contains("  ") &&
            englishText.value.split(" ").size <= 10 &&
            russianText.value.length < 70 && !russianText.value.contains("  ") &&
            russianText.value.split(" ").size <= 10

    val isTextNotEmpty = russianText.value.isNotEmpty() && englishText.value.isNotEmpty()

        Column(
        modifier = Modifier
            .background(Blue15)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(0.5F)
                .fillMaxWidth()
                .background(Blue10) // цвет фона между рядами клавиш
                .padding(bottom = 10.dp)
        ) {
            itemsIndexed(lessonList.value) { _, lesson ->
                UserLessonPage(
                    lesson,
                    database,
                    originIdText,
                    englishText,
                    russianText,
                    flagOfDelete
                )
            }
        }

        Column(
            modifier = Modifier
                .background(Blue15)
                .weight(0.5F)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom, // verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextButton(
                onClick = {
                    if (isTextNotEmpty && isValidText) {
                    val handler = DbTextEditor(lessonTitle, database)
                        englishText.value = englishText.value.trim()
                        russianText.value = russianText.value.trim()

                    if (originIdText.value == englishText.value) {
                        handler.addLesson(englishText.value, russianText.value)
                    } else {
                        handler.deleteLesson(originIdText.value, russianText.value)
                        handler.addLesson(englishText.value, russianText.value)
                    }
                    originIdText.value = ""
                    englishText.value = ""
                    russianText.value = ""
                    }
                },
                modifier = Modifier
                    .padding(bottom = 20.dp, top = 20.dp)
                    .background(Blue15),
                colors = ButtonDefaults.textButtonColors(containerColor = if (isTextNotEmpty && isValidText) Blue30 else Blue10),
                border = BorderStroke(1.dp, Color.White)
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = if (originIdText.value.isEmpty()) "Добавить текст" else "Редактировать",
                    style = TextStyle(color = Color.White, fontSize = 18.sp)
                )
            }

            TextField( // TODO поведение клавиатуры
                placeholder = {
                    Text(
                        text = "✎ Введите текст на русском языке",
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                },
                value = russianText.value,
                onValueChange = { if (isValidText) russianText.value = it else russianText.value = it.dropLast(1) },
                modifier = Modifier
                    .background(Blue10)
                    .border(10.dp, Blue10)
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                textStyle = TextStyle(fontSize = 18.sp, color = if (isValidText) Color.White else Red30),
                colors = TextFieldDefaults.outlinedTextFieldColors(Blue10)

            )

            TextField(
                placeholder = {
                    Text(
                        text = "✎ Введите текст на английском языке",
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                },
                value = englishText.value,
                onValueChange = { if (isValidText) englishText.value = it else englishText.value = it.dropLast(1) },
                modifier = Modifier
                    .background(Blue10)
                    .border(10.dp, Blue10)
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                    .weight(0.3F),
                textStyle = TextStyle(fontSize = 18.sp, color = if (isValidText)Color.White else Red30),
                colors = TextFieldDefaults.outlinedTextFieldColors(Blue10)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Blue10),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    modifier = Modifier
                        .background(Blue10)
                        .padding(10.dp),
                    text = "$lessonTitle, текстов добавлено: ${lessonList.value.size}",
                    style = TextStyle(color = Yellow30, fontSize = 14.sp),
                )
            }

            Spacer(
                modifier = Modifier
                    .width(10.dp)
                    .background(Blue15)
                    .weight(0.1F)
            )
        }
    }

    Thread { lessonList.value = database.lessonsDao().receiveLessons()
        .filter { it.lessonTitle == lessonTitle }.sortedBy { it.russianText } }.start()
    flagOfDelete.value = !flagOfDelete.value

}
