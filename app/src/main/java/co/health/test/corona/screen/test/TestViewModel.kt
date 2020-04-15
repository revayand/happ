package co.health.test.corona.screen.test

import androidx.lifecycle.MutableLiveData
import co.health.test.corona.repository.db.entities.Question
import co.health.test.corona.repository.db.entities.Questionnaire
import co.health.test.corona.repository.db.entities.QuestionnaireWithQuestions
import co.health.test.corona.repository.manager.questionnaire.QuestionnaireManager
import co.health.test.corona.screen.utils.BaseViewModel
import co.health.test.corona.screen.utils.LoadingLayout
import io.reactivex.observers.DisposableObserver

class TestViewModel(private val questionnaireManager: QuestionnaireManager) :
    BaseViewModel() {

    val questions: MutableLiveData<List<Question>> = MutableLiveData()

    fun fetch(id:Long) {
        state.value = Pair(LoadingLayout.LoadingLayoutState.STATE_LOADING, null)
        compositeDisposable.add(questionnaireManager.getQuestionsByQuestionnaireId(id).subscribeWith(object :
            DisposableObserver<QuestionnaireWithQuestions>() {
            override fun onComplete() {

            }

            override fun onNext(t:QuestionnaireWithQuestions) {
                questions.postValue(t.questions)
                state.value = Pair(LoadingLayout.LoadingLayoutState.STATE_SHOW_DATA, null)

            }

            override fun onError(e: Throwable) {

                state.value = Pair(LoadingLayout.LoadingLayoutState.STATE_SHOW_ERROR, e.message)
            }
        }
        ))
    }


}