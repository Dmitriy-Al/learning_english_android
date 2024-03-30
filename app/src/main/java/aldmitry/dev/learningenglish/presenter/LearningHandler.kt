package aldmitry.dev.learningenglish.presenter

import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.database.Database
import aldmitry.dev.learningenglish.database.UserLesson

class LearningHandler(val chooseLearningTypeSection: LearningTypeSection, val lesson: Learnable, val userLessons: MutableList<UserLesson>) {

    fun receiveLessonTextCollector(): List<LessonUnit> {
        val userLessonText = mutableMapOf<String, String>()

        for (elem in userLessons) {
            if (elem.lessonTitle == lesson.receiveTitle()) {
                userLessonText[elem.russianText] = elem.englishText
            }
        }
        return lesson.takeLesson(chooseLearningTypeSection, userLessonText)
    }


}