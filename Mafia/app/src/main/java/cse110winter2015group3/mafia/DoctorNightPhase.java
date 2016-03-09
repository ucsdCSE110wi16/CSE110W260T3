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

public class DoctorNightPhase extends AppCompatActivity implements NightPhase{
    private Doctor doctor;
    private Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/");
    private ArrayList<String> validPlayersToSave;
    private String playerToSave;
    private boolean canSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_night_phase);
        setObject();
        setValidPlayers();
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
    public void setValidPlayers(){
        Firebase playerRef = mFirebaseRef.child("Game/PlayerList/");
        playerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "DataSnapshotExists", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "DataSnapshot is null", Toast.LENGTH_LONG).show();
                }
                validPlayersToSave = (ArrayList<String>) dataSnapshot.getValue();
                String savePlayerString = "";
                for (String entry : validPlayersToSave) {
                    savePlayerString += entry + "\n";
                }
                TextView playerToSaveList = (TextView) findViewById(R.id.listToBeSaved);
                playerToSaveList.setText(savePlayerString);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public void getDoctorObject(Doctor doctorPlayer){
        doctor = doctorPlayer;
    }
    public void setObject(){
        String uID = mFirebaseRef.getAuth().getProviderData().get("email").toString();
        String [] strArray = uID.split("@");
        String userName = strArray[0];
        Firebase doctorRef = mFirebaseRef.child("Game/player/" + userName + "/DoctorObject");
        doctorRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Doctor doctorPlayer = dataSnapshot.getValue(Doctor.class);
                getDoctorObject(doctorPlayer);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public void performAction(){
        if (doctor.canHeal == false){
            Toast.makeText(getApplicationContext(), "Doctor Cannot Heal", Toast.LENGTH_LONG);
            return;
        }
        EditText editName = (EditText) findViewById(R.id.playerToSave);
        editName.setOnKeyListener(null);
        playerToSave = editName.getText().toString();
        editName.setText("");
        Button playerSaveButton = (Button) findViewById(R.id.playerSaveButton);
        playerSaveButton.setText("The player you have selected has been killed");
        doctor.healPlayer(playerToSave);
        playerSaveButton.setText("Please enter a valid player");

    }
    public void performDoctorAction(View view){
        Firebase isPromptedRef = mFirebaseRef.child("Game/Moderator");
        isPromptedRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Moderator moderator = dataSnapshot.getValue(Moderator.class);
                if (moderator.allowDoctorToSave == true) {
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
