package aldmitry.dev.learningenglish.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserLesson::class],  // лист entity-классов
    version = 2 // номер версии для отслеживания изменений бд
)
abstract class LessonDatabase : RoomDatabase() {


    abstract fun lessonsDao(): Dao

}