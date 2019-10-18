package com.bharathksunil.utils

import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import java.util.Objects.requireNonNull

/**
 * Use this Utility to load fragments.
 *
 * @author Bharath
 */
object FragmentTransactionUtils {

    //region Replace Fragment Methods

    /**
     * Call this method from the activity to replace a frame with the fragment passed. If you don't
     * want to give any animations for the transition then pass -1 or call
     * [.replaceFragment]
     *
     * @param supportFragmentManager the Support Fragment Manager
     * @param fragment               the fragment to be replaced
     * @param frame                  the frame in which the fragment must be replaced
     * @param tag                    the unique tag for the Fragment
     * @param addToBackStack         if the fragment must be added to be back stack
     * @param entryAnimationRes      the entry animation resource
     * @param exitAnimationRes       the exit animation resource
     */
    @JvmOverloads
    fun replaceFragment(supportFragmentManager: FragmentManager,
                        fragment: Fragment, @IdRes frame: Int,
                        tag: String, addToBackStack: Boolean,
                        @AnimRes entryAnimationRes: Int = -1, @AnimRes exitAnimationRes: Int = -1) {
        requireNonNull(supportFragmentManager)
        requireNonNull(fragment)
        requireNonNull(tag)
        // This check is added to ensure we don't add the same fragment twice while the fragment is already added
        if (fragment.isAdded)
            return
        val transaction = supportFragmentManager.beginTransaction()
        //check if the user does not want any animation
        if (!(entryAnimationRes == -1 || exitAnimationRes == -1))
            transaction.setCustomAnimations(
                    entryAnimationRes,
                    exitAnimationRes
            )

        transaction.replace(frame, fragment, tag)
        if (addToBackStack)
            transaction.addToBackStack(tag)
        transaction.commit()
    }

    /**
     * Call this method from the activity to replace a frame with the fragment passed and give it
     * some animation.
     *
     * @param supportFragmentManager the support fragment manager.
     * @param fragment               the fragment that must be loaded.
     * @param frame                  the FrameLayout to which the fragment muse be loaded
     * @param tag                    the unique tag that is given to identify the fragment
     * @param addToBackStack         if the fragment must be added to the back stack
     * @param transitionAnimation    the type of animation
     */
    fun replaceFragment(supportFragmentManager: FragmentManager,
                        fragment: Fragment, @IdRes frame: Int,
                        tag: String, addToBackStack: Boolean,
                        transitionAnimation: TransitionAnimation) {
        var inAnim = android.R.anim.slide_in_left
        var outAnim = android.R.anim.slide_out_right
        when (transitionAnimation) {
            FragmentTransactionUtils.TransitionAnimation.FADING -> {
                inAnim = R.anim.fade_in
                outAnim = R.anim.fade_out
            }
            FragmentTransactionUtils.TransitionAnimation.SLIDING_IN_LEFT -> {
                inAnim = R.anim.slide_in_left
                outAnim = R.anim.slide_out_right
            }
            FragmentTransactionUtils.TransitionAnimation.SLIDING_IN_RIGHT -> {
                inAnim = R.anim.slide_in_right
                outAnim = R.anim.slide_out_left
            }
        }
        replaceFragment(
                supportFragmentManager,
                fragment,
                frame,
                tag,
                addToBackStack,
                inAnim,
                outAnim
        )

    }
    //endregion

    //region Add Fragment Methods

    /**
     * Call this method from the activity to add the fragment passed to a frame. If you don't
     * want to give any animations for the transition then pass -1 or call
     * [.replaceFragment]
     *
     * @param supportFragmentManager the Support Fragment Manager
     * @param fragment               the fragment to be replaced
     * @param frame                  the frame in which the fragment must be replaced
     * @param tag                    the unique tag for the Fragment
     * @param addToBackStack         if the fragment must be added to be back stack
     * @param entryAnimationRes      the entry animation resource
     * @param exitAnimationRes       the exit animation resource
     */
    @JvmOverloads
    fun addFragment(supportFragmentManager: FragmentManager,
                    fragment: Fragment, @IdRes frame: Int,
                    tag: String, addToBackStack: Boolean,
                    @AnimRes entryAnimationRes: Int = -1, @AnimRes exitAnimationRes: Int = -1) {
        requireNonNull(supportFragmentManager)
        requireNonNull(fragment)
        requireNonNull(tag)
        // This check is added to ensure we don't add the same fragment twice while the fragment is already added
        if (fragment.isAdded)
            return
        val transaction = supportFragmentManager.beginTransaction()
        //check if the user does not want any animation
        if (!(entryAnimationRes == -1 || exitAnimationRes == -1))
            transaction.setCustomAnimations(
                    entryAnimationRes,
                    exitAnimationRes
            )

        transaction.add(frame, fragment, tag)
        if (addToBackStack)
            transaction.addToBackStack(tag)
        transaction.commit()
    }

