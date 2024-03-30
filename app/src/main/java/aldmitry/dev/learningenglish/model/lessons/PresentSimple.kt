package aldmitry.dev.learningenglish.model.lessons

import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonCreator
import aldmitry.dev.learningenglish.presenter.LessonUnit

class PresentSimple : Learnable {

    private val title = "Present simple"

    private val presentSimple: Map<String, String> = mapOf("Ты пойдешь домой" to "You will go home", "Ты не пойдешь домой" to "You will not go home", "Ты ходил домой" to "You went home")


    private val appLessonTexts = mapOf("Я люблю" to "I love", "Я не люблю" to "I do not love", "Я люблю?" to "do I love", "Я буду любить" to "I will love", "Я не буду любить" to "I will not love", "Я буду любить?" to "will I love",
        "Я любил" to "I loved", "Я не любил" to "I did not love", "Я любил?" to "did I love", " Я иду" to "I go", "Я не иду" to "I do not go", "Я иду?" to "do I go", "Я буду ходить" to "I will go",
        "Я не буду ходить" to "I will not go", "Я буду ходить?" to "will I go", "Я ходил" to "I went", "Я не ходил" to "I did not go", "Я ходил?" to "did I go", " Я смотрю" to "I look", "Я не смотрю" to "I do not look",
        "Я смотрю?" to "do I look", "Я буду смотреть" to "I will look", "Я не буду смотреть" to "I will not look", "Я буду смотреть?" to "will I look", "Я смотрел" to "I looked", "Я не смотрел" to "I did not look",
        "Я смотрел?" to "did I look", "Мы слышим" to "we hear", "Мы не слышим" to "we do not hear", "Мы слышим?" to "do we hear", "Мы будем слышать" to "we will hear",
        "Мы не будем слышать" to "we will not hear", "Мы будем слышать?" to "will we hear", "Мы слышали" to "we heard", "Мы не слышали" to "we did not hear", "Мы слышали?" to "did we hear", "Я хочу" to "I want",
        "Я не хочу" to "I do not want", "Я хочу?" to "do I want", "Я буду хотеть" to "I will want", "Я не буду хотеть" to "I will not want", "Я буду хотеть?" to "will I want", "Я хотел" to "I wanted",
        "Я не хотел" to "I did not want", "Я хотел?" to "did I want", "Я вижу" to "I see", "Я не вижу" to "I do not see", "Я вижу?" to "do I see", "Я буду видеть" to "I will see", "Я не буду видеть" to "I will not see",
        "Я буду видеть?" to "will I see", "Я видел" to "I saw", "Я не видел" to "I did not see", "Я видел?" to "did I see", "Он слушает" to "he listens", "Он не слушает" to "he does not listen",
        "Он слушает?" to "does he listen", "Он будет слушать" to "he will listen", "Он не будет слушать" to "he will not listen", "Он будет слушать?" to "will he listen", "Он слушал" to "he listened",
        "Он не слушал" to "he did not listen", "Он слушал?" to "did he listen", "Он спит" to "he sleeps", "Он не спит" to "he does not sleep", "Он спит?" to "does he sleep", "Он будет спать" to "he will sleep",
        "Он не будет спать" to "he will not sleep", "Он будет спать?" to "will he sleep", "Он спал" to "he slept", "Он не спал" to "he did not sleep", "Он спал?" to "did he sleep", "Она улыбается" to "she smiles",
        "Она не улыбается" to "she does not smile", "Она улыбается?" to "does she smile", "Она будет улыбаться" to "she will smile", "Она не будет улыбаться" to "she will not smile", "Она будет улыбаться?" to "will she smile",
        "Она улыбалась" to "she smiled", "Она не улыбалась" to "she did not smile", "Она улыбалась?" to "did she smile", "Она читает книгу" to "she reads a book", "Она не читает книгу" to "she does not read a book",
        "Она читает книгу?" to "does she read a book", "Она будет читать книгу" to "she will read a book", "Она не будет читать книгу" to "she will not read a book", "Она будет читать книгу?" to "will she read a book",
        "Она читала книгу" to "she read a book", "Она не читала книгу" to "she did not read a book", "Она читала книгу?" to "did she read a book", "Он хочет бежать" to "he wants to run",
        "Он не хочет бежать" to "he doesn't want to run", "Он хочет бежать?" to "does he want to run", "Он захочет бежать" to "he will want to run", "Он не захочет бежать" to "he will not want to run",
        "Он захочет бежать?" to "will he want to run", "Он хотел бежать" to "he wanted to run", "Он не хотел бежать" to "he didn't want to run", "Он хотел бежать?" to "did he want to run",
        "Они любят выигрывать" to "they like to win", "Они не любят проигрывать" to "they don't like to lose", "Они любят выигрывать?" to "do they like to win", "Они полюбят выигрывать" to "they will like to win",
        "Они не будут любить проигрывать" to "they will not like to lose", "Они полюбят выигрывать?" to "will they like to win", "Они любили выигрывать" to "they liked to win",
        "Они не любили проигрывать" to "they didn't like to lose", "Они любили выигрывать?" to "did they like to win", "Тебе надо идти домой" to "You need to go home", "Тебе не надо идти домой" to "You don't need to go home",
        "Тебе надо идти домой?" to "do You need to go home", "Тебе надо будет идти домой" to "You will need to go home", "Тебе не надо будет идти домой" to "You will not need to go home",
        "Тебе надо будет идти домой?" to "will You need to go home", "Тебе надо было идти домой" to "You needed to go home", "Тебе не надо было идти домой" to "You didn't need to go home",
        "Тебе надо было идти домой?" to "did You need to go home", "Ты учишь Английский язык" to "You learn English", "Ты не учишь Английский язык" to "You don't learn English",
        "Ты учишь Английский язык?" to "do You learn English", "Ты будешь учить Английский язык" to "You will learn English", "Ты не будешь учить Английский язык" to "You won't learn English",
        "Ты будешь учить Английский язык?" to "will You learn English", "Ты учил Английский язык" to "You learned English", "Ты не учил Английский язык" to "You didn't learn English",
        "Ты учил Английский язык?" to "did You learn English", "Это выглядит как яблоко" to "It looks like an apple", "Это не выглядит как яблоко" to "It doesn't look like an apple",
        "Это выглядит как яблоко?" to "does it look like an apple", "Это будет выглядеть как яблоко" to "It will look like an apple", "Это не будет выглядеть как яблоко" to "It won't look like an apple",
        "Это будет выглядеть как яблоко?" to "will it look like an apple", "Это выглядело как яблоко" to "It looked like an apple", "Это не выглядело как яблоко" to "It didn't look like an apple",
        "Это выглядело как яблоко?" to "did it look like an apple", "Мы смотрим на него" to "we look at him", "Мы не смотрим на него" to "we don't look at him", "Мы смотрим на него?" to "do we look at him",
        "Мы будем смотреть на него" to "we will look at him", "Мы не будем смотреть на него" to "we won't look at him", "Мы будем смотреть на него?" to "will we look at him", "Мы смотрели на него" to "we looked at him",
        "Мы не смотрели на него" to "we didn't look at him", "Мы смотрели на него?" to "did we look at him")

    override fun receiveTitle(): String {
        return title
    }

    override fun takeLesson(learningTypeSection: LearningTypeSection, lessonTexts: Map<String, String>): List<LessonUnit> {
        return LessonCreator().createLesson(learningTypeSection, lessonTexts, appLessonTexts)
    }

}

