package co.health.test.corona.screen.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.edit
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.QuestionnaireWithQuestions
import co.health.test.corona.screen.main.home.learn.LearnFragment
import co.health.test.corona.screen.main.questionnaire.QuestionnaireFragment
import co.health.test.corona.screen.main.settings.UserItemFragment
import co.health.test.corona.screen.main.settings.dummy.DummyContent
import co.health.test.corona.screen.test.TestActivity
import co.health.test.corona.screen.utils.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : QuestionnaireFragment.OnListFragmentInteractionListener, BaseActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    UserItemFragment.OnUserListFragmentInteractionListener {

    val sharedPreferences: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav.setOnNavigationItemSelectedListener(this)
        nav.selectedItemId = R.id.navigation_settings
        sharedPreferences.getString("name", null)?.let {
            renameTitle(it)

        }
    }

    private fun renameTitle(it: String) {
        val current = getString(R.string.app_name)
        toolbar_title.text = "$current ($it)"
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, LearnFragment.newInstance(1)).commit()

            }
            R.id.navigation_question -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, QuestionnaireFragment.newInstance())
                    .commit()
            }
            R.id.navigation_settings -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, UserItemFragment.newInstance(1))
                    .commit()
            }
        }
        return true
    }

    override fun onListFragmentInteraction(item: QuestionnaireWithQuestions?) {
        val intent = Intent(this, TestActivity::class.java).apply {
            putExtra("questionnaireId", item?.questionnaire?.title)
        }
        startActivity(intent)
    }

    override fun onUserListFragmentInteraction(item: DummyContent.DummyItem?) {
        sharedPreferences.edit(true) {
            this.putString("name", item!!.name)
            renameTitle(item!!.name)
        }
    }
}
