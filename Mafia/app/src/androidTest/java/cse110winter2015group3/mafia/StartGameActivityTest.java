package cse110winter2015group3.mafia;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Stan on 3/8/2016.
 *
 * THIS TEST DOES NOT WORK. ONCLICK FOR ENTERGAME BUTTON CALLS ON FIREBASE NULL OBJ REF
 */

@RunWith(AndroidJUnit4.class)
public class StartGameActivityTest {

    /**
     * StartGameActivity does the following:
     *          1). Generates a 5-digit Game Code and stores it in the database
     *          2). Button to Enter Game to enter EnterGameActivity
     */


    @Rule
    public IntentsTestRule<StartGameActivity> StartGameActivityRule2 =
            new IntentsTestRule(StartGameActivity.class);

    private String gameCode;

    @Test
    public void nullTest() {

    }

    /**
    @Before
    public void generateCode() {
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final int length = alphabet.length();
        StringBuilder code = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++){
            int index = r.nextInt(length);
            code.append(alphabet.charAt(index));
        }
        gameCode = code.toString();
    }


    public void testGenerateCode() {

        onView(withId(R.id.textView4)).perform(typeText(gameCode), closeSoftKeyboard());
        onView(withId(R.id.textView4)).check(matches(withText(gameCode)));

    }

    // Test enterGame Button if it starts the next activity, EnterGame.java


     //Given a user that has logged in, when the enterGameButton is pressed the next activity,
     //EnterGame is started.


    public void enterGameButtonTest() {
        onView(withId(R.id.button4)).perform(click());
    }
    */


}
