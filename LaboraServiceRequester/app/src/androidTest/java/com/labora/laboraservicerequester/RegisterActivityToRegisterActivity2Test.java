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

public class RegisterActivityToRegisterActivity2Test {

    // Rule for test
    @Rule
    public ActivityTestRule<RegisterActivity> nActivityTestRule = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);

    // Private variable to initialise register activity
    private RegisterActivity nActivity = null;

    // Monitor the register 2 activity
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(RegisterActivity2.class.getName(), null, false);

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
        assertNotNull(nActivity.findViewById(R.id.buttonRegister));

        // Take the view with register button id
        onView(withId(R.id.buttonRegister)).perform(click());

        // Wait for monitor to be hit and then expires in 50000000 ms
        Activity registerActivity2 = getInstrumentation().waitForMonitorWithTimeout(monitor, 50000000);

        // Check that the profile activity is not null
        assertNotNull(registerActivity2);

        // Finish the opened activity
        registerActivity2.finish();
    }

    // Tear down function when launch has ended
    @After
    public void tearDown() throws Exception
    {
        // Set to null and finish
        nActivity = null;
    }


}