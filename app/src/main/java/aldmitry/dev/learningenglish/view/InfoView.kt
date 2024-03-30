package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.ui.theme.Blue30
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// @Preview
@Composable
fun InfoView(text: String) { // onClick: () -> Unit
    Column(
        modifier = Modifier
            .background(Blue30)
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Box(
            modifier = Modifier
                .background(Blue30)
                .fillMaxSize()
                .border(3.dp, Color.White)
        ) {
            Text(
                modifier = Modifier.padding(15.dp),
                text = text,
                style = TextStyle(color = Color.White, fontSize = 25.sp)
            )
            /*
            Text(
                modifier = Modifier.padding(5.dp),
                text = "Разработчик: Алимов Дмитрий Викторович, dmitr@u.ru",
                style = TextStyle(color = Color.White, fontSize = 18.sp)
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = "Разработчик сервера: Шкитина Александра, sasha@u.ru",
                style = TextStyle(color = Color.White, fontSize = 18.sp)
            )

             */
        }

    }

}