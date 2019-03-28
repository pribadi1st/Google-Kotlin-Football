package com.example.ridwan.submissionii

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.ridwan.submissionii.Fragment.LastMatchFragment
import com.example.ridwan.submissionii.R.id.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception


@RunWith(AndroidJUnit4::class)
class LastMatchFragmentTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp(){
        //OPEN FRAGMENT AND DELAY FOR 5 SECONDS
        activityRule.activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
            .commit()
        Thread.sleep(5000)
    }

    @Test
    fun addToFavorite() {
        // SCROLL RECYCLEVIEW TO 10 POSITION
        onView(withId(list_matchs))
            .check(matches(isDisplayed()))
        onView(withId(list_matchs)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        //CHOOSE 8 item and click on it
        onView(withId(list_matchs)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                9, click()))


        //CLICK ADD TO FAVORITE
        onView(withId(R.id.add_to_favorite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())

        //SHOW SNACKBAR POP UP AND GO BACK TO MAIN ACTIVITY
        try {
            onView(ViewMatchers.withText("Added to Favourite")).check(matches(isDisplayed()))
        }catch (e : Exception){
            onView(ViewMatchers.withText("Removed from favourite")).check(matches(isDisplayed()))
        }

        Espresso.pressBack()

        //SHOW THE ADDED MATCH AT FAVORITE FRAGMENT
        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(favorite)).perform(click())
    }
}