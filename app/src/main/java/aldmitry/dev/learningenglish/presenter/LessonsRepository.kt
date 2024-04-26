package aldmitry.dev.learningenglish.presenter

import aldmitry.dev.learningenglish.database.Dao
import aldmitry.dev.learningenglish.database.UserLesson
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LessonsRepository(private val dao: Dao) {

    suspend fun addLesson(englishText: String, russianText: String, lessonTitle: String) {
        val userLesson = UserLesson(englishText, russianText, lessonTitle)
        withContext(Job() + Dispatchers.IO) {
            dao.addLessonInDb(userLesson)
        }
    }


    suspend fun deleteLesson(englishText: String, russianText: String, lessonTitle: String) {
        val userLesson = UserLesson(englishText, russianText, lessonTitle)
        withContext(Job() + Dispatchers.IO) {
            dao.deleteLessonInDb(userLesson)
        }
    }


    suspend fun receiveLessons(lessonList: MutableState<MutableList<UserLesson>>) {
        withContext(Job() + Dispatchers.IO) {
            lessonList.value = dao.receiveLessons()
        }
    }


    suspend fun receiveLessonsByTitle(list: MutableList<UserLesson>, lessonTitle: String) {
        withContext(Job() + Dispatchers.IO) {
            list.addAll(dao.receiveLessonsByTitle(lessonTitle))
        }
    }


}