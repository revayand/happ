package co.health.test.corona.screen.resource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.health.test.corona.R
import kotlinx.android.synthetic.main.activity_resource.*

class ResourceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        if (intent.getBooleanExtra("amozesh", false)) {
            pdfview.fromAsset("amozesh.pdf")
                .defaultPage(0)
                .enableSwipe(true)
                .load()
        } else {
            pdfview.fromAsset("tests.pdf")
                .defaultPage(0)
                .enableSwipe(true)
                .load()
        }
    }
}