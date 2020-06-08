package co.health.test.corona.repository.db.entities

import androidx.room.*

@Entity(tableName = "answer")
data class Answerr(
    val point: Int?,
    val desc:String?,
    val part:String?,
    @ColumnInfo(name = "parent_users_id") val usersId: Long,
    @ColumnInfo(name = "parent_questionnaire_id") val questionnaireId: Long,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "answer_id")
    val id: Long = 0
)

data class AnswerAndQuestionnaire(
    @Embedded val answer: Answerr,
    @Relation(
        parentColumn = "parent_questionnaire_id",
        entityColumn = "questionnaire_id"
    )
    val questionnaire: Questionnaire
)
