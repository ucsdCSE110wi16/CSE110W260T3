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
    //private Firebase codeRef;
    public String gameCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        Firebase.setAndroidContext(this);
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
        Firebase codeRef = mFirebaseRef.child("Game/gameCode");
        codeRef.setValue(code);

        TextView txtView;
        txtView = (TextView)findViewById(R.id.textView4);
        txtView.setText(output);

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
        String uID = mFirebaseRef.getAuth().getProviderData().get("email").toString();
        String [] strArray = uID.split("@");
        String userName = strArray[0];
        Player player = new Player();
        player.setPlayerStatus();
        Firebase playerRef = mFirebaseRef.child("Game/player/"+userName+"/PlayerObject");
        playerRef.setValue(player);
        Firebase playerCountRef = mFirebaseRef.child("Game/playerCount");
        playerCountRef.setValue(1);
        startActivity(new Intent(this, EnterGame.class));
    }

}
