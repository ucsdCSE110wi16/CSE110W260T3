package cse110winter2015group3.mafia;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.firebase.client.Firebase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by Stan on 3/8/2016.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StartGameActivityTest {

    /**
     * StartGameActivity does the following:
     *          1). Generates a 5-digit Game Code and stores it in the database
     *          2). Button to Enter Game to enter EnterGameActivity
     */
    @Rule
    public ActivityTestRule<StartGameActivity> StartGameActivityRule =
            new ActivityTestRule(StartGameActivity.class);

    @Rule
    public IntentsTestRule<StartGameActivity> StartGameActivityRule2 =
            new IntentsTestRule(StartGameActivity.class);

    @Test
    public void testGenerateCode() {

        Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com");
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final int length = alphabet.length();
        StringBuilder code = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++){
            int index = r.nextInt(length);
            code.append(alphabet.charAt(index));
        }

        // THE KEY IS GAMECODE
        String output = "Entry Game Code is: " + code;
        String gameCode = code.toString();
        Firebase codeRef = mFirebaseRef.child("Game/gameCode");
        codeRef.setValue(code);
        onView(withId(R.id.textView4)).check(matches(withText("gameCode")));

    }

    // Test enterGame Button if it starts the next activity, EnterGame.java

    /**
     * Given a user that has logged in, when the enterGameButton is pressed the next activity,
     * EnterGame is started.
     */
    @Test
    public void enterGameButtonTest() {
        onView(withId(R.id.button4)).perform(click());
        intended(hasComponent(EnterGame.class.getName()));
    }


}
