package cse110winter2015group3.mafia;

import android.os.Handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

import cse110winter2015group3.mafia.Civilian;
import cse110winter2015group3.mafia.Cop;
import cse110winter2015group3.mafia.Doctor;
import cse110winter2015group3.mafia.Mafia;
import cse110winter2015group3.mafia.Moderator;
import cse110winter2015group3.mafia.Player;

/**
 * Created by aneeshnatarajan on 3/3/16.
 */
public class AssignRoles extends Activity{
    public static String currentUserRole;
    public static String moderatorName;
    Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/Game");
    Map<String,Player> playerMap;
    Map<String,String> roleMap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_roles);
        assignRoles();
        // DELAY TIMER BEFORE MOVING ONTO NEXT PAGE --> RevealRoles --> GameStory
        int delay = 5000;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), RevealRoles.class);
                startActivity(intent);
            }
        }, delay);
        //Intent intent = new Intent(this,GameStory.class);
        //startActivity(intent);


    }
    private void assignRoles(){
        final Context context = getApplicationContext();
        final int duration = Toast.LENGTH_SHORT;
        final Firebase playerListRef = mFirebaseRef.child("player");
        playerListRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    Toast.makeText(context, "The dataSnapshot is null", duration);
                }
                playerMap = (Map<String, Player>) dataSnapshot.getValue();
                mFirebaseRef.child("PlayerList").setValue(playerMap.keySet());
                Firebase playerRoleRef = mFirebaseRef.child("player/");
                int i = 0;
                int mafiaCount = 0;
                for (Map.Entry<String, Player> entry : playerMap.entrySet()) {

                    String playerName = entry.getKey();
                    Log.d("Player Name", "The player is: " + playerName);
                    int index = i % 5;
                    if (index == 0) {
                        //Create a moderator
                        Firebase moderatorRef = playerRoleRef.child(playerName + "/Role");
                        moderatorRef.setValue("Moderator");
                        Moderator moderator = new Moderator();
                        moderator.initializeModeratorPlayer();
                        Firebase modRef = mFirebaseRef.child("Moderator");
                        modRef.setValue(moderator);
                        Firebase userRef = playerRoleRef.child(playerName + "/ModeratorObject");
                        userRef.setValue(moderator);
                        currentUserRole = "Moderator";
                        moderatorName = playerName;
                    }
                    if (index == 1) {
                        //Create A Mafia Player
                        Mafia mafiaPlayer = new Mafia();
                        mafiaPlayer.initializeMafiaPlayer();
                        Firebase mafiaRef = playerRoleRef.child(playerName + "/Role/");
                        mafiaRef.setValue("Mafia");
                        Firebase mafiaUserRef = playerRoleRef.child(playerName + "/MafiaObject");
                        mafiaUserRef.setValue(mafiaPlayer);
                        mafiaCount = mafiaCount + 1;
                        mFirebaseRef.child("MafiaCount").setValue(mafiaCount);
                        Log.d("Mafia Created", "Creating a Mafia Player");
                        currentUserRole = "Mafia";
                    } else if (index == 2) {
                        //Create A Cop Player
                        Cop copPlayer = new Cop();
                        copPlayer.copPlayerInitializer();
                        Firebase copRef = playerRoleRef.child(playerName + "/Role/");
                        copRef.setValue("Cop");
                        Firebase copUserRef = playerRoleRef.child(playerName + "/CopObject");
                        copUserRef.setValue(copPlayer);
                        Log.d("Cop Created", "Creating a Cop Player");
                        currentUserRole = "Cop";

                    } else if (index == 3) {
                        //Create A Doctor
                        Doctor doctorPlayer = new Doctor();
                        doctorPlayer.initializeDoctorPlayer();
                        Firebase doctorRef = playerRoleRef.child(playerName + "/Role/");
                        doctorRef.setValue("Doctor");
                        Firebase docUserRef = playerRoleRef.child(playerName + "/DoctorObject");
                        docUserRef.setValue(doctorPlayer);
                        Log.d("Doctor Created", "Creating a Doctor Player");
                        currentUserRole = "Doctor";

                    } else if (index == 4 || index == 5) {
                        //Create A Villager
                        Civilian civilianPlayer = new Civilian();
                        civilianPlayer.civilianInitializer();
                        Firebase civilianRef = playerRoleRef.child(playerName + "/Role/");
                        civilianRef.setValue("Civilian");
                        Firebase civUserRef = playerRoleRef.child(playerName + "/CivilianObject");
                        civUserRef.setValue(civilianPlayer);
                        Log.d("Civilian Created", "Creating a Civilian Player");
                        currentUserRole = "Civilian";
                    }
                    i = i + 1;

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

}