    /**
     * Call this method from the activity to add the fragment passed to a frame and give it
     * some animation.
     *
     * @param supportFragmentManager the support fragment manager.
     * @param fragment               the fragment that must be loaded.
     * @param frame                  the FrameLayout to which the fragment muse be loaded
     * @param tag                    the unique tag that is given to identify the fragment
     * @param addToBackStack         if the fragment must be added to the back stack
     * @param transitionAnimation    the type of animation
     */
    fun addFragment(supportFragmentManager: FragmentManager,
                    fragment: Fragment, @IdRes frame: Int,
                    tag: String, addToBackStack: Boolean,
                    transitionAnimation: TransitionAnimation) {
        var inAnim = android.R.anim.slide_in_left
        var outAnim = android.R.anim.slide_out_right
        when (transitionAnimation) {
            FragmentTransactionUtils.TransitionAnimation.FADING -> {
                inAnim = R.anim.fade_in
                outAnim = R.anim.fade_out
            }
            FragmentTransactionUtils.TransitionAnimation.SLIDING_IN_LEFT -> {
                inAnim = R.anim.slide_in_left
                outAnim = R.anim.slide_out_right
            }
            FragmentTransactionUtils.TransitionAnimation.SLIDING_IN_RIGHT -> {
                inAnim = R.anim.slide_in_right
                outAnim = R.anim.slide_out_left
            }
        }
        addFragment(
                supportFragmentManager,
                fragment,
                frame,
                tag,
                addToBackStack,
                inAnim,
                outAnim
        )

    }
    //endregion

    //region Remove Fragment Methods

    /**
     * Call this function to remove a fragment from the Activity
     *
     * @param fragmentManager the support fragment Manager
     * @param fragment        the fragment that must be removed
     */
    fun removeFragment(fragmentManager: FragmentManager,
                       fragment: Fragment) {
        requireNonNull(fragmentManager)
        requireNonNull(fragment)
        if (fragment.isAdded && !fragment.isRemoving)
            fragmentManager.beginTransaction().remove(fragment).commit()
    }

    /**
     * Call this method to remove the topmost fragment in the view stack
     *
     * @param fragmentManager the Support fragment manager
     */
    fun removeCurrentFragment(fragmentManager: FragmentManager) {
        requireNonNull(fragmentManager)
        fragmentManager.popBackStack()
    }
    //endregion

    /**
     * This contains the list of fragment transition animations that is supported
     */
    enum class TransitionAnimation {
        /**
         * For the fragment to slide in from the left and exit to the right
         */
        SLIDING_IN_LEFT,
        /**
         * For the fragment to slide in from the right and exit to the left
         */
        SLIDING_IN_RIGHT,
        /**
         * For the fragment to fade in and exit by fading out
         */
        FADING
    }
}
/**
 * Call this method from the activity to replace a frame with the fragment passed and don't want
 * to use any animation.
 *
 * @param supportFragmentManager the Support Fragment Manager
 * @param fragment               the fragment to be replaced
 * @param frame                  the frame in which the fragment must be replaced
 * @param tag                    the unique tag for the Fragment
 * @param addToBackStack         if the fragment must be added to be back stack
 */
/**
 * Call this method from the activity to add the fragment passed to a frame and don't want
 * to use any animation.
 *
 * @param supportFragmentManager the Support Fragment Manager
 * @param fragment               the fragment to be replaced
 * @param frame                  the frame in which the fragment must be replaced
 * @param tag                    the unique tag for the Fragment
 * @param addToBackStack         if the fragment must be added to be back stack
 */
