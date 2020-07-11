package co.health.test.corona.screen.info

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.Detail
import co.health.test.corona.repository.db.entities.Users
import co.health.test.corona.repository.manager.questionnaire.UsersManager
import co.health.test.corona.screen.history.HistoryActivity
import co.health.test.corona.screen.utils.showSnack
import co.health.test.corona.screen.utils.startActivity
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.android.synthetic.main.activity_info.*
import org.koin.android.ext.android.inject

class InfoActivity : AppCompatActivity() {
    val usersManager: UsersManager by inject()
    val sharedPreferences: SharedPreferences by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        btn_apply.setOnClickListener {
            if (et_fname.text.isEmpty() || et_lname.text.isEmpty() || et_phone.text.isEmpty()) {
                showSnack("لطفا اطلاعات ضروری را تکمیل کنید.")
                return@setOnClickListener
            }
            if (!et_phone.text.startsWith("09") || et_phone.text.length != 11) {
                showSnack("شماره تلفن را به شکل صحیح وارد کنید.")
                return@setOnClickListener
            }
            var gen:String? =null
            try{
               gen = findViewById<RadioButton>(rgg.checkedRadioButtonId).text.toString()

            }
            catch(t:Throwable){}

            usersManager.addUsers(
                Users(
                    0,
                    Detail(
                        et_fname.text.toString(),
                        et_lname.text.toString(), et_phone.text.toString(),
                        gen, null
                    ), "", 0
                )
            ).subscribeWith(object : DisposableSingleObserver<Long>() {
                override fun onSuccess(t: Long) {
                    sharedPreferences.edit(true) {
                        putLong("id", t)
                    }

                }

                override fun onError(e: Throwable) {

                }
            })
            startActivity(HistoryActivity::class.java)
        }
    }
}
