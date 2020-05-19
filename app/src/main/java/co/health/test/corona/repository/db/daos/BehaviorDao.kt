package co.health.test.corona.repository.db.daos

import androidx.room.*
import co.health.test.corona.repository.db.entities.*
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface BehaviorDao {


    @Query("SELECT * FROM behavior WHERE behavior_id=:id")
    fun getBehaviorById(id: Long): Observable<Behavior>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Behavior):Single<Long>

}