package cse110winter2015group3.mafia;

import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Aneesh on 2/20/2016.
 */
public class Mafia extends Player {

    // MAFIA PLAYER CAN KILL OTHER PLAYER CLASSES
    boolean canKill;

    public Mafia() {
    }
    public void initializeMafiaPlayer(){
        role = "Mafia";
        canMessage = true;
        canVote = true;
        canKill = true;
    }

    public String getRoleType() { return role; }
    public boolean getCanMessage(){
        return canMessage;
    }
    public boolean getCanVote(){
        return canVote;
    }
    public boolean getCanKill(){
        return canKill;
    }

    public void killPlayer(String playerName) {
        // IMPLEMENT THIS TO "KILL" OTHER PLAYER
        // THAT PLAYER SHOULD BE "DISABLED"
        Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/");

        String queryString = "/Game/player/" + playerName;
        final Firebase playerKilledRef = mFirebaseRef.child(queryString);
        playerKilledRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Player player = dataSnapshot.child("PlayerObject").getValue(Player.class);
                String role = dataSnapshot.child("Role").getValue().toString();
                //if player is the moderator or has been saved, then he cannot be killed
                if(role.equals("Moderator") || player.canDie == false || role.equals("Mafia") || player.isDead == true){
                    return;
                }
                if(role.equals("Doctor")){
                    Doctor doctor = dataSnapshot.child("DoctorObject").getValue(Doctor.class);
                    doctor.canHeal = false;
                    doctor.disablePlayer();
                    player.disablePlayer();
                    playerKilledRef.child("DoctorObject").setValue(doctor);
                }
                if(role.equals("Cop")){
                    Cop cop= dataSnapshot.child("CopObject").getValue(Cop.class);
                    cop.canArrest = false;
                    cop.disablePlayer();
                    player.disablePlayer();
                    playerKilledRef.child("CopObject").setValue(cop);
                }
                if(role.equals("Civilian")){
                    Civilian civilian = dataSnapshot.child("CivilianObject").getValue(Civilian.class);
                    civilian.disablePlayer();
                    player.disablePlayer();
                    playerKilledRef.child("CivilianObject").setValue(civilian);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
