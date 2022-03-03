package com.aridev.cordero.starwarsapp.core.dataApp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager2.widget.ViewPager2
import com.aridev.cordero.starwarsapp.R
import com.aridev.cordero.starwarsapp.core.dataApp.CustomClasses.SwiperTransformer
import com.google.android.material.textfield.TextInputLayout


fun ViewPager2.addSwiperStyleVp() {

    this.apply {
        orientation = ViewPager2.ORIENTATION_HORIZONTAL
        setPadding(40,0,40,0)
        offscreenPageLimit = 3
        setPageTransformer(SwiperTransformer(3))
    }
}

fun View.getHeightCustom(): Int {
    return this.measuredHeight
}

fun Context.getDrawableImage(url: String): Int {
    if(url != "") {
        var data = url.subSequence(27, url.length - 1)
        data = data.removePrefix("/")
        val number = data.filter { it.isDigit() }
        val name = data.filter { it.isLetter() }
        val drawableName: String = name.toString() + "_" + number.toString()
        return resources.getIdentifier(drawableName, "drawable", packageName)
    }
    return R.drawable.seach_ic
}

fun View.increaseViewWidthAnimation(value: Int, callback: () -> Unit) {
    val valueAnimator = ValueAnimator.ofInt(this.measuredWidth, this.measuredWidth + value)
    valueAnimator.duration = 500L
    valueAnimator.addUpdateListener {
        val animatedValue = valueAnimator.animatedValue as Int
        val layoutParams = this.layoutParams
        layoutParams.width = animatedValue
        this.layoutParams = layoutParams
    }

    valueAnimator.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            callback.invoke()
        }
    })
    valueAnimator.start()
}


fun View.hideSoftInput() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun TextInputLayout.searchMode(clearListener: (() -> Unit)) {
    this.isEndIconVisible = false
    this.setEndIconOnClickListener {
        clearListener.invoke()
    }

    this.setStartIconOnClickListener {
    }
}

fun TextInputLayout.endIconStatus(query: String) {
    this.isEndIconVisible = query.isNotEmpty()
}

fun EditText.onChangeListener(changeListener: ((query: String) -> Unit)) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            changeListener.invoke(p0.toString())
        }

    })
}

fun View.setOnClickListenerBounce(callback: () -> Unit) {
    this.setOnClickListener {
        this.isClickable = false
        val animation = AnimationUtils.loadAnimation(this.context, R.anim.bounce)
        this.startAnimation(animation)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                this.isClickable = true
                callback.invoke()
            }, 150
        )
    }
}

fun View.increaseViewHeightAnimation(value: Int, callback: () -> Unit) {
    val valueAnimator = ValueAnimator.ofInt(this.measuredHeight, this.measuredHeight + value)
    valueAnimator.duration = 500L
    valueAnimator.addUpdateListener {
        val animatedValue = valueAnimator.animatedValue as Int
        val layoutParams = this.layoutParams
        layoutParams.height = animatedValue
        this.layoutParams = layoutParams
    }

    valueAnimator.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            callback.invoke()
        }
    })
    valueAnimator.start()
}

fun EditText.clearText() {
    this.setText("")
}

fun Context.openUrlLink(url : String) {
    val defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
    defaultBrowser.data = Uri.parse(url)
    startActivity(defaultBrowser)
}