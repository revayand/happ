package co.health.test.corona.screen.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import co.health.test.corona.R
import co.health.test.corona.screen.main.home.HomeFragment
import co.health.test.corona.screen.main.question.QuestionFragment
import co.health.test.corona.screen.main.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, HomeFragment.newInstance("", ""))

            }
            R.id.navigation_question -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, QuestionFragment.newInstance("", ""))
            }
            R.id.navigation_settings -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, SettingsFragment.newInstance("", ""))
            }
        }
        return true
    }
}
