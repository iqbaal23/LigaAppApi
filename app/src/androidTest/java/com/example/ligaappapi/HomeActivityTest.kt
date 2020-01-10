package com.example.ligaappapi

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.EditText
import com.example.ligaappapi.view.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testSearchBehaviour() {
        Espresso.onView(withId(R.id.action_search))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.action_search)).perform(click())
        Espresso.onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("Arsenal"), pressImeActionButton())
        Thread.sleep(3000)

        Espresso.onView(withId(R.id.action_search)).perform(click())
        Espresso.onView(isAssignableFrom(EditText::class.java)).perform(typeText("Barcelona"))
        Thread.sleep(3000)
        Espresso.onView(isAssignableFrom(EditText::class.java)).perform(clearText())
        Espresso.onView(isAssignableFrom(EditText::class.java)).perform(typeText("Manchester"))
        Thread.sleep(3000)
        Espresso.onView(withText("No Match Found")).check(matches(isDisplayed()))


    }
}