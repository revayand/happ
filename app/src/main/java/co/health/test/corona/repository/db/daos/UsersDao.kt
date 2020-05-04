package co.health.test.corona.repository.db.daos

import androidx.room.*
import co.health.test.corona.repository.db.entities.Question
import co.health.test.corona.repository.db.entities.QuestionnaireWithQuestions
import co.health.test.corona.repository.db.entities.Users
import co.health.test.corona.repository.db.entities.UsersWithAnswers
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface UsersDao {


    @Query("SELECT * FROM users WHERE users_id=:id")
    fun getUsersById(id: Long): Observable<Users>

    @Query("SELECT * FROM users")
    fun getAllUsers(): Observable<List<Users>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: Users):Single<Long>

    @Delete
    fun delete(users: Users):Single<Int>


    @Transaction
    @Query("select * FROM users WHERE users_id=:id")
    fun getAnswersByUserId(id: Long): Observable<UsersWithAnswers>

}