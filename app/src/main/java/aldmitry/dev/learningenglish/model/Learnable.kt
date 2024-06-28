package aldmitry.dev.learningenglish.model

import aldmitry.dev.learningenglish.presenter.LearningTypeSection
import aldmitry.dev.learningenglish.presenter.LessonUnit

interface Learnable {
    /**
     * Интерфейс Learnable объединяет классы-уроки, разделенные на тематические разделы. Каждый
     * класс Learnable содержит название и функцию возвращающюю его, id изображения-подсказки
     * и функцию возвращающюю его, а также функцию, возвращающую list содежащий LessonUnit-объекты.
     * Опционално Learnable класс может иметь/не иметь Map с текстами уроков.
     *
     * The Learnable interface combines lesson classes divided into thematic sections. Each
     * Learnable class contains a name and a function to get the name, an id for image with hint and
     * a function to get this id, as well as a function to get a list containing LessonUnit objects.
     * Optionally, Learnable class may/may not have a Map with lesson texts.
     */

    /**
     * Функция возвращает название раздела-урока (Present continuous, Present simple и т.д.)
     *
     * The function returns the name of the lesson section (Present continuous, Present simple, etc.)
     */
    fun receiveTitle(): String

    /**
     * Функция возвращает id изображения-подсказки из drawable
     *
     * The function returns the id of the hint image from the drawable
     */
    fun receiveHintPictureId(): Int

    /**
     * Функция takeLesson принимает в качестве параметров:
     * 1. выбранную секцию тренировки, которая определяет, будут ли использованы тексты класса
     * Learnable
     * 2. Map с английскими и русскими текстами тренировок, которые пользователь добавил
     * самостоятельно
     * В соответствии с полученными параметрами, функция использует тексты пользователя и
     * опционально тексты выбранного урока, объединяя их в LessonUnit - объект-урок.
     *
     * The takeLesson function takes as parameters:
     * 1. The selected training section, which determines whether the texts of the Learnable class
     * will be used
     * 2. Map with English and Russian texts that the user added himself
     * According to the received parameters, the function uses the user's texts and optionally
     * the texts of the selected lesson, combining them into a Lesson Unit - lesson object
     */
    fun takeLesson(learningTypeSection: LearningTypeSection, lessonTexts: Map<String, String>): List<LessonUnit>

}