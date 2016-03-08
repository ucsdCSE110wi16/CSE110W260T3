package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Map;

public class RevealRoles extends AppCompatActivity {
    private String userRole;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_roles);
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://shining-inferno-5525.firebaseio.com/Game/player/");

        final TextView revealRole = (TextView) findViewById(R.id.revealRole);

        // Now we need to check the Player's role by accessing the db before we assign it to a
        // string and then setting it into revealRole
        String userEmail = firebase.getAuth().getProviderData().get("email").toString();
        String[] strArry = userEmail.split("@");
        String userName = strArry[0];
        Firebase roleRef = firebase.child(userName + "/Role");
        roleRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userRole = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //String playerRole = "Your Role is: " + firebase.child(userName+"/Role/Mafia").getKey();
        // HERE WE USE A GLOBAL STRING FROM AssignRoles.java THAT IS STORED UPON ROLE CREATION
        String playerRole = "Your Role is: " + userRole;
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
