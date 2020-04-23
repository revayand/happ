package co.health.test.corona.screen.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.Questionnaire
import co.health.test.corona.repository.db.entities.QuestionnaireState
import co.health.test.corona.screen.main.questionnaire.QuestionnaireRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_home.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter: QuestionnaireRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = QuestionnaireRecyclerViewAdapter(listOf(Questionnaire("درس اول: آیا واکنش\u200Cهای من به ویروس کرونا طبیعی است؟",QuestionnaireState.FILLED),
            Questionnaire("درس دوم: مهارت\u200Cهای مدیریت استرس کرونا (بخش اول)",QuestionnaireState.FILLED),
            Questionnaire("درس سوم: چرا در شرایط همه\u200Cگیری اپیدمی کرونا، نباید نگران باشیم؟ (فکر فقط فکر است)",QuestionnaireState.FILLED),
            Questionnaire("درس چهارم: مهارت\u200Cهای مدیریت استرس کرونا (بخش دوم)",QuestionnaireState.FILLED),
            Questionnaire("درس پنجم: نامه\u200Cای برای حامیان - شیوه مدیریت استرس در دیگران (در حمایت از بیماران و خانواده\u200Cهای آنها)",QuestionnaireState.FILLED)
        ),null)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(context)

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
