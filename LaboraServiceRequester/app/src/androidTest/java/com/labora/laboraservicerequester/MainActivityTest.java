package com.labora.laboraservicerequester;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import static org.junit.Assert.*;

public class MainActivityTest {

    // Rule created, which will be required for this test
    @Rule
    public ActivityTestRule<MainActivity> nActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    // Creating and initialising a private variable of type MainActivity for use later in the tests
    private MainActivity nActivity = null;

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
        // Attempt to launch the app and find the id of one of the components in MainActivity
        View view = nActivity.findViewById(R.id.textView);
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