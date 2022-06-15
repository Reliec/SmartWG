package com.example.android.smartwg

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HighscoreTest {
    @Test
    fun testHighscore() {
        launchActivity<SignInActivity>().use { scenario ->
            Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(ViewActions.typeText("test"))
            Espresso.onView(ViewMatchers.withId(R.id.etPasswordLogin)).perform(ViewActions.typeText("test"))

            scenario.onActivity { activity ->
                activity.loginValidate() }

        }
        Espresso.onView(ViewMatchers.withId(R.id.bHighscoreList)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.bKitchen)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.bCreateNewKitchen)).perform(click())
    }
}