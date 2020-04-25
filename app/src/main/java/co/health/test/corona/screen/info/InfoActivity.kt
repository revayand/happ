package co.health.test.corona.screen.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.health.test.corona.R
import co.health.test.corona.screen.history.HistoryActivity
import co.health.test.corona.screen.utils.startActivity
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        btn_apply.setOnClickListener {
            startActivity(HistoryActivity::class.java)
        }
    }
}
