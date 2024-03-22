package aldmitry.dev.learningenglish.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLessonText(entity: LessonsTexts)

    @Delete
    fun deleteLessonText(entity: LessonsTexts)


    @Query("SELECT * FROM lessons_texts")
    fun receiveTextByLessonTitle(): List<LessonsTexts>

}