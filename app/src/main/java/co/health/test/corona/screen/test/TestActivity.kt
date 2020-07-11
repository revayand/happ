package co.health.test.corona.screen.test


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.UserManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.*
import co.health.test.corona.repository.manager.questionnaire.AnswerManager
import co.health.test.corona.repository.manager.questionnaire.QuestionnaireManager
import co.health.test.corona.repository.manager.questionnaire.UsersManager
import co.health.test.corona.screen.result.ResultActivity
import co.health.test.corona.screen.utils.BaseActivity
import co.health.test.corona.screen.utils.Tuple
import co.health.test.corona.screen.utils.showSnack
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.test_row.view.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class TestActivity : BaseActivity(), View.OnClickListener {
    val answerManager: AnswerManager by inject()
    val userManager: UsersManager by inject()
    val sharedPreferences: SharedPreferences by inject()

    private val parts: MutableList<Tuple<String, List<Int>, Int>> = ArrayList()
    private lateinit var questionnaire: Questionnaire
    var questionnaireId: Long = -1L

    val testViewModel: TestViewModel by viewModel()
    var index = 0

    private var questions: MutableList<Question> = ArrayList()
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
                    questionnaire = t.questionnaire
                    questionnaire.questionsPart?.let {
                        val a = it.split("=")
                        a[0].split("-").forEachIndexed { index, s ->
                            val tup =
                                Tuple(s, a[1].split(",")[index].split("-").map { it.toInt() }, 0)
                            parts.add(tup)

                        }
                    }
                    toolbar_title.text = questionnaire.title

                    questions.clear()
                    questions.addAll(a)

                    diss()
                    if (questionnaire.description == null) {
                        tv_desc.visibility = View.GONE
                    } else
                        tv_desc.text = questionnaire.description!!


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

                    checkBox.id = index * 100 + questionnaire.margin + i
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
            var khodkoshi = ""
            for ((i, q) in questions.withIndex()) {
                val rg = findViewById<RadioGroup>(i * 100)
                val check = rg.checkedRadioButtonId
                if (check == -1) {
                    showSnack("لطفا به همه سوالات پاسخ دهید")
                    return
                }

                parts.findLast {
                    it.second.contains(i + 1)
                }?.let {
                    val c = it.third
                    it.third = c + (check % 100) - questionnaire.margin
                }

                res += (check % 100) - questionnaire.margin

                sum += rg.childCount - questionnaire.margin
            }


            var desc = "این تست انجام شده است"
            when (questionnaire.title) {
                "تست وسواس" -> {
                    if (res in 0..10) {
                        desc = "اختلال وسواس شما بسیار ضعیف است"

                    }
                    if (res in 10..15) {
                        desc = "شما علايم خفیف وسواس را دارید"

                    }
                    if (res in 16..25) {
                        desc = "اختلال وسواس شما متوسط است"

                    }
                    if (res > 25) {
                        desc = "اختلال وسواس شما شدید است"

                    }
                }
                "تست اضطراب" -> {
                    if (res in 0..7) {
                        desc = "شما هیچ اضطرابی ندارید"

                    }
                    if (res in 8..15) {
                        desc = "میزان اضطراب شما خفیف است"

                    }
                    if (res in 16..25) {
                        desc = "میزان اضطراب شما متوسط است"

                    }
                    if (res in 26..63) {
                        desc = "میزان اضطراب شما شدید است"

                    }
                }
                "تست افسردگی" -> {
                    if (res >=55)
                        khodkoshi = "khodkoshi,"
                    if (res in 0..10) {
                        desc = "میزان افسردگی شما طبیعی است"

                    }
                    if (res in 11..16) {
                        desc = "شما کمی افسرده هستید"

                    }
                    if (res in 17..20) {
                        desc = "شما نیازمند مشورت با روانشناس و روانپزشگ هستید"

                    }
                    if (res in 21..30) {
                        desc = "شما به نسبت افسرده هستید"

                    }
                    if (res in 31..40) {
                        desc = "شما افسردگی شدید دارید"

                    }

                    if (res >= 41) {
                        desc = "شما بیش از حد افسردگی دارید"

                    }
                }
            }

            if (khodkoshi.isNotEmpty()) {
                userManager.getUsers(sharedPreferences.getLong("id", -1)).subscribe { user ->
                    user.detail.properties += khodkoshi
                    userManager.addUsers(user).subscribe { _ -> }
                }
            }

            answerManager.addAnswer(
                Answerr(
                    res, desc, null,System.currentTimeMillis(),
                    sharedPreferences.getLong("id", -1),
                    questionnaireId,
                    0
                )
            ).subscribe { _ -> }

            parts.forEach {

                answerManager.addAnswer(
                    Answerr(
                        it.third, null, it.first,System.currentTimeMillis(),
                        sharedPreferences.getLong("id", -1),
                        questionnaireId,
                        0
                    )
                ).subscribe { _ -> }

            }
            if (questionnaire.title == "تست مکمل افسردگی") {
                finish()
                return
            }

            val next = Intent(this, ResultActivity::class.java)
            next.putExtra("title", toolbar_title.text.toString())
            next.putExtra("questionnaireId", questionnaireId)
            next.putExtra("res", res)
            next.putExtra("sum", sum)
            next.putExtra("average", questionnaire.average)
            next.putExtra("sDeviation", questionnaire.sDeviation)
            startActivity(next)
        }
    }
}
