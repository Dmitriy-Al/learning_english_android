package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.ui.theme.Blue15
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun InfoScreen() { // onClick: () -> Unit
    Column(
        modifier = Modifier
            .background(Blue15)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier.padding(5.dp),
            text = "Информация о приложении English Learning",
            style = TextStyle(color = Color.White, fontSize = 25.sp)
        )
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
    }

}