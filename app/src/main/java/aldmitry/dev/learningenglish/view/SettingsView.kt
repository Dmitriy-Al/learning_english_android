package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.R
import aldmitry.dev.learningenglish.model.settings.DataStoreHandler
import aldmitry.dev.learningenglish.model.settings.SettingsData
import aldmitry.dev.learningenglish.ui.theme.Blue15
import aldmitry.dev.learningenglish.ui.theme.Blue30
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// @Preview
@Composable
fun SettingsView(dataStoreHandler: DataStoreHandler, settings: MutableState<SettingsData>) {

    val switch = remember {
        mutableStateOf(settings.value.repeatWrongLesson)
    }

    val slide = remember {
        mutableStateOf(settings.value.answerShowTime)
    }

    Column(
        modifier = Modifier
            .background(Blue15)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = "Меню настроек",
            style = TextStyle(color = Color.White, fontSize = 25.sp)
        )

        Text(
            modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp, bottom = 5.dp),
            text = "Включить/отключить повторное выполнение задачи при неверном ответе",
            style = TextStyle(color = Color.White, fontSize = 18.sp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Выключить",
                style = TextStyle(color = Color.White, fontSize = 14.sp)
            )

            Switch(
                checked = switch.value,
                onCheckedChange = {
                    CoroutineScope(Job()).launch {
                        dataStoreHandler.saveSettings(
                            SettingsData(
                                !settings.value.repeatWrongLesson,
                                settings.value.answerShowTime
                            )
                        )
                    }
                    switch.value = !switch.value
                },
                modifier = Modifier
                    .background(Blue15),
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White, // цвет переключателя
                    checkedTrackColor = Blue30, // цвет переключателя
                    checkedBorderColor = Color.White, // цвет рамки переключателя
                    // 
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Blue30,
                    uncheckedBorderColor = Color.White,
                )
            )

            Text(
                modifier = Modifier.padding(10.dp),
                text = "Включить",
                style = TextStyle(color = Color.White, fontSize = 14.sp)
            )
        }

        Text(
            modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp, bottom = 5.dp),
            text = "Время демонстрации правильного ответа",
            style = TextStyle(color = Color.White, fontSize = 18.sp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if (settings.value.answerShowTime > 0) {
                        slide.value = settings.value.answerShowTime - 1
                        CoroutineScope(Job()).launch {
                            dataStoreHandler.saveSettings(
                                SettingsData(
                                    settings.value.repeatWrongLesson,
                                    settings.value.answerShowTime - 1
                                )
                            )
                        }
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "",
                    tint = Color.White
                )
            }

            Text(
                modifier = Modifier.padding(10.dp),
                text = "${slide.value} сек.",
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )

            IconButton(
                onClick = {
                    if (settings.value.answerShowTime < 5) {
                        slide.value = settings.value.answerShowTime + 1
                        CoroutineScope(Job()).launch {
                            dataStoreHandler.saveSettings(
                                SettingsData(
                                    settings.value.repeatWrongLesson,
                                    settings.value.answerShowTime + 1
                                )
                            )
                        }
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    }

}