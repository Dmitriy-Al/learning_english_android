package aldmitry.dev.learningenglish.presenter

import aldmitry.dev.learningenglish.database.UserLesson
import aldmitry.dev.learningenglish.model.Learnable

/**
 * LearningHandler - обработчик данных, создающий посредством функции receiveLessonTextCollector
 * list с элементами LessonUnit.
 *
 * LearningHandler is a data handler that creates a list with Lesson Unit elements using the
 * receiveLessonTextCollector function
 */
class LearningHandler(
    private val chooseLearningTypeSection: LearningTypeSection,
    private val lesson: Learnable,
    private val lessons: List<UserLesson>
) {

    // Функция использует следующие свойства - выбранный тип тренировки, выбранный урок, list с
    // элементами UserLesson и возвращает list с LessonUnit - элементами содержащими уроки
    fun receiveLessonTextCollector(): List<LessonUnit> {
        val userLessonText = lessons.associate { it.russianText to it.englishText }
        return lesson.takeLesson(chooseLearningTypeSection, userLessonText)
    }

}

