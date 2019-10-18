package com.bharathksunil.utils

import java.util.Objects.requireNonNull

/**
 * Use this utility to perform some generic operations related to firebase
 *
 * @author Bharath
 * @since 10-01-2018.
 */
object FirebaseUtils {

    private val DOT_REPLACEMENT = "_dot_"

    /**
     * Call this function to convert the email as a key in the Real-time database. It stores the
     * email by replacing the '.'(dot) with the [.DOT_REPLACEMENT] token instead.
     *
     * @param email the email address
     * @return the key safe of the email address
     */
    fun getEmailAsFirebaseKey(email: String): String {
        var email = email
        requireNonNull(email)
        email = email.toLowerCase()
        return email.replace("\\.".toRegex(), DOT_REPLACEMENT)
    }

    /**
     * Call this function to convert the firebase key in the form of an email back to a regular email
     * It removes the [.DOT_REPLACEMENT] from the key and replaces it with '.'(dot). This
     * method reverses the action in the [.getEmailAsFirebaseKey].
     *
     * @param key the email which was stored as a key
     * @return the regular email ID
     */
    fun getEmailFromFirebaseKey(key: String): String {
        requireNonNull(key)
        return key.replace(DOT_REPLACEMENT.toRegex(), "\\.").toLowerCase()
    }

}