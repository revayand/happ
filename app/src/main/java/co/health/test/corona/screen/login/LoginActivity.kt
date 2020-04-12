package co.health.test.corona.screen.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import co.health.test.corona.R
import co.health.test.corona.screen.main.MainActivity
import co.health.test.corona.screen.utils.startActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_apply.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v == btn_apply) {
            startActivity(MainActivity::class.java)
        }

    }
}
