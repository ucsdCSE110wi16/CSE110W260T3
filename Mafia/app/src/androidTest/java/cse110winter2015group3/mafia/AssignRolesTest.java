package cse110winter2015group3.mafia;

import android.os.Handler;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

/**
 * Created by Stan on 3/8/2016.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AssignRolesTest {

    /**
     * Assign Roles:
     *          1). Assigns roles to users and stores that data into the DB
     *          2). Then it pauses the page for x seconds before it starts the next intent
     */

    @Rule
    public ActivityTestRule<AssignRoles> AssignRoleRule1 = new ActivityTestRule(AssignRoles.class);

    @Rule
    public IntentsTestRule<AssignRoles> AssignRoleRule2 = new IntentsTestRule<>(AssignRoles.class);

    @Test
    public void testAssignRolesAutoIntent() {
        int delay = 1000;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, delay);

        intended(hasComponent(RevealRoles.class.getName()));
    }
}
