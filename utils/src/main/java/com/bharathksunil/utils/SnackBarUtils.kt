package com.bharathksunil.utils

import android.app.Activity
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import androidx.core.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.Objects.requireNonNull


/**
 * This Utility has methods related to snackBars, creating appTheme specific SnackBar, error SnackBar<br></br>
 * **Note: ** The accent Color must be set by the user in the Application Class by calling the
 * [.initialise] method
 */
object SnackBarUtils {

    private var accentColorResource = R.color.snackbar_default_accent
    private var textColorResource = android.R.color.white

    /**
     * Call this method in the onCreate() method of the Application class of your app to pass in the
     * accent color of the app so that the SnackBars can be matching to those colors
     *
     * @param accentColorResource the accent color or the desired color for the SnackBar
     */
    fun initialise(@ColorRes accentColorResource: Int,
                   @ColorRes textColorResource: Int) {
        SnackBarUtils.accentColorResource = accentColorResource
        SnackBarUtils.textColorResource = textColorResource
    }

    /**
     * Call this method to show a SnackBar with custom background color.<br></br>
     * **Note: **The color of text should be selected such that the message is visible on the
     * SnackBar with the background color.
     *
     * @param rootView        the view on which the SnackBar must be displayed on
     * @param message         the message that must be displayed
     * @param duration        the duration of the message in milliseconds or use SnackBar.LENGTH_XXXX
     * @param backgroundColor the color of the background
     * @param textColor       the color of the text
     */
    fun showCustomisedSnackBar(rootView: View,
                               message: String,
                               duration: Int,
                               @ColorInt backgroundColor: Int,
                               @ColorInt textColor: Int) {
        requireNonNull(rootView)
        requireNonNull(message)
        val snackbar = Snackbar.make(rootView, message, duration)
        snackbar.view.setBackgroundColor(backgroundColor)
        val textView = snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(textColor)
        snackbar.show()
    }

    /**
     * Call this method to show a Normal SnackBar with black background and white text
     * on the view with a message and the duration of the message
     *
     * @param rootView the view on which the snack bar must be displayed
     * @param message  the message that must be displayed
     * @param duration the duration of the message in milliseconds or use SnackBar.LENGTH_XXXX
     */
    fun showSimpleSnackBar(rootView: View,
                           message: String,
                           duration: Int) {
        showCustomisedSnackBar(
                rootView,
                message,
                duration,
                Color.BLACK,
                Color.WHITE
        )
    }

    //region Long SnackBar Methods

    /**
     * This function makes [.accentColorResource] colored SnackBar for displaying messages for
     * Long interval and the text color will be [.textColorResource]
     *
     * @param activity the activity on which the snack must be shown
     * @param message  the message to be shown
     */
    fun showLongSnackBar(activity: Activity,
                         message: String) {
        requireNonNull(activity)
        showCustomisedSnackBar(
                (activity.findViewById<View>(android.R.id.content) as ViewGroup)
                        .getChildAt(0), //gets the root view of the activity
                message,
                Snackbar.LENGTH_LONG,
                ContextCompat.getColor(activity.baseContext, accentColorResource),
                ContextCompat.getColor(activity.baseContext, textColorResource)
        )
    }

    /**
     * This function makes SnackBar for displaying messages for Long interval with the background color
     * passed and the text color will be [.textColorResource]
     *
     * @param activity        the activity on which the snack must be shown
     * @param message         the message to be shown
     * @param backgroundColor the Color of the background
     */
    fun showLongSnackBar(activity: Activity,
                         message: String,
                         @ColorInt backgroundColor: Int) {
        requireNonNull(activity)
        showCustomisedSnackBar(
                (activity.findViewById<View>(android.R.id.content) as ViewGroup)
                        .getChildAt(0), //gets the root view of the activity
                message,
                Snackbar.LENGTH_LONG,
                backgroundColor,
                ContextCompat.getColor(activity.baseContext, textColorResource)
        )
    }

