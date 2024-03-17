package aldmitry.dev.learningenglish.presenter

import aldmitry.dev.learningenglish.model.Learnable

class LearningHandler(chooseLearningTypeSection: LearningTypeSection, lesson: Learnable) {

    val lessonCollector: Map<String, String>

    /** Map lessonCollector получает тексты уроков из функции takeLesson() объектов классов,
     * имплементирующих интерфейс Learnable: (PresentSimple, PresentContinuous, DateAndTime, т.д.)
     * / Map lessonCollector receives lesson texts from the fun takeLesson() of class objects
     * implementing the Learnable interface: (PresentSimple, PresentContinuous, DateAndTime, etc.)
     * */
    init {
        lessonCollector = lesson.takeLesson(chooseLearningTypeSection)
    }



}