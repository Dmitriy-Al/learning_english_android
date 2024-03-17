package aldmitry.dev.learningenglish.model

import aldmitry.dev.learningenglish.presenter.LearningTypeSection

interface Learnable {

    fun receiveTitle(): String

    fun takeLesson(learningTypeSection: LearningTypeSection): Map<String, String>

}