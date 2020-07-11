package co.health.test.corona.screen.result

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.*
import co.health.test.corona.repository.manager.questionnaire.AnswerManager
import co.health.test.corona.repository.manager.questionnaire.QuestionManager
import co.health.test.corona.repository.manager.questionnaire.QuestionnaireManager
import co.health.test.corona.repository.manager.questionnaire.UsersManager
import co.health.test.corona.screen.main.MainActivity
import co.health.test.corona.screen.utils.Constants
import co.health.test.corona.screen.utils.toFarsi
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_test.toolbar_title
import org.koin.android.ext.android.inject


class ResultActivity : AppCompatActivity() {
    private lateinit var answers: List<Answerr>
    private lateinit var questions: List<Question>
    private lateinit var questionnaire: Questionnaire
    private lateinit var user: Users
    val answerManager: AnswerManager by inject()
    val userManager: UsersManager by inject()
    val questionnaireManager: QuestionnaireManager by inject()
    val questionManager: QuestionManager by inject()
    val sharedPreferences: SharedPreferences by inject()
    var questionnaireId: Long = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        questionnaireId = intent.getLongExtra("questionnaireId", -1)
        getData()

        btn_apply.setOnClickListener {

            onBackPressed()
        }

        btn_share.setOnClickListener {
            val shareBody = "${user.detail.fname} ${user.detail.lname} نتیجه ${toolbar_title.text} شما ${tv_res.text}"
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "نتیجه تست")
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(
                Intent.createChooser(
                    sharingIntent,
                    "اشتراک گزاری"
                )
            )

        }


    }

    private fun getData() {
        questionnaireManager.getQuestionsByQuestionnaireId(questionnaireId).zipWith(
            userManager.getAnswerByUser(sharedPreferences.getLong("id", -1))
            , BiFunction { a: QuestionnaireWithQuestions, b: UsersWithAnswers -> Pair(a, b) })
            .subscribe { t ->
                user = t.second.users
                questionnaire = t.first.questionnaire
                questions = t.first.questions
                answers = t.second.answers.filter { it.questionnaireId == questionnaire.id }
                toolbar_title.text =questionnaire.title

                showData()
            }
    }

    private fun showData() {


        val result = answers.first { it.part == null }.point ?: 0
        val sum = questions.map { (it.answer.selections?.size ?: 0) -1 + questionnaire.margin }.sum()
        val min = questions.map {  questionnaire.margin }.sum()

        tv_point.text = result.toInt().toString().toFarsi()

        val params: LinearLayout.LayoutParams = filler.layoutParams as LinearLayout.LayoutParams
        params.weight = (100.0 * result / (sum * 1.0)).toFloat()
        filler.layoutParams = params
        val average = questionnaire.average
        val deviation = questionnaire.sDeviation
        val target = (result - average) / deviation
        tv_max.text = sum.toString().toFarsi()
        tv_min.text = min.toString().toFarsi()


        when {
            target < (average / 3) -> {
                img_chart.setImageResource(R.drawable.mm_2a)
            }
            target < (average / 2) -> {
                img_chart.setImageResource(R.drawable.mm_a)
            }
            target < (average) -> {
                img_chart.setImageResource(R.drawable.mmchap)
            }
            target < (average * 2) -> {
                img_chart.setImageResource(R.drawable.mmrast)

            }

            target < (average * 3) -> {
                img_chart.setImageResource(R.drawable.mmpa)

            }
            else -> {
                img_chart.setImageResource(R.drawable.mmp2a)
            }
        }


        val res = result
        var desc = ""

        when (questionnaire.title) {
            "تست وسواس" -> {

                sugg.text = Constants.vasvasPish
                if (result.toDouble()/sum.toDouble()>0.75){
                    danger.visibility=View.VISIBLE
                }
                if (res in 0..10) {
                    tv_res.text = "اختلال وسواس شما بسیار ضعیف است"
                    desc = tv_res.text.toString()
                }
                if (res in 11..15) {
                    tv_res.text = "شما علایم خفیف وسواس را دارید"
                    desc = tv_res.text.toString()
                }
                if (res in 16..25) {
                    tv_res.text = "اختلال وسواس شما متوسط است"
                    desc = tv_res.text.toString()
                }
                if (res > 25) {
                    tv_res.text = "اختلال وسواس شما شدید است"
                    desc = tv_res.text.toString()
                }
            }
            "تست اضطراب" -> {

                sugg.text = Constants.ezterabPish
                if (result.toDouble()/sum.toDouble()>0.75){
                    danger.visibility=View.VISIBLE
                }
                if (res in 0..7) {
                    tv_res.text = "شما هیچ اضطرابی ندارید"
                    desc = tv_res.text.toString()
                }
                if (res in 8..15) {
                    tv_res.text = "میزان اضطراب شما خفیف است"
                    desc = tv_res.text.toString()
                }
                if (res in 16..25) {
                    tv_res.text = "میزان اضطراب شما متوسط است"
                    desc = tv_res.text.toString()
                }
                if (res in 26..63) {
                    tv_res.text = "میزان اضطراب شما شدید است"
                    desc = tv_res.text.toString()
                }
            }
            "تست افسردگی" -> {
                sugg.text = Constants.afsordegiPish
                if (result.toDouble()/sum.toDouble()>0.75){
                    danger.visibility=View.VISIBLE
                }
                if (res in 0..10) {
                    tv_res.text = "میزان افسردگی شما طبیعی است"
                    desc = tv_res.text.toString()
                }
                if (res in 11..16) {
                    tv_res.text = "شما کمی افسرده هستید"
                    desc = tv_res.text.toString()
                }
                if (res in 17..20) {
                    tv_res.text = "شما نیازمند مشورت با روانشناس و روانپزشگ هستید"
                    desc = tv_res.text.toString()
                }
                if (res in 21..30) {
                    tv_res.text = "شما به نسبت افسرده هستید"
                    desc = tv_res.text.toString()
                }
                if (res in 31..40) {
                    tv_res.text = "شما افسردگی شدید دارید"
                    desc = tv_res.text.toString()
                }

                if (res > 41) {
                    tv_res.text = "شما بیش از حد افسردگی دارید"
                    desc = tv_res.text.toString()
                }
            }
            "تست حل مسئله اجتماعی" -> {

                chart.visibility = View.GONE
                line_chart.visibility = View.GONE
                if (res in 16..32) {
                    tv_res.text = "شما در حل مسئله اجتماعی درانتظاردر حل مسائل اجتماعی ضعیف هستید"

                }
                if (res in 33..48) {
                    tv_res.text = "شما در حل مسئله اجتماعی درانتظاردر حل مسائل اجتماعی قوی نیستید"

                }
                if (res in 49..64) {
                    tv_res.text = "شما در حل مسائل اجتماعی متوسط عمل می کنید"

                }
                if (res > 64) {
                    tv_res.text = "شما در حل مسائل اجتماعی قوی هستید"

                }

                val max = answers.filter { it.part != null }.maxBy { it.point ?: 0 }
                if (max?.part == "سبک منطقی حل مسئله") {
                    sugg.text =  Constants.sabkManteghiHalMasale
                } else if (max?.part == "سبک اجتنابی حل مسئله") {

                    sugg.text = Constants.sabkEjtenabiHalMasale
                } else if (max?.part == "سبک تکانشی حل مسئله") {
                    sugg.text = Constants.sabkTakaneshiHalMasale
                }

            }
            "تست نظم جویی شناخت هیجان" -> {
                chart.visibility = View.GONE
                sugg.visibility = View.GONE
                line_chart.visibility = View.GONE
                val malamatKhish = answers.findLast { it.part == "ملامت خویش" }
                val malamatDigran = answers.findLast { it.part == "ملامت دیگران" }
                val fajeesazi = answers.findLast { it.part == "فاجعه سازی" }
                val noshkhargari = answers.findLast { it.part == "تمرکز روی افکار" }
                val paziresh = answers.findLast { it.part == "پذیرش" }
                val tamarkozMojadadBarBarnameRizi =
                    answers.findLast { it.part == "تمرکز مجدد بر برنامه ریزی" }
                val tamarkozMojadadMosbat = answers.findLast { it.part == "تمرکز مجدد مثبت" }
                val arzyabiMojadadMosbat = answers.findLast { it.part == "ارزیابی مجدد مثبت" }
                val didgahGiri = answers.findLast { it.part == "دیدگاه گیری" }
                val nakaramad: Int =
                    (malamatDigran?.point ?: 0) + (malamatKhish?.point ?: 0) + (fajeesazi?.point
                        ?: 0) + (noshkhargari?.point ?: 0)
                val karamad: Int = (paziresh?.point ?: 0) + (tamarkozMojadadBarBarnameRizi?.point
                    ?: 0) + (tamarkozMojadadMosbat?.point ?: 0) + (arzyabiMojadadMosbat?.point
                    ?: 0) + (didgahGiri?.point ?: 0)

                if (nakaramad in 24..40) {
                    sugg.visibility = View.VISIBLE
                    sugg.text = Constants.nakaramad

                } else if (karamad in 30..50) {

                    sugg.visibility = View.VISIBLE
                    sugg.text = Constants.karamad
                }
                val max = answers.filter { it.part != null }.maxBy { it.point ?: 0 }
                if (max == malamatDigran) {
                    tv_res.text = Constants.malamatDigran
                } else if (max == malamatKhish) {
                    tv_res.text =Constants.malamatKhish

                } else if (max == fajeesazi) {
                    tv_res.text = Constants.fajeesazi

                } else if (max == noshkhargari) {
                    tv_res.text = Constants.noshkhargari

                } else if (max == paziresh) {
                    tv_res.text = Constants.paziresh

                } else if (max == tamarkozMojadadBarBarnameRizi) {
                    tv_res.text =
                        Constants.tamarkozMojadadBarBarnameRizi

                } else if (max == tamarkozMojadadMosbat) {
                    tv_res.text = Constants.tamarkozMojadadMosbat

                } else if (max == arzyabiMojadadMosbat) {
                    tv_res.text = Constants.arzyabiMojadadMosbat

                } else if (max == didgahGiri) {
                    tv_res.text = Constants.didgahGiri

                }

            }
            "تست تعارضات زناشویی" -> {
                chart.visibility = View.GONE
                sugg.visibility = View.GONE
                line_chart.visibility = View.VISIBLE
                if (100.0 * result / (sum * 1.0) < 50)
                    tv_res.text = "تعارض شما کم است"
                else tv_res.text = "تعارض شما زیاد است"
            }
        }
    }

    override fun onBackPressed() {
        val gotoScreenVar = Intent(this, MainActivity::class.java)
        gotoScreenVar.putExtra("gotoLearn", true)

        gotoScreenVar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)

        startActivity(gotoScreenVar)

    }
}
