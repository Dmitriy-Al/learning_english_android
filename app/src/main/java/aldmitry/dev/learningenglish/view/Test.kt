package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.database.UserLesson
import aldmitry.dev.learningenglish.presenter.LessonsRepository
import aldmitry.dev.learningenglish.ui.theme.Blue10
import aldmitry.dev.learningenglish.ui.theme.Blue15
import aldmitry.dev.learningenglish.ui.theme.Green50
import aldmitry.dev.learningenglish.ui.theme.Green60
import aldmitry.dev.learningenglish.ui.theme.Red30
import aldmitry.dev.learningenglish.ui.theme.Yellow30
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@Preview
@Composable
fun UserLessonPag() {

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

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "• userLesson.russianText",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                )
                Text(
                    text = "• userLesson.englishText",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "Изменить",
                        color = Yellow30,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(5.dp)
                    )
                }

                TextButton(
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "В словарь",
                        color = Yellow30,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(5.dp)
                    )
                }

                TextButton(
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "Удалить",
                        color = Yellow30,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(5.dp)
                    )
                }

            }
        }

        /*
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "• userLesson.russianText\n\n• userLesson.englishText",
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
                    onClick = {},
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1F)
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
                }

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1F)
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }

        }
         */
    }
}