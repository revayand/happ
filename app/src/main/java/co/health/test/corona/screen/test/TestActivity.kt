package co.health.test.corona.screen.test


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.AnswerType
import co.health.test.corona.repository.db.entities.Question
import co.health.test.corona.screen.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_test.*
import org.koin.android.viewmodel.ext.android.viewModel


class TestActivity : BaseActivity(), View.OnClickListener {

    var questionnaireId: Long = 0L

    val testViewModel: TestViewModel by viewModel()
    var index = 0

    private var questions: MutableList<Question> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        questionnaireId = intent.getLongExtra("questionnaireId", 0L)
        observableViewModel()
        btn_apply.setOnClickListener(this)
        btn_previus.setOnClickListener(this)

    }

    private fun observableViewModel() {
        testViewModel.questions.observe(this, Observer {
            if (it == null)
                return@Observer
            questions.clear()
            questions.addAll(it)
            display(questions[index])
        })

//        testViewModel.state.observe(this, Observer {
//            ll.state = it.first
//            if (ll.state == LoadingLayout.LoadingLayoutState.STATE_SHOW_ERROR)
//                it.second?.let { it1 -> ll.setError(it1) }
//        })
    }

    fun display(question: Question) {
        tv_question.text = question.question
        v_answer.removeAllViews()
        when (question.answer.type) {
            AnswerType.CHECK -> {


                for (i in question.answer.selections!!.indices) {
                    val row = TableRow(this)
                    row.id = i
                    row.layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    val checkBox = CheckBox(this)
//                    checkBox.setOnCheckedChangeListener(this)
                    checkBox.id = i
                    checkBox.text = question.answer.selections[i]
                    row.addView(checkBox)
                    v_answer.addView(row)
                }

            }
            AnswerType.RADIO -> {

                val radioGroup = RadioGroup(this)

                for (i in question.answer.selections!!.indices) {
                    val row = TableRow(this)
                    row.id = i
                    row.layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    val checkBox = RadioButton(this)
//                    checkBox.setOnCheckedChangeListener(this)
                    checkBox.id = i
                    checkBox.text = question.answer.selections[i]
                    row.addView(checkBox)
                    radioGroup.addView(row)
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
        testViewModel.fetch(questionnaireId)
    }

    override fun onClick(v: View?) {

        if (v == btn_apply) {
            index++
            display(questions[index])
        } else
            if (v == btn_previus) {
                index--
                display(questions[index])
            }
    }
}
