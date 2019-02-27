package com.labora.laboraservicerequester;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServicesTest {

    // Rule created, which will be required for this test
    @Rule
    public ActivityTestRule<Services> nActivityTestRule = new ActivityTestRule<Services>(Services.class);

    // Creating and initialising a private variable of type Services for use later in the tests
    private Services nActivity = null;

    // Auto generated set up function
    @Before
    public void setUp() throws Exception
    {
        // This gets the services page
        nActivity = nActivityTestRule.getActivity();
    }

    // Function to test the launch of the services page
    @Test
    public void testLaunch()
    {
        // Attempt to launch the app and find the id of one of the components in Services page
        View view = nActivity.findViewById(R.id.request);
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