package com.labora.laboraservicerequester;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProfileActivityTest {

    // Rule created, which will be required for this test
    @Rule
    public ActivityTestRule<ProfileActivity> nActivityTestRule = new ActivityTestRule<ProfileActivity>(ProfileActivity.class);

    // Creating and initialising a private variable of type ProfileActivity for use later in the tests
    private ProfileActivity nActivity = null;

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
        // Attempt to launch the app and find the id of one of the components in ProfileActivity
        View view = nActivity.findViewById(R.id.textViewUserEmail);
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