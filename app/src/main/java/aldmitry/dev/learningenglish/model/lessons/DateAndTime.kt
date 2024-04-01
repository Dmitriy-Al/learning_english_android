package aldmitry.dev.learningenglish.model.lessons

import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonCreator
import aldmitry.dev.learningenglish.presenter.LessonUnit

class DateAndTime : Learnable {

    private val title = "Дата и время"

    private val appLessonTexts = mapOf("Сейчас пять минут пятого" to "it’s five past four", "Сейчас полшестого" to "it’s half past five", "Сейчас без четверти восемь" to "it’s quarter to eight",
        "Сейчас без двух минут час" to "it’s two minutes to one", "Я приду к нему в понедельник" to "I will come to him on Monday", "Они придут к ним во вторник" to "they will come to them on Tuesday",
        "Он не придёт к ней в среду" to "he will not come to her on Wednesday", "Она приходила к нему в четверг" to "she came to him on Thursday", "Она приходила ко мне в пятницу" to "she came to me on Friday",
        "Я приду к нему в субботу" to "I will come to him on Saturday", "Они придут к ним в воскресенье" to "they will come to them on Sunday", "Они придут к ним зимой" to "they will come to them in winter",
        "Она приходила к нему летом" to "she came to him in summer", "Он не придёт к ней весной" to "he will not come to her in spring", "Я приду к нему осенью" to "I will come to him in autumn",
        "Я приду к нему в январе" to "I will come to him in January", "Он не придёт к ней в  марте" to "he will not come to her in March", "Они придут к ним в апреле" to "they will come to them in April",
        "Она приходила ко мне в июле" to "she came to me in July", "Я приду к нему в августе" to "I will come to him in August", "Они придут к ним в ноябре" to "hey will come to them in November",
        "Он не придёт к ней в декабре" to "he will not come to her in December", "Она приходила к нему в июне" to "she came to him in June", "Она приходила ко мне в феврале" to "she came to me in February",
        "Они придут к ним в октябре" to "they will come to them in October", "Я приду к нему в августе" to "I will come to him in August", "Она приходила к нему в сентябре" to "she came to him in September",
        "До истечения одного месяца" to "before one month", "Я приду сегодня" to "I will come today", "Всё изменилось в 2014 году" to "everything changed in 2014",
        "Они зажгли лампу в 9 часов" to "they lit a lamp at nine o'clock", "Мы пойдем домой в шесть часов десять минут" to "we will go home at ten past six",
        "Поезд поедет в 14:40" to "the train will leave at twenty to three", "Мой день рождения третьего мая" to "my birthday is on the third of May",
        "Мой день рождения десятого июня" to "my birthday is on the tenth of June", "Мой день рождения первого января" to "my birthday is on the first of January", "Первый" to "first",
        "Второй" to "second", "Третий" to "third", "Четвертый" to "fourth", "Пятый" to "fifth", "Шестой" to "sixth", "Седьмой" to "seventh", "Восьмой" to "eighth", "Девятый" to "ninth",
        "Десятый" to "tenth", "Одиннадцатый" to "eleventh", "Двенадцатый" to "twelfth", "Двадцатый" to "twentieth", "Тридцатый" to "thirtieth", "Сороковой" to "fortieth", "307-й" to "three hundred and seventh",
        "600-й" to "six hundredth", "1000 000-й" to "one millionth", "Я хочу купить билет на завтрашний авиарейс" to "I want a ticket for tomorrow flight",
        "Вчера все мои беды казались такими далекими" to "yesterday all my troubles seemed so far away", "Купить сейчас" to "buy now", "За два дня до" to "two days ago", "После полудня" to "past noon",
        "После заката" to "after sunset", "День за днем" to "day by day", "Всю ночь" to "all through the night", "Это случилось осенью" to "it happened in the autumn")

    override fun receiveTitle(): String {
        return title
    }

    override fun takeLesson(learningTypeSection: LearningTypeSection, lessonTexts: Map<String, String>): List<LessonUnit> {
        return LessonCreator().createLesson(learningTypeSection, lessonTexts, appLessonTexts)
    }

}