package cse110winter2015group3.mafia;

//import com.parse.ParseObject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {

            Intent intent = new Intent(MainActivity.this, SignInSignUpActivity.class);
            startActivity(intent);
            finish();
        } else {
            ParseUser currentUser = ParseUser.getCurrentUser();

            if (currentUser != null) {

                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            } else {

                Intent intent = new Intent(MainActivity.this, SignInSignUpActivity.class);
                startActivity(intent);
                finish();
            }

        }


        //setContentView(R.layout.activity_main);


        //setContentView(R.layout.activity_main);
        // [Optional] Power your app with Local Datastore. For more info, go to
        // https://parse.com/docs/android/guide#local-datastore
        /**

        Parse.enableLocalDatastore(this);

        Parse.initialize(this);
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
         */
    }

    //make a onClickButton for
    // i) Start Game - pressing button should generate an access code to a server for the user
    //                 It should automatically copy that code and then prompt the user to send
    //                 the code to nearby players/friends. Once at least 3 other players have
    //                 joined the lobby with the user, the user can start the game.
    // ii) Join Game - The user is already given an access code via text or some other method. He/
    //                 she should be prompted to enter that code once they click the "Join Game"
    //                 button. After entering the code, given that it is a correct code that is
    //                 currently active, the player will join that game servers lobby and wait until
    //                 the owner of the game starts the game.

    public void onStartGameButtonClick() {
        startActivity(new Intent(getApplicationContext(), StartGameActivity.class));
    }

    public void onJoinGameButtonClick(View v) {
        startActivity(new Intent(getApplicationContext(), JoinGameActivity.class));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
