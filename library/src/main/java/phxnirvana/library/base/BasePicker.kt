package phxnirvana.library.base

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import phxnirvana.library.R

/**
 * @author phxnirvana
 * *         2017/5/24
 */

open class BasePicker(internal var mContext: Activity) {
    private val outAnim: Animation
    private val inAnim: Animation
    private var dismissing: Boolean = false
    var isShowing: Boolean = false
        private set
    private var cancelable: Boolean = false
    private val decorView: ViewGroup
    private val rootView: ViewGroup
    internal var contentContainer: ViewGroup

    init {
        decorView = mContext.window.decorView.findViewById(android.R.id.content) as ViewGroup
        rootView = LayoutInflater.from(mContext).inflate(R.layout.picker_layout_base_view, decorView, false) as ViewGroup
        rootView.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )

        contentContainer = rootView.findViewById(R.id.content_container) as ViewGroup
        contentContainer.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM
        )
        rootView.findViewById(R.id.outmost_container).setOnClickListener { v ->
            if (cancelable) {
                dismiss()
            }
        }
        inAnim = inAnimation
        outAnim = outAnimation
    }

    fun show() {
        if (isShowing) {
            return
        }
        isShowing = true
        decorView.addView(rootView)
        contentContainer.startAnimation(inAnim)
    }

    fun dismiss() {
        if (dismissing) {
            return
        }
        dismissing = true
        //消失动画
        outAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                decorView.post { dismissImmediately() }
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        contentContainer.startAnimation(outAnim)
    }

    //从activity根视图移除
    fun dismissImmediately() {
        decorView.removeView(rootView)
        isShowing = false
        dismissing = false
    }

    fun setCancelable(cancelable: Boolean) {
        this.cancelable = cancelable
    }

    val inAnimation: Animation
        get() = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom)

    val outAnimation: Animation
        get() = AnimationUtils.loadAnimation(mContext, R.anim.slide_out_bottom)
}
