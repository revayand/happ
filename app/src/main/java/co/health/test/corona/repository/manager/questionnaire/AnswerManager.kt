package co.health.test.corona.repository.manager.questionnaire

import co.health.test.corona.repository.db.daos.AnswerDao
import co.health.test.corona.repository.db.entities.Answer
import co.health.test.corona.repository.db.entities.AnswerAndQuestionnaire
import co.health.test.corona.repository.db.entities.Answerr
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AnswerManager(private val answerDao: AnswerDao) {

    fun getAnswer(id: Long): Observable<Answerr> {
        return answerDao.getAnswerById(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addAnswer(answer: Answerr): Single<Long> {
        return answerDao.insert(answer).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun getQuestionnaireByAnswer(id: Long): Observable<AnswerAndQuestionnaire> {
        return answerDao.getQuestionnaireByAnswerId(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}