package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.database.LessonDatabase
import aldmitry.dev.learningenglish.database.UserLesson
import aldmitry.dev.learningenglish.presenter.LessonsRepository
import aldmitry.dev.learningenglish.ui.theme.Blue10
import aldmitry.dev.learningenglish.ui.theme.Green50
import aldmitry.dev.learningenglish.ui.theme.Green60
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun UserLessonPage(userLesson: UserLesson, repository: LessonsRepository,
                   originIdText: MutableState<String>, englishText: MutableState<String>,
                   russianText: MutableState<String>, changeKey: MutableState<Int>) {

    val cardColor = remember {
        mutableStateOf(Green50)
    }

    val cardValueChange = remember {
        mutableStateOf("0")
    }

    Card(
        modifier = Modifier
            .background(Blue10)
            .height(150.dp)
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(cardColor.value),
        border = BorderStroke(1.dp, Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "• ${userLesson.russianText}\n\n• ${userLesson.englishText}",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1F)
                    .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                IconButton(
                    onClick = {
                        originIdText.value = userLesson.englishText
                        englishText.value = userLesson.englishText
                        russianText.value = userLesson.russianText
                        cardValueChange.value = originIdText.value
                        cardColor.value = Green60
                    },
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1F)
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
                }

                IconButton(
                    onClick = {
                        CoroutineScope(Job() + Dispatchers.IO).launch {
                            repository.deleteLesson(userLesson.englishText, userLesson.russianText, userLesson.lessonTitle)
                        }
                        changeKey.value++
                    },
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1F)
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }

        }
    }

    cardColor.value = if (cardValueChange.value == originIdText.value) Green60 else Green50

}
