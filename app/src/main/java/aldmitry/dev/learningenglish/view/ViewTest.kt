package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.ui.theme.Blue10
import aldmitry.dev.learningenglish.ui.theme.Blue15
import aldmitry.dev.learningenglish.ui.theme.Blue50
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun TrainingScreen() {

    Column(
        modifier = Modifier
            .background(Blue15)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom // Bottom
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = "screenText.value", // "Введите перевод текста:
            style = TextStyle(color = Color.White, fontSize = 20.sp)
        )

        Text(
            modifier = Modifier.padding(10.dp),
            text = "underScreenText.value", // "Введите перевод текста:
            style = TextStyle(color = Color.White, fontSize = 23.sp)
        )

        Column(
            modifier = Modifier
                .background(Blue15)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            //// verticalArrangement = Arrangement.Top //////////////////////////////////////
        ) {
            Text(
                modifier = Modifier.padding(top = 60.dp, start = 25.dp, end = 25.dp),
                text = "inputtedText.value",
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )
        }

        Spacer(
            modifier = Modifier
                .background(Blue15)
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        )


// if(savedLessonUnit.value.buttonText.isNotEmpty()) TrainingKeyboard(savedLessonUnit, inputtedText)


                Column(
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .background(Blue10)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {

                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        TextButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(10.dp)
                                .background(Blue10),
                            colors = ButtonDefaults.textButtonColors(containerColor = Blue50),
                            border = BorderStroke(2.dp, Color.White) // Blue30  Blue10  Color.White
                        ) {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = "Редактировать текст",
                                style = TextStyle(color = Color.White, fontSize = 18.sp)
                            )
                        }

                    }
        Spacer(
            modifier = Modifier
                .background(Blue15)
                .fillMaxWidth()
                .padding(bottom = 40.dp)
        )

        Column(
            modifier = Modifier
                .background(Blue10)
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                        },
                    text = "#",
                    style = TextStyle(color = Color.White, fontSize = 20.sp)
                )

                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                        },
                    text = "#",
                    style = TextStyle(color = Color.White, fontSize = 20.sp)
                )

                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                        },
                    text = "#",
                    style = TextStyle(color = Color.White, fontSize = 20.sp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                        },
                    text = "#",
                    style = TextStyle(color = Color.White, fontSize = 20.sp)
                )

                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                        },
                    text = "#",
                    style = TextStyle(color = Color.White, fontSize = 20.sp)
                )

                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                        },
                    text = "#",
                    style = TextStyle(color = Color.White, fontSize = 20.sp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                        },
                    text = "#",
                    style = TextStyle(color = Color.White, fontSize = 20.sp)
                )

                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                        },
                    text = "#",
                    style = TextStyle(color = Color.White, fontSize = 20.sp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                        },
                    text = "#",
                    style = TextStyle(color = Color.White, fontSize = 20.sp)
                )

                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                        },
                    text = "#",
                    style = TextStyle(color = Color.White, fontSize = 20.sp)
                )
            }
        }
    }
}
}