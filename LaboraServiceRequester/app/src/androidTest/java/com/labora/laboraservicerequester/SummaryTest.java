package com.labora.laboraservicerequester;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SummaryTest {

    // Rule created, which will be required for this test
    @Rule
    public ActivityTestRule<Summary> nActivityTestRule = new ActivityTestRule<Summary>(Summary.class);

    // Creating and initialising a private variable of type Summary for use later in the tests
    private Summary nActivity = null;

    // Auto generated set up function
    @Before
    public void setUp() throws Exception
    {
        // This gets the summary page
        nActivity = nActivityTestRule.getActivity();
    }

    // Function to test the launch of the summary page
    @Test
    public void testLaunch()
    {
        // Attempt to launch the app and find the id of one of the components in Summary page
        View view = nActivity.findViewById(R.id.summary);
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