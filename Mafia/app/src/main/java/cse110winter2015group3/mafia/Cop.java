package cse110winter2015group3.mafia;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Stan on 2/20/2016.
 */
public class Cop extends Player {
    boolean canArrest;
    //need open constructor and getter/setter methods for Firebase
    public Cop() {

    }

    public void copPlayerInitializer(){
        canDie = true;
        canVote = true;
        canMessage = true;
        canArrest = true;
    }
    public boolean getCanDie(){
        return canDie;
    }
    public boolean getCanVote(){
        return canVote;
    }
    public boolean getCanMessage(){
        return canMessage;
    }
    public boolean getCanArrest(){
        return canArrest;
    }

    public void investigatePlayer(String playerName) {

        Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/");

        String queryString = "/Game/player/" + playerName;
        final Firebase playerKilledRef = mFirebaseRef.child(queryString);
        playerKilledRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Player player = dataSnapshot.child("PlayerObject").getValue(Player.class);
                String role = dataSnapshot.child("Role").getValue().toString();
                //if player is the moderator or has been saved, then he cannot be killed
                if(role.equals("Mafia") && player.isDead == false && player.isArrested == false){
                    Mafia mafia = dataSnapshot.child("MafiaObject").getValue(Mafia.class);
                    mafia.canKill = false;
                    mafia.disablePlayer();
                    player.disablePlayer();
                    playerKilledRef.child("MafiaObject").setValue(mafia);
                }
                else{
                    return;
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
