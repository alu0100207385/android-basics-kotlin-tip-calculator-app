package com.example.tiptime

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    private fun add_cost_of_service() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.5"))
        Espresso.closeSoftKeyboard()
    }

    @Test
    fun calculate_default_tip_round_up() {
        add_cost_of_service()
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(CoreMatchers.containsString("11.00"))))
    }

    @Test
    fun calculate_18_percent_tip_round_up() {
        add_cost_of_service()
        onView(withId(R.id.option_eighteen_percent)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(CoreMatchers.containsString("10.00"))))
    }

    @Test
    fun calculate_15_percent_tip_round_up() {
        add_cost_of_service()
        onView(withId(R.id.option_fifteen_percent)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(CoreMatchers.containsString("8.00"))))
    }

    @Test
    fun calculate_default_tip() {
        add_cost_of_service()
        onView(withId(R.id.round_up_switch)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(CoreMatchers.containsString("10.10"))))
    }

    @Test
    fun calculate_18_percent_tip() {
        add_cost_of_service()
        onView(withId(R.id.option_eighteen_percent)).perform(click())
        onView(withId(R.id.round_up_switch)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(CoreMatchers.containsString("9.09"))))
    }

    @Test
    fun calculate_15_percent_tip() {
        add_cost_of_service()
        onView(withId(R.id.option_fifteen_percent)).perform(click())
        onView(withId(R.id.round_up_switch)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(CoreMatchers.containsString("7.57"))))
    }
}