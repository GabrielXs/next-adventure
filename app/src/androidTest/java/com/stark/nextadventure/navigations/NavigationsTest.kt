package com.stark.nextadventure.navigations

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.stark.nextadventure.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

abstract class NavigationsTest<T : Fragment> {

    protected suspend fun getDirectionLabel(
        buttonId:Int,
        dispatcher: CoroutineDispatcher,
        fragmentScenario: FragmentScenario<T>
    ): String? {
        return withContext(dispatcher) {
            val navControllerAsync = async {
                TestNavHostController(ApplicationProvider.getApplicationContext()).apply {
                    withContext(Dispatchers.Main) {
                        setGraph(R.navigation.nav_graph)
                    }
                }
            }
            val navController = navControllerAsync.await()

            fragmentScenario.apply {
                onFragment(object : FragmentScenario.FragmentAction<T> {
                    override fun perform(fragment: T) {
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                })
            }

            Espresso.onView(ViewMatchers.withId(buttonId))
                .perform(ViewActions.click())
            navController.currentDestination?.label.toString()
        }
    }
}
