package aldmitry.dev.learningenglish.presenter

import aldmitry.dev.learningenglish.model.settings.SettingsData
import aldmitry.dev.learningenglish.view.answer_field
import aldmitry.dev.learningenglish.view.input_field
import aldmitry.dev.learningenglish.view.keyboard_field
import aldmitry.dev.learningenglish.view.topText_field
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LearningProcessor(
    private val answerCounter: MutableState<Int>,
    private val lessonUnits: List<LessonUnit>,
    private val settings: MutableState<SettingsData>
) {

    suspend fun process(
        isAnswer: MutableState<Boolean>,
        lessonUnit: MutableState<LessonUnit>,
        keyBoardField: MutableState<String>,
        topScreenText: MutableState<String>,
        bottomScreenText: MutableState<String>,
    ) {
        when {
            !isAnswer.value && // если экран с правильным ответом не запущен
                    bottomScreenText.value.isNotEmpty() && // если поле с введенным ответом не пустое
                    lessonUnit.value.englishText.split(" ").size == bottomScreenText.value.trim() // если количество слов в задании соответствует количеству введенных слов
                .split(" ").size ||
                    lessonUnit.value.keyButtonsWords.isEmpty() -> showAnswer( // если в LessonUnit List со словами для клавиатуры пуст (все 10 введены)
                    lessonUnit,
                    keyBoardField,
                    isAnswer,
                    topScreenText,
                    bottomScreenText,
                    answerCounter,
                    lessonUnits)

            keyBoardField.value == keyboard_field || keyBoardField.value == input_field -> {
                topScreenText.value = "$topText_field${lessonUnit.value.russianText}"
            }

            // если введенный текст отсутствует и поле ввода показывает ответ, запускается таймер показа экрана
            isAnswer.value && keyBoardField.value == answer_field -> {
                if (bottomScreenText.value.isEmpty()) {
                    withContext(Dispatchers.IO) {
                        isAnswer.value = false
                        delay(settings.value.answerShowTime * 1000)
                        restartScreen(lessonUnit, keyBoardField, topScreenText, bottomScreenText)
                    }
                } else {
                    if (topScreenText.value.isEmpty()) {
                        restartScreen(lessonUnit, keyBoardField, topScreenText, bottomScreenText)
                        isAnswer.value = false
                    }
                }
            }
        }
    }


    private fun showAnswer(
        lessonUnit: MutableState<LessonUnit>,
        keyBoardField: MutableState<String>,
        isAnswer: MutableState<Boolean>,
        topScreenText: MutableState<String>,
        bottomScreenText: MutableState<String>,
        answerCounter: MutableState<Int>,
        lessonUnits: List<LessonUnit>
    ) {
        isAnswer.value = true
        keyBoardField.value = answer_field
        val previewLessonText = lessonUnit.value.englishText

        if (bottomScreenText.value.trim().equals(lessonUnit.value.englishText, ignoreCase = true)
        ) {
            answerCounter.value++
            topScreenText.value =
                "✔ ${lessonUnit.value.englishText}" // оригинальный eng текст на экране
            bottomScreenText.value = ""

            if (lessonUnits.size > 1) {
                lessonUnit.value = receiveNextLessonUnit(previewLessonText)
            }

        } else {
            topScreenText.value =
                "✔ ${lessonUnit.value.englishText}" // оригинальный eng текст на экране
            bottomScreenText.value = "✘${bottomScreenText.value}" // user eng текст на экране

            if (lessonUnits.size > 1 && !settings.value.repeatWrongLesson) {
                lessonUnit.value = receiveNextLessonUnit(previewLessonText)
            }
        }
    }


    private fun receiveNextLessonUnit(previewLessonText: String): LessonUnit {
        var newLessonUnit = lessonUnits[(lessonUnits.indices).random()]

        for (i in 0..10) { // цикл конечен для того случая, когда englishText == ruText и урок инвертирован
            if (newLessonUnit.englishText != previewLessonText) {
                break
            } else {
                newLessonUnit = lessonUnits[(lessonUnits.indices).random()]
            }
        }
        return newLessonUnit
    }


    private fun restartScreen(
        lessonUnit: MutableState<LessonUnit>,
        keyBoardField: MutableState<String>,
        topScreenText: MutableState<String>,
        bottomScreenText: MutableState<String>
    ) {
        if (lessonUnit.value.keyButtonsWords.isEmpty()) {
            keyBoardField.value = input_field
        } else {
            keyBoardField.value = keyboard_field
        }
        topScreenText.value = "" // Текст для перевода на экране
        bottomScreenText.value = ""
    }

}
