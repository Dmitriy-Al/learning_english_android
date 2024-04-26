package aldmitry.dev.learningenglish.presenter

import aldmitry.dev.learningenglish.database.LessonDatabase
import aldmitry.dev.learningenglish.model.Learnable
import aldmitry.dev.learningenglish.database.UserLesson
import aldmitry.dev.learningenglish.receiveUserLessons
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class LearningHandler(private val chooseLearningTypeSection: LearningTypeSection, private val lesson: Learnable, private val userLessons: MutableList<UserLesson>) {

    fun receiveLessonTextCollector(): List<LessonUnit> { // TODO list в map
        val userLessonText = mutableMapOf<String, String>()

        for (elem in userLessons) {
            if (elem.lessonTitle == lesson.receiveTitle()) {
                userLessonText[elem.russianText] = elem.englishText
            }
        }
        return lesson.takeLesson(chooseLearningTypeSection, userLessonText)
    }
}

