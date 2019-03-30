package com.labora.laboraservicerequester;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static java.util.regex.Pattern.matches;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

public class ServicesMethodsTest {

    @Rule
    // Need a rule to get the activity
    public ActivityTestRule<Services> nActivityTestRule = new ActivityTestRule<Services>(Services.class);

    // Creating and initialising a private variable of type LoginActivity for use later in the tests
    private Services nActivity = null;

    // Test email address
    private String nPostCode = "SW6";
    private String nJobDesc = "Test Job Description field";
    private String nKeyW = "Test Key Word Field";

    @Before
    // setUp method
    public void setUp() throws Exception
    {
        // This gets the main activity
        nActivity = nActivityTestRule.getActivity();
    }

    @Test
    public void testEmptyRequest()
    {
        // Input some text in edit text
        onView(withId(R.id.postC)).perform(typeText(""));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.job)).perform(typeText(""));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.keyW)).perform(typeText(""));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();


        // Perform button click
        onView(withId(R.id.request)).perform(click());
    }


    @Test
    // Testing username field with empty password
    // Activity Launch Test
    public void testRequest()
    {
        onView(withId(R.id.spinner1)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Artist"))).perform(click());

        // Input some text in edit text
        onView(withId(R.id.postC)).perform(typeText(nPostCode));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.job)).perform(typeText(nJobDesc));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.keyW)).perform(typeText(nKeyW));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();


        // Perform button click
        // Commented out as user ID will be required
        //onView(withId(R.id.request)).perform(click());
    }


    // tearDown method
    @After
    public void tearDown() throws Exception
    {
        // Nullify the activity after it has/has not launched
        nActivity = null;
    }


}