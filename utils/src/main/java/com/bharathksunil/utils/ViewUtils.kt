package com.bharathksunil.utils

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.VibrationEffect
import android.os.Vibrator
import com.google.android.material.textfield.TextInputLayout
import androidx.appcompat.app.AlertDialog
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ScrollView

import timber.log.Timber

import java.util.Objects.requireNonNull

/**
 * @author Bharath Kumar S on 08-01-2018.
 */

object ViewUtils {

    /**
     * Call this method to focus on any view inside the scrollView
     *
     * @param view       the view that needs to be focused on
     * @param scrollView the scrollview inside which the view is present
     */
    fun focusOnView(view: View, scrollView: ScrollView) {
        requireNonNull(view)
        requireNonNull(scrollView)
        Handler().post {
            try {
                val vLeft = view.left
                val vRight = view.right
                val sWidth = scrollView.width
                scrollView.smoothScrollTo((vLeft + vRight - sWidth) / 2, view.top)
            } catch (e: Exception) {
                //defensive programming, suppress the error
                Timber.e(e)
            }
        }
    }

    /**
     * This is used to make any activity a popup type with 75% background transparency. <br></br>
     * **NOTE:** the activity must be having a theme with these properties:<br></br>
     *
     *  * <item name="android:windowIsTranslucent">true</item>
     *  * <item name="android:windowCloseOnTouchOutside">true</item>
     *  * <item name="android:windowIsFloating">true</item>
     * <br></br>
     * <h4> How to use:</h4><br></br>
     * Call this method at the onCreate of the activity that you want to make it as a popup
     * Pass the window of the activity. you may get the activity window by using getWindow().
     *
     * @param window the window corresponding to the activity that must be made as a popup.
     */
    fun makePopupDisplay(window: Window, transparencyPercentage: Float) {
        requireNonNull(window)
        kotlin.require(!(transparencyPercentage < 10.00f || transparencyPercentage > 100.00f)
                //Aggressive programming. notify the Developer that the value passed is invalid
        ) {
            "The transparency amount passed must be between" +
                    "10.00 and 100.00. But the value passed is : " + transparencyPercentage
        }
        try {
            val windowParams = window.attributes
            windowParams.dimAmount = transparencyPercentage / 100.0f //convert from percent to a value
            windowParams.flags = windowParams.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
            window.attributes = windowParams
            window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT)
        } catch (e: Exception) {
            //defensive programming, suppress the error
            Timber.e(e)
        }

    }

    //region View Visibility Related Methods (Visible, Invisible, Gone)

    /**
     * Call this function to set the views as visible
     *
     * @param views the multiple views to set visible
     */
    fun setVisible(vararg views: View) {
        requireNonNull<Array<View>>(views)
        for (v in views) v.visibility = View.VISIBLE
    }

    /**
     * Call this function to set the views as visible, use this when the views are in a list
     * preferable to use this when using ButterKnife.
     *
     * @param views the multiple views to set visible
     */
    fun setVisible(views: List<View>) {
        requireNonNull(views)
        for (v in views) setVisible(v)
    }

    /**
     * Call this function to set the views as invisible
     *
     * @param views the multiple views to set invisible
     */
    fun setInvisible(vararg views: View) {
        requireNonNull<Array<View>>(views)
        for (v in views) v.visibility = View.INVISIBLE
    }

    /**
     * Call this function to set the views as invisible, use this when the views are in a list
     * preferable to use this when using ButterKnife.
     *
     * @param views the multiple views to set invisible
     */
    fun setInvisible(views: List<View>) {
        requireNonNull(views)
        for (v in views) setInvisible(v)
    }

    /**
     * Call this function to set the views as gone
     *
     * @param views the multiple views to set gone
     */
    fun setGone(vararg views: View) {
        requireNonNull<Array<View>>(views)
        for (v in views) {
            v.visibility = View.GONE
        }
    }

    /**
     * Call this function to set the views as gone, use this when the views are in a list
     * preferable to use this when using ButterKnife.
     *
     * @param views the multiple views to set gone
     */
    fun setGone(views: List<View>) {
        requireNonNull(views)
        for (v in views) setGone(v)
    }
    //endregion

    //region View State Related Methods (Enabled, Disabled)

    /**
     * Call this function to enable the views
     *
     * @param views the multiple views to be enabled
     */
    fun setEnabled(vararg views: View) {
        requireNonNull<Array<View>>(views)
        for (v in views) v.isEnabled = true
    }

    /**
     * Call this function to enable the views, use this when the views are in a list
     * preferable to use this when using ButterKnife.
     *
     * @param views the multiple views to be enabled
     */
    fun setEnabled(views: List<View>) {
        requireNonNull(views)
        for (v in views) setEnabled(v)
    }

    /**
     * Call this function to disable the views
     *
     * @param views the multiple views to be disabled
     */
    fun setDisabled(vararg views: View) {
        requireNonNull<Array<View>>(views)
        for (v in views) v.isEnabled = false
    }

    /**
     * Call this function to disable the views, use this when the views are in a list
     * preferable to use this when using ButterKnife.
     *
     * @param views the multiple views to be disabled
     */
    fun setDisabled(views: List<View>) {
        requireNonNull(views)
        for (v in views) setDisabled(v)
    }
    //endregion

    /**
     * Call this function to reset the errors in the [TextInputLayout]
     *
     * @param textInputLayouts the textInputLayout
     */
    fun resetTextInputError(vararg textInputLayouts: TextInputLayout) {
        requireNonNull<Array<TextInputLayout>>(textInputLayouts)
        for (textInputLayout in textInputLayouts)
            textInputLayout.error = null
    }

    /**
     * Call this function to reset the errors in the [TextInputLayout], use this when the
     * textInputLayouts are in a list. Preferable to use this when using ButterKnife.
     *
     * @param textInputLayouts the multiple views to be disabled
     */
    fun resetTextInputError(textInputLayouts: List<TextInputLayout>) {
        requireNonNull(textInputLayouts)
        for (textInputLayout in textInputLayouts)
            resetTextInputError(textInputLayout)
    }

    /**
     * Use these methods to vibrate the device.<br></br>
     * **Note: ** add the permission <uses-permission android:name="android.permission.VIBRATE"></uses-permission>to Manifest
     *
     * @param context      the context.
     * @param milliseconds the time the vibration must continue.
     */
    @SuppressLint("MissingPermission")
    fun vibrate(context: Context, milliseconds: Long) {
        requireNonNull(context)
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(milliseconds)
            }
        }
    }

    /**
     * This creates a simple alert dialogue with an 'OK' positive button(if clickListener is passed
     * and is cancellable. The theme of the alert dialogue is Theme_Material_Light_Dialog_Alert.
     *
     * @param context         the context.
     * @param title           the title of the alert dialogue.
     * @param message         the message that must be displayed.
     * @param onClickListener the click listener for the okButton.
     * @return the AlertDialog that can be displayed using .show() or further customised.
     */
    fun createSimpleAlertDialog(context: Context,
                                title: String,
                                message: String,
                                onClickListener: AlertDialog.OnClickListener?): AlertDialog {
        requireNonNull(context)
        requireNonNull(title)
        requireNonNull(message)

        val builder: AlertDialog.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert)
        } else {
            builder = AlertDialog.Builder(context)
        }
        builder.setTitle(title)
        builder.setMessage(message)
        if (onClickListener != null)
            builder.setPositiveButton("OK", onClickListener)
        builder.setCancelable(true)
        return builder.create()
    }

    //region View Animation Methods

    /**
     * To animate view slide out from left to right
     *
     * @param view the view that must be animated
     */
    fun slideOutToRight(view: View) {
        requireNonNull(view)
        val animate = TranslateAnimation(0f, view.width.toFloat(), 0f, 0f)
        animate.duration = 500
        animate.fillAfter = true
        animate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                //do nothing
            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation) {
                //do nothing
            }
        })
        view.startAnimation(animate)
    }

    /**
     * To animate view slide out from right to left
     *
     * @param view the view that must be animated
     */
    fun slideOutToLeft(view: View) {
        requireNonNull(view)
        val animate = TranslateAnimation(0f, (-view.width).toFloat(), 0f, 0f)
        animate.duration = 500
        animate.fillAfter = true
        animate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                //do nothing
            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation) {
                //do nothing
            }
        })
        view.startAnimation(animate)
    }

    /**
     * To animate view slide out from top to bottom
     *
     * @param view the view that must be animated
     */
    fun slideOutToBottom(view: View) {
        requireNonNull(view)
        val animate = TranslateAnimation(0f, 0f, 0f, view.height.toFloat())
        animate.duration = 500
        animate.fillAfter = true
        animate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                //do nothing
            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation) {
                //do nothing
            }
        })
        view.startAnimation(animate)
    }

    /**
     * To animate view slide out from bottom to top
     *
     * @param view the view that must be animated
     */
    fun slideOutToTop(view: View) {
        requireNonNull(view)
        val animate = TranslateAnimation(0f, 0f, 0f, (-view.height).toFloat())
        animate.duration = 500
        animate.fillAfter = true
        animate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                //do nothing
            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation) {
                //do nothing
            }
        })
        view.startAnimation(animate)
    }
    //endregion
}//so that no instance is made
