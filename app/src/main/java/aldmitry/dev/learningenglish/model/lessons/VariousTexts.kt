package aldmitry.dev.learningenglish.model.lessons

import aldmitry.dev.learningenglish.R
import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonCreator
import aldmitry.dev.learningenglish.presenter.LessonUnit

class VariousTexts : Learnable {

    private val title = "Разные"

    private val hintPictureId = 0


    private val appLessonTexts = mapOf("Я люблю яблоки" to "I like apples", "как собака" to "like a dog", "Я не знаю ничего" to "I know nothing",
        "Если он захочет (предложения с условиями if и when всегда употребляются в настоящем времени)" to "if he wants",
        "Когда ты пойдёшь в лес, не забудь компас (предложения с условиями if и when всегда употребляются в настоящем времени)" to "when you go to the forest don't forget the compass",
        "Что ты обычно делаешь утром?" to "What do you usually do in the morning", "Ночью все кошки серые" to "all cats are grey at night", "Это был холодный вечер" to "it was a cold evening",
        "Не опаздывай на ланч" to "don’t be late for lunch", "Включи свет!" to "switch on the light", "Я могу дать тебе много советов" to "I can give you a lot of advice",
        "Я не люблю покупать новую одежду" to "I don’t like to by new clothes", "Она одевается очень вызывающе" to "she dresses very provocatively", "Ты вел себя отвратительно" to "You behaved disgustingly",
        "Я хочу, чтобы ты пошёл" to "I want you to go", "Я хочу, чтобы он ответил" to "I want him to answer", "За столом сидели мужики и ели" to "men were sitting and eating at the table",
        "Это жена моего брата" to "it’s my brother’s wife", "Кусочек сыра на столе – твой" to "the piece of cheese on the table is yours", "Двухдневное путешествие" to "two days’ trip",
        "Это - моё" to "it's mine", "Они видели их" to "they saw themselves", "Мы увидели сбя" to "we saw ourselves", "Он нравится сбе" to "he likes himself", "Ты нравишься сбе" to "You like yourselves",
        "Он бежал так быстро, как мог" to "he ran as fast as he could", "Театр Лондона" to "London’s theatre")

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