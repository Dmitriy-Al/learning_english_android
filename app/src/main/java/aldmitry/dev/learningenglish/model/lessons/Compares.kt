package aldmitry.dev.learningenglish.model.lessons

import aldmitry.dev.learningenglish.R
import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonCreator
import aldmitry.dev.learningenglish.presenter.LessonUnit

class Compares : Learnable {

    private val title = "Сравнения"

    private val hintPictureId = R.drawable.compares

    private val appLessonTexts = mapOf("Ты выше других" to "You are taller than others", "Эта машина самая быстрая" to "this car is the fastest", "Анна самая умная в классе" to "Anna is the cleverest in the class",
        "Моя машина быстрее твоей" to "My car is faster than your", "Эта дорога была самой короткой" to "this road was the shortest", "Сегодня она выглядит лучше, чем вчера" to "today she looks better than yesterday",
        "Вчера она выглядела хуже, чем сегодня" to "yesterday she looked worse than today", "Он был его лучшим другом" to "he was the best his friend",
        "Эта задача будет самой сложной" to "this task will be the most difficult", "Этот цветок удивительнее других" to "this flower is more wonderful than others",
        "Эта самая добрая собака в мире" to "this is the kindest dog in the world", "Становится всё жарче и жарче" to "it is getting hotter and hotter",
        "Я работал всё усерднее и усерднее, пока мы не завершили этот проект" to "I worked harder and harder until we finished this project", "Фильм был самым скучным" to "the movie was the most boring",
        "Меркурий не больше Земли" to "Mercury isn't bigger than Earth", "Вчера был самый жаркий день года" to "yesterday was the hottest day of the year",
        "Один из самых милых людей, которых я знаю" to "one of the nicest people I know", "Ты старше меня" to "You are older than me")


    override fun receiveTitle(): String {
        return title
    }


    override fun takeLesson(learningTypeSection: LearningTypeSection, lessonTexts: Map<String, String>): List<LessonUnit> {
        return LessonCreator().createLesson(learningTypeSection, lessonTexts, appLessonTexts)
    }


    override fun receiveHintPictureId(): Int {
        return hintPictureId
    }

}