package co.health.test.corona.screen.utils

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


open class BaseActivity : AppCompatActivity()


fun <T> Context.startActivity(nextActivity: Class<T>) {
    startActivity(Intent(this, nextActivity))
}

fun <T> Fragment.startActivity(nextActivity: Class<T>) {
    startActivity(Intent(context, nextActivity))
}


fun AppCompatActivity.showSnack(str: String) {
    val parentLayout: View? = findViewById(android.R.id.content)

    parentLayout?.let {

        val snackbar = Snackbar.make(it, str, Snackbar.LENGTH_LONG)
        ViewCompat.setLayoutDirection(snackbar.view, ViewCompat.LAYOUT_DIRECTION_RTL)

        snackbar.show()
    }

}

fun Fragment.showSnack(str: String) {
    val parentLayout: View? = activity?.findViewById(android.R.id.content)
    parentLayout?.let {

        val snackbar = Snackbar.make(it, str, Snackbar.LENGTH_LONG)
        ViewCompat.setLayoutDirection(snackbar.view, ViewCompat.LAYOUT_DIRECTION_RTL)

        snackbar.show()
    }
}

