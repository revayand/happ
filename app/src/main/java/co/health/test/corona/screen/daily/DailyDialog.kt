package co.health.test.corona.screen.daily

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.UserManager
import android.view.View
import android.view.WindowManager
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.Behavior
import co.health.test.corona.repository.db.entities.Users
import co.health.test.corona.repository.manager.questionnaire.BehaviorManager
import co.health.test.corona.repository.manager.questionnaire.UsersManager
import kotlinx.android.synthetic.main.dialog_daily.*

class DailyDialog(
    context: Context,
    private val behaviorManager: BehaviorManager,
    private val userManager: UsersManager,
    private val users: Users
) : Dialog(context), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_daily)
        window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        setCancelable(false)
//        window?.setBackgroundDrawableResource(android.R.color.transparent)
//        setCancelable(cancelable)
        bad.setOnClickListener(this)
        very_bad.setOnClickListener(this)
        good.setOnClickListener(this)
        very_good.setOnClickListener(this)
        normal.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v) {
            very_bad -> {
                behaviorManager.addBehavior(Behavior(1, System.currentTimeMillis(), users.id, 0))
                    .subscribe { t ->
                        users.lastBehaviorTime = System.currentTimeMillis()
                        userManager.addUsers(users).subscribe { _ ->
                            dismiss()
                        }
                    }
            }
            bad -> {
                behaviorManager.addBehavior(Behavior(2, System.currentTimeMillis(), users.id, 0))
                    .subscribe { t ->
                        users.lastBehaviorTime = System.currentTimeMillis()
                        userManager.addUsers(users).subscribe { _ ->
                            dismiss()
                        }

                    }
            }
            normal -> {
                behaviorManager.addBehavior(Behavior(3, System.currentTimeMillis(), users.id, 0))
                    .subscribe { t ->
                        users.lastBehaviorTime = System.currentTimeMillis()
                        userManager.addUsers(users).subscribe { _ ->
                            dismiss()
                        }
                    }
            }
            good -> {
                behaviorManager.addBehavior(Behavior(4, System.currentTimeMillis(), users.id, 0))
                    .subscribe { t ->
                        users.lastBehaviorTime = System.currentTimeMillis()
                        userManager.addUsers(users).subscribe { _ ->
                            dismiss()
                        }

                    }
            }
            very_good -> {
                behaviorManager.addBehavior(Behavior(5, System.currentTimeMillis(), users.id, 0))
                    .subscribe { t ->
                        users.lastBehaviorTime = System.currentTimeMillis()
                        userManager.addUsers(users).subscribe { _ ->
                            dismiss()
                        }
                    }
            }
        }
    }
}