package aldmitry.dev.learningenglish.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserLesson::class], version = 2)
abstract class LessonDatabase : RoomDatabase() {


abstract fun lessonsDao(): Dao

companion object {
    fun createDb(context: Context): LessonDatabase {
        return  Room.databaseBuilder(
            context,
            LessonDatabase::class.java,
            "lessons_db"
        ).build()
    }
}



}