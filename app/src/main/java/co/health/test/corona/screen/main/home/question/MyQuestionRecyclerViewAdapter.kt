package co.health.test.corona.screen.main.home.question

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.health.test.corona.R


import co.health.test.corona.screen.main.home.question.QuestionFragment.OnListFragmentInteractionListener
import co.health.test.corona.screen.main.home.question.dummy.DummyContentQuestionnaire.DummyItem
import co.health.test.corona.screen.main.home.question.dummy.DummyContentt

import kotlinx.android.synthetic.main.fragment_question.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyQuestionRecyclerViewAdapter(
    private val mValues: List<DummyContentt.DummyItem>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyQuestionRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyContentt.DummyItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
//            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_question, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mTvDesc.text=item.desc
        holder.mTvTitle.text = item.title
        holder.mTvState.text = item.state
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mTvDesc: TextView = mView.tv_desc
        val mTvState: TextView = mView.tv_state
        val mTvTitle: TextView = mView.tv_title

        override fun toString(): String {
            return super.toString() + " '" + mTvDesc.text + "'"
        }
    }
}
