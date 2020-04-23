package co.health.test.corona.screen.utils

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseActivity : AppCompatActivity()


fun <T> Context.startActivity(nextActivity: Class<T>) {
    startActivity(Intent(this, nextActivity))
}

fun <T> Fragment.startActivity(nextActivity: Class<T>) {
    startActivity(Intent(context, nextActivity))
}
