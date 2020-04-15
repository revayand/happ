package co.health.test.corona.repository.manager.questionnaire

import co.health.test.corona.repository.db.daos.QuestionDao
import co.health.test.corona.repository.db.entities.Question
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuestionManager(private val questionDao: QuestionDao) {


    fun getQuestionsByQuestionnaireId(id: Long): Observable<Question> {
        return questionDao.getQuestionById(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addQuestion(question: Question): Single<Long> {
        return questionDao.insert(question).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}