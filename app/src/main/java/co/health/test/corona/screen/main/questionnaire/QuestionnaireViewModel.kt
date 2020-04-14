package co.health.test.corona.screen.main.questionnaire

import androidx.lifecycle.MutableLiveData
import co.health.test.corona.repository.db.entities.Questionnaire
import co.health.test.corona.repository.manager.questionnaire.QuestionnaireManager
import co.health.test.corona.screen.utils.BaseViewModel
import co.health.test.corona.screen.utils.LoadingLayout
import io.reactivex.observers.DisposableObserver

class QuestionnaireViewModel(private val questionnaireManager: QuestionnaireManager) :
    BaseViewModel() {

    val questionnaires: MutableLiveData<List<Questionnaire>> = MutableLiveData()

    fun fetch() {
        state.value = Pair(LoadingLayout.LoadingLayoutState.STATE_LOADING, null)
        compositeDisposable.add(questionnaireManager.getAllQuestionnaire().subscribeWith(object :
            DisposableObserver<List<Questionnaire>>() {
            override fun onComplete() {

            }

            override fun onNext(t: List<Questionnaire>) {
                questionnaires.postValue(t)
                state.value = Pair(LoadingLayout.LoadingLayoutState.STATE_SHOW_DATA, null)

            }

            override fun onError(e: Throwable) {

                state.value = Pair(LoadingLayout.LoadingLayoutState.STATE_SHOW_ERROR, e.message)
            }
        }
        ))
    }


}