package com.bharathksunil.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager

import timber.log.Timber

import android.content.Context.CONNECTIVITY_SERVICE
import java.util.Objects.requireNonNull

/**
 * <h>Utils</h>
 * This is a Java Class Which provides many generic utilities for functioning of the app
 * <br></br>
 *
 * @author Bharath <email>bharathk.sunil.k@gmail.com</email>
 */
object Utils {

    val PERMISSION_REQUEST_CODE = 22

    /**
     * This function is used to check if the App is connected to the Internet
     * **NOTE:** The App Must have a Permission :
     * <uses-permission android:name="android.permission.INTERNET"></uses-permission>
     * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
     *
     * @return true if the app is connected to the internet
     */
    fun isConnectedToTheNetwork(context: Context): Boolean {
        requireNonNull(context)
        val cManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        @SuppressLint("MissingPermission")
        val nInfo = cManager?.activeNetworkInfo
        //IF THE NETWORK IS AVAILABLE:
        return nInfo != null && nInfo.isConnected
    }

    //todo: Move methods from here to appropriate classes
    fun isPermissionGranted(context: Context,
                            permissionString: String): Boolean {
        requireNonNull(context)
        requireNonNull(permissionString)
        return Build.VERSION.SDK_INT < 23 || context.checkSelfPermission(permissionString) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * this function is used for hide keyboard panel.
     *
     * @param activity - activity instance
     */
    fun hideKeyboard(activity: Activity) {
        requireNonNull(activity)
        try {
            val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            Timber.e(e)
        }

    }

    fun isHighAccuracyLocationEnabled(context: Context): Boolean {
        requireNonNull(context)
        val locationMode: Int
        val locationProviders: String

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.contentResolver, Settings.Secure.LOCATION_MODE)
            } catch (e: Settings.SettingNotFoundException) {
                e.printStackTrace()
                return false
            }

            return locationMode == Settings.Secure.LOCATION_MODE_HIGH_ACCURACY

        } else {
            locationProviders = Settings.Secure.getString(context.contentResolver,
                    Settings.Secure.LOCATION_PROVIDERS_ALLOWED)
            return !TextUtils.isEmpty(locationProviders)
        }
    }
}
