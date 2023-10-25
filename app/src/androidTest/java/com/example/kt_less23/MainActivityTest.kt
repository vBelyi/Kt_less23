package com.example.kt_less23

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @Rule
    fun activityRule() = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun when_launch_shows_hint() {
        Espresso.onView(ViewMatchers.withId(R.id.textV))
            .check(ViewAssertions.matches(ViewMatchers.withHint("here will be displayed crypto")))
    }

    @Test
    fun is_textView_displayed() {
        Espresso.onView(ViewMatchers.withId(R.id.textV))
            .check((ViewAssertions.matches(ViewMatchers.isDisplayed())))
    }

    @Test
    fun when_press_button() {
        Espresso.onView(ViewMatchers.withId(R.id.button))
            .perform(ViewActions.click())
    }
}