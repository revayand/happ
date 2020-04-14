package co.health.test.corona.repository.manager.questionnaire

import co.health.test.corona.repository.db.daos.QuestionnaireDao
import co.health.test.corona.repository.db.entities.Questionnaire
import co.health.test.corona.repository.db.entities.QuestionnaireWithQuestions
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuestionnaireManager(private val questionnaireDao: QuestionnaireDao) {

    fun getAllQuestionnaire(): Observable<List<Questionnaire>> {
        return questionnaireDao.getAll().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getQuestionsByQuestionnaireId(id: Long): Observable<List<QuestionnaireWithQuestions>> {
        return questionnaireDao.getQuestionnaireByIdWithQuestions(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addQuestionnaire(questionnaire: Questionnaire): Single<Long> {
        return questionnaireDao.insert(questionnaire).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}