package aldmitry.dev.learningenglish.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons_table")
data class UserLesson(

    @PrimaryKey
    val englishText: String,

    val russianText: String,

    val lessonTitle: String
)




