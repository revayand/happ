package co.health.test.corona.screen.main.other

import android.R.attr.path
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Paint
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RawRes
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import co.health.test.corona.R
import co.health.test.corona.screen.resource.ResourceActivity
import co.health.test.corona.screen.utils.showSnack
import co.health.test.corona.utils.PermissionHandler
import kotlinx.android.synthetic.main.fragment_other.*
import java.io.*
import java.util.jar.Manifest


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OtherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OtherFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        descc.text = resources.getString(R.string.desc)
        descc.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
        descc.setLineSpacing(15)
        descc.setPadding(5)
        descc.alignment = Paint.Align.RIGHT
        descc.typeFace = Typeface.createFromAsset(activity?.assets, "fonts/IRANSansMobile.ttf")
        afsordegi.setOnClickListener {

            startActivity(Intent(activity, ResourceActivity::class.java))
        }

        ezterab.setOnClickListener {

            startActivity(Intent(activity, ResourceActivity::class.java).apply {
                putExtra(
                    "amozesh",
                    true
                )
            })
        }


    }


    @Throws(IOException::class)
    private fun copyFile(`in`: InputStream, out: OutputStream) {


        val buffer = ByteArray(1024)
        var read: Int
        while (`in`.read(buffer).also { read = it } != -1) {
            out.write(buffer, 0, read)
        }

    }

    fun openPDF(@RawRes raw: Int, fileName: String) {
        try {

            var dirpicture: String =
                activity?.externalCacheDir?.path.toString() + "/Bulenttes/"

            val dir = File(dirpicture)

            if (dir.mkdirs() || dir.isDirectory) {
                val pdfFile = File(dirpicture, fileName)

                copyFile(
                    resources.openRawResource(raw),
                    FileOutputStream(pdfFile)
                )
                val path = Uri.fromFile(pdfFile)
                val intent = Intent(Intent.ACTION_VIEW)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.setDataAndType(path, "application/pdf")
                startActivity(intent)
            }
        } catch (ex: java.lang.Exception) {
            ex.toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OtherFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OtherFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
