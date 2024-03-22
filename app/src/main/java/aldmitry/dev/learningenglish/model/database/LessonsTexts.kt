package aldmitry.dev.learningenglish.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons_texts")
data class LessonsTexts(

    @PrimaryKey
    val englishText: String,

    val russianText: String,

    val lessonTitle: String
)




