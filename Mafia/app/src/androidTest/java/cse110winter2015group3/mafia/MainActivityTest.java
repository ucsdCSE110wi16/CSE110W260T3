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

    /**
     * Given a user When he fills in the email and password field And clicks log in Then he is
     * show a success toast And a new activity is started.
     *
     *

     */
    @Test
    public void testLoginButton() {
        // Login button testing with onView
        onView(withId(R.id.login)).perform(click());
        intended(hasComponent(ChatApp.class.getName()));
    }

    /**
     * Given a user When he fills in the email and password field And clicks sign up Then he is
     * shown a success toast.
     */
    @Test
    public void testSignupButton() {
        onView(withId(R.id.signup)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));

    }
}
