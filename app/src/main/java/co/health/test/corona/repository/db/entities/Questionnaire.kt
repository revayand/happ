package co.health.test.corona.repository.db.entities

import androidx.room.*

@Entity(tableName = "questionnaire")
data class Questionnaire(
    val average:Double = 0.0,
    val sDeviation: Double = 1.0,
    val description:String?,
    val img:Int ,
    val margin:Int ,
    val questionsPart:String? , // ravanparishi:1-2-3-4-5,
    val answerPart:String? , // ravanparishi:3,
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