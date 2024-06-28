package aldmitry.dev.learningenglish.model.lessons

import aldmitry.dev.learningenglish.R
import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonCreator
import aldmitry.dev.learningenglish.presenter.LessonUnit

class PerfectTense : Learnable {

    private val title = "Perfect tense"

    private val hintPictureId = R.drawable.perfect_tence

    private val appLessonTexts = mapOf("Я (сейчас) посмотрел фильм" to "I have seen the movie", "Я (когда-то в прошлом) смотрел этот фильм" to "I had seen this movie",
        "Я увижу этот фильм к вечеру" to "I will have seen this movie by evening", "У него (к настоящему моменту) была собака" to "he has had a dog", "У него (когда-то в прошлом) была собака" to "he had had a dog",
        "У него будет собака" to "he will have had a dog", "Ты находишься здесь год" to "You have been here for a year", "Ты находился здесь год" to "You had been here for a year",
        "Ты будешь находиться здесь год" to "You will have been here for a year", "Он - сбежавший" to "he has escaped", "Он сбежал раньше, чем они нашли его" to "he had escaped before they found him",
        "Он убежит (будет сбежавшим) к полуночи" to "he will have escaped by midnight", "Они (когда-то в прошлом) работали" to "they had worked", "Они работали (сейчас)" to "they have worked",
        "Они отработают" to "they will have worked", "Я только завершил мою работу" to "I have just finished my work", "Я закончил мою работу вчера" to "I had finished my work yesterday",
        "Я закончу работу (моя работа будет законченной) завтра к 5 часам" to "I will have finished my work tomorrow by 5 o’clock", "Они уже приехали" to "they have arrived already",
        "Мы не услышали" to "we haven't heard", "Я (к настоящему моменту) писал книгу" to "I have written a book", "Я (к настоящему моменту) никогда не писал книгу" to "I have newer written a book",
        "Я (сейчас) писал книгу?" to "have I written a book", "Я буду писать книгу" to "I will have written a book", "Я не буду писать книгу" to "I won't have written a book",
        "Я буду писать книгу?" to "will I have written a book", "Мы услышали (сейчас)" to "we have heard", "Я (когда-то в прошлом) писал книгу" to "I had written a book",
        "Я (когда-то в прошлом) не писал книгу" to "I hadn't written a book", "Я писал книгу? (когда-то в прошлом)" to "had I written a book", "Мы услышали? (сейчас)" to "have we heard",
        "Мы будем слышать" to "we will have heard", "Мы не будем слышать" to "we won't have heard", "Мы будем слышать?" to "will we have heard", "Мы слышали (когда-то в прошлом)" to "we had heard",
        "Мы не слышали (когда-то в прошлом)" to "we hadn't heard", "Мы слышали? (когда-то в прошлом)" to "had we heard", "Она (сейчас) читала книгу " to "she has read a book",
        "Она не читала книгу (сейчас)" to "she doesn't have read a book", "Она (сейчас) читала книгу?" to "has she read a book", "Она будет читать книгу" to "she will have read a book",
        "Она не будет читать книгу" to "she won't have read a book", "Она будет читать книгу?" to "will she have read a book", "Она прочитала книгу (когда-то в прошлом)" to "she had read a book",
        "Она не прочитала книгу (когда-то в прошлом)" to "she hadn't read a book", "Она прочитала книгу? (когда-то в прошлом)" to "had she read a book",
        "Ты (сейчас) учил Английский язык" to "You have learned English", "Ты не учил Английский язык (сейчас)" to "You haven't learned English", "Ты учил Английский язык? (сейчас)" to "have You learned English",
        "Ты будешь учить Английский язык" to "You will have learned English", "Ты не будешь учить Английский язык" to "You won't have learned English",
        "Ты будешь учить Английский язык?" to "will You have learned English", "Ты выучил Английский язык (когда-то в прошлом)" to "You had learned English",
        "Ты не выучил Английский язык (когда-то в прошлом)" to "You hadn't learned English", "Ты учил Английский язык? (когда-то в прошлом)" to "had You learned English")


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