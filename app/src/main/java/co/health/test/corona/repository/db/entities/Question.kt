package co.health.test.corona.repository.db.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "question")
data class Question(
    val question: String,
    @Embedded val answer: Answer,
    @ColumnInfo(name = "parent_questionnaire_id")val questionnaireId: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    val id: Long
)

data class Answer(val data: String, val type: AnswerType)

enum class AnswerType {
    TEXT, RADIO, CHECK
}
