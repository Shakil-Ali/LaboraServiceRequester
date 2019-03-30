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

public class RegisterActivity2ToProfileActivityTest {

    // Rule for test
    @Rule
    public ActivityTestRule<RegisterActivity2> nActivityTestRule = new ActivityTestRule<RegisterActivity2>(RegisterActivity2.class);

    // Private variable to initialise profile activity
    private RegisterActivity2 nActivity = null;

    // Monitor the profile activity
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(ProfileActivity.class.getName(), null, false);

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
        Activity profileActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 50000000);

        // Check that the profile activity is not null
        assertNotNull(profileActivity);

        // Finish the opened activity
        profileActivity.finish();
    }

    // Tear down function when launch has ended
    @After
    public void tearDown() throws Exception
    {
        // Set to null and finish
        nActivity = null;
    }


}