package co.health.test.corona.repository.db.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class Question(
    val question: String,
    @Embedded val answer: Answer,
    @ColumnInfo(name = "parent_questionnaire_id") val questionnaireId: Long,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    val id: Long = 0
)

data class Answer(val data: String?, val type: AnswerType,val selections:List<String>?)

enum class AnswerType {
    TEXT, RADIO, CHECK
}
