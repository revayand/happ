package co.health.test.corona.screen.main

import android.os.Bundle
import android.view.MenuItem
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.Questionnaire
import co.health.test.corona.screen.main.home.HomeFragment
import co.health.test.corona.screen.main.questionnaire.QuestionnaireFragment
import co.health.test.corona.screen.main.settings.SettingsFragment
import co.health.test.corona.screen.utils.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :QuestionnaireFragment.OnListFragmentInteractionListener, BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, HomeFragment.newInstance("", "")).commit()

            }
            R.id.navigation_question -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, QuestionnaireFragment.newInstance())
                    .commit()
            }
            R.id.navigation_settings -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, SettingsFragment.newInstance("", ""))
                    .commit()
            }
        }
        return true
    }

    override fun onListFragmentInteraction(item: Questionnaire?) {
        TODO("Not yet implemented")
    }
}
