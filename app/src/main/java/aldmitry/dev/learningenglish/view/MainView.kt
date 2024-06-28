package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.R
import aldmitry.dev.learningenglish.info_view
import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.model.lessons.Compares
import aldmitry.dev.learningenglish.model.lessons.DateAndTime
import aldmitry.dev.learningenglish.model.lessons.MuchMany
import aldmitry.dev.learningenglish.model.lessons.PassiveVoice
import aldmitry.dev.learningenglish.model.lessons.PerfectTense
import aldmitry.dev.learningenglish.model.lessons.PresentContinuous
import aldmitry.dev.learningenglish.model.lessons.PresentSimple
import aldmitry.dev.learningenglish.model.lessons.VariousTexts
import aldmitry.dev.learningenglish.model.lessons.WordsForLearning
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.settings_view
import aldmitry.dev.learningenglish.textEditionDictionary_view
import aldmitry.dev.learningenglish.ui.theme.Blue10
import aldmitry.dev.learningenglish.ui.theme.Blue15
import aldmitry.dev.learningenglish.ui.theme.Blue30
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainView(
    buttonClick: () -> Unit,
    controllerText: MutableState<String>,
    chooseLesson: MutableState<Learnable>,
    chooseLearningTypeSection: MutableState<LearningTypeSection>
) {

    val lessonCategories = listOf(
        PresentContinuous(), PresentSimple(), PassiveVoice(),
        DateAndTime(), PerfectTense(), MuchMany(), Compares(), VariousTexts(), WordsForLearning()
    )

    Column(
        modifier = Modifier
            .background(Blue10)
    ) {
        Column(
            modifier = Modifier
                .background(Blue15)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .background(Blue15)
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        controllerText.value = info_view
                        buttonClick()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.info_button),
                        contentDescription = "info_pic",
                        tint = Color.White
                    )
                }

                IconButton(
                    onClick = {
                        controllerText.value = settings_view
                        buttonClick()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings_button),
                        contentDescription = "settings_pic",
                        tint = Color.White
                    )
                }
            }

            TextButton(
                onClick = {
                    controllerText.value = textEditionDictionary_view // TODO
                    buttonClick()
                },
                modifier = Modifier
                    .padding(bottom = 5.dp, start = 3.dp, end = 3.dp)
                    .background(Blue15),
                // colors = ButtonDefaults.textButtonColors(containerColor = if (chooseLearningTypeSection.value == LearningTypeSection.DICTIONARY_TEXTS) Blue30 else Blue15),
                colors = ButtonDefaults.textButtonColors(containerColor = if (controllerText.value == "textEdition_view") Blue30 else Blue15),
                border = BorderStroke(
                    3.dp,
                    // if (chooseLearningTypeSection.value == LearningTypeSection.DICTIONARY_TEXTS) Color.White else Blue15
                    if (controllerText.value == "textEdition_view") Color.White else Blue15
                )
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    textAlign = TextAlign.Center,
                    text = LearningTypeSection.DICTIONARY_TEXTS.title, // "Словарь"
                    style = TextStyle(color = Color.White, fontSize = 20.sp) // 18
                )
            }

            TextButton(
                onClick = { chooseLearningTypeSection.value = LearningTypeSection.USERS_TEXTS },
                modifier = Modifier
                    .padding(bottom = 5.dp, start = 3.dp, end = 3.dp)
                    .background(Blue15),
                colors = ButtonDefaults.textButtonColors(containerColor = if (chooseLearningTypeSection.value == LearningTypeSection.USERS_TEXTS) Blue30 else Blue15),
                border = BorderStroke(
                    3.dp,
                    if (chooseLearningTypeSection.value == LearningTypeSection.USERS_TEXTS) Color.White else Blue15
                ) // Blue30  Blue10  Color.White

            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    textAlign = TextAlign.Center,
                    text = LearningTypeSection.USERS_TEXTS.title, // "Только собственные тексты"
                    style = TextStyle(color = Color.White, fontSize = 20.sp) // 18
                )
            }

            TextButton(
                onClick = { chooseLearningTypeSection.value = LearningTypeSection.UNITED_TEXTS },
                modifier = Modifier
                    .padding(bottom = 10.dp, start = 3.dp, end = 3.dp)
                    .background(Blue15),
                colors = ButtonDefaults.textButtonColors(containerColor = if (chooseLearningTypeSection.value == LearningTypeSection.UNITED_TEXTS) Blue30 else Blue15),
                border = BorderStroke(
                    3.dp,
                    if (chooseLearningTypeSection.value == LearningTypeSection.UNITED_TEXTS) Color.White else Blue15
                ) // , shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    textAlign = TextAlign.Center,
                    text = LearningTypeSection.UNITED_TEXTS.title, // "Тексты приложения и собственные"
                    style = TextStyle(color = Color.White, fontSize = 20.sp) // 18
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue10) // цвет фона между рядами клавиш
                .padding(bottom = 10.dp)
        ) {
            itemsIndexed(lessonCategories) { _, lesson ->
                LessonPage(buttonClick, controllerText, chooseLesson, lesson)
            }
        }
    }
}






