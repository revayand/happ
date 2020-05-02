package co.health.test.corona.screen.main.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import co.health.test.corona.R
import co.health.test.corona.screen.info.InfoActivity
import co.health.test.corona.screen.main.settings.dummy.DummyContent
import co.health.test.corona.screen.main.settings.dummy.DummyContent.DummyItem
import co.health.test.corona.screen.utils.startActivity
import kotlinx.android.synthetic.main.fragment_user_item_list.*
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [UserItemFragment.OnUserListFragmentInteractionListener] interface.
 */
class UserItemFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listenerUser: OnUserListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Set the adapter
        return inflater.inflate(R.layout.fragment_user_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(list) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = MyUserItemRecyclerViewAdapter(DummyContent.ITEMS, listenerUser)
        }
        fab_add.setOnClickListener { startActivity(InfoActivity::class.java) }

        MaterialShowcaseView.Builder(activity).setTarget(fab_add)
            .setContentText("خودتان و افراد خانواده را اضافه کنید").setDismissText("باشه")
            .singleUse("fab").show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnUserListFragmentInteractionListener) {
            listenerUser = context
        }
//        else {
//            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerUser = null
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
    interface OnUserListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onUserListFragmentInteraction(item: DummyItem?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            UserItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
