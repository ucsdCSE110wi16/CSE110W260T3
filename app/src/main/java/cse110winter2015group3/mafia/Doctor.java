package cse110winter2015group3.mafia;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Aneesh on 2/20/2016.
 */
public class Doctor extends Player {
    boolean canHeal;

    public Doctor() {

    }

    public void initializeDoctorPlayer(){
        canHeal = true;
        canMessage = true;
        canVote = true;
        canDie = true;
    }
    public boolean getCanHeal(){
        return canHeal;
    }
    public boolean getCanMessage(){
        return canMessage;
    }
    public boolean getCanVote(){
        return canVote;
    }
    public boolean getCanDie(){
        return canDie;
    }

    public void healPlayer(String playerName) {
        final String tempPlayerName = playerName;
        final Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/");
        mFirebaseRef.child("Game/Results/SavedPlayer").setValue(playerName);
        String queryString = "/Game/player/" + playerName;
        final Firebase playerKilledRef = mFirebaseRef.child("Game/Results/KilledPlayer");
        playerKilledRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String killedPlayer = dataSnapshot.getValue().toString();
                if(killedPlayer.equals(tempPlayerName)){
                   playerKilledRef.setValue("");
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        final Firebase playerSavedRef = mFirebaseRef.child(queryString);
        playerSavedRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Player player = dataSnapshot.child("PlayerObject").getValue(Player.class);
                String role = dataSnapshot.child("Role").getValue().toString();
                //if player is the moderator or has been saved, then he cannot be killed
                if(role.equals("Moderator") || player.canDie == false){
                    mFirebaseRef.child("Game/Results/SavedPlayer").setValue("");
                    return;
                }
                if(role.equals("Mafia")){
                    Mafia mafia = dataSnapshot.child("MafiaObject").getValue(Mafia.class);
                    mafia.savePlayer();
                    player.savePlayer();
                    playerSavedRef.child("MafiaObject").setValue(mafia);
                    playerSavedRef.child("PlayerObject").setValue(player);
                }
                if(role.equals("Cop")){
                    Cop cop= dataSnapshot.child("CopObject").getValue(Cop.class);
                    cop.canArrest = false;
                    cop.savePlayer();
                    player.savePlayer();
                    playerSavedRef.child("CopObject").setValue(cop);
                    playerSavedRef.child("PlayerObject").setValue(player);

                }
                if(role.equals("Civilian")){
                    Civilian civilian = dataSnapshot.child("CivilianObject").getValue(Civilian.class);
                    civilian.savePlayer();
                    player.savePlayer();
                    playerSavedRef.child("CivilianObject").setValue(civilian);
                    playerSavedRef.child("PlayerObject").setValue(player);


                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
