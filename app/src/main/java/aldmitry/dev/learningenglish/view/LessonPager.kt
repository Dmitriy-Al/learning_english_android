package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.MainActivity
import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.ui.theme.Blue50
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity



// @Preview(showBackground = true)
@Composable
fun LessonPage(buttonClick: () -> Unit, controllerText: MutableState<String>, chooseLesson: MutableState<Learnable>, lesson: Learnable) {
    Card(
        elevation = 5.dp,
        modifier = Modifier.height(70.dp)
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .clickable {
                controllerText.value = "TRAINING_CLICK"
                chooseLesson.value = lesson
                buttonClick()
            },
        backgroundColor = Blue50,
        shape = RoundedCornerShape(10.dp),
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
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}
