package cse110winter2015group3.mafia;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by Stan on 3/8/2016.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule1 = new ActivityTestRule(MainActivity.class);


    @Rule
    public IntentsTestRule<MainActivity> mainActivityRule2 = new IntentsTestRule(MainActivity.class);

    @Test
    public void testLoginButton() {
        // Login button testing with onView
        onView(withId(R.id.login)).perform(click());
        intended(hasComponent(ChatApp.class.getName()));
    }

    // ???PRETEND??? TO BE A USER AND GO THROUGH THE MOTIONS OF A FEATURE, LOGIC SHOULD HAVE ALREADY BEEN
    // IMPLEMENTED IN YOUR ACTIVITY FILE
    @Test
    public void testSignupButton() {
        onView(withId(R.id.signup)).perform(click());
        // THE SIGNUP BUTTON CREATES A USER ACCOUNT IN DB. WE NEED TO CHECK IF THE USER IS CREATED
        // IT DOESN'T FUCKING GO TO A NEW INTENT.
        intended(hasComponent(MainActivity.class.getName()));

    }
}
