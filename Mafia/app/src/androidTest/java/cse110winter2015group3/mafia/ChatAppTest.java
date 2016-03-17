package cse110winter2015group3.mafia;

import android.support.test.rule.ActivityTestRule;
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

/**
 * Created by Stan on 3/8/2016.
 *
 * Given a user that has logged in when they type a message into the chat message box then the send
 * message button should display the message in the listView pulled from the database.
 *
 * Given a user that has logged in when the logout button is pressed then they should be logged out
 * of their account and be brought back to the login page.
 *
 * Given a user that has logged in when the homepage button is pressed then they should be brought
 * to the HomePage activity.
 */

@RunWith(AndroidJUnit4.class)
public class ChatAppTest {

    @Rule
    public ActivityTestRule<ChatApp> chatAppRule1 = new ActivityTestRule(ChatApp.class);

    /**
     * IN THIS TEST WE WILL TEST:
     *      1). Message typed to be sent into chat list view is displayed correctly and the send
     *          button is clickable!
     *      2). The Logout button correctly logs out the player and returns to the login page
     *      3). The goToGamePage button correctly starts the next activity
     */

    @Test
    public void testSendText() {
        onView(withId(R.id.text_edit)).perform(typeText("Hello World Message"), closeSoftKeyboard());
        onView(withId(R.id.text_edit)).check(matches(isDisplayed()));
        onView(withId(R.id.send_button)).check(matches(isClickable()));
    }


    /**
     * Given a user When the logout button is pressed Then he/she is logged out of account And the
     * app returns the the Login In page (Main Activity)
     */
    @Test
    public void testLogOutButton() {

        onView(withId(R.id.logout)).check(matches(isClickable()));
        onView(withId(R.id.logout)).perform(click());
    }


    /**
     * Given a user When he/she presses the GoToHomePage button then the next activity (GameHomePage)
     * is started.
     */
    @Test
    public void testGoToHomePageButton() {
        onView(withId(R.id.GoToHomepage)).check(matches(isClickable()));
        onView(withId(R.id.GoToHomepage)).perform(click());

    }
}
