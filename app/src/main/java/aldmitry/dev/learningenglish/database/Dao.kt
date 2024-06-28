package aldmitry.dev.learningenglish.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLessonInDb(entity: UserLesson)


    @Delete
    fun deleteLessonInDb(entity: UserLesson)


    @Update
    fun replaceLessonInDb(oldEntity: UserLesson, newEntity: UserLesson)


    @Query("SELECT * FROM lessons_table")
    fun receiveLessons(): MutableList<UserLesson>


    @Query("SELECT * FROM lessons_table WHERE lessonTitle = :lessonTitle")
    fun receiveLessonsByTitle(lessonTitle: String): List<UserLesson>

}