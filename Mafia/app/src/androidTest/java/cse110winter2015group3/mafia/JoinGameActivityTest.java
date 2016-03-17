package cse110winter2015group3.mafia;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

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

    String gameCode = "WXYZ";

    @Rule
    public IntentsTestRule<JoinGameActivity> JoinGameActivityRule =
            new IntentsTestRule(JoinGameActivity.class);


    // Prompt user to "submit" game code and enters Enter Game class
    @Test
    public void testEnterGameButton() {
        onView(withId(R.id.userInput)).perform(typeText(gameCode),closeSoftKeyboard());
        onView(withId(R.id.userInput)).check(matches(isDisplayed()));

        onView(withId(R.id.button3)).check(matches(isClickable()));
        onView(withId(R.id.button3)).perform(click());
        // Does not match the given game code, so user will be prompted with the Try Again message
        onView(withId(R.id.button3)).check(matches(withText("Try Again")));
    }
}
