package aldmitry.dev.learningenglish.model.lessons

import aldmitry.dev.learningenglish.R
import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonCreator
import aldmitry.dev.learningenglish.presenter.LessonUnit

class MuchMany : Learnable {

    private val title = "Множества"

    private val hintPictureId = R.drawable.many


    private val appLessonTexts = mapOf("Много столов" to "many tables", "Много ложек" to "many spoons", "Много яблок" to "many apples", "Много денег" to "much money",
        "Было найдено много информации" to "much information has been found", "Много столов" to "many tables", "У них мало денег" to "they have little money", "У них мало монет" to "they have few coins",
        "Они имеют много денег" to "they have got much money", "У них много монет" to "they have many coins", "Многие из них" to "a lot of them", "Она ему очень нравится" to "he likes her a lot",
        "Его фильмы получили множество призов" to "his movies have won lots of prizes", "Много сахара" to "a lot of sugar", "Он не ест много мяса" to "he doesn't eat much meat",
        "Она ест много фруктов" to "She eats much fruits", "В городе немного старых зданий" to "there are a few old buildings in the city", "В музее было мало людей" to "there were few people at the museum",
        "У него мало свободного времени" to "he has little free time", "Прошлой зимой у нас было мало снега" to "we had little snow last winter",
        "У меня так много новостей для тебя" to "I have got so much news to tell you", "Я хочу задать тебе так много вопросов" to "there are so many things I want to ask you", "Много сахара" to "much sugar",
        "С тех пор утекло много воды" to "much of water has flown away since then")

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