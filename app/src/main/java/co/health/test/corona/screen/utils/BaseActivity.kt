package co.health.test.corona.screen.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity()


fun <T> AppCompatActivity.startActivity(nextActivity: Class<T>) {
    startActivity(Intent(this, nextActivity))
}