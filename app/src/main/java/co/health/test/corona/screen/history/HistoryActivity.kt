package co.health.test.corona.screen.history

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.health.test.corona.R
import co.health.test.corona.screen.main.MainActivity
import kotlinx.android.synthetic.main.activity_history.*


class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        btn_apply.setOnClickListener {
            val gotoScreenVar = Intent(this, MainActivity::class.java)

            gotoScreenVar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)

            startActivity(gotoScreenVar)
        }
    }
}
