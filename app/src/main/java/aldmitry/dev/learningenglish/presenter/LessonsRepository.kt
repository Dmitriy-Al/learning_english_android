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


    suspend fun receiveLessonsByTitle(list: MutableState<List<UserLesson>>, lessonTitle: String) {
        withContext(Job() + Dispatchers.IO) {
            list.value = dao.receiveLessonsByTitle(lessonTitle)
        }
    }


    suspend fun replaceLesson(oldEnglishText: String, oldRussianText: String,
                              oldLessonTitle: String, newEnglishText: String,
                              newRussianText: String, newLessonTitle: String) {
        val oldTitledUserLesson = UserLesson(oldEnglishText, oldRussianText, oldLessonTitle)
        val newTitledUserLesson = UserLesson(newEnglishText, newRussianText, newLessonTitle)
        withContext(Job() + Dispatchers.IO) {
            dao.replaceLessonInDb(oldTitledUserLesson, newTitledUserLesson)
        }
    }


    suspend fun receiveLessons(lessonList: MutableState<MutableList<UserLesson>>) {
        withContext(Job() + Dispatchers.IO) {
            lessonList.value = dao.receiveLessons()
        }
    }

}

