package cse110winter2015group3.mafia;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Stan on 3/8/2016.
 */

@RunWith(AndroidJUnit4.class)
public class JoinGameActivityTest {

    /**
     * JoinGameActivity does the following:
     *      1). Prompts User to enter the gameCode value generated when another user pressed
     *          startGame.
     */

    @Rule
    public IntentsTestRule<JoinGameActivity> JoinGameActivityRule =
            new IntentsTestRule(JoinGameActivity.class);


    // Prompt user to "sumbit" game code and enters Enter Game class
    @Test
    public void testEnterGameButton() {
        onView(withId(R.id.button3)).perform(click());
    }
}
