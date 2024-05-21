package aldmitry.dev.learningenglish.model

import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonUnit

interface Learnable {

    fun receiveTitle(): String

    fun receiveHintPictureId(): Int

    fun takeLesson(learningTypeSection: LearningTypeSection, lessonTexts: Map<String, String>): List<LessonUnit>

}