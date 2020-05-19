package co.health.test.corona.screen.test


import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.AnswerType
import co.health.test.corona.repository.db.entities.Question
import co.health.test.corona.repository.db.entities.QuestionnaireWithQuestions
import co.health.test.corona.repository.manager.questionnaire.QuestionnaireManager
import co.health.test.corona.screen.result.ResultActivity
import co.health.test.corona.screen.utils.BaseActivity
import co.health.test.corona.screen.utils.showSnack
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.test_row.view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class TestActivity : BaseActivity(), View.OnClickListener {

    var questionnaireId: Long = -1L

    val testViewModel: TestViewModel by viewModel()
    var index = 0

    private var questions: MutableList<Question> = ArrayList()
    val questionManager: QuestionnaireManager by inject()
    val questionnaireManager: QuestionnaireManager by inject()

    private val questionIds: MutableList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        questionnaireId = intent.getLongExtra("questionnaireId", -1L)

        btn_apply.setOnClickListener(this)

        getQuestions()

    }

    private fun getQuestions() {

        questionnaireManager.getQuestionsByQuestionnaireId(questionnaireId)
            .subscribeWith(object : DisposableObserver<QuestionnaireWithQuestions>() {
                override fun onComplete() {


                }

                override fun onNext(t: QuestionnaireWithQuestions) {


                    val a = t.questions

                    toolbar_title.text = t.questionnaire.title

                    questions.clear()
                    questions.addAll(a)

                    diss()

                }

                override fun onError(e: Throwable) {

                }
            })
    }

    private fun diss() {
        for ((i, q) in questions.withIndex()) {
            val vq = LayoutInflater.from(this).inflate(R.layout.test_row, ll, false)
            val vqf = display(q, i, vq)
            if (i % 2 == 0)
                vqf.setBackgroundColor(ContextCompat.getColor(this, R.color.gray_light))
            else
                vqf.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            ll.addView(vqf, i + 1)
        }
    }

    private fun display(question: Question, index: Int, view: View): View {
        view.tv_question.text = "${index + 1}. ${question.question.trim()}"
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
                radioGroup.id = index * 100
                questionIds.add(index * 100)

                for (i in question.answer.selections!!.indices) {

                    val checkBox = RadioButton(this)
//                    checkBox.setOnCheckedChangeListener(this)
                    checkBox.layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    checkBox.setPadding(20)

                    checkBox.id = index * 100 + 1 + i
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

            var res = 0
            var sum = 0

            for ((i, q) in questions.withIndex()) {
                val rg = findViewById<RadioGroup>(i * 100)
                val check = rg.checkedRadioButtonId
                if (check == -1) {
                    showSnack("لطفا به همه سوالات پاسخ دهید")
                    return
                }

                res += (check % 100) - 1

                sum += rg.childCount - 1
            }


            val next = Intent(this, ResultActivity::class.java)
            next.putExtra("title", toolbar_title.text.toString())
            next.putExtra("questionnaireId", questionnaireId)
            next.putExtra("res", res)
            next.putExtra("sum", sum)
            startActivity(next)
            index++
            if (index == questions.size)
                index = 0
//            display(questions[index], index)
        }
    }
}
