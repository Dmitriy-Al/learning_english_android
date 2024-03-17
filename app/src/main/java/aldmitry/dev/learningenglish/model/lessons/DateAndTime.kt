package aldmitry.dev.learningenglish.model.lessons

import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonAggregator

class DateAndTime : Learnable {

    private val title = "Дата и время"

    private val lessonTexts: Map<String, String> = mapOf("Лососни тунца" to "App text", "Текст приложения" to "App text", "Пришел писец" to "App text")

    override fun receiveTitle(): String {
        return title
    }

    override fun takeLesson(learningTypeSection: LearningTypeSection): Map<String, String> {
        return LessonAggregator().buildLesson(learningTypeSection, lessonTexts)
    }


}