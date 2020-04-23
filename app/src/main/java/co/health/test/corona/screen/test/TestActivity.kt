package co.health.test.corona.screen.test


import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.setPadding
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.AnswerType
import co.health.test.corona.repository.db.entities.Question
import co.health.test.corona.screen.main.home.question.dummy.DummyContentQuestionnaire
import co.health.test.corona.screen.utils.BaseActivity
import co.health.test.corona.screen.utils.toFarsi
import kotlinx.android.synthetic.main.activity_test.*
import org.koin.android.viewmodel.ext.android.viewModel


class TestActivity : BaseActivity(), View.OnClickListener {

    var questionnaireId: String = ""

    val testViewModel: TestViewModel by viewModel()
    var index = 0

    private var questions: MutableList<Question> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        questionnaireId = intent.getStringExtra("questionnaireId")
        observableViewModel()
        btn_apply.setOnClickListener(this)
        btn_previus.setOnClickListener(this)

    }

    private fun observableViewModel() {
        val a =
            DummyContentQuestionnaire.ITEMS.filter { it.questionnaire.title.trim() == questionnaireId.trim() }
                .map { it.questions }.firstOrNull()

        questions.clear()
        questions.addAll(a!!)
        tv_total_q.text = questions.size.toString().toFarsi()
        display(questions[index], index)

//        testViewModel.questions.observe(this, Observer {
//            if (it == null)
//                return@Observer
//            questions.clear()
//            questions.addAll(it)
//            tv_total_q.text = questions.size.toString().toFarsi()
//            display(questions[index], index)
//        })


//        testViewModel.state.observe(this, Observer {
//            ll.state = it.first
//            if (ll.state == LoadingLayout.LoadingLayoutState.STATE_SHOW_ERROR)
//                it.second?.let { it1 -> ll.setError(it1) }
//        })
    }

    private fun display(question: Question, index: Int) {
        tv_question.text = question.question
        tv_current_q.text = (index + 1).toString().toFarsi()
        v_answer.removeAllViews()
        when (question.answer.type) {
            AnswerType.CHECK -> {


                for (i in question.answer.selections!!.indices) {


                    val checkBox = CheckBox(this)
//                    checkBox.setOnCheckedChangeListener(this)
                    checkBox.layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    checkBox.setPadding(20)
                    checkBox.id = i
                    checkBox.text = question.answer.selections[i]

                    v_answer.addView(checkBox)
                }


            }
            AnswerType.RADIO -> {

                val radioGroup = RadioGroup(this)
                radioGroup.orientation = LinearLayout.VERTICAL
                radioGroup.gravity = Gravity.RIGHT
                for (i in question.answer.selections!!.indices) {

                    val checkBox = RadioButton(this)
//                    checkBox.setOnCheckedChangeListener(this)
                    checkBox.layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    checkBox.setPadding(20)

                    checkBox.id = i
                    checkBox.text = question.answer.selections[i]

                    radioGroup.addView(checkBox)
                }
                v_answer.addView(radioGroup)
            }
            AnswerType.TEXT -> {

                val row = EditText(this)
                row.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                v_answer.addView(row)
            }
        }
    }


    override fun onResume() {
        super.onResume()
//        testViewModel.fetch(questionnaireId)
    }

    override fun onClick(v: View?) {

        if (v == btn_apply) {
            index++
            if (index == questions.size)
                index = 0
            display(questions[index], index)
        } else
            if (v == btn_previus) {

                index--
                if (index == -1)
                    index = questions.size - 1
                display(questions[index], index)
            }
    }
}
