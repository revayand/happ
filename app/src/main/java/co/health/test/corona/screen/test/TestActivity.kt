package co.health.test.corona.screen.test


import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.setPadding
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.AnswerType
import co.health.test.corona.repository.db.entities.Question
import co.health.test.corona.screen.main.home.question.dummy.DummyContentQuestionnaire
import co.health.test.corona.screen.result.ResultActivity
import co.health.test.corona.screen.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.test_row.view.*
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

    }

    private fun observableViewModel() {
        val t =
            DummyContentQuestionnaire.ITEMS.first { it.questionnaire.title.trim() == questionnaireId.trim() }


        val a = t.questions

        toolbar_title.text = t.questionnaire.title

        questions.clear()
        questions.addAll(a!!)

//        display(questions[index], index)
        diss()

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

    private fun diss() {
        for ((i, q) in questions.withIndex()) {
            val vq = LayoutInflater.from(this).inflate(R.layout.test_row, ll, false)
            val vqf = display(q, i, vq)
            ll.addView(vqf, i)
        }
    }

    private fun display(question: Question, index: Int, view: View): View {
        view.tv_question.text = "${index + 1}. ${question.question}"
//        tv_current_q.text = (index + 1).toString().toFarsi()
        view.v_answer.removeAllViews()
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

                    view.v_answer.addView(checkBox)
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
                view.v_answer.addView(radioGroup)
            }
            AnswerType.TEXT -> {

                val row = EditText(this)
                row.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                view.v_answer.addView(row)
            }
        }
        return view
    }


    override fun onResume() {
        super.onResume()
//        testViewModel.fetch(questionnaireId)
    }

    override fun onClick(v: View?) {

        if (v == btn_apply) {
            val next = Intent(this, ResultActivity::class.java)
            next.putExtra("title", toolbar_title.text.toString())
            next.putExtra("res", "شما بدون اختلال هستید")
            startActivity(next)
            index++
            if (index == questions.size)
                index = 0
//            display(questions[index], index)
        }
    }
}
