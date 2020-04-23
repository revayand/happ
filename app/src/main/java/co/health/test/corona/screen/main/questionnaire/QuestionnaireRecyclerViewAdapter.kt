package co.health.test.corona.screen.main.questionnaire


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.Questionnaire
import co.health.test.corona.repository.db.entities.QuestionnaireWithQuestions
import co.health.test.corona.screen.main.questionnaire.QuestionnaireFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.row_questionnnaire.view.*


class QuestionnaireRecyclerViewAdapter(
    private val mValues: List<QuestionnaireWithQuestions>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<QuestionnaireRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as QuestionnaireWithQuestions
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_questionnnaire, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]

        holder.mContentView.text = item.questionnaire.title

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val mContentView: TextView = mView.tv

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
