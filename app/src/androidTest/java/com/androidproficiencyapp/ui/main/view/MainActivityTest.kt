package com.androidproficiencyapp.ui.main.view


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.androidproficiencyapp.R.id.swipeContainer
import com.androidproficiencyapp.R.id.rvItems
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun pullToRefresh_shouldPass() {
        onView(withId(swipeContainer)).perform(swipeDown())
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun loadMore_shouldPass() {

        Thread.sleep(2000);
        onView(withId(rvItems)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2)).perform(swipeUp())

    }
}