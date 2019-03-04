package com.labora.laboraservicerequester;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest {

    // Rule created, which will be required for this test
    @Rule
    public ActivityTestRule<LoginActivity> nActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    // Creating and initialising a private variable of type LoginActivity for use later in the tests
    private LoginActivity nActivity = null;

    // Auto generated set up function
    @Before
    public void setUp() throws Exception
    {
        // This gets the main activity
        nActivity = nActivityTestRule.getActivity();
    }

    // Function to test the launch of the main menu
    @Test
    public void testLaunch()
    {
        // Attempt to launch the app and find the id of one of the components in LoginActivity
        View view = nActivity.findViewById(R.id.buttonSignUp);
        // Check if launch is not null
        assertNotNull(view);
    }

    // Auto generated tear down function
    @After
    public void tearDown() throws Exception
    {
        // Nullify the activity after it has/has not launched
        nActivity = null;
    }

}