package co.health.test.corona.screen.result

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.Answerr
import co.health.test.corona.repository.manager.questionnaire.AnswerManager
import co.health.test.corona.screen.main.MainActivity
import co.health.test.corona.screen.utils.toFarsi
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_test.toolbar_title
import org.koin.android.ext.android.inject


class ResultActivity : AppCompatActivity() {
    val answerManager: AnswerManager by inject()
    val sharedPreferences: SharedPreferences by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        toolbar_title.text = intent.getStringExtra("title")

        val result = intent.getIntExtra("res", -1)
        val sum = intent.getIntExtra("sum", -1)
        tv_point.text = (100.0*result/(sum*1.0)).toInt().toString().toFarsi()

        val params:LinearLayout.LayoutParams = filler.layoutParams as LinearLayout.LayoutParams
        params.weight = (100.0*result/(sum*1.0)).toFloat()
        filler.layoutParams = params

        val res = result
        var desc = ""
        when (intent.getStringExtra("title")) {
            "تست وسواس" -> {
                if (res in 0..10) {
                    tv_res.text = "اختلال وسواس شما بسیار ضعیف است"
                    desc = tv_res.text.toString()
                }
                if (res in 10..15) {
                    tv_res.text = "شما علايم خفیف وسواس را دارید"
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
            else -> {
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

                if (res >= 41) {
                    tv_res.text = "شما بیش از حد افسردگی دارید"
                    desc = tv_res.text.toString()
                }
            }
        }

        btn_apply.setOnClickListener {
            answerManager.addAnswer(
                Answerr(
                    res,desc,
                    sharedPreferences.getLong("id", -1),
                    intent.getLongExtra("questionnaireId", -1),
                    0
                )
            ).subscribeWith(object : DisposableSingleObserver<Long>() {
                override fun onSuccess(t: Long) {

                    onBackPressed()

                }

                override fun onError(e: Throwable) {

                }
            })
        }

        btn_share.setOnClickListener {
            val shareBody = "نتیجه ${toolbar_title.text} شما ${tv_res.text}"
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

    override fun onBackPressed() {
        val gotoScreenVar = Intent(this, MainActivity::class.java)

        gotoScreenVar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)

        startActivity(gotoScreenVar)

    }
}
