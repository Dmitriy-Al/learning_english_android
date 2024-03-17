package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.ui.theme.Blue15
import androidx.compose.foundation.background
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


@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .background(Blue15)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier.padding(5.dp),
            text = "Меню настроек",
            style = TextStyle(color = Color.White, fontSize = 25.sp)
        )
        Text(
            modifier = Modifier.padding(5.dp),
            text = "Вкл.",
            style = TextStyle(color = Color.White, fontSize = 18.sp)
        )
        Text(
            modifier = Modifier.padding(5.dp),
            text = "Выкл.",
            style = TextStyle(color = Color.White, fontSize = 18.sp)
        )
    }


}