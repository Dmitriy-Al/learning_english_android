package aldmitry.dev.learningenglish.presenter

import aldmitry.dev.learningenglish.view.answer_field
import aldmitry.dev.learningenglish.view.input_field
import aldmitry.dev.learningenglish.view.keyboard_field
import aldmitry.dev.learningenglish.view.rightAnswer_text
import aldmitry.dev.learningenglish.view.wrongAnswer_text
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalTime

class LearningProcessor(
    private val lessonUnit: MutableState<LessonUnit>,
    private val keyBoardField: MutableState<String>,
    private val isAnswer: MutableState<Boolean>,
    private val answerCounter: MutableState<Int>,
    private val topScreenText: MutableState<String>,
    private val midScreenText: MutableState<String>,
    private val inputtedText: MutableState<String>,
    private val wrongAnswerText: MutableState<String>,
    private val lessonUnits : List<LessonUnit>
) {

    suspend fun process() {
        var previewLessonText = ""

        when {
            lessonUnit.value.keyButtonsWords.isEmpty() && inputtedText.value.isNotEmpty() && !isAnswer.value || inputtedText.value.isNotEmpty() &&
                    lessonUnit.value.englishText.split(" ").size == inputtedText.value.trim().split(" ").size -> {

                if (inputtedText.value.trim().equals(lessonUnit.value.englishText, ignoreCase = true)) {
                    answerCounter.value ++
                    topScreenText.value = rightAnswer_text // return
                    midScreenText.value = "✔ ${lessonUnit.value.englishText}" // оригинальный eng текст на экране
                    inputtedText.value = ""
                    var newLessonUnit = lessonUnits[(lessonUnits.indices).random()]

                    for (i in 0 .. 10) { // цикл конечен для того случая, когда englishText == ruText и урок инвертирован
                        if (lessonUnits.size < 2 && newLessonUnit.englishText != previewLessonText) {// TODO исключить повтор уроков
                            return
                        } else {
                            newLessonUnit = lessonUnits[(lessonUnits.indices).random()]
                        }
                    }

                    lessonUnit.value = newLessonUnit
                    previewLessonText = newLessonUnit.englishText
                }  else {
                    topScreenText.value = wrongAnswer_text // оригинальный eng текст на экране
                    midScreenText.value = "✔ ${lessonUnit.value.englishText}" // оригинальный eng текст на экране
                    wrongAnswerText.value = "✘ ${inputtedText.value}" // user eng текст на экране
                    inputtedText.value = ""
                }

                isAnswer.value = true
                keyBoardField.value = answer_field
            }

            keyBoardField.value == keyboard_field || keyBoardField.value == input_field -> {
                topScreenText.value = "Введите перевод для текста:"
                midScreenText.value = lessonUnit.value.russianText
            }

            // если введенный текст отсутствует и поле ввода показывает ответ, запускается таймер показа экрана
            isAnswer.value && keyBoardField.value == answer_field -> {
                withContext(Dispatchers.IO) {
                    delay(3000)
                    if (lessonUnit.value.keyButtonsWords.isEmpty()) {
                        keyBoardField.value = input_field
                    } else {
                        keyBoardField.value = keyboard_field
                    }
                    inputtedText.value = ""
                    topScreenText.value = "" // Текст для перевода на экране
                    midScreenText.value = ""
                    wrongAnswerText.value = ""
                    isAnswer.value = false
                }
            }
        }
    }


}
