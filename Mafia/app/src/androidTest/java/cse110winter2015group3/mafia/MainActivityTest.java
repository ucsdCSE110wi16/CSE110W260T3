package cse110winter2015group3.mafia;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Stan on 3/8/2016.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void testLoginButton() {
        // Login button testing with onView
        //Espresso.onView(ViewMatchers.withId(R.id.login));
    }
}
