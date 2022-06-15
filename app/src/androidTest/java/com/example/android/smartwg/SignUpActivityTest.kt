package com.example.android.smartwg

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SignUpActivityTest {

    @Test
    fun testSignUp() {
        launchActivity<SignUpActivity>().use { scenario ->
            Espresso.onView(ViewMatchers.withId(R.id.etName)).perform(ViewActions.typeText("test12"))
            Espresso.onView(ViewMatchers.withId(R.id.etFirstName)).perform(ViewActions.typeText("test12"))
            Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(ViewActions.typeText("test12"))
            Espresso.onView(ViewMatchers.withId(R.id.etEmail2)).perform(ViewActions.typeText("test12"))
            Espresso.onView(ViewMatchers.withId(R.id.etPasswordSignUp)).perform(
                ViewActions.typeText(
                    "test12"
                )
            )
            Espresso.onView(ViewMatchers.withId(R.id.etPassword2SignUp)).perform(
                ViewActions.typeText(
                    "test12"
                )
            )

            Espresso.closeSoftKeyboard()
            Espresso.onView(ViewMatchers.withId(R.id.cboxTermsOfService)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.cboxPrivacyPolicy)).perform(ViewActions.click())

            scenario.onActivity { activity ->
                Assert.assertEquals(
                    true, activity.validateRegister(
                        activity.etName,
                        activity.etFirstName,
                        activity.etSACode,
                        activity.etEmail,
                        activity.etEmail2,
                        activity.etPasswordSignUp,
                        activity.etPassword2SignUp
                    )
                )

                Assert.assertEquals(
                    true,
                    activity.validateCheckbox(
                        activity.cboxPrivacyPolicy,
                        activity.cboxTermsOfService
                    )
                )

                activity.signUpdb(
                    activity.etName,
                    activity.etFirstName,
                    activity.etSACode,
                    activity.etEmail,
                    activity.etPasswordSignUp
                )
            }
        }
    }
}