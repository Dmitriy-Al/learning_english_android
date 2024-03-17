package aldmitry.dev.learningenglish.view

import aldmitry.dev.learningenglish.R
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


    @OptIn(ExperimentalPagerApi::class) // rememberPagerState() является эксперементальной функцией
    //  @Preview(showBackground = true)
    @Composable
    fun MainScreen(buttonClick: () -> Unit) {
        val pagerState = rememberPagerState()
        val tabIndex = pagerState.currentPage
        val coroutineScope = rememberCoroutineScope()

        val tabList =
            listOf("Тексты приложения и собственные", "Только собственные тексты", "Тексты других пользователей")

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
                        onClick = { buttonClick() } // onClick
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.info_button),
                            contentDescription = "info_pic",
                            tint = Color.White
                        )
                    }

                    IconButton(
                        onClick = { buttonClick() }
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
                        text = "Добавить собственный текст",
                        style = TextStyle(color = Color.White, fontSize = 18.sp)
                    )
                }

            }

            TabRow(
                containerColor = Blue30, // цвет клавиш
                selectedTabIndex = tabIndex,
                // TabRowDefaults- реализация по умолчанию и значения, используемые для строки табуляции; модификатор занимает всю доступную ширину внутри строки табуляции, а затем анимирует смещение индикатора
                indicator = { position ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(position[tabIndex]), // .border(width = 1.dp, color = Color.White, RectangleShape)
                        color = Color.White
                    ) // цвет полоски-селектора под клавишами
                },
                modifier = Modifier
                    .background(Blue10)
                    .clip(RoundedCornerShape(0.dp))
            ) {
                tabList.forEachIndexed { index, text ->
                    Tab(
                        selected = false,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index) // реакция на клик и анимация скроллинга в отдельном потоке
                            }
                        },
                        text = { Text(text = text, color = Color.White, fontSize = 14.sp) }
                        //modifier = Modifier.padding(top = 10.dp, )
                        // unselectedContentColor = Color.White, // цвет клавиш в состоянии покоя
                        // selectedContentColor = Color.Gray // цвет клавиш в состоянии переключения
                    )
                }
            }

            // Функция библиотеки google.accompanist.pager - макет с горизонтальной прокруткой, который позволяет пользователям переключаться между элементами влево и вправо
            HorizontalPager(
                count = tabList.size,
                state = pagerState,
                modifier = Modifier.weight(1F)
            ) { index ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Blue10) // цвет фона между рядами клавиш
                ) {

                    items(10) { // контент HorizontalPager завистит от переключения клавиш
                        when (index) {
                            /*
                            0 -> LessonPage(buttonClick)
                            1 -> LessonPage(buttonClick)
                            2 -> LessonPage(buttonClick)

                             */
                        }
                    }
                }
            }
        }
    }










