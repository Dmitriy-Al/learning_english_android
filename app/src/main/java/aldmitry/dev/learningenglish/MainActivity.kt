package aldmitry.dev.learningenglish

import aldmitry.dev.learningenglish.database.UserLesson
import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.model.aboutApp_text
import aldmitry.dev.learningenglish.model.lessons.PresentSimple
import aldmitry.dev.learningenglish.model.settings.DataStoreHandler
import aldmitry.dev.learningenglish.model.settings.SettingsData
import aldmitry.dev.learningenglish.presenter.LearningHandler
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonUnit
import aldmitry.dev.learningenglish.presenter.LessonsRepository
import aldmitry.dev.learningenglish.view.InfoView
import aldmitry.dev.learningenglish.view.MainView
import aldmitry.dev.learningenglish.view.SettingsView
import aldmitry.dev.learningenglish.view.TextEditionView
import aldmitry.dev.learningenglish.view.TrainingView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
        super.onCreate(savedInstanceState)

        DbInitializer.init(applicationContext)
        val repository = DbInitializer.lessonsRepository
        val dataStoreHandler = DataStoreHandler(this) // инициализация бд с настройками приложения

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

            val settings = remember {
                mutableStateOf(SettingsData(true, 2))
            }

            LaunchedEffect(key1 = true) {// изменение состояния key1 обновляет настройки приложения
                dataStoreHandler.getSettings().collect {
                    settings.value.repeatWrongLesson = it.repeatWrongLesson
                    settings.value.answerShowTime = it.answerShowTime
                }
            }

            LaunchedEffect(lesson.value) {//  onUserLessonsChange.value  chooseLearningTypeSection.value ++lesson.value, controllerText.value
                CoroutineScope(Job() + Dispatchers.Default).launch {
                    receiveUserLessons(repository, userLessons, lesson.value.receiveTitle())
                }
            }

            val navController = rememberNavController();

            NavHost(navController = navController, startDestination = mainScreen_view) {
                composable(mainScreen_view) { // composable - добавляет компонуемый объект в NavGraphBuilder
                    MainView(
                        { navController.navigate(controllerText.value) },
                        controllerText,
                        lesson,
                        chooseLearningTypeSection
                    )
                    controllerText.value = "" // сброс изменения цветовой схемы кнопки "Словарь"
                }

                composable(textEdition_view) { // Экран добавления текста
                    TextEditionView(lesson.value.receiveTitle(), repository)
                }

                composable(info_view) { // Экран инфо // TODO
                    InfoView(aboutApp_text, 17.sp)
                }

                composable(settings_view) { // Экран настроек
                    SettingsView(dataStoreHandler, settings)// TODO
                }

                composable(textEditionDictionary_view) {
                    TextEditionView(
                        LearningTypeSection.DICTIONARY_TEXTS.title,
                        repository
                    ) // "Разные"  "Словарь"
                }

                composable(training_view) {// "TRAINING_VIEW"
                    val learningHandler = LearningHandler(
                        chooseLearningTypeSection.value,
                        lesson.value,
                        userLessons.value
                    )
                    val lessonUnits: List<LessonUnit> = learningHandler.receiveLessonTextCollector()
                    if (lessonUnits.isEmpty()) InfoView(
                        "\n\n\nУ вас ещё нет добавленных слов в этой категории",
                        25.sp
                    ) else TrainingView(lessonUnits, settings, lesson.value.receiveHintPictureId())
                }

                userLessons.value = listOf()
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


const val textEditionDictionary_view = "TEXT_EDITION_VIEW"
const val mainScreen_view = "MAIN_SCREEN_VIEW"
const val textEdition_view = "ADD_TEXT_VIEW"
const val training_view = "TRAINING_VIEW"
const val settings_view = "SETTINGS_VIEW"
const val info_view = "INFO_VIEW"
