package co.health.test.corona.repository.db.entities

import androidx.room.*

@Entity(tableName = "questionnaire")
data class Questionnaire(
    val title: String, val state: QuestionnaireState,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "questionnaire_id")
    val id: Long = 0L)

data class QuestionnaireWithQuestions(
    @Embedded val questionnaire: Questionnaire,
    @Relation(parentColumn = "questionnaire_id", entityColumn = "parent_questionnaire_id")
    val questions: List<Question>
)

enum class QuestionnaireState {
    FILLED, NOT_FILLED, IN_FILLING
}