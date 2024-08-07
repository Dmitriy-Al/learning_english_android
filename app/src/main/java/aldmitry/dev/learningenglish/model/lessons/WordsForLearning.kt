package aldmitry.dev.learningenglish.model.lessons

import aldmitry.dev.learningenglish.R
import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonCreator
import aldmitry.dev.learningenglish.presenter.LessonUnit

class WordsForLearning : Learnable {

    private val title = "Учить новые слова"

    private val hintPictureId = 0


    override fun receiveTitle(): String {
        return title
    }


    override fun takeLesson(learningTypeSection: LearningTypeSection, lessonTexts: Map<String, String>): List<LessonUnit> {
        return LessonCreator().createLesson(lessonTexts)
    }


    override fun receiveHintPictureId(): Int {
        return hintPictureId
    }

}