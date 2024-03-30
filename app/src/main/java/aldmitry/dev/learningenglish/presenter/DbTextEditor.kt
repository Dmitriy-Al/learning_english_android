package aldmitry.dev.learningenglish.presenter

import aldmitry.dev.learningenglish.database.Database
import aldmitry.dev.learningenglish.database.UserLesson


class DbTextEditor(private val lessonTitle: String, val database: Database) {

    fun addLesson(englishText: String, russianText: String) {
        val userLesson = UserLesson(englishText, russianText, lessonTitle)
        Thread {
            database.lessonsDao().addLessonInDb(userLesson)
        }.start()
    }

    fun deleteLesson(englishText: String, russianText: String) {
        val userLesson = UserLesson(englishText, russianText, lessonTitle)
        Thread {
            database.lessonsDao().deleteLessonInDb(userLesson)
        }.start()
    }

    fun receiveLessonsFromDb(): List<UserLesson> {
        val lessonsList = mutableListOf<UserLesson>()
        Thread {
            lessonsList.addAll(database.lessonsDao().receiveLessonByTitle())
        }.start()

        return lessonsList
    }


}