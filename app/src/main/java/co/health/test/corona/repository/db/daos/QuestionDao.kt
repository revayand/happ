package co.health.test.corona.repository.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.health.test.corona.repository.db.entities.Question
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface QuestionDao {

    @Query("SELECT * FROM question WHERE question_id=:id")
    fun getQuestionById(id: Long): Observable<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: Question):Single<Long>
}