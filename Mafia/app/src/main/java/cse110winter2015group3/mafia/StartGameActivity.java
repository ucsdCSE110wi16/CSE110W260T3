package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.Random;

public class StartGameActivity extends AppCompatActivity {

    private Firebase mFirebaseRef;
    private Firebase codeRef;
    public String gameCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        Firebase.setAndroidContext(this);
        //mFirebaseRef = new Firebase("https://radiant-torch-4018.firebaseio.com");
        //mFirebaseRef = MainActivity.firebase;
        mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com");
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final int length = alphabet.length();
        StringBuilder code = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++){
            int index = r.nextInt(length);
            code.append(alphabet.charAt(index));
        }
        String output = "Entry Game Code is: " + code;
        gameCode = code.toString();
        // Coderef is a child of the original firebase db. We need to make this global so
        // that it's child, playerRef can access it and make itself the child
        codeRef = mFirebaseRef.child("gameCode");
        codeRef.setValue(code);

        TextView txtView;
        txtView = (TextView)findViewById(R.id.textView4);
        txtView.setText(output);

    }

    public void onButtonClick(View v) {
        startActivity(new Intent(getApplicationContext(), GameScript.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Handle app bar items clicks here. The app bar
        //automatic,ally handles clicks on the Home/Up button, so long
        //as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goEnterGame(View v){

        Player player = new Player();
        //player.setPlayerStatus();
        // store player value into db.
        Firebase playerRef = codeRef.child("Players/player1");
        // what if we do:
        playerRef.push().setValue(player);
        //playerRef.setValue(player);
        // store the current # of players into db. Currently hardcoded at 1?
        Firebase playerCountRef = codeRef.child("playerCount");
        playerCountRef.push().setValue(1);
        startActivity(new Intent(this, EnterGame.class));
    }

}
