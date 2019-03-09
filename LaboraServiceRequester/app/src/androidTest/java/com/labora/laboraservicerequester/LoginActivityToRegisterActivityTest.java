package com.labora.laboraservicerequester;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class LoginActivityToRegisterActivityTest {
    // Rule for test
    @Rule
    public ActivityTestRule<LoginActivity> nActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    // Private variable to initialise register activity
    private LoginActivity nActivity = null;

    // Monitor the register activity
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(RegisterActivity.class.getName(), null, false);

    // Set uo the function when launch starting
    @Before
    public void setUp() throws Exception
    {
        // Get the activity
        nActivity = nActivityTestRule.getActivity();
    }

    // Function for when activity is launching
    @Test
    public void testLaunchOfRegisterButton()
    {
        // Check if it does not return null
        assertNotNull(nActivity.findViewById(R.id.buttonSignUp));

        // Take the view with register button id
        onView(withId(R.id.buttonSignUp)).perform(click());

        // Wait for monitor to be hit and then expires in 50000000 ms
        Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 50000000);

        // Check that the profile activity is not null
        assertNotNull(registerActivity);

        // Finish the opened activity
        registerActivity.finish();
    }

    // Tear down function when launch has ended
    @After
    public void tearDown() throws Exception
    {
        // Set to null and finish
        nActivity = null;
    }
}