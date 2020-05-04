package co.health.test.corona.repository.manager.questionnaire

import co.health.test.corona.repository.db.daos.QuestionDao
import co.health.test.corona.repository.db.daos.UsersDao
import co.health.test.corona.repository.db.entities.Question
import co.health.test.corona.repository.db.entities.Users
import co.health.test.corona.repository.db.entities.UsersWithAnswers
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UsersManager(private val usersDao: UsersDao) {


    fun getUsers(id: Long): Observable<Users> {
        return usersDao.getUsersById(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getAllUsers(): Observable<List<Users>> {
        return usersDao.getAllUsers().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addUsers(users: Users): Single<Long> {
        return usersDao.insert(users).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun delete(users: Users): Single<Int> {
        return usersDao.delete(users).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun getAnswerByUser(id: Long): Observable<UsersWithAnswers> {
        return usersDao.getAnswersByUserId(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}