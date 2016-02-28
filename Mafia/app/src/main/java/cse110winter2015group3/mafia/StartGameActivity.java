package cse110winter2015group3.mafia;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.Random;


/**
 * Created by aneeshnatarajan on 2/6/16.
 */
public class StartGameActivity extends AppCompatActivity{
    private Firebase mFirebaseRef;

    public String gameCode = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        //initialize Firebase database
        mFirebaseRef = new Firebase("https://radiant-torch-4018.firebaseio.com");
        setContentView(R.layout.activity_start_game);
        final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int length = alphabet.length();
        StringBuilder code = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(length);
            code.append(alphabet.charAt(index));
        }
        String output = "Entry Game Code is: " + code;
        gameCode = gameCode + code;
        Firebase codeRef = mFirebaseRef.child("gameCode");
        codeRef.setValue(gameCode);




        TextView txtView;
        txtView = (TextView)findViewById(R.id.textView4);
        txtView.setText(output);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle app bar item clicks here. The app bar
        // automatic,ally handles clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goEnterGame(View v){
        startActivity(new Intent(getApplicationContext(),EnterGame.class));

    }





};
