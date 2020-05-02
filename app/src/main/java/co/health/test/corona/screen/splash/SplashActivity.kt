package co.health.test.corona.screen.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.health.test.corona.R
import co.health.test.corona.screen.main.MainActivity
import co.health.test.corona.screen.utils.startActivity
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val a = Timer().schedule(object : TimerTask() {
            override fun run() {

                startActivity(MainActivity::class.java)
                finish()
            }
        }, 3000)
    }
}
