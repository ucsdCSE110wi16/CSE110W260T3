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
public class EnterGameTest {

    /**
     * EnterGame has
     *      1). a single button to allow the user to Enter the game called "Ready To Start"
     *      2). a list of all of the current players currently playing/available
     */

    @Rule
    public ActivityTestRule<JoinGameActivity> JoinGameActivityRule =
            new ActivityTestRule(JoinGameActivity.class);

    @Rule
    public IntentsTestRule<JoinGameActivity> JoinGameActivityRule2 =
            new IntentsTestRule<>(JoinGameActivity.class);

    // Test if the enter Game button enters the next activity, AssignRoles.java
    @Test
    public void testEnterGameButton() {
        onView(withId(R.id.ready_to_start)).perform(click());
        intended(hasComponent(AssignRoles.class.getName()));
    }

    @Test
    public void testPlayerList() {
        // This will have to be implemented later
    }
}
