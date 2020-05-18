package co.health.test.corona.screen.daily

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import co.health.test.corona.R
import kotlinx.android.synthetic.main.dialog_daily.*

class DailyDialog(context: Context) : Dialog(context), View.OnClickListener {

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
            bad -> {
                dismiss()
            }
            very_bad -> {
                dismiss()
            }
            good -> {
                dismiss()
            }
            very_bad -> {
                dismiss()
            }
            normal -> {
                dismiss()
            }
        }
    }
}