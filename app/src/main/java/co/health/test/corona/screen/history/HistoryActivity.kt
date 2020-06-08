package co.health.test.corona.screen.history

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.Users
import co.health.test.corona.repository.manager.questionnaire.UsersManager
import co.health.test.corona.screen.main.MainActivity
import co.health.test.corona.screen.utils.showSnack
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.android.synthetic.main.activity_history.*
import org.koin.android.ext.android.inject


class HistoryActivity : AppCompatActivity() {

    val usersManager: UsersManager by inject()
    val sharedPreferences: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        btn_apply.setOnClickListener {

            if (rg_anxity.checkedRadioButtonId == -1 || rg_decision.checkedRadioButtonId == -1 || rg_depression.checkedRadioButtonId == -1 || rg_obsession1.checkedRadioButtonId == -1 || rg_obsession2.checkedRadioButtonId == -1) {
                showSnack("لطفا همه تست‌ها را تکمیل کنید.")
                return@setOnClickListener
            }

            val ezterab = getValueFromRB(rg_anxity)
            val afsordegi = getValueFromRB(rg_depression)
            val vasvas = getValueFromRB(rg_obsession2) || getValueFromRB(rg_obsession1)
            usersManager.getUsers(sharedPreferences.getLong("id", -1))
                .subscribeWith(object : DisposableSingleObserver<Users>() {

                    override fun onError(e: Throwable) {

                    }

                    override fun onSuccess(t: Users) {
                        t.detail.properties =
                            "${if (ezterab) "ezterab," else ""}${if (afsordegi) "afsordegi," else ""}${if (vasvas) "vasvas," else ""}"
                        usersManager.upsert(t)
                            .subscribeWith(object : DisposableSingleObserver<Long>() {
                                override fun onSuccess(t: Long) {


                                    val gotoScreenVar =
                                        Intent(this@HistoryActivity, MainActivity::class.java)

                                    gotoScreenVar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                                    gotoScreenVar.putExtra("gotoQuestionnaire",true)

                                    startActivity(gotoScreenVar)

                                }

                                override fun onError(e: Throwable) {
                                    e.printStackTrace()
                                }
                            })
                    }
                })
//            showSnack(rg_anxity.checkedRadioButtonId.toString())}
        }

    }

    fun getValueFromRB(rg: RadioGroup): Boolean {
        when (rg) {
            rg_anxity -> {
                val checked = rg.checkedRadioButtonId
                if (checked == rb_5.id || checked == rb_4.id)
                    return true
            }
            rg_depression -> {
                val checked = rg.checkedRadioButtonId
                if (checked == rb_45.id || checked == rb_44.id)
                    return true
            }
            rg_obsession1 -> {
                val checked = rg.checkedRadioButtonId
                if (checked == rb_21.id || checked == rb_22.id)
                    return true
            }
            rg_obsession2 -> {
                val checked = rg.checkedRadioButtonId
                if (checked == rb_31.id || checked == rb_32.id)
                    return true
            }
        }
        return false
    }
}