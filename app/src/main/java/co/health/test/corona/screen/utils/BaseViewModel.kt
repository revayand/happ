package co.health.test.corona.screen.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<Pair<Boolean, String?>> = MutableLiveData()


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}