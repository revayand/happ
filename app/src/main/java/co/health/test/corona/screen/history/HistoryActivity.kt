package co.health.test.corona.screen.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.health.test.corona.R
import co.health.test.corona.screen.main.MainActivity
import co.health.test.corona.screen.utils.startActivity
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        btn_apply.setOnClickListener {
            startActivity(MainActivity::class.java)
        }
    }
}
