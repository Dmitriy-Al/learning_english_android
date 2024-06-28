package aldmitry.dev.learningenglish.model.lessons

import aldmitry.dev.learningenglish.R
import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonCreator
import aldmitry.dev.learningenglish.presenter.LessonUnit

class PresentContinuous : Learnable {

    private val title = "Present continuous"

    private val hintPictureId = R.drawable.present_continuos

    private val appLessonTexts = mapOf("Лондон - столица Великобритании" to "London is the capital of Great Britain", "Манчестер - не столица Великобритании" to "Manchester is not the capital of Great Britain",
        "Лондон - столица Великобритании?" to "is London the capital of Great Britain", "Лондон будет столицей Великобритании" to "London will be the capital of Great Britain",
        "Манчестер - не будет столицей Великобритании" to "Manchester will not be the capital of Great Britain", "Будет Лондон столицей Великобритании?" to "will be London the capital of Great Britain",
        "Лондон был столицей Великобритании" to "London was the capital of Great Britain", "Манчестер - не был столицей Великобритании" to "Manchester was not the capital of Great Britain",
        "Лондон был столицей Великобритании?" to "was London the capital of Great Britain", "Я голодный" to "I'm hungry", "Я не голодный" to "I'm not hungry", "Я голодный?" to "am I hungry",
        "Я буду голодным" to "I'll be hungry", "Я не буду голодным" to "I won't be hungry", "Я буду голодным?" to "will I be hungry", "Я был голодный" to "I was hungry", "Я не был голодный" to "I wasn't hungry",
        "Я был голодный?" to "was I hungry", "Я сплю сейчас" to "I am sleeping now", "Я не сплю сейчас" to "I am not sleeping now", "Я сплю сейчас?" to "am I sleeping now",
        "Я иду" to "I am going", "Я не иду" to "I'm not going", "Я иду?" to "am I going", "Я буду идти" to "I will be going", "Я не буду идти" to "I will not be going", "Я буду идти?" to "will I be going",
        "Я шёл" to "I was going", "Я не шёл" to "I was not going", "Я шёл?" to "was I going", "Он бледный" to "he is pale", "Он не бледный" to "he is not pale", "Он бледный?" to "is he pale",
        "Он будет бледный" to "he will be pale", "Он не будет бледный" to "he will not be pale", "Он будет бледный?" to "will he be pale", "Он был бледный" to "he was pale",
        "Он не был бледный" to "he was not pale", "Он был бледный?" to "was he pale", "Он поет песню" to "he is singing a song", "Он не поет песню" to "he is not singing a song",
        "Он поет песню?" to "is he singing a song", "Он будет петь песню" to "he will be singing a song", "Он не будет петь песню" to "he won't be singing a song",
        "Он будет петь песню?" to "will he be singing a song", "Он пел песню" to "he was singing a song", "Он не пел песню" to "he wasn't singing a song", "Он пел песню?" to "was he singing a song",
        "Мы счастливы" to "we are happy", "Мы не счастливы" to "we are not happy", "Мы счастливы?" to "are we happy", "Мы будем счастливы" to "we will be happy", "Мы не будем счастливы" to "we will not be happy",
        "Мы будем счастливы?" to "will we be happy", "Мы были счастливы" to "we were happy", "Мы не были счастливы" to "we were not happy", "Мы были счастливы?" to "were we happy",
        "Они улыбаются тебе" to "they are smiling at You", "Они не улыбаются тебе" to "they aren't smiling at You", "Они улыбаются тебе?" to "are they smiling at You",
        "Они будут улыбаться тебе" to "they will be smiling at You", "Они не будут улыбаться тебе" to "they won't be smiling at You", "Они будут улыбаться тебе?" to "will they be smiling at you",
        "Они улыбались тебе" to "they were smiling at You", "Они не улыбались тебе" to "they weren't smiling at You", "Они улыбались тебе?" to "were they smiling at You", "Она прекрасна" to "she is beautiful",
        "Она не прекрасна" to "she isn't beautiful", "Она прекрасна?" to "is she beautiful", "Она будет прекрасна" to "she will be beautiful", "Она не будет прекрасна" to "she won't be beautiful",
        "Она будет прекрасна?" to "will she be beautiful", "Она была прекрасна" to "she was beautiful", "Она не была прекрасна" to "she wasn't beautiful", "Она была прекрасна?" to "was she beautiful",
        "Ты тренируешься" to "You are training", "Ты не тренируешься" to "You aren't training", "Ты тренируешься?" to "are You training", "Ты будешь тренироваться" to "You will be training",
        "Ты не будешь тренироваться" to "You won't be training", "Ты будешь тренироваться?" to "will You be training", "Ты тренировался" to "You were training", "Ты не тренировался" to "You weren't training",
        "Ты тренировался?" to "were You training", "Я сплю сейчас" to "I am sleeping now", "Я не сплю сейчас" to "I am not sleeping now", "Я сплю сейчас?" to "am I sleeping now")


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