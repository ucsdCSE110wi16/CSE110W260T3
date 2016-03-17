package cse110winter2015group3.mafia;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Stan on 3/8/2016.
 *
 * Given a user that has logged in, when a button is pressed than the next activity should be
 * started
 */

@RunWith(AndroidJUnit4.class)
public class EnterGameTest {

    /**
     * EnterGame has
     *      1). a single button to allow the user to Enter the game called "Ready To Start"
     *      2). a button to bring the user to LobbyChat activity
     */

    @Rule
    public IntentsTestRule<EnterGame> EnterGameActivityRule =
            new IntentsTestRule(EnterGame.class);


    // Test if the enter Game button enters the next activity, AssignRoles.java
    @Test
    public void testEnterGameButton() {
        // Is not clickable because we need to manually make it clickable after playerCount reaches
        // baseline
        //onView(withId(R.id.ready_to_start)).check(matches(isClickable()));
        onView(withId(R.id.ready_to_start)).perform(click());
    }

    @Test
    public void testLobbyChat() {
        onView(withId(R.id.lobbyChat)).check(matches(isClickable()));
        onView(withId(R.id.lobbyChat)).perform(click());
    }
}
