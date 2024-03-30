package aldmitry.dev.learningenglish.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLessonInDb(entity: UserLesson)


    @Delete
    fun deleteLessonInDb(entity: UserLesson)


    @Query("SELECT * FROM lessons_table")
    fun receiveLessonByTitle(): List<UserLesson>

}