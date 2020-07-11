package co.health.test.corona.screen.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.Answerr
import co.health.test.corona.repository.db.entities.Questionnaire
import co.health.test.corona.repository.db.entities.Users
import co.health.test.corona.repository.db.entities.UsersWithAnswers
import co.health.test.corona.repository.manager.questionnaire.QuestionnaireManager
import co.health.test.corona.repository.manager.questionnaire.UsersManager
import co.health.test.corona.screen.result.ResultActivity
import co.health.test.corona.screen.utils.BaseActivity
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.row_divider.*
import kotlinx.android.synthetic.main.row_questionnaire_small_share.view.*
import org.koin.android.ext.android.inject


class ProfileActivity : BaseActivity() {


    private lateinit var answers: List<Answerr>
    private lateinit var user: Users

    val sharedPreferences: SharedPreferences by inject()
    val userManager: UsersManager by inject()
    val questionnaireManager: QuestionnaireManager by inject()

    private var questionnaires: MutableList<Questionnaire> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        fillData()
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    private fun fillData() {

        tv.text = "نتیجه پرسشنامه های شما"
        userManager.getAnswerByUser(intent.getLongExtra("id", -1)).zipWith(
            questionnaireManager.getAllQuestionnaire(),
            BiFunction { a: UsersWithAnswers, b: List<Questionnaire> ->
                Pair(a, b)
            }).subscribe {
            user = it.first.users
            questionnaires = it.second.toMutableList()
            val answers = it.first.answers.filter { ii -> ii.part == null }
            tv_name.text = "${user.detail.fname} ${user.detail.lname}"
            tv_phone.text = user.detail.phone

            img.setImageResource(R.drawable.male)
            if (user.detail.gender=="زن")
                img.setImageResource(R.drawable.female)

            answers.forEach { ans ->
                val q = questionnaires.findLast { qq -> qq.id == ans.questionnaireId }


                val row =
                    LayoutInflater.from(this)
                        .inflate(R.layout.row_questionnaire_small_share, list, false)
                row.tv_desc.text = q?.title
                row.setOnClickListener {
                    Intent(this, ResultActivity::class.java).also { ii ->
                        ii.putExtra("questionnaireId", q?.id)
                        startActivity(ii)
                    }
                }
                row.btn_share.setOnClickListener {

                    val shareBody = "${user.detail.fname} ${user.detail.lname} نتیجه ${q?.title} شما ${ans.desc}"
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

                row.tv_date.text = ans.desc


                when (q?.title) {
                    "تست وسواس" -> {
                        row.iv.setImageResource(R.drawable.vasvas)
                    }
                    "تست اضطراب" -> {
                        row.iv.setImageResource(R.drawable.ezterab)
                    }
                    else -> {
                        row.iv.setImageResource(R.drawable.afsordegi)
                    }
                }

                list.addView(row)

            }

        }
    }
}
