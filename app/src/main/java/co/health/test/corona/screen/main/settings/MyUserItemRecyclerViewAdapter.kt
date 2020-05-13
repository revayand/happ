package co.health.test.corona.screen.main.settings


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import co.health.test.corona.R
import co.health.test.corona.repository.db.entities.Users
import co.health.test.corona.screen.main.settings.UserItemFragment.OnUserListFragmentInteractionListener
import co.health.test.corona.screen.main.settings.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.fragment_user_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnUserListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyUserItemRecyclerViewAdapter(
    private val mValues: List<Users>, private val id: Long,
    private val mListenerUser: OnUserListFragmentInteractionListener?
) : RecyclerView.Adapter<MyUserItemRecyclerViewAdapter.ViewHolder>() {

    private var context: Context? = null
    private val mOnClickListener: View.OnClickListener
    private val check: View.OnClickListener
    private val mOnLongClickListener: View.OnLongClickListener
    private lateinit var selected: View

    init {
        check = View.OnClickListener { v ->
            val item = v.tag as Users
            selected.tv_state.setColorFilter(ContextCompat.getColor(context!!, R.color.gray_dark))

            v.tv_state.setColorFilter(ContextCompat.getColor(context!!, R.color.colorPrimary))
            selected = v
            mListenerUser?.onChangeUser(item)

        }
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Users
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListenerUser?.onUserListFragmentInteraction(item)
        }
        mOnLongClickListener = View.OnLongClickListener { v ->
            val item = v.tag as Users
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListenerUser?.onLongUserListFragmentInteraction(item)
            return@OnLongClickListener true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.detail?.fname + " " + item.detail.lname
//        holder.mContentView.text = ""
        if (item.id == id) {
            holder.mContentView.setColorFilter(
                ContextCompat.getColor(
                    context!!,
                    R.color.colorPrimary
                )
            )
            selected = holder.itemView
        } else {
            holder.mContentView.setColorFilter(ContextCompat.getColor(context!!, R.color.gray_dark))


        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
            setOnLongClickListener(mOnLongClickListener)
        }
        with(holder.mContentView) {
            tag = item
            setOnClickListener(check)

        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.tv_title
        val mContentView: ImageView = mView.tv_state

        override fun toString(): String {
            return super.toString() + " '" + "'"
        }
    }
}
