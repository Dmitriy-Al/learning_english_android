package aldmitry.dev.learningenglish.model.lessons

import aldmitry.dev.learningenglish.R
import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonCreator
import aldmitry.dev.learningenglish.presenter.LessonUnit

class PassiveVoice : Learnable {

    private val title = "Passive voice"

    private val hintPictureId = R.drawable.pasiive_voice

    private val appLessonTexts = mapOf("Ты любим" to "You are loved", "Ты не любим" to "You aren't loved", "Ты любим?" to "are You loved", "Ты будешь любим" to "You will be loved",
        "Ты не будешь любим" to "You won't be loved", "Ты будешь любим?" to "will You be loved", "Ты был любим" to "You were loved", "Ты не был любим" to "You weren't loved",
        "Ты был любим?" to "were You loved", "Письма пишут каждый день" to "letters are written every day", "Письмо было написано вчера" to "the letter was written yesterday",
        "Письмо будет написано завтра" to "the letter will be written tomorrow", "Письмо написанное сейчас" to "the letter is being written now", "Вас встретят в гостинице" to "You will be met at the hotel",
        "Письмо писали вчера в пять часов" to "the letter was being written at five o'clock yesterday", "Письмо уже написано" to "the letter has already been written",
        "Комната была убрана ужасно" to "the room was cleaned horribly", "Письмо было написано вчера к пяти часам" to "the letter had been written by five o'clock yesterday",
        "Письмо будет написано завтра к пяти часам" to "the letter will have been written by five o'clock tomorrow", "Тысячи долларов тратятся на кофе" to "thousands of dollars are spent on coffee",
        "Собаку кормят каждый день" to "the dog is fed every day", "Собака была накормлена вчера" to "the dog was fed yesterday", "Радио изобрели 150 лет назад" to "the radio was invented 150 years ago",
        "Машину заправляют сейчас" to "the car is being refueled now", "Экзамен сдавали вчера утром" to "the exam was being taken yesterday morning",
        "Эти цветы уже полили" to "this flowers have already been watered", "Статью перепишут к завтрашнему утру" to "the article will have been rewritten by tomorrow morning",
        "Марли был мертв" to "Marley was dead", "Моя машина уже угнана" to "my car has been stolen", "Эта книга была написана не им" to "this book was not written by him",
        "Меня нечасто приглашают на вечеринки" to "I am not often invited to the parties", "Подарок еще не привезли" to "The gift has not been bought yet",
        "Кот не был накормлен им вчера" to "the cat was not fed by him yesterday", "Кота не часто оставляли голодным" to "the cat was not often left hungry",
        "Вам объяснили это домашнее задание?" to "has this homework been explained to you", "Задача обсуждалась, когда я вошел в класс" to "the task was being discussed when I entered the class",
        "Письмо будет написанным к следующему утру" to "the letter will have been written by the next morning", "Письмо будет написано на следующий день" to "the letter will be written the next day",
        "Собака была накормлена вчера" to "the dog was fed yesterday", "Собака будет накормленной завтра»" to "the dog will be fed tomorrow",
        "Собаку кормят прямо в этот момент" to "the dog is being fed at the moment", "Собаку кормили, когда мы пришли" to "the dog was being fed when we come",
        "В 9 вечера собака будет накормлена" to "at nine o'clock tonight the dog will be being fed", "Собака недавно покормлена" to "the dog is has been fed recently",
        "Собака была покормлена до того, как я пришёл»" to "the dog was had been fed before I came", "Собака будет покормленной к 9 часам" to "the dog will have been fed by nine o’clock")

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