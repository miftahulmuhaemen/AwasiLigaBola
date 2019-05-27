package com.example.ligasubmission


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.ligasubmission.mainMenu.MainActivity
import com.example.ligasubmission.util.EspressoIdlingResource
import com.example.ligasubmission.util.Util
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SearchViewTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun mainActivityTest() {

        onView(withText("English Premier League")).perform(click())
        onView(withText(Util.LIST_LEAGUE_PREV_MATCH)).perform(click())

        onView(
            allOf(
                    withClassName(`is`("android.support.v7.widget.AppCompatImageView")), withContentDescription("Search"),
                isDisplayed()
            )
        ).perform(click())

        onView(
            allOf(
                IsInstanceOf.instanceOf(android.widget.EditText::class.java), withText(""),
                isDisplayed()
            )
        ).perform(replaceText("Fulham"))

        onView(
            allOf(
                IsInstanceOf.instanceOf(android.widget.EditText::class.java), withText("Fulham"),
                isDisplayed()
            )
        ).perform(clearText())

        onView(
            allOf(
                IsInstanceOf.instanceOf(android.widget.EditText::class.java), withText(""),
                isDisplayed()
            )
        ).perform(clearText(),replaceText("Man"))

        onView(
            allOf(
                withId(R.id.recylerVieww),
                isCompletelyDisplayed()
            )
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1,
            click()
        ))

    }

}
