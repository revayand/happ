package co.health.test.corona.screen.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import co.health.test.corona.R


class LoadingLayout : RelativeLayout {
    private var mContext: Context? = null
    private var vError: View? = null
    private var mainLayout: View? = null
    private var vLoading: View? = null

    private var listener: OnClickListener? = null
    var state = LoadingLayoutState.STATE_SHOW_DATA
        set(mState) {
            field = mState

            when (mState) {
                LoadingLayoutState.STATE_LOADING -> {
                    vError!!.visibility = View.GONE
                    mainLayout!!.visibility = View.INVISIBLE
                    vLoading!!.visibility = View.VISIBLE
                }
                LoadingLayoutState.STATE_SHOW_DATA -> {
                    vLoading!!.visibility = View.GONE
                    vError!!.visibility = View.GONE

                    //                mainLayout!!.alpha = 0f
                    mainLayout!!.visibility = View.VISIBLE
                    //                mainLayout!!.animate()
                    //                        .alpha(1f)
                    //                        .setInterpolator(FastOutLinearInInterpolator()).duration = 500
                    //                if (isInEditMode) {
                    //                    mainLayout!!.alpha = 1f
                    //
                    //                }
                }
                LoadingLayoutState.STATE_SHOW_ERROR -> {
                    mainLayout!!.visibility = View.INVISIBLE
                    vLoading!!.visibility = View.GONE
                    vError!!.visibility = View.VISIBLE

                }
            }

        }

    fun setListener(listener: OnClickListener) {
        this.listener = listener
    }


    constructor(context: Context) : super(context) {
        initializeViews(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initializeViews(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initializeViews(context, attrs)
    }


    private fun initializeViews(context: Context, attrs: AttributeSet?) {
        this.mContext = context


    }


    override fun onFinishInflate() {
        super.onFinishInflate()

        val inflater = LayoutInflater.from(mContext)
        vError = inflater.inflate(R.layout.layout_error, null)
        vLoading = inflater.inflate(R.layout.layout_loading, null)
        vError!!.setOnClickListener { v ->
            if (listener != null) {
                state = LoadingLayoutState.STATE_LOADING
                listener!!.onClick(v)
            }
        }


        val lp = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )


        lp.addRule(CENTER_IN_PARENT, TRUE)



        vError?.layoutParams = lp
        vLoading?.layoutParams = lp


        mainLayout = getChildAt(0)

        addView(vLoading)
        addView(vError)

        state = LoadingLayoutState.STATE_LOADING


    }


    fun setError(mError: String) {
        val tvError = vError!!.findViewById<TextView>(R.id.lbl_error)
        tvError.text = mError
        state = LoadingLayoutState.STATE_SHOW_ERROR
    }

    enum class LoadingLayoutState {
        STATE_LOADING,
        STATE_SHOW_DATA,
        STATE_SHOW_ERROR

    }


}