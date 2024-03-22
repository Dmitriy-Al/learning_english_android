package aldmitry.dev.learningenglish.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LessonsTexts::class], version = 2)
abstract class Database : RoomDatabase() {

abstract fun lessonsTextsDao(): Dao

}