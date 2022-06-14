package com.example.android.smartwg

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import android.app.Activity
import android.content.ContextWrapper
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class SignInActivityTest {
    @get:Rule var activityScenarioRule = activityScenarioRule<SignInActivity>()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.android.smartwg", appContext.packageName)
    }

    @Test fun testSignIn(){
        launchActivity<SignInActivity>().use{ scenario ->
            Espresso.onView(ViewMatchers.withId(R.id.bSignIn)).perform(click())
            Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(typeText("test"))
            Espresso.onView(ViewMatchers.withId(R.id.etPasswordLogin)).perform(typeText("test"))

            val originalActivityState = scenario.state

            scenario.onActivity { activity ->
                activity.loginValidate()
                activity.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_BACK))
            }
        }
    }

    @Test fun testSignUp(){

    }
}