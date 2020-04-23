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

        fill()

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

    private fun fill() {
        questionnaireManager.addQuestionnaire(
            Questionnaire(
                "خطای شناختی کرونا",
                QuestionnaireState.FILLED
            )
        ).subscribeWith(object : DisposableSingleObserver<Long>() {
            override fun onSuccess(t: Long) {
                questionManager.addQuestion(
                    Question(
                        "احساس خود در مورد وضعیت پیش آمده از ویروس کرونا را به اختصار توضیح دهید",
                        Answer(null, AnswerType.TEXT, null),
                        t
                    )
                ).subscribeWith(object : DisposableSingleObserver<Long>() {
                    override fun onSuccess(t: Long) {
                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
                questionManager.addQuestion(
                    Question(
                        "چه احساسی دارید؟", Answer(
                            null, AnswerType.CHECK,
                            arrayListOf(
                                "افسردگی","گناه","اندوه","خجالت","خشم","رنجش","ناامیدی","اضطراب","حقارت","تنهایی","ناتوانی","ابهام"
                            )
                        ), t
                    )
                ).subscribeWith(object : DisposableSingleObserver<Long>() {
                    override fun onSuccess(t: Long) {
                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }
                })

                questionManager.addQuestion(
                    Question(
                        "تقريباً منحصراً بر جنبه های منفی متمركز می شويد و به ندرت به جنبه های مثبت توجه می كنيد. مثلاً: \"آمار دقیق فوتی ها را می دانید ولی توجهی به آمار بهبودی ها ندارید\".", Answer(
                            null, AnswerType.RADIO,
                            arrayListOf("ندارم","تا حدودی دارم")
                        ), t
                    )
                ).subscribeWith(object : DisposableSingleObserver<Long>() {
                    override fun onSuccess(t: Long) {
                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")

            }
        })
    }


}