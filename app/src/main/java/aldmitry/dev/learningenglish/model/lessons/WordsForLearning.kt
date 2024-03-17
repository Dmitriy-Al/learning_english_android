package aldmitry.dev.learningenglish.model.lessons

import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.model.Trainingable
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonAggregator

class WordsForLearning : Learnable, Trainingable {

    private val title = "Учить новые слова"

    private val lessonTexts: Map<String, String> = mapOf("Текст приложения" to "App text")

    override fun receiveTitle(): String {
        return title
    }

    override fun takeLesson(learningTypeSection: LearningTypeSection): Map<String, String> {
        return LessonAggregator().buildLesson(learningTypeSection, lessonTexts)
    }

}