package co.health.test.corona.screen.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.health.test.corona.R
import co.health.test.corona.screen.info.InfoActivity
import co.health.test.corona.screen.utils.startActivity
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val a = Timer().schedule(object : TimerTask() {
            override fun run() {
                startActivity(InfoActivity::class.java)
            }
        }, 3000)
    }
}
