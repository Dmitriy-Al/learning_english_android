package aldmitry.dev.learningenglish

import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.database.UserLesson
import aldmitry.dev.learningenglish.model.lessons.Compares
import aldmitry.dev.learningenglish.model.lessons.DateAndTime
import aldmitry.dev.learningenglish.model.lessons.MuchMany
import aldmitry.dev.learningenglish.model.lessons.PassiveVoice
import aldmitry.dev.learningenglish.model.lessons.PerfectTense
import aldmitry.dev.learningenglish.model.lessons.PresentContinuous
import aldmitry.dev.learningenglish.model.lessons.PresentSimple
import aldmitry.dev.learningenglish.model.lessons.VariousTexts
import aldmitry.dev.learningenglish.model.lessons.WordsForLearning
import aldmitry.dev.learningenglish.presenter.LearningHandler
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonUnit
import aldmitry.dev.learningenglish.presenter.LessonsRepository
import aldmitry.dev.learningenglish.ui.theme.Blue10
import aldmitry.dev.learningenglish.ui.theme.Blue15
import aldmitry.dev.learningenglish.ui.theme.Blue30
import aldmitry.dev.learningenglish.view.InfoView
import aldmitry.dev.learningenglish.view.LessonPage
import aldmitry.dev.learningenglish.view.SettingsScreen
import aldmitry.dev.learningenglish.view.TextEditionView
import aldmitry.dev.learningenglish.view.TrainingView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        DbInitializer.init(applicationContext)
        val repository = DbInitializer.lessonsRepository

        super.onCreate(savedInstanceState)

        setContent {

            val chooseLearningTypeSection = remember {
                mutableStateOf(LearningTypeSection.UNITED_TEXTS)
            }

            val controllerText = remember {
                mutableStateOf("")
            }

            val lesson = remember {
                mutableStateOf<Learnable>(PresentSimple())
            }

            val userLessons = remember {
                mutableStateOf<List<UserLesson>>(listOf())
            }

            LaunchedEffect(lesson.value) {//  onUserLessonsChange.value  chooseLearningTypeSection.value ++lesson.value, controllerText.value
                CoroutineScope(Job() + Dispatchers.Default).launch {
                    receiveUserLessons(repository, userLessons, lesson.value.receiveTitle())
                }
            }

            val navController = rememberNavController();

            NavHost(navController = navController, startDestination = mainScreen_view) {
                composable(mainScreen_view) { // composable - добавляет компонуемый объект в NavGraphBuilder
                    Screen(
                        { navController.navigate(controllerText.value) },
                        controllerText,
                        lesson,
                        chooseLearningTypeSection
                    )
                    controllerText.value = "" // сброс изменения цветовой схемы кнопки "Словарь"
                }

                composable(addTextScreen_view) { // Экран добавления текста
                    TextEditionView(lesson.value.receiveTitle(), repository)
                }

                composable(info_view) { // Экран инфо // TODO
                    InfoView("Создатель приложения:\nАлимов дмитрий\n\nсервера:\nШкитина Александра")
                }

                composable(settings_view) { // Экран настроек
                    SettingsScreen()
                }

                composable("textEdition_view") {
                    TextEditionView(LearningTypeSection.DICTIONARY_TEXTS.title, repository) // "Разные"  "Словарь"
                }

                composable("training_view") {// "TRAINING_VIEW"
                    val learningHandler = LearningHandler(
                        chooseLearningTypeSection.value,
                        lesson.value,
                        userLessons.value
                    )
                    val lessonUnits: List<LessonUnit> = learningHandler.receiveLessonTextCollector()

                    if (lessonUnits.isEmpty()) InfoView("\n\n\n\n\n\nУ вас ещё нет добавленных слов в этой категории") else TrainingView(lessonUnits, lesson.value.receiveHintPictureId())
                }

                userLessons.value = listOf()
            }
        }
    }
}


@Composable
fun Screen(
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
                    controllerText.value = "textEdition_view" // TODO
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
                    text = LearningTypeSection.DICTIONARY_TEXTS.title, // "Добавить собственный текст"
                    style = TextStyle(color = Color.White, fontSize = 18.sp)
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
                    text = LearningTypeSection.USERS_TEXTS.title, // "Добавить собственный текст"
                    style = TextStyle(color = Color.White, fontSize = 18.sp)
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
                    text = LearningTypeSection.UNITED_TEXTS.title, // "Добавить собственный текст"
                    style = TextStyle(color = Color.White, fontSize = 18.sp)
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


suspend fun receiveUserLessons(
    repository: LessonsRepository,
    userLessons: MutableState<List<UserLesson>>, lessonTitle: String
) {
    CoroutineScope(Job()).launch {
        repository.receiveLessonsByTitle(userLessons, lessonTitle)
    }
}


const val mainScreen_view = "MAIN_SCREEN_VIEW"
const val addTextScreen_view = "ADD_TEXT_VIEW"
const val training_view = "TRAINING_VIEW"
const val settings_view = "SETTINGS_VIEW"
const val info_view = "INFO_VIEW"

