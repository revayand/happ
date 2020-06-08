package co.health.test.corona.screen.main.home.learn

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.Questionnaire
import co.health.test.corona.repository.db.entities.Users
import co.health.test.corona.repository.db.entities.UsersWithAnswers
import co.health.test.corona.repository.manager.questionnaire.QuestionnaireManager
import co.health.test.corona.repository.manager.questionnaire.UsersManager
import co.health.test.corona.screen.course.CourseActivity
import co.health.test.corona.screen.main.home.learn.dummy.DummyContent.DummyItem
import co.health.test.corona.screen.utils.Constants
import co.health.test.corona.screen.utils.LoadingLayout
import com.bumptech.glide.Glide
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.fragment_learn.view.*
import kotlinx.android.synthetic.main.fragment_questionnaire_list.*
import kotlinx.android.synthetic.main.row_divider.view.tv

import org.koin.android.ext.android.inject


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [LearnFragment.OnListFragmentInteractionListener] interface.
 */
class LearnFragment : Fragment() {

    private var indexMokamle: Int = -1
    private lateinit var user: Users
    private lateinit var olaviatItems: MutableList<DummyItem>
    private lateinit var mokamelItems: MutableList<DummyItem>

    val sharedPreferences: SharedPreferences by inject()
    val userManager: UsersManager by inject()
    val questionnaireManager: QuestionnaireManager by inject()

    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        olaviatItems =ArrayList()
        mokamelItems =ArrayList()
        syncWithUser()
    }



    private fun showData2() {

        ll.state = LoadingLayout.LoadingLayoutState.STATE_SHOW_DATA

        if (olaviatItems.isNotEmpty()) {
            val header = LayoutInflater.from(activity).inflate(R.layout.row_divider, list, false)
            header.tv.text = "آموزش های اولویت دار"
            list.addView(header)
            olaviatItems.forEach { q ->
                val row =
                    LayoutInflater.from(activity)
                        .inflate(R.layout.fragment_learn, list, false)
                row.tv_desc.text = q.content
                row.tv_date.text = q.date
                Glide.with(this).load(q.srcImage).centerCrop()
                    .error(R.drawable.light)
                    .into(row.iv)
                with(row) {
                    tag = q
                    setOnClickListener{
                        listener?.onListFragmentInteraction(it.tag as DummyItem?)
                    }
                }

                list.addView(row)

            }

        }




        if (mokamelItems.isNotEmpty()) {
            val header = LayoutInflater.from(activity).inflate(R.layout.row_divider, list, false)
            header.tv.text = "آموزش های مکمل"
            list.addView(header)
            mokamelItems.forEach { q ->
                val row =
                    LayoutInflater.from(activity)
                        .inflate(R.layout.fragment_learn, list, false)
                row.tv_desc.text = q.content
                row.tv_date.text = q.date
                Glide.with(this).load(q.srcImage).centerCrop()
                    .error(R.drawable.light)
                    .into(row.iv)
                with(row) {
                    tag = q
                    setOnClickListener{
                        listener?.onListFragmentInteraction(it.tag as DummyItem?)
                    }
                }

                list.addView(row)

            }
        }
    }

    private fun syncWithUser() {

        fetchData()

    }

    private fun fetchData() {
        val userId = sharedPreferences.getLong("id", -1)

        if (userId != -1L) {
            userManager.getAnswerByUser(userId).zipWith(questionnaireManager.getAllQuestionnaire(),
                BiFunction<UsersWithAnswers, List<Questionnaire>, Pair<UsersWithAnswers, List<Questionnaire>>> { t, u ->
                    Pair(
                        t,
                        u
                    )
                }).subscribeWith(object :
                DisposableObserver<Pair<UsersWithAnswers, List<Questionnaire>>>() {
                override fun onComplete() {


                }

                override fun onNext(t: Pair<UsersWithAnswers, List<Questionnaire>>) {
                    user = t.first.users

                    showData()
                }

                override fun onError(e: Throwable) {

                }
            })
        }
    }
    private fun showData() {

        if (user.detail.properties?.contains("ezterab") == true) {
            indexMokamle++
            olaviatItems.add(
                0,
                DummyItem("1000", "راهکارهای کاهش اضطراب", "", "", Constants.ezterab, true)
            )


        }
        if (user.detail.properties?.contains("afsordegi") == true) {
            indexMokamle++
            olaviatItems.add(
                0,
                DummyItem("1001", "راهکارهای کاهش افسردگی", "", "", Constants.afsordegi, true)
            )

        }


        if (user.detail.properties?.contains("vasvas") == true) {
            indexMokamle++

            olaviatItems.add(0, DummyItem("1002", "راهکارهای کاهش وسواس", "", "", Constants.vasvas, true))

        }


        if (user.detail.properties?.contains("ezterab") == false) {
            mokamelItems.add(
                0,
                DummyItem("1000", "راهکارهای کاهش اضطراب", "", "", Constants.ezterab, true)
            )


        }
        if (user.detail.properties?.contains("afsordegi") == false) {

            mokamelItems.add(
                0,
                DummyItem("1001", "راهکارهای کاهش افسردگی", "", "", Constants.afsordegi, true)
            )

        }


        if (user.detail.properties?.contains("vasvas") == false) {

            mokamelItems.add(0, DummyItem("1002", "راهکارهای کاهش وسواس", "", "", Constants.vasvas, true))

        }

        showData2()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_learn_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = object : OnListFragmentInteractionListener {
            override fun onListFragmentInteraction(item: DummyItem?) {
                if (item?.internal == true) {
                    val next = Intent(activity, CourseActivity::class.java)
                    next.putExtra("title", item.content)
                    next.putExtra("desc", item.href)
                    startActivity(next)
                    return


                }
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://isna.ir${item?.href}"))
                startActivity(browserIntent)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem?)
    }

    companion object {


        const val ARG_COLUMN_COUNT = "column-count"


        @JvmStatic
        fun newInstance(columnCount: Int) =
            LearnFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
