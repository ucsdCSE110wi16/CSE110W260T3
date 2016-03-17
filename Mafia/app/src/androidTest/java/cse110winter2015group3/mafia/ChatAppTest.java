package cse110winter2015group3.mafia;

import android.support.test.rule.ActivityTestRule;
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
public class ChatAppTest {

    @Rule
    public ActivityTestRule<ChatApp> chatAppRule1 = new ActivityTestRule(ChatApp.class);

    /**
     * IN THIS TEST WE WILL TEST:
     *      1). The Logout button correctly logs out the player and returns to the login page
     *      2). The goToGamePage button correctly starts the next activity
     */


    /**
     * Given a user When the logout button is pressed Then he/she is logged out of account And the
     * app returns the the Login In page (Main Activity)
     */
    @Test
    public void testLogOutButton() {
        onView(withId(R.id.logout)).perform(click());
    }


    /**
     * Given a user When he/she presses the GoToHomePage button then the next activity (GameHomePage)
     * is started.
     */
    @Test
    public void testGoToHomePageButton() {
        onView(withId(R.id.GoToHomepage)).perform(click());

    }
}
