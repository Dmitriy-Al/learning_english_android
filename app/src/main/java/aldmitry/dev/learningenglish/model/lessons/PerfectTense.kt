package aldmitry.dev.learningenglish.model.lessons

import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonAggregator

class PerfectTense : Learnable {

    private val title = "Perfect tense"

    private val lessonTexts: Map<String, String> = mapOf("Текст приложения" to "App text")

    override fun receiveTitle(): String {
        return title
    }

    override fun takeLesson(learningTypeSection: LearningTypeSection): Map<String, String> {
        return LessonAggregator().buildLesson(learningTypeSection, lessonTexts)
    }


}