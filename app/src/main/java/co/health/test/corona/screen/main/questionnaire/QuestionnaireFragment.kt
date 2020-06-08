package co.health.test.corona.screen.main.questionnaire

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.*
import co.health.test.corona.repository.manager.questionnaire.QuestionnaireManager
import co.health.test.corona.repository.manager.questionnaire.UsersManager
import co.health.test.corona.screen.main.home.question.dummy.DummyContentQuestionnaire
import co.health.test.corona.screen.test.TestActivity
import co.health.test.corona.screen.utils.LoadingLayout
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.fragment_learn.view.*
import kotlinx.android.synthetic.main.fragment_questionnaire_list.*
import kotlinx.android.synthetic.main.row_divider.view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class QuestionnaireFragment : Fragment() {


    private lateinit var answers: List<Answerr>
    private lateinit var user: Users
    private var listener: OnListFragmentInteractionListener? = null

    val questionnaireViewModel: QuestionnaireViewModel by viewModel()
    val sharedPreferences: SharedPreferences by inject()
    val userManager: UsersManager by inject()
    val questionnaireManager: QuestionnaireManager by inject()

    private var questionnaires: MutableList<Questionnaire> = ArrayList()
//    private lateinit var adapter: QuestionnaireRecyclerViewAdapter


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
//            adapter.notifyDataSetChanged()
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


//        list.layoutManager = LinearLayoutManager(context)
//        list.adapter = adapter
        fetchData()
    }

    private fun fetchData() {
        val userId = sharedPreferences.getLong("id", -1)
        if (userId != -1L) {
            userManager.getAnswerByUser(userId).zipWith(questionnaireManager.getAllQuestionnaire(),
                BiFunction<UsersWithAnswers, List<Questionnaire>, Pair<UsersWithAnswers, List<Questionnaire>>> { t, u ->
                    Pair(
                        t,
                        u
                    )
                }).subscribeWith(object :
                DisposableObserver<Pair<UsersWithAnswers, List<Questionnaire>>>() {
                override fun onComplete() {


                }

                override fun onNext(t: Pair<UsersWithAnswers, List<Questionnaire>>) {
                    user = t.first.users
                    answers = t.first.answers.filter { it.part == null }
                    questionnaires = t.second.toMutableList()
                    showData()
                }

                override fun onError(e: Throwable) {

                }
            })
        }
    }

    private fun showData() {
        val questionnaireOlaviat: MutableList<Questionnaire> = ArrayList()
        if (user.detail.properties?.contains("ezterab") == true) {
            questionnaires.find { it.title == "تست اضطراب" }?.let {
                questionnaireOlaviat.add(it)
                questionnaires.remove(it)
            }
        }
        if (user.detail.properties?.contains("khodkoshi") == true) {
            questionnaires.find { it.title == "تست مکمل افسردگی" }?.let {
                questionnaireOlaviat.add(it)
                questionnaires.remove(it)
            }
        }
        if (user.detail.properties?.contains("afsordegi") == true) {
            questionnaires.find { it.title == "تست افسردگی" }?.let {
                questionnaireOlaviat.add(it)
                questionnaires.remove(it)
            }
        }


        if (user.detail.properties?.contains("vasvas") == true) {
            questionnaires.find { it.title == "تست وسواس" }?.let {
                questionnaireOlaviat.add(it)
                questionnaires.remove(it)
            }
        }
        if (questionnaireOlaviat.isNotEmpty()) {
            val header = LayoutInflater.from(activity).inflate(R.layout.row_divider, list, false)
            header.tv.text = "پرسشنامه های اولویت دار"
            list.addView(header)
            questionnaireOlaviat.forEach { q ->
                val row =
                    LayoutInflater.from(activity)
                        .inflate(R.layout.row_questionnaire_small, list, false)
                row.tv_desc.text = q.title
                row.setOnClickListener {
                    val intent = Intent(activity, TestActivity::class.java).apply {
                        putExtra("questionnaireId", q.id)
                    }
                    startActivity(intent)

                }
                row.tv_date.setTextColor(Color.RED)
                row.tv_date.text = "این تست هنوز انجام نشده"
                answers.findLast { it.questionnaireId == q.id }?.let {
                    row.tv_date.text = it.desc

                    row.tv_date.setTextColor(ContextCompat.getColor(activity!!, R.color.green))
                    row.setOnClickListener(null)
                }
                row.iv.setImageResource(q.img)

                list.addView(row)

            }

        }



        if (questionnaires.isNotEmpty()) {
            val header = LayoutInflater.from(activity).inflate(R.layout.row_divider, list, false)
            header.tv.text = "پرسشنامه های مکمل"
            list.addView(header)
            questionnaires.filter { it.title!= "تست مکمل افسردگی" }.forEach { q ->
                val row =
                    LayoutInflater.from(activity)
                        .inflate(R.layout.row_questionnaire_small, list, false)
                row.tv_desc.text = q.title
                row.setOnClickListener {
                    val intent = Intent(activity, TestActivity::class.java).apply {
                        putExtra("questionnaireId", q.id)
                    }
                    startActivity(intent)

                }
                row.tv_date.setTextColor(Color.RED)
                row.tv_date.text = "این تست هنوز انجام نشده"
                answers.findLast { it.questionnaireId == q.id }?.let {
                    row.tv_date.text = it.desc

                    row.tv_date.setTextColor(ContextCompat.getColor(activity!!, R.color.green))
                    row.setOnClickListener(null)
                }

                row.iv.setImageResource(q.img)

                list.addView(row)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
//            adapter = QuestionnaireRecyclerViewAdapter(DummyContentQuestionnaire.ITEMS, listener)
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
