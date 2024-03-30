package aldmitry.dev.learningenglish.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserLesson::class], version = 2)
abstract class Database : RoomDatabase() {

abstract fun lessonsDao(): Dao

}