package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class CopNightPhase extends AppCompatActivity implements NightPhase{
    private Cop cop;
    private Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/Game");
    private ArrayList<String> validPlayersToArrest;
    private String playerToArrest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cop_night_phase);
        setValidPlayers();
        setObject();
        Handler handler = new Handler();
        int delay = 25000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Results.class);
                startActivity(intent);
            }
        }, delay);
    }


    @Override
    public void setValidPlayers() {
        Firebase playerRef = mFirebaseRef.child("PlayerList/");
        playerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "DataSnapshotExists", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "DataSnapshot is null", Toast.LENGTH_LONG).show();
                }
                validPlayersToArrest = (ArrayList<String>) dataSnapshot.getValue();
                String arrestPlayerString = "";
                for (String entry : validPlayersToArrest) {
                    arrestPlayerString += entry + "\n";
                }
                TextView playerToArrestList = (TextView) findViewById(R.id.listPotentialArrested);
                playerToArrestList.setText(arrestPlayerString);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void getCopObject(Cop copPlayer){
        cop = copPlayer;
    }
    @Override
    public void setObject(){
        String uID = mFirebaseRef.getAuth().getProviderData().get("email").toString();
        String [] strArray = uID.split("@");
        String userName = strArray[0];
        Firebase doctorRef = mFirebaseRef.child("/player/" + userName + "/CopObject");
        doctorRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Cop copPlayer = dataSnapshot.getValue(Cop.class);
                getCopObject(copPlayer);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    @Override
    public void performAction(){
        if (cop.canArrest == false){
            Toast.makeText(getApplicationContext(), "Cop Cannot Arrest", Toast.LENGTH_LONG);
            return;
        }
        EditText editName = (EditText) findViewById(R.id.playerToArrest);
        editName.setOnKeyListener(null);
        playerToArrest = editName.getText().toString();
        if (playerToArrest.equals("")){
            Button arrestButton = (Button) findViewById(R.id.playerArrestButton);
            arrestButton.setText("Please enter a valid UserName");
        }
        editName.setText("");
        Button playerArrestButton = (Button) findViewById(R.id.playerArrestButton);
        playerArrestButton.setText("The arrest attempt has been logged");
        cop.investigatePlayer(playerToArrest);
    }

    public void performCopAction(View view){
        Firebase promptedRef = mFirebaseRef.child("Moderator");
        promptedRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Moderator moderator = dataSnapshot.getValue(Moderator.class);
                if (moderator.allowCopToInvestigate == true) {
                    performAction();
                } else {
                    Toast.makeText(getApplicationContext(), "Please Wait till Moderator has Prompted You", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
