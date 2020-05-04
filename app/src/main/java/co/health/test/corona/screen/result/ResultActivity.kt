package co.health.test.corona.screen.result

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.health.test.corona.R
import co.health.test.corona.screen.main.MainActivity
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_test.toolbar_title

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        toolbar_title.text = intent.getStringExtra("title")
        tv_res.text = intent.getStringExtra("res")
        btn_apply.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        val gotoScreenVar = Intent(this, MainActivity::class.java)

        gotoScreenVar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)

        startActivity(gotoScreenVar)

    }
}
