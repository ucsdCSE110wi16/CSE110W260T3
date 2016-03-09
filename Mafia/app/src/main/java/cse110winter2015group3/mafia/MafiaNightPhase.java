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

/*
*Class: MafiaNightPhase.java
* Description: This Activity class represents the activity of the Mafia during the night phase aspect
* of the game. This class implements the NightPhase interface, which allows it to implement all of its
* methods. The mafia has methods to recieve a prompt from the moderator, at which point, they can
* enter the username of the player they would like to kill
*
* Fields                                                    Description
* ------------------------------------------------------------------------------------------------------------
* Firebase mFirebaseRef                                 reference to DB
* Mafia mafia                                  mafia object represents curr moderator player
* */

public class MafiaNightPhase extends AppCompatActivity implements NightPhase{

    private Mafia mafia;
    private ArrayList<String> playersToKill;
    private boolean promptedToKill;
    private String killedPlayer;
    Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/Game");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mafia_night_phase);
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
    public void getMafiaObject(Mafia mafiaPlayer){
        mafia = mafiaPlayer;
    }
    public void setObject(){
        String uID = mFirebaseRef.getAuth().getProviderData().get("email").toString();
        String [] strArray = uID.split("@");
        String userName = strArray[0];
        Toast.makeText(getApplicationContext(),"userName is" + userName, Toast.LENGTH_LONG).show();
        Firebase mafiaRef = mFirebaseRef.child("/player/" + userName + "/MafiaObject");
        mafiaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "Datasnapshot is null", Toast.LENGTH_LONG).show();
                }
                Mafia mafiaPlayer = dataSnapshot.getValue(Mafia.class);
                getMafiaObject(mafiaPlayer);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public void setValidPlayers(){
        Firebase playerRef = mFirebaseRef.child("PlayerList/");
        playerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(getApplicationContext(),"DataSnapshotExists",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"DataSnapshot is null",Toast.LENGTH_LONG).show();
                }
                playersToKill = (ArrayList<String>)dataSnapshot.getValue();
                String killablePlayerString = "";
                for (String entry: playersToKill){
                    killablePlayerString += entry + "\n";
                }
                TextView playerToArrestList = (TextView) findViewById(R.id.killList);
                playerToArrestList.setText(killablePlayerString);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    public void performAction(){
        if (mafia.canKill == false){
            Toast.makeText(getApplicationContext(),"Mafia Cannot Kill", Toast.LENGTH_LONG);
            return;
        }
        EditText editName = (EditText) findViewById(R.id.playerToKill);
        editName.setOnKeyListener(null);
        killedPlayer = editName.getText().toString();
        Toast.makeText(getApplicationContext(), "Killed Player is " + killedPlayer , Toast.LENGTH_SHORT).show();
        editName.setText("");
        Button playerKillButton = (Button) findViewById(R.id.killPlayerButton);
        playerKillButton.setText("Attempt to Kill Has Been Logged");
        mafia.killPlayer(killedPlayer);
    }
    public void performMafiaAction(View view){
        Firebase isPromptedRef = mFirebaseRef.child("Moderator");
        isPromptedRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Moderator moderator = dataSnapshot.getValue(Moderator.class);
                if (moderator.allowMafiaToKill == true) {
                    performAction();
                } else {
                    Toast.makeText(getApplicationContext(), "Please Wait Until You Are Prompted By Moderator", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
