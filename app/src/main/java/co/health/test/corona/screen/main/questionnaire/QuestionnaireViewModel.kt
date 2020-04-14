package co.health.test.corona.screen.main.questionnaire

import androidx.lifecycle.MutableLiveData
import co.health.test.corona.repository.db.entities.Questionnaire
import co.health.test.corona.repository.manager.questionnaire.QuestionnaireManager
import co.health.test.corona.screen.utils.BaseViewModel
import io.reactivex.observers.DisposableObserver

class QuestionnaireViewModel(private val questionnaireManager: QuestionnaireManager) :
    BaseViewModel() {

    val questionnaires: MutableLiveData<List<Questionnaire>> = MutableLiveData()

    fun fetch() {
        loading.value = true
        error.value = Pair(false, null)
        compositeDisposable.add(questionnaireManager.getAllQuestionnaire().subscribeWith(object :
            DisposableObserver<List<Questionnaire>>() {
            override fun onComplete() {

            }

            override fun onNext(t: List<Questionnaire>) {
                questionnaires.postValue(t)
                error.value = Pair(false, null)
                loading.value = false

            }

            override fun onError(e: Throwable) {
                loading.value = false
                error.value = Pair(true, e.message)
            }
        }
        ))
    }


}