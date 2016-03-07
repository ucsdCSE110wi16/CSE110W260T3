package cse110winter2015group3.mafia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by aneeshnatarajan on 3/3/16.
 */
public class AssignRoles extends Activity{
    Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com");
    Map<String,Player> playerMap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_roles);
        assignRoles();

        Intent intent = new Intent(this,GameStory.class);
        startActivity(intent);
        moveToRevealRoles();

    }
    private void assignRoles(){
        Firebase playerListRef = mFirebaseRef.child("Game/player");
        playerListRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playerMap = (Map<String, Player>) dataSnapshot.getValue();
                Firebase playerRoleRef = mFirebaseRef.child("Game/player/");
                int i = 0;
                //Create a moderator

                for (Map.Entry<String, Player> entry : playerMap.entrySet()) {
                    String playerName = entry.getKey();
                    Log.d("Player Name", "The player is: " + playerName);
                    int index = i % 4;
                    if (index == 0) {
                        //Create A Mafia Player
                        Mafia mafiaPlayer = new Mafia();
                        mafiaPlayer.initializeMafiaPlayer();
                        Firebase mafiaRef = playerRoleRef.child(playerName + "/Role/Mafia");
                        mafiaRef.setValue(mafiaPlayer);
                        Log.d("Mafia Created", "Creating a Mafia Player");
                    } else if (index == 1) {
                        //Create A Cop Player
                        Cop copPlayer = new Cop();
                        copPlayer.copPlayerInitializer();
                        Firebase copRef = playerRoleRef.child(playerName + "/Role/Cop");
                        copRef.setValue(copPlayer);
                        Log.d("Cop Created", "Creating a Cop Player");
                    } else if (index == 2) {
                        //Create A Doctor
                        Doctor doctorPlayer = new Doctor();
                        doctorPlayer.initializeDoctorPlayer();
                        Firebase doctorRef = playerRoleRef.child(playerName + "/Role/Doctor");
                        doctorRef.setValue(doctorPlayer);
                        Log.d("Doctor Created", "Creating a Doctor Player");

                    } else if (index == 3 || index == 4) {
                        //Create A Villager
                        Civilian civilianPlayer = new Civilian();
                        civilianPlayer.civilianInitializer();
                        Firebase civilianRef = playerRoleRef.child(playerName + "/Role/Civilian");
                        civilianRef.setValue(civilianPlayer);
                        Log.d("Civilian Created", "Creating a Civilian Player");
                    }
                    i = i + 1;

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }
    public void moveToRevealRoles(){
        //if Players/username/role == Mafia { Start MafiaNightPhase Activity }
        //if Players/username/role == Cop { Start MafiaNightPhase Activity }
        //if Players/username/role == Doctor { Start MafiaNightPhase Activity }
        //if Players/username/role == Moderator { Start MafiaNightPhase Activity }
        //if Players/username/role == Civilian { Start MafiaNightPhase Activity }
    }
}
