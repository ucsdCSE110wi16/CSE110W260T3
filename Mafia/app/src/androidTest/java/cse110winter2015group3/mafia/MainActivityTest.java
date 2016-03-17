package cse110winter2015group3.mafia;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by Stan on 3/8/2016.
 *
 * Given a user when a valid email address and password are entered, they should be able to
 * log in and sign up their accounts
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private String validEmail;
    private String validPw;

    @Rule
    public IntentsTestRule<MainActivity> mainActivityRule1 = new IntentsTestRule(MainActivity.class);
    // Gives us a choice to delay the starting of the activity

    @Before
    public void generateValid() {
        validEmail = "a@z.com";
        validPw = "a";
    }


    /**
     * Given a user When he fills in the email and password field And clicks log in Then he is
     * show a success toast And a new activity is started.
     */
    @Test
    public void testLoginButton() {
        // Login button testing with onView
        onView(withId(R.id.emailAddress)).perform(typeText(validEmail), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(validPw), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
    }

    /**
     * Given a user When he fills in the email and password field And clicks sign up Then he is
     * shown a success toast.
     */
    @Test
    public void testSignupButton() {
        onView(withId(R.id.emailAddress)).perform(typeText(validEmail), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(validPw), closeSoftKeyboard());
        onView(withId(R.id.signup)).perform(click());
    }
}