    /**
     * This function makes SnackBar for displaying messages for Long interval with the background color
     * passed and the text color = textColor
     *
     * @param activity        the activity on which the snack must be shown
     * @param message         the message to be shown
     * @param backgroundColor the Color of the background
     * @param textColor       the Color for the text
     */
    fun showLongSnackBar(activity: Activity,
                         message: String,
                         @ColorInt backgroundColor: Int,
                         @ColorInt textColor: Int) {
        requireNonNull(activity)
        showCustomisedSnackBar(
                (activity.findViewById<View>(android.R.id.content) as ViewGroup)
                        .getChildAt(0), //gets the root view of the activity
                message,
                Snackbar.LENGTH_LONG,
                backgroundColor,
                textColor
        )
    }

    /**
     * This function makes apps accent colored SnackBar for displaying messages for Long interval
     *
     * @param activity the activity on which the snack must be shown
     * @param message  the string resource of message to be shown
     */
    fun showLongSnackBar(activity: Activity,
                         @StringRes message: Int) {
        showLongSnackBar(activity, activity.getString(message))
    }

    /**
     * This function makes SnackBar for displaying messages for Long interval with the background color
     * passed and the text color will be [.textColorResource]
     *
     * @param activity        the activity on which the snack must be shown
     * @param message         the message to be shown
     * @param backgroundColor the Color of the background
     */
    fun showLongSnackBar(activity: Activity,
                         @StringRes message: Int,
                         @ColorInt backgroundColor: Int) {
        requireNonNull(activity)
        showCustomisedSnackBar(
                (activity.findViewById<View>(android.R.id.content) as ViewGroup)
                        .getChildAt(0), //gets the root view of the activity
                activity.getString(message),
                Snackbar.LENGTH_LONG,
                backgroundColor,
                ContextCompat.getColor(activity.baseContext, textColorResource)
        )
    }

    /**
     * This function makes SnackBar for displaying messages for Long interval with the background color
     * passed and the text color = textColor
     *
     * @param activity        the activity on which the snack must be shown
     * @param message         the message to be shown
     * @param backgroundColor the Color of the background
     * @param textColor       the Color for the text
     */
    fun showLongSnackBar(activity: Activity,
                         @StringRes message: Int,
                         @ColorInt backgroundColor: Int,
                         @ColorInt textColor: Int) {
        requireNonNull(activity)
        showCustomisedSnackBar(
                (activity.findViewById<View>(android.R.id.content) as ViewGroup)
                        .getChildAt(0), //gets the root view of the activity
                activity.getString(message),
                Snackbar.LENGTH_LONG,
                backgroundColor,
                textColor
        )
    }
    //endregion

    //region Short SnackBar Methods

    //todo: Add few more methods here for higher customization

    /**
     * TThis function makes apps accent colored SnackBar for displaying messages for short interval
     *
     * @param activity the activity on which the snack must be shown
     * @param message  the message to be shown
     */
    fun showShortSnackBar(activity: Activity, message: String) {
        requireNonNull(activity)
        showCustomisedSnackBar(
                (activity.findViewById<View>(android.R.id.content) as ViewGroup)
                        .getChildAt(0), //gets the root view of teh activity
                message,
                Snackbar.LENGTH_SHORT,
                ContextCompat.getColor(activity, accentColorResource),
                Color.WHITE
        )
    }

    /**
     * This function makes apps accent colored SnackBar for displaying messages for short interval
     *
     * @param activity the activity on which the snack must be shown
     * @param message  the string resource of message to be shown
     */
    fun showShortSnackBar(activity: Activity, @StringRes message: Int) {
        showShortSnackBar(activity, activity.getString(message))
    }
    //endregion

    //region Error SnackBar Methods

    /**
     * This function makes a Red colored SnackBar for 4 seconds for displaying errors.
     *
     * @param activity the activity on which the snack must be shown
     * @param message  the message to be shown
     */
    fun showErrorBar(activity: Activity, message: String) {
        requireNonNull(activity)
        showCustomisedSnackBar(
                (activity.findViewById<View>(android.R.id.content) as ViewGroup)
                        .getChildAt(0), //gets the root view of the activity
                message,
                4000,
                ContextCompat.getColor(activity, android.R.color.holo_red_dark),
                Color.WHITE
        )
    }

    /**
     * This function makes a Red colored SnackBar for 4 seconds for displaying errors.
     *
     * @param activity the activity on which the snack must be shown
     * @param message  the message to be shown
     */
    fun showErrorBar(activity: Activity, @StringRes message: Int) {
        showErrorBar(activity, activity.getString(message))
    }
    //endregion
}//do not let anyone create an instance of this class
