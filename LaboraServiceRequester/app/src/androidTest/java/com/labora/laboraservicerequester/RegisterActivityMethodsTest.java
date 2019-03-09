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

public class RegisterActivityMethodsTest {

    @Rule
    // Need a rule to get the activity
    public ActivityTestRule<RegisterActivity> nActivityTestRule = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);

    // Creating and initialising a private variable of type LoginActivity for use later in the tests
    private RegisterActivity nActivity = null;

    // Test email address
    private String nEmail = "registerTest@goldsmiths.com";
    private String nPassword = "123456";

    @Before
    // setUp method
    public void setUp() throws Exception
    {
        // This gets the main activity
        nActivity = nActivityTestRule.getActivity();
    }

    @Test
    // Testing password field with empty username
    public void testPasswordEmptyUsername()
    {
        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextEmail)).perform(typeText(""));
        Espresso.onView(withId(R.id.editTextPassword)).perform(typeText(nPassword));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonRegister)).perform(click());
    }

    @Test
    // Testing username field with empty password
    // Activity Launch Test
    public void testUsernameEmptyPassword()
    {
        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextEmail)).perform(typeText(nEmail));
        Espresso.onView(withId(R.id.editTextPassword)).perform(typeText(""));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonRegister)).perform(click());
    }

    @Test
    // Testing user email and password login
    public void testUserInputScenario()
    {
        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextEmail)).perform(typeText(nEmail));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextPassword)).perform(typeText(nPassword));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonRegister)).perform(click());
    }

    // tearDown method
    @After
    public void tearDown() throws Exception
    {
        // Nullify the activity after it has/has not launched
        nActivity = null;
    }


}