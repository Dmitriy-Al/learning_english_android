package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.addTextScreen_view
import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.training_view
import aldmitry.dev.learningenglish.ui.theme.Blue10
import aldmitry.dev.learningenglish.ui.theme.Green50
import aldmitry.dev.learningenglish.ui.theme.Yellow30
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// @Preview(showBackground = true)
@Composable
fun LessonPage(buttonClick: () -> Unit, controllerText: MutableState<String>, chooseLesson: MutableState<Learnable>, lesson: Learnable) {
    Card(
        elevation = 5.dp,
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .clickable {
                controllerText.value = training_view
                chooseLesson.value = lesson
                buttonClick()
            },
        backgroundColor = Green50,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = lesson.receiveTitle(),
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1F)
                    .padding(10.dp)
            )

            TextButton(
                colors = ButtonDefaults.textButtonColors(contentColor = Yellow30),
                modifier = Modifier
                    .padding(3.dp)
                    .background(Yellow30, RoundedCornerShape(10.dp)),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    controllerText.value = addTextScreen_view
                    chooseLesson.value = lesson
                    buttonClick()
                   }
            ) {
                Text(
                    text = " Добавить\nсвой текст",
                    color = Blue10,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////


@Preview(showBackground = true)
@Composable
fun LessonPage() {
    Card(
        elevation = 5.dp,
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .clickable {

            },
        backgroundColor = Green50,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Present Simple",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1F)
                    .padding(10.dp)
            )

            TextButton(
                colors = ButtonDefaults.textButtonColors(contentColor = Yellow30),
                modifier = Modifier
                    .padding(3.dp)
                    .background(Yellow30, RoundedCornerShape(10.dp)),
                border = BorderStroke(2.dp, Color.White),
                shape = RoundedCornerShape(10.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = " Добавить\nсвой текст",
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }

        }
    }
}