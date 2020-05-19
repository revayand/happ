package co.health.test.corona.repository.db.daos

import androidx.room.*
import co.health.test.corona.repository.db.entities.Users
import co.health.test.corona.repository.db.entities.UsersWithAnswers
import co.health.test.corona.repository.db.entities.UsersWithBehaviors
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface UsersDao {


    @Query("SELECT * FROM users WHERE users_id=:id")
    fun getUsersById(id: Long): Single<Users>

    @Query("SELECT * FROM users")
    fun getAllUsers(): Observable<List<Users>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: Users): Long


    @Update
    fun update(users: Users): Int

    @Transaction
    fun upsert(users: Users): Long {
        var id: Long = insert(users)
        if (id == -1L) {
            id = update(users).toLong()
        }
        return id
    }


    @Delete
    fun delete(users: Users): Single<Int>


    @Transaction
    @Query("select * FROM users WHERE users_id=:id")
    fun getAnswersByUserId(id: Long): Observable<UsersWithAnswers>

    @Transaction
    @Query("select * FROM users WHERE users_id=:id")
    fun getBehaviorByUserId(id: Long): Observable<UsersWithBehaviors>

}