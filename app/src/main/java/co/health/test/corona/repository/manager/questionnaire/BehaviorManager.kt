package co.health.test.corona.repository.manager.questionnaire


import co.health.test.corona.repository.db.daos.BehaviorDao
import co.health.test.corona.repository.db.entities.Behavior
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BehaviorManager(private val behaviorDao: BehaviorDao) {

    fun getBehavior(id: Long): Observable<Behavior> {
        return behaviorDao.getBehaviorById(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addBehavior(behavior: Behavior): Single<Long> {
        return behaviorDao.insert(behavior).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}