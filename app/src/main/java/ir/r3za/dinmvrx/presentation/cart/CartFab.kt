package ir.r3za.dinmvrx.presentation.cart

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.r3za.dinmvrx.R


class CartFab @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    lateinit var tvCount: TextView
    lateinit var fabCart: FloatingActionButton
    private var size = 0
    private val bounceAnimation: Animation =
        AnimationUtils.loadAnimation(context, R.anim.bounce_animation)

    init {
        initView()
    }

    private fun initView() {
        inflate(context, R.layout.view_fab_cart, this)
        tvCount = findViewById(R.id.tv_count)
        fabCart = findViewById(R.id.fab_cart)
    }

    fun setCount(size: Int) {
        this.size = size
        if (size == 0) {
            tvCount.visibility = View.GONE
        } else {
            tvCount.text = size.toString()
            if (visibility == View.VISIBLE && tvCount.visibility != View.VISIBLE) {
                tvCount.visibility = View.VISIBLE
            }
            if (!bounceAnimation.hasEnded()) {
                bounceAnimation.cancel()
            }
            tvCount.startAnimation(bounceAnimation)
        }
    }

    fun hide() {
        visibility = View.GONE
    }

    fun show() {
        visibility = View.VISIBLE
        if (size > 0) {
            tvCount.visibility = View.VISIBLE
        }
    }
}