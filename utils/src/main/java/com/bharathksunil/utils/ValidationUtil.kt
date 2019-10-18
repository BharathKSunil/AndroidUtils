package com.bharathksunil.utils

import java.util.regex.Pattern

import java.util.Objects.requireNonNull

object ValidationUtil {

    //region Constants
    /**
     * a digit must occur at least once
     */
    val MUST_HAVE_DIGIT = "(?=.*[0-9])"
    /**
     * a lower case letter must occur at least once
     */
    val MUST_HAVE_LOWER_CASE_ALPHA = "(?=.*[0-9])"
    /**
     * an upper case letter must occur at least once
     */
    val MUST_HAVE_UPPER_CASE_ALPHA = "(?=.*[a-z])"
    /**
     * a special character must occur at least once
     */
    val MUST_HAVE_SPECIAL_CHAR = "(?=.*[@#$%^&+=])"
    /**
     * no whitespace allowed in the entire string
     */
    val MUST_NOT_HAVE_WHITE_SPACES = "(?=\\S+$)"
    /**
     * anything, at least eight places though
     */
    val MUST_HAVE_MINIMAL_8_CHAR = ".{8,}"
    //endregion

    //region Regular Expressions & their Setters
    /**
     * Regular expression for Password Strength validation , default modules:<br></br>
     * # a digit must occur at least once<br></br>
     * # a lower case letter must occur at least once<br></br>
     * # no whitespace allowed in the entire string<br></br>
     * # anything, at least eight places though<br></br>
     * Set this for your app by calling the [.setPasswordStrength] function in your
     * Application Class.
     */
    private var validPasswordRegex = Pattern.compile(
            "^"
                    + MUST_HAVE_DIGIT
                    + MUST_HAVE_LOWER_CASE_ALPHA
                    + MUST_NOT_HAVE_WHITE_SPACES
                    + MUST_HAVE_MINIMAL_8_CHAR
                    + "$"
    )

    /**
     * Phone number Validation Regular Expression to check if
     * ^                # start-of-string
     * [6789]           # contains 6 or 7 or 8 or 9
     * \\d{9}           # contains other 9 digits
     * $                # end-of-string
     */
    private var validPhoneNumberRegex = Pattern.compile("^[6789]\\d{9}$")

    /**
     * Email Id Validation Regular Expression to validate an email ID
     */
    private var validEmailAddressRegex = Pattern.compile(
            "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
            Pattern.CASE_INSENSITIVE
    )

    /**
     * Call this method to set the password strength that is required
     *
     * @param regex the regular Expression
     */
    fun setPasswordStrength(regex: String) {
        requireNonNull(regex)
        validPasswordRegex = Pattern.compile(regex)
    }

    /**
     * Call this method to set the password strength that is required
     *
     * @param pattern the pattern for the strength of password
     */
    fun setPasswordStrength(pattern: Pattern) {
        requireNonNull(pattern)
        validPasswordRegex = pattern
    }

    /**
     * Call this method to set the phone number validation regular expression
     *
     * @param regex the regular expression
     */
    fun setValidPhoneNumberRegex(regex: String) {
        requireNonNull(regex)
        validPhoneNumberRegex = Pattern.compile(regex)
    }

    /**
     * Call this method to set the phone number validation pattern
     *
     * @param pattern the pattern for phone number validation
     */
    fun setValidPhoneNumberRegex(pattern: Pattern) {
        requireNonNull(pattern)
        validPhoneNumberRegex = pattern
    }

    /**
     * Call this method to set the EmailId validation regular Expression
     *
     * @param regex the regular expression
     */
    fun setValidEmailAddressRegex(regex: String) {
        requireNonNull(regex)
        validEmailAddressRegex = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
    }

    /**
     * Call this method to set the EmailId validation pattern
     *
     * @param pattern the pattern for validation of emailID
     */
    fun setValidEmailAddressRegex(pattern: Pattern) {
        requireNonNull(pattern)
        validEmailAddressRegex = pattern
    }
    //endregion

    /**
     * This method checks if the emailID syntax is valid
     *
     * @param email the emailId to be validated
     * @return true, if the email is syntactically valid
     */
    fun isEmailValid(email: CharSequence): Boolean {
        requireNonNull(email)
        return validEmailAddressRegex.matcher(email).find()
    }

    /**
     * It checks if the phone number is a valid mobile number; by default in India.
     * For any other, use [.setValidPhoneNumberRegex] and pass the regex
     *
     * @param phone the mobile number to be validated
     * @return true, if the phone number is valid
     */
    fun isPhoneNumberValid(phone: CharSequence): Boolean {
        requireNonNull(phone)
        return validPhoneNumberRegex.matcher(phone).find()
    }

    /**
     * It checks if the password matches these criteria to ensure a strong password
     * C1: Contains 8 characters
     * C2: Contains minimum one digit
     * C3: Contains one Special Character
     *
     * @param password the password to be validated
     * @return true, if the password is strong and matches all criteria
     */
    fun isPasswordStrong(password: CharSequence): Boolean {
        requireNonNull(password)
        return validPasswordRegex.matcher(password).find()
    }

    /**
     * Call this method to check if the two strings are equal
     *
     * @param firstString  the first string
     * @param secondString the second string
     * @return true if the strings are equal
     */
    fun areEqual(firstString: String,
                 secondString: String?): Boolean {
        requireNonNull(firstString)
        return firstString == secondString
    }

}
