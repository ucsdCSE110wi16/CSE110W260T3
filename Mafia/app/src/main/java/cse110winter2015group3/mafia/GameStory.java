package cse110winter2015group3.mafia;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by aneeshnatarajan on 3/5/16.
 */
public class GameStory extends Activity {
    private static TextView story;
    static final int READ_BLOCK_SIZE = 100;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_story);
        try {
            story = (TextView) findViewById(R.id.gameStory);
            String gameStory = readStoryFile();
            story.setText(gameStory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendToNightPhase();
    }
    public String readStoryFile() throws IOException {
        InputStream inputStream;
        inputStream = getAssets().open("gameStory.txt");

        byte [] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(buffer);
        inputStream.close();
        outputStream.close();

        return outputStream.toString();

    }
    public void sendToNightPhase(){
        Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/Game");
        String uID = mFirebaseRef.getAuth().getProviderData().get("email").toString();
        String [] strArray = uID.split("@");
        String userName = strArray[0];
        String queryString = "/player/" + userName + "/Role";
        Firebase roleRef = mFirebaseRef.child(queryString);
        roleRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String role = dataSnapshot.getValue().toString();
                Toast.makeText(getApplicationContext(),"role is: " + role, Toast.LENGTH_LONG).show();
                if(role.equals("Mafia")){
                    startActivity(new Intent(getApplicationContext(),MafiaNightPhase.class));
                }
                else if(role.equals("Cop")){
                    startActivity(new Intent(getApplicationContext(),CopNightPhase.class));

                }
                else if(role.equals("Moderator")){
                    startActivity(new Intent(getApplicationContext(),ModeratorNightPhase.class));
                }
                else if(role.equals("Doctor")){
                    startActivity(new Intent(getApplicationContext(),DoctorNightPhase.class));
                }
                else if(role.equals("Civilian")){
                    startActivity(new Intent(getApplicationContext(),CivilianNightPhase.class));
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
