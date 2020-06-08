package co.health.test.corona.screen.course

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import co.health.test.corona.R
import co.health.test.corona.screen.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_course.*


class CourseActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        tv_desc.movementMethod = ScrollingMovementMethod()
        val title = intent.getStringExtra("title")
        val desc = intent.getStringExtra("desc")
        tv_desc.text = desc
        tv_title.text = title
    }
}
