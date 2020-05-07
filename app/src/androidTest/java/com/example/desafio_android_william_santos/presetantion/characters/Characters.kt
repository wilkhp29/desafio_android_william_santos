package com.example.desafio_android_william_santos.presetantion.characters


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.desafio_android_william_santos.R
import com.example.desafio_android_william_santos.presentation.characters.CharactersActivity
import com.example.desafio_android_william_santos.presentation.characters.CharactersAdapter

import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class Characters {
    private lateinit var stringToBetyped: String


    @get:Rule
    var activityRule: ActivityTestRule<CharactersActivity>
            = ActivityTestRule(CharactersActivity::class.java)

    @Test fun verifyTitle(){
        onView(withId(R.id.toolbarMain)).check(matches(isDisplayed()));
        onView(withText(R.string.heros_title)).check(matches(withParent(withId(R.id.toolbarMain))));
    }

    @Test fun verifyData(){
        onView(withId(R.id.recyclerCharacters)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5,
            click()))
    }


}