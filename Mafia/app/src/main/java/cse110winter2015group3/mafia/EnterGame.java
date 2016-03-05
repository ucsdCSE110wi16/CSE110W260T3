package cse110winter2015group3.mafia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EnterGame extends AppCompatActivity {

    // initialize the fireBase
    private Firebase mFirebaseRef;
    public int playerCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_game);
        Firebase.setAndroidContext(this);
        //initialize FireBase database for player count
        mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com");
        Firebase playerCountRef = mFirebaseRef.child("playerCount");
        playerCountRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // ERROR HERE :
                // Attempt to invoke virtual method 'java.lang.String java.lang.Object.toString()'
                // on a null object reference

                playerCount = Integer.parseInt(dataSnapshot.getValue().toString());
                // If the number of player reaches the minimal baseline of 4 players, then
                // we allow the button to be pressed
                if (playerCount >= 4) {
                    Button enterButton = (Button) findViewById(R.id.ready_to_start);
                    enterButton.setClickable(true);
                } else {
                    Button enterButton = (Button) findViewById(R.id.ready_to_start);
                    enterButton.setClickable(false);
                    Toast.makeText(getApplicationContext(),
                            "Need " + (4-playerCount) + " moreplayers!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(),
                        "Error in changing data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
