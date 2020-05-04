package co.health.test.corona.repository.db.daos

import androidx.room.*
import co.health.test.corona.repository.db.entities.*
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface AnswerDao {


    @Query("SELECT * FROM answer WHERE answer_id=:id")
    fun getAnswerById(id: Long): Observable<Answerr>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(answer: Answerr):Single<Long>



    @Transaction
    @Query("select * FROM answer WHERE answer_id=:id")
    fun getQuestionnaireByAnswerId(id: Long): Observable<AnswerAndQuestionnaire>

}