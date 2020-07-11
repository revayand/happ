package co.health.test.corona.screen.daily

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.animation.doOnEnd
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
        btn_apply.setOnClickListener(this)
    }

    private var selectedView: View? = null
    var behavior: Behavior? = null

    override fun onClick(v: View?) {
        when (v) {
            btn_apply -> {


                behavior?.let {
                    behaviorManager.addBehavior(it)
                        .subscribe { t ->
                            users.lastBehaviorTime = System.currentTimeMillis()
                            userManager.addUsers(users).subscribe { _ ->
                            }
                        }
                }
                dismiss()

            }
            very_bad -> {
                selectedView?.let { it.setBackgroundColor(Color.WHITE) }
                selectedView = v
                val colorFrom: Int = Color.WHITE
                val colorTo: Int = context.resources.getColor(R.color.colorPrimary)
                val duration = 100
                ObjectAnimator.ofObject(
                    v,
                    "backgroundColor",
                    ArgbEvaluator(),
                    colorFrom,
                    colorTo
                )
                    .setDuration(duration.toLong())
                    .start()
                behavior = Behavior(1, System.currentTimeMillis(), users.id, 0)
            }
            bad -> {
                selectedView?.let { it.setBackgroundColor(Color.WHITE) }
                selectedView = v
                val colorFrom: Int = Color.WHITE
                val colorTo: Int = context.resources.getColor(R.color.colorPrimary)
                val duration = 100
                ObjectAnimator.ofObject(
                    v,
                    "backgroundColor",
                    ArgbEvaluator(),
                    colorFrom,
                    colorTo
                )
                    .setDuration(duration.toLong())
                    .start()

                behavior = Behavior(2, System.currentTimeMillis(), users.id, 0)
            }
            normal -> {
                selectedView?.let { it.setBackgroundColor(Color.WHITE) }
                selectedView = v
                val colorFrom: Int = Color.WHITE
                val colorTo: Int = context.resources.getColor(R.color.colorPrimary)
                val duration = 100
                ObjectAnimator.ofObject(
              v,      "backgroundColor",
                    ArgbEvaluator(),
                    colorFrom,
                    colorTo
                )
                    .setDuration(duration.toLong())
                    .start()

                behavior = Behavior(3, System.currentTimeMillis(), users.id, 0)
            }
            good -> {
                selectedView?.let { it.setBackgroundColor(Color.WHITE) }
                selectedView = v
                val colorFrom: Int = Color.WHITE
                val colorTo: Int = context.resources.getColor(R.color.colorPrimary)
                val duration = 100
                ObjectAnimator.ofObject(
                v,    "backgroundColor",
                    ArgbEvaluator(),
                    colorFrom,
                    colorTo
                )
                    .setDuration(duration.toLong())
                    .start()

                behavior = Behavior(4, System.currentTimeMillis(), users.id, 0)
            }
            very_good -> {
                selectedView?.let { it.setBackgroundColor(Color.WHITE) }
                selectedView = v

                val colorFrom: Int = Color.WHITE
                val colorTo: Int = context.resources.getColor(R.color.colorPrimary)
                val duration = 100
                ObjectAnimator.ofObject(
                v,    "backgroundColor",
                    ArgbEvaluator(),
                    colorFrom,
                    colorTo
                )
                    .setDuration(duration.toLong())
                    .start()

                behavior = Behavior(5, System.currentTimeMillis(), users.id, 0)
            }
        }
    }
}