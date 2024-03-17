package aldmitry.dev.learningenglish

import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.model.lessons.PresentSimple
import aldmitry.dev.learningenglish.presenter.LearningHandler
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonsCollection
import aldmitry.dev.learningenglish.ui.theme.Blue10
import aldmitry.dev.learningenglish.ui.theme.Blue15
import aldmitry.dev.learningenglish.ui.theme.Blue30
import aldmitry.dev.learningenglish.view.InfoScreen
import aldmitry.dev.learningenglish.view.LessonPage
import aldmitry.dev.learningenglish.view.SettingsScreen
import aldmitry.dev.learningenglish.view.TrainingScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val chooseLearningTypeSection = remember {
                mutableStateOf(LearningTypeSection.UNITED_TEXTS)
            }

            val controllerText = remember {
                mutableStateOf("")
            }

            val lesson = remember {
                mutableStateOf<Learnable>(PresentSimple()) // TODO <Learnable>
            }

            val navController = rememberNavController();

            NavHost(navController = navController, startDestination = "screen_1") {  //
                composable("screen_1") { // composable - добавляет компонуемый объект в NavGraphBuilder

                    Screen(
                        { navController.navigate(controllerText.value) },
                        controllerText,
                        lesson,
                        chooseLearningTypeSection
                    )
                }

                composable("INFO_CLICK") {
                    InfoScreen()
                }

                composable("SETTINGS_CLICK") {
                    SettingsScreen()
                }

                composable("TRAINING_CLICK") {
                    val learningHandler = LearningHandler(chooseLearningTypeSection.value, lesson.value);
                    TrainingScreen(learningHandler)
                }

            }

        }
    }
}


@Composable
fun Screen(buttonClick: () -> Unit, controllerText: MutableState<String>, chooseLesson: MutableState<Learnable>, chooseLearningTypeSection: MutableState<LearningTypeSection>) {

    val  lessonStorage = LessonsCollection()

    val lessons = lessonStorage.allLessons


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
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        controllerText.value = "INFO_CLICK"
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
                        controllerText.value = "SETTINGS_CLICK"
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
                onClick = { chooseLearningTypeSection.value = LearningTypeSection.ADD_TEXT },
                modifier = Modifier
                    .padding(start = 3.dp, end = 3.dp)
                    //  .fillMaxWidth()
                    .background(Blue15),
                colors = ButtonDefaults.textButtonColors(containerColor = if (chooseLearningTypeSection.value == LearningTypeSection.ADD_TEXT) Blue30 else Blue15),
                border = BorderStroke(1.dp, if (chooseLearningTypeSection.value == LearningTypeSection.ADD_TEXT) Color.White else Blue15) // Blue30  Blue10  Color.White

            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = LearningTypeSection.ADD_TEXT.title, // "Добавить собственный текст"
                    style = TextStyle(color = Color.White, fontSize = 15.sp)
                )
            }

            TextButton(
                onClick = { chooseLearningTypeSection.value = LearningTypeSection.USERS_TEXTS },
                modifier = Modifier
                    .padding(start = 3.dp, end = 3.dp)
                    //  .fillMaxWidth()
                    .background(Blue15),
                colors = ButtonDefaults.textButtonColors(containerColor = if (chooseLearningTypeSection.value == LearningTypeSection.USERS_TEXTS) Blue30 else Blue15),
                border = BorderStroke(1.dp, if (chooseLearningTypeSection.value == LearningTypeSection.USERS_TEXTS) Color.White else Blue15) // Blue30  Blue10  Color.White

            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = LearningTypeSection.USERS_TEXTS.title, // "Добавить собственный текст"
                    style = TextStyle(color = Color.White, fontSize = 15.sp)
                )
            }

            TextButton(
                onClick = { chooseLearningTypeSection.value = LearningTypeSection.UNITED_TEXTS },
                modifier = Modifier
                   // .fillMaxWidth()
                    .padding(bottom = 10.dp, start = 3.dp, end = 3.dp)
                    .background(Blue15),
                colors = ButtonDefaults.textButtonColors(containerColor = if (chooseLearningTypeSection.value == LearningTypeSection.UNITED_TEXTS) Blue30 else Blue15),
                border = BorderStroke(1.dp, if (chooseLearningTypeSection.value == LearningTypeSection.UNITED_TEXTS) Color.White else Blue15) // , shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = LearningTypeSection.UNITED_TEXTS.title, // "Добавить собственный текст"
                    style = TextStyle(color = Color.White, fontSize = 15.sp)
                )
            }


        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue10) // цвет фона между рядами клавиш
        ) {
            itemsIndexed(lessons) {
                _, lesson -> LessonPage(buttonClick, controllerText, chooseLesson, lesson)

            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun Look() {

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
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {

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
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .background(Blue15),
                colors = ButtonDefaults.textButtonColors(containerColor = Blue30),
                border = BorderStroke(2.dp, Color.White) // Blue30  Blue10  Color.White
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Добавить собственный текст", // "Добавить собственный текст"
                    style = TextStyle(color = Color.White, fontSize = 18.sp)
                )
            }


                /*

                            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth(),
            ) {

                Box(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .width(120.dp)
                        .clickable { chooseLearningTypeSection.value = LearningTypeSection.UNITED_TEXTS }
                        .border(width = 2.dp,
                            color = if (chooseLearningTypeSection.value == LearningTypeSection.UNITED_TEXTS) Color.White else Blue15,
                            shape = RoundedCornerShape(5.dp))
                        .background(Blue15, shape = RoundedCornerShape(5.dp))

                ) {
                    Text(
                        modifier = Modifier
                            .padding(7.dp),
                        text = LearningTypeSection.UNITED_TEXTS.title,
                        style = TextStyle(color = Color.White, fontSize = 14.sp)
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .width(120.dp)
                        .clickable { chooseLearningTypeSection.value = LearningTypeSection.USERS_TEXTS }
                        .border(width = 2.dp,
                            color = if (chooseLearningTypeSection.value == LearningTypeSection.USERS_TEXTS) Color.White else Blue15,
                            shape = RoundedCornerShape(5.dp))
                        .background(Blue15, shape = RoundedCornerShape(5.dp))
                ) {
                    Text(
                        modifier = Modifier
                            .padding(7.dp),
                        text = LearningTypeSection.USERS_TEXTS.title,
                        style = TextStyle(color = Color.White, fontSize = 14.sp)
                    )
                }
            }
                 */

        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue10) // цвет фона между рядами клавиш
        ) {


        }
    }
}









/*


 */