package co.health.test.corona.screen.main.home.learn

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.health.test.corona.R
import co.health.test.corona.screen.main.home.learn.dummy.DummyContent
import co.health.test.corona.screen.main.home.learn.dummy.DummyContent.DummyItem


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [LearnFragment.OnListFragmentInteractionListener] interface.
 */
class LearnFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Set the adapter
        if (view is RecyclerView) {

            view.layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            val a = MyLearnRecyclerViewAdapter(DummyContent.ITEMS, listener)
            view.adapter = a

        }
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

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            LearnFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
