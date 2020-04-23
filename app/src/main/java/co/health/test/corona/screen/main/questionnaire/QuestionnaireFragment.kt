package co.health.test.corona.screen.main.questionnaire

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
import co.health.test.corona.repository.db.entities.QuestionnaireWithQuestions
import co.health.test.corona.screen.main.home.question.dummy.DummyContentQuestionnaire
import co.health.test.corona.screen.main.home.question.dummy.DummyContentt
import co.health.test.corona.screen.utils.LoadingLayout
import kotlinx.android.synthetic.main.fragment_questionnaire_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class QuestionnaireFragment : Fragment() {


    private var listener: OnListFragmentInteractionListener? = null

    val questionnaireViewModel: QuestionnaireViewModel by viewModel()

    private var questionnaires: MutableList<Questionnaire> = ArrayList()
    private lateinit var adapter: QuestionnaireRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            adapter = QuestionnaireRecyclerViewAdapter(DummyContentQuestionnaire.ITEMS, listener)
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: QuestionnaireWithQuestions?)
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
