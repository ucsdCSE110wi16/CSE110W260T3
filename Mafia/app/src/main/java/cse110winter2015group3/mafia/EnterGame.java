package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class EnterGame extends AppCompatActivity {

    public int playerCount;
    Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com");
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_game);
        Firebase playerCountRef = mFirebaseRef.child("Game/playerCount");
        playerCountRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playerCount = Integer.parseInt(dataSnapshot.getValue().toString());
                if (playerCount >= 2) {
                    Button button1 = (Button) findViewById(R.id.ready_to_start);
                    button1.setClickable(true);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public void goAssignRoles(View view){
        startActivity(new Intent(this, AssignRoles.class));
    }

}
