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

    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_roles);
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://shining-inferno-5525.firebaseio.com/Game/player/Role");

        final TextView revealRole = (TextView) findViewById(R.id.revealRole);

        // Now we need to check the Player's role by accessing the db before we assign it to a
        // string and then setting it into revealRole
        String userEmail = firebase.getAuth().getProviderData().get("email").toString();
        String[] strArry = userEmail.split("@");
        String userName = strArry[0];

        // THIS NEXT PARTS NEEDS WORK. IT IS CURRENTLY HARDCODED. WE NEED TO BE ABLE TO
        // QUERY THE DB AND PULL THE ACTUAL ROLE

        String playerRole = "Your Role is: " + firebase.child(userName+"/Role/Mafia").getKey();
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
