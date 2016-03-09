package cse110winter2015group3.mafia;

import android.support.test.espresso.intent.rule.IntentsTestRule;
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
public class GameHomePageTest {


    /** THE GAMEPAGE HOMEPAGE ONLY HAS TWO BUTTONS:
     *          1). Start Game Button: Press StartGameButton and enter the StartGameActivity
     *          2). Join Game Button: Press JoinGameButton and enter the JoinGameActivity
     */

    @Rule
    public IntentsTestRule<GameHomePage> GameHomePageRule = new IntentsTestRule(GameHomePage.class);

    // Test StartGameButton

    /**
     * Given a user that has logged in, when the StartGameButton is pressed the next activity,
     * StartGameActivity, is started.
     */
    @Test
    public void testStartGameButton() {
        onView(withId(R.id.button)).perform(click());
        intended(hasComponent(StartGameActivity.class.getName()));
    }

    // Test JoinGameButton

    /**
     * Given a user that has logged in, when the StartGameButton is pressed the next activity,
     * JoinGameActivity, is started.
     */
    @Test
    public void testJoinGameButton() {
        onView(withId(R.id.button2)).perform(click());
        intended(hasComponent(JoinGameActivity.class.getName()));
    }
}
