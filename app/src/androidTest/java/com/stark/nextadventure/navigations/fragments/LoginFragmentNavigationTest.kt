package com.stark.nextadventure.navigations.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.stark.nextadventure.R
import com.stark.nextadventure.navigations.NavigationsTest
import com.stark.nextadventure.ui.fragment.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentNavigationTest : NavigationsTest<Login>() {

    @Test
    fun Should_Navigate_To_Signup_When_Call_Navigate() {
        runBlocking {
            val directionLabel =
                getDirectionLabel(R.id.button_signup, Dispatchers.IO, launchFragmentInContainer())
            Assert.assertEquals("fragment_sign_up", directionLabel)
        }
    }

    @Test
    fun Should_Navigate_To_Dashboard_When_Call_Navigate() {
        runBlocking {
            val directionLabel =
                getDirectionLabel(R.id.button, Dispatchers.IO, launchFragmentInContainer())
            Assert.assertEquals("fragment_dashboard", directionLabel)
        }
    }

}