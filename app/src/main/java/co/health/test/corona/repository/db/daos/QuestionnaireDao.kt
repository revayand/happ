package co.health.test.corona.repository.db.daos

import androidx.room.*
import co.health.test.corona.repository.db.entities.Questionnaire
import co.health.test.corona.repository.db.entities.QuestionnaireWithQuestions
import io.reactivex.Observable

@Dao
interface QuestionnaireDao {

    @Query("SELECT * FROM questionnaire WHERE questionnaire_id=:id")
    fun getQuestionnaireById(id: Long): Observable<List<Questionnaire>>

    @Query("SELECT * FROM questionnaire")
    fun getAll(): Observable<List<Questionnaire>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(questionnaire: Questionnaire)

    @Transaction
    @Query("select * FROM questionnaire WHERE questionnaire_id=:id")
    fun getQuestionnaireByIdWithQuestions(id: Long): Observable<List<QuestionnaireWithQuestions>>

}