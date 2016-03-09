package cse110winter2015group3.mafia;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

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
public class ChatAppTest {

    @Rule
    public ActivityTestRule<ChatApp> chatAppRule1 = new ActivityTestRule(ChatApp.class);

    @Rule
    public IntentsTestRule<ChatApp> chatAppRule2 = new IntentsTestRule(ChatApp.class);

    /**
     * IN THIS TEST WE WILL TEST:
     *      1). THAT THE CORRECT MESSAGE IS SENT AND STORED IN THE DB
     *      2). The Logout button correctly logs out the player and returns to the login page
     *      3). The goToGamePage button correctly starts the next activity
     */

    // WORK NEEDS TO BE DONE ON CHECKMESSAGES. HOW DO WE PULL BACK THE MESSAGE LOG?

    @Test
    public void checkMessages() {
        onView(withId(R.id.text_edit));
        // We don't need to login, we can set the username to a hardcoded value
        Firebase firebase = new Firebase("https://shining-inferno-5525.firebaseio.com/Game/Chat");
        String text = "damn Daniel";
        ChatMessage chatMessage = new ChatMessage("user", text);
        firebase.push().setValue(chatMessage);
        // Message has been pushed to DB. Now we need to pull back the chatMessage data
    }

    /**
     * Given a user When the logout button is pressed Then he/she is logged out of account And the
     * app returns the the Login In page (Main Activity)
     */
    @Test
    public void testLogoutButton() {
        onView(withId(R.id.logout)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));

        /**
         * The following is the code used to logout a player:
         *          1). First we need to login,
         *          2). Then we can log out
         */
        final Firebase firebase = new Firebase("https://shining-inferno-5525.firebaseio.com");
        firebase.authWithPassword("stan_deng@yahoo.com", "1111", new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("Successfully logged in!");
                // Now we log out
                firebase.unauth();
                System.out.println("Successfully logged out!");
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                switch (firebaseError.getCode()) {
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        System.out.println("ERROR: USER DOES NOT EXIST!");
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        System.out.println("ERROR: INVALID PASSWORD!");
                        break;
                    default:
                        System.out.println("ERROR: DEFAULT ERROR!");
                        break;
                }
                System.out.println("Successfully logged in!");
            }
        });
    }

    /**
     * Given a user When he/she presses the GoToHomePage button then the next activity (GameHomePage)
     * is started.
     */
    @Test
    public void testGoToHomePageButton() {
        onView(withId(R.id.GoToHomepage)).perform(click());
        intended(hasComponent(GameHomePage.class.getName()));
    }
}
