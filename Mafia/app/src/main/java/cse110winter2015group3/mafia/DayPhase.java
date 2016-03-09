package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class DayPhase extends AppCompatActivity {

    private Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/Game");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_phase);
        createVoteList();
        int delay = 25000;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tallyVotes();
                //call method to disable player who was killed
                //go to finishGame
            }
        }, delay);
        int delay1 = 10000;
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),EndGame.class);
                startActivity(intent);
                //call method to disable player who was killed
                //go to finishGame
            }
        }, delay1);
    }
    //
    public void createVoteList(){
        Firebase playerListRef = mFirebaseRef.child("player");
        playerListRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> playerMap = (Map<String, Object>) dataSnapshot.getValue();
                String playerList = "";
                for (Map.Entry<String, Object> entry : playerMap.entrySet()) {
                    String playerName = entry.getKey();
                    playerList += playerName + "\n";
                    mFirebaseRef.child("PlayerVote/" + playerName).setValue(0);
                }
                TextView playerListView = (TextView) findViewById(R.id.playerList);
                playerListView.setText(playerList);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    //implement vote method
    public void playerVote(View view){
        EditText playerVote = (EditText) findViewById(R.id.voteText);
        playerVote.setOnClickListener(null);
        String userName = playerVote.getText().toString();
        final Firebase playerVoteRef = mFirebaseRef.child("PlayerVote/" + userName);
        playerVoteRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int voteCount = Integer.parseInt(dataSnapshot.getValue().toString());
                voteCount = voteCount + 1;
                playerVoteRef.setValue(voteCount);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void tallyVotes(){
        Firebase playerListRef = mFirebaseRef.child("PlayerVote");
        playerListRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Long> playerMap = (Map<String,Long>)dataSnapshot.getValue();
                String playerList = "";
                long maxCount = 0;
                String maxPlayer = "";
                for(Map.Entry<String,Long> entry:playerMap.entrySet()){
                    long voteCount = entry.getValue();
                    if (maxCount < voteCount){
                        maxCount = voteCount;
                        maxPlayer = entry.getKey();
                    }
                }
                TextView resultView = (TextView) findViewById(R.id.displayResult);
                resultView.setText("Player Who was Killed Is: " + maxPlayer);
                deletePlayer(maxPlayer);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void deletePlayer(String deletedPlayerName){
        final Firebase playerKilledRef = mFirebaseRef.child("player/" + deletedPlayerName);
        playerKilledRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Player player = dataSnapshot.child("PlayerObject").getValue(Player.class);
                String role = dataSnapshot.child("Role").getValue().toString();
                Toast.makeText(getApplicationContext(), "Role is: " + role, Toast.LENGTH_LONG).show();
                //if player is the moderator or has been saved, then he cannot be killed
                if(role.equals("Moderator") || player.canDie == false || player.isDead == true){
                    return;
                }
                else if(role.equals("Mafia")){
                    Mafia mafia = dataSnapshot.child("MafiaObject").getValue(Mafia.class);
                    Toast.makeText(getApplicationContext(),"Killing Mafia Player",Toast.LENGTH_LONG).show();
                    mafia.canKill = false;
                    mafia.disablePlayer();
                    player.disablePlayer();
                    playerKilledRef.child("MafiaObject").setValue(mafia);
                    playerKilledRef.child("PlayerObject").setValue(player);
                    decerementPlayerCount();
                    decerementMafiaCount();
                }
                else if(role.equals("Doctor")){
                    Doctor doctor = dataSnapshot.child("DoctorObject").getValue(Doctor.class);
                    doctor.canHeal = false;
                    doctor.disablePlayer();
                    player.disablePlayer();
                    playerKilledRef.child("DoctorObject").setValue(doctor);
                    playerKilledRef.child("PlayerObject").setValue(player);
                    decerementPlayerCount();
                }
                else if(role.equals("Cop")){
                    Cop cop= dataSnapshot.child("CopObject").getValue(Cop.class);
                    cop.canArrest = false;
                    cop.disablePlayer();
                    player.disablePlayer();
                    playerKilledRef.child("CopObject").setValue(cop);
                    playerKilledRef.child("PlayerObject").setValue(player);
                    decerementPlayerCount();

                }
                else if(role.equals("Civilian")){
                    Civilian civilian = dataSnapshot.child("CivilianObject").getValue(Civilian.class);
                    civilian.disablePlayer();
                    player.disablePlayer();
                    playerKilledRef.child("CivilianObject").setValue(civilian);
                    playerKilledRef.child("PlayerObject").setValue(player);
                    decerementPlayerCount();

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }
    public void decerementPlayerCount(){
        Firebase playerCountRef = mFirebaseRef.child("playerCount");
        playerCountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int playerCount = Integer.parseInt(dataSnapshot.getValue().toString());
                playerCount = playerCount - 1;
                Toast.makeText(getApplicationContext(),"Player Count is " + String.valueOf(playerCount), Toast.LENGTH_LONG).show();
                mFirebaseRef.child("playerCount").setValue(playerCount);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void decerementMafiaCount(){
        Firebase playerCountRef = mFirebaseRef.child("MafiaCount");
        playerCountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int mafiaCount = Integer.parseInt(dataSnapshot.getValue().toString());
                mafiaCount = mafiaCount - 1;
                mFirebaseRef.child("MafiaCount").setValue(mafiaCount);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
