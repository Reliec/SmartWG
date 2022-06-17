package com.example.android.smartwg

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import kotlinx.android.synthetic.main.activity_settings.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SettingsTest {
    @Test
    fun testSettings() {
        launchActivity<SignInActivity>().use { scenario ->
            Espresso.onView(ViewMatchers.withId(R.id.etEmail))
                .perform(ViewActions.typeText("test6"))
            Espresso.onView(ViewMatchers.withId(R.id.etPasswordLogin))
                .perform(ViewActions.typeText("test6"))
            scenario.onActivity { activity ->
                activity.loginValidate()
            }
            Thread.sleep(400)
            Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
    }
        Thread.sleep(400)
        launchActivity<SettingsActivity>().use { scenario ->
            Espresso.onView(ViewMatchers.withId(R.id.edWGBS))
                .perform(ViewActions.typeText("Tested WGB Update"))
            Thread.sleep(1000)
            scenario.onActivity { activity ->
                activity.updateSettings(
                    activity.edSharedAppartmentCode.text.toString().toInt(),
                    activity.edUserEmail.text.toString(),
                    activity.edPassword.text.toString(),
                    activity.edWGBS.text.toString(),
                    activity.edWGTitle.text.toString()
                )
            }
            Thread.sleep(400)
            Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        }
    }
}