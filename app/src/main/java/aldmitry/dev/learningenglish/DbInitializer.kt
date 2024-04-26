package aldmitry.dev.learningenglish

import aldmitry.dev.learningenglish.database.LessonDatabase
import aldmitry.dev.learningenglish.presenter.LessonsRepository
import android.content.Context
import androidx.room.Room

object DbInitializer {
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context
    }


    private val lessonDatabase: LessonDatabase by lazy {
        Room.databaseBuilder(
            appContext,
            LessonDatabase::class.java,
            "lessons_db"
        ).build()
    }


    val lessonsRepository: LessonsRepository by lazy {
        LessonsRepository(lessonDatabase.lessonsDao())
    }

}