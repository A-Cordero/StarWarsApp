package com.aridev.cordero.starwarsapp.core.dataApp.CustomClasses

import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs


class SwiperTransformer(private val offscreenPageLimit: Int) : ViewPager2.PageTransformer {

    companion object {

        private const val DEFAULT_TRANSLATION_X = .0f
        private const val DEFAULT_TRANSLATION_FACTOR = 1.2f

        private const val SCALE_FACTOR = .14f
        private const val DEFAULT_SCALE = 1f

        private const val ALPHA_FACTOR = .1f
        private const val DEFAULT_ALPHA = 1f

    }

    override fun transformPage(page: View, position: Float) {

        page.apply {
            ViewCompat.setElevation(page, -kotlin.math.abs(position))

            val scaleFactor = DEFAULT_SCALE - 3.5f* abs(position) *SCALE_FACTOR

            when {
                position < 0f && position > -3f -> {
                    ViewCompat.setElevation(page,1f- abs(position))
                    translationX = position * -(width*0.48f);
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                position == 0f -> {
                    translationX = position

                    ViewCompat.setElevation(page,2f)
                    scaleX = DEFAULT_SCALE
                    scaleY = DEFAULT_SCALE
                }
                position > 0f && position < 3-> {
                    ViewCompat.setElevation(page,-1f)
                    translationX = position * -(width*0.48f);
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
            }
        }
    }

}