package aldmitry.dev.learningenglish.presenter

import aldmitry.dev.learningenglish.view.answer_field
import aldmitry.dev.learningenglish.view.input_field
import aldmitry.dev.learningenglish.view.keyboard_field
import aldmitry.dev.learningenglish.view.topText_field
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LearningProcessor(
    private val lessonUnit: MutableState<LessonUnit>,
    private val keyBoardField: MutableState<String>,
    private val isAnswer: MutableState<Boolean>,
    private val answerCounter: MutableState<Int>,
    private val topScreenText: MutableState<String>,
    private val bottomScreenText: MutableState<String>,
    private val lessonUnits : List<LessonUnit>
) {

    suspend fun process() {
        var previewLessonText = ""

        when {
            lessonUnit.value.keyButtonsWords.isEmpty() && bottomScreenText.value.isNotEmpty() && !isAnswer.value || bottomScreenText.value.isNotEmpty() &&
                    lessonUnit.value.englishText.split(" ").size == bottomScreenText.value.trim().split(" ").size -> {
                if (bottomScreenText.value.trim().equals(lessonUnit.value.englishText, ignoreCase = true)) {
                    answerCounter.value ++
                    topScreenText.value = "✔ ${lessonUnit.value.englishText}" // оригинальный eng текст на экране
                    bottomScreenText.value = ""
                    var newLessonUnit = lessonUnits[(lessonUnits.indices).random()]

                    /// var counter = 0//////////////////////////////////////////////////////////////////////////////////////////////////
                    repeat (15) { // цикл конечен для того случая, когда englishText == ruText и урок инвертирован
                        if (lessonUnits.size < 2 && newLessonUnit.englishText != previewLessonText) {// TODO исключить повтор уроков
                            return
                        } else {
                            newLessonUnit = lessonUnits[(lessonUnits.indices).random()]
                        }
                        // counter++
                    }
                    // topScreenText.value = "${topScreenText.value} >>> $counter"//////////////////////////////////////////////////////////////////////////////////////////////////

                    lessonUnit.value = newLessonUnit
                    previewLessonText = newLessonUnit.englishText
                }  else {
                    topScreenText.value = "✔ ${lessonUnit.value.englishText}" // оригинальный eng текст на экране
                    bottomScreenText.value = "✘ ${bottomScreenText.value}" // user eng текст на экране
                }

                isAnswer.value = true
                keyBoardField.value = answer_field
            }

            keyBoardField.value == keyboard_field || keyBoardField.value == input_field -> {
                topScreenText.value = "$topText_field${lessonUnit.value.russianText}"
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
                    topScreenText.value = "" // Текст для перевода на экране
                    bottomScreenText.value = ""
                    isAnswer.value = false
                }
            }
        }
    }


}
