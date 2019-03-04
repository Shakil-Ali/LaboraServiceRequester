package com.labora.laboraservicerequester;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class LoginActivityMethodsTest {

    @Rule
    // Need a rule to get the activity
    public ActivityTestRule<LoginActivity> nActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    // Creating and initialising a private variable of type LoginActivity for use later in the tests
    private LoginActivity nActivity = null;

    // Test email address
    private String nEmail = "shakil@goldsmiths.com";
    private String nPassword = "123456";

    @Before
    // setUp method
    public void setUp() throws Exception
    {
        // This gets the main activity
        nActivity = nActivityTestRule.getActivity();
    }

    @Test
    // Testing user email
    public void testUserInputScenario()
    {
        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextEmail)).perform(typeText(nEmail));
        Espresso.onView(withId(R.id.editTextPassword)).perform(typeText(nPassword));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonSignIn)).perform(click());
    }

    // tearDown method
    @After
    public void tearDown() throws Exception
    {
        // Nullify the activity after it has/has not launched
        nActivity = null;
    }


}