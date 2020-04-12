package co.health.test.corona.repository.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.health.test.corona.repository.db.entities.Questionnaire
import co.health.test.corona.repository.db.entities.QuestionnaireWithQuestions

@Dao
interface QuestionnaireDao  {

    @Query("SELECT * FROM questionnaire WHERE questionnaire_id=:id")
    fun  getQuestionnaireById(id:String):List<Questionnaire>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun  insert(questionnaire: Questionnaire)

    @Query("select * FROM questionnaire WHERE questionnaire_id=:id")
    fun  getQuestionnaireByIdWithQuestions(id:String):List<QuestionnaireWithQuestions>

}