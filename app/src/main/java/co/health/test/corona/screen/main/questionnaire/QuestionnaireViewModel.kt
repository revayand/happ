package co.health.test.corona.screen.main.questionnaire

import androidx.lifecycle.MutableLiveData
import co.health.test.corona.repository.db.entities.*
import co.health.test.corona.repository.manager.questionnaire.QuestionManager
import co.health.test.corona.repository.manager.questionnaire.QuestionnaireManager
import co.health.test.corona.screen.utils.BaseViewModel
import co.health.test.corona.screen.utils.LoadingLayout
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver

class QuestionnaireViewModel(
    private val questionnaireManager: QuestionnaireManager,
    private val questionManager: QuestionManager
) :
    BaseViewModel() {

    val questionnaires: MutableLiveData<List<Questionnaire>> = MutableLiveData()

    fun fetch() {

//        fill()
//
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