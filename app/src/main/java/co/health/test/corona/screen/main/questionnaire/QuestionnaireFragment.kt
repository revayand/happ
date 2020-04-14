package co.health.test.corona.screen.main.questionnaire

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.Questionnaire
import co.health.test.corona.repository.db.entities.QuestionnaireState
import co.health.test.corona.repository.manager.questionnaire.QuestionnaireManager
import co.health.test.corona.screen.utils.LoadingLayout
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.android.synthetic.main.fragment_questionnaire_list.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class QuestionnaireFragment : Fragment() {


    private var listener: OnListFragmentInteractionListener? = null

    val questionnaireViewModel: QuestionnaireViewModel by viewModel()

    val questionnaireManager: QuestionnaireManager by inject()

    private var questionnaires: MutableList<Questionnaire> = ArrayList()
    private var adapter = QuestionnaireRecyclerViewAdapter(questionnaires, listener)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionnaireManager.addQuestionnaire(
            Questionnaire(
                "q1",
                QuestionnaireState.FILLED,
                System.currentTimeMillis()
            )
        ).subscribeWith(object : DisposableSingleObserver<Long>() {
            override fun onSuccess(t: Long) {

            }

            override fun onError(e: Throwable) {

            }
        })
        observableViewModel()

    }

    private fun observableViewModel() {
        questionnaireViewModel.questionnaires.observe(this, Observer {
            if (it == null)
                return@Observer
            questionnaires.clear()
            questionnaires.addAll(it)
            adapter.notifyDataSetChanged()
        })

        questionnaireViewModel.state.observe(this, Observer {
            ll.state = it.first
            if (ll.state == LoadingLayout.LoadingLayoutState.STATE_SHOW_ERROR)
                it.second?.let { it1 -> ll.setError(it1) }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_questionnaire_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


            list.layoutManager = LinearLayoutManager(context)
            list.adapter = adapter

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Questionnaire?)
    }

    override fun onResume() {
        super.onResume()
        questionnaireViewModel.fetch()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            QuestionnaireFragment()
    }
}
