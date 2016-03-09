package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class RevealRoles extends AppCompatActivity {

    private String userRole;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_roles);
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://shining-inferno-5525.firebaseio.com/Game/player/Role");
        Firebase mFireBaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com");

        final TextView revealRole = (TextView) findViewById(R.id.revealRole);

        // Now we need to check the Player's role by accessing the db before we assign it to a
        // string and then setting it into revealRole
        String userEmail = mFireBaseRef.getAuth().getProviderData().get("email").toString();
        String[] strArry = userEmail.split("@");
        String userName = strArry[0];

        /**
        Firebase roleRef = firebase.child("Game/player/Role/"+userName + "/Role");
        roleRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //userRole = dataSnapshot.getKey();
                userRole = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
         */

        //String playerRole = "Your Role is: " + userRole;
        String playerRole = "Your Role is: " + AssignRoles.currentUserRole;
        revealRole.setText(playerRole);

        int delay = 5000;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), GameStory.class);
                startActivity(intent);
            }
        }, delay);

    }
}
