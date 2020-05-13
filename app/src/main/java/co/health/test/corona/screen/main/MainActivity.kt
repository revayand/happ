package co.health.test.corona.screen.main

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.QuestionnaireWithQuestions
import co.health.test.corona.repository.db.entities.Users
import co.health.test.corona.repository.manager.questionnaire.UsersManager
import co.health.test.corona.screen.main.home.learn.LearnFragment
import co.health.test.corona.screen.main.other.OtherFragment
import co.health.test.corona.screen.main.questionnaire.QuestionnaireFragment
import co.health.test.corona.screen.main.settings.UserItemFragment
import co.health.test.corona.screen.profile.ProfileActivity
import co.health.test.corona.screen.test.TestActivity
import co.health.test.corona.screen.utils.BaseActivity
import co.health.test.corona.screen.utils.startActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : QuestionnaireFragment.OnListFragmentInteractionListener, BaseActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    UserItemFragment.OnUserListFragmentInteractionListener {

    val sharedPreferences: SharedPreferences by inject()
    val usersManager: UsersManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav.setOnNavigationItemSelectedListener(this)
        nav.selectedItemId = R.id.navigation_settings

    }

    override fun onResume() {
        super.onResume()
        sharedPreferences.getLong("id", -1).let {
            if (it == -1L)
                return
            usersManager.getUsers(it).subscribeWith(object : DisposableSingleObserver<Users>() {

                override fun onError(e: Throwable) {

                }

                override fun onSuccess(t: Users) {

                    renameTitle(t.detail.fname + " " + t.detail.lname)
                }
            })
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
            R.id.navigation_other -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, OtherFragment.newInstance("", ""))
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

    override fun onUserListFragmentInteraction(item: Users) {
        startActivity(ProfileActivity::class.java)
    }

    override fun onLongUserListFragmentInteraction(item: Users) {
        val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle("حذف کاربر")
        alertDialog.setMessage("آیا این کاربر را حذف میکنید؟")

        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE,
            "بلی"
        ) { _, _ ->
            usersManager.delete(item).subscribeWith(object : DisposableSingleObserver<Int>() {
                override fun onSuccess(t: Int) {
                    alertDialog.dismiss()
                    ((supportFragmentManager.findFragmentById(R.id.frame)) as? UserItemFragment)?.getUsers()

                }

                override fun onError(e: Throwable) {

                }
            })
        }

        alertDialog.setButton(
            AlertDialog.BUTTON_NEGATIVE,
            "خیر"
        ) { _, _ ->
            alertDialog.dismiss()
        }

        alertDialog.show()
        val face = Typeface.createFromAsset(assets, "fonts/IRANSansMobile.ttf")
        alertDialog.findViewById<TextView>(android.R.id.message)?.typeface = face

    }

    override fun onChangeUser(item: Users) {
        sharedPreferences.edit(true) {
            this.putLong("id", item.id)
            renameTitle(item.detail.fname + " " + item.detail.lname)
        }
    }
}
