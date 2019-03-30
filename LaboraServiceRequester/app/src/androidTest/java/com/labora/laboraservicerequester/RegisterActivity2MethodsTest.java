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

public class RegisterActivity2MethodsTest {

    @Rule
    // Need a rule to get the activity
    public ActivityTestRule<RegisterActivity2> nActivityTestRule = new ActivityTestRule<RegisterActivity2>(RegisterActivity2.class);

    // Creating and initialising a private variable of type RegisterActivity2 for use later in the tests
    private RegisterActivity2 nActivity = null;

    // Variables for inout fields
    private String nName = "test name";
    private String nPhone = "123456789";

    @Before
    // setUp method
    public void setUp() throws Exception
    {
        // This gets the main activity
        nActivity = nActivityTestRule.getActivity();
    }

    @Test
    // Testing name field with empty phone
    // Activity Launch Test
    public void testUsernameEmptyPassword()
    {
        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextFullName)).perform(typeText(nName));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextPhoneNumber)).perform(typeText(""));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonRegister)).perform(click());
    }

    @Test
    // Testing phone field with empty name
    public void testPasswordEmptyUsername()
    {
        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextFullName)).perform(typeText(""));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextPhoneNumber)).perform(typeText(nPhone));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonRegister)).perform(click());
    }

    @Test
    // Testing user name and phone number
    public void testUserInputScenario()
    {
        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextFullName)).perform(typeText(nName));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextPhoneNumber)).perform(typeText(nPhone));
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