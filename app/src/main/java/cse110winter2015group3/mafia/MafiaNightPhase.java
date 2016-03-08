package cse110winter2015group3.mafia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    private List<String> playersToKill;
    private boolean promptedToKill;
    private String killedPlayer;
    private Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mafia_night_phase);
        setValidPlayers();
        String killablePlayersString = "";
        for(String player:playersToKill){
            killablePlayersString += player;
        }
        TextView killList = (TextView) findViewById(R.id.killList);
        killList.setText(killablePlayersString);
        Button killButton = (Button) findViewById(R.id.killPlayerButton);
        setObject();
        killButton.setClickable(false);
        if(isPrompted() == true){
            performAction();
        }
    }
    public void setObject(){
        String uID = mFirebaseRef.getAuth().getProviderData().get("email").toString();
        String [] strArray = uID.split("@");
        String userName = strArray[0];
        Firebase mafiaRef = mFirebaseRef.child("Game/player/" + userName + "/MafiaObject");
        mafiaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mafia = dataSnapshot.getValue(Mafia.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public void setValidPlayers(){
        Firebase playerRef = mFirebaseRef.child("Game/player");
        playerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    playersToKill.add(child.getValue().toString());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    //method to check if moderator has prompted us to kill
    public boolean isPrompted(){
        Firebase canKillRef = mFirebaseRef.child("Game/Moderator/");
        final Query queryRef = canKillRef.orderByChild("allowMafiaToKill").equalTo("true");
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                promptedToKill = (Boolean) dataSnapshot.child("allowMafiaToKill").getValue();
                if (promptedToKill == true) {
                    queryRef.removeEventListener(this);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return promptedToKill;
    }

    public void performAction(){
        if (mafia.canKill == false){
            Toast.makeText(getApplicationContext(),"Mafia Cannot Kill", Toast.LENGTH_LONG);
            return;
        }
        EditText editName = (EditText) findViewById(R.id.playerToKill);
        editName.setOnKeyListener(null);
        killedPlayer = editName.getText().toString();
        editName.setText("");
        Button playerKillButton = (Button) findViewById(R.id.killPlayerButton);
        if(Arrays.asList(playersToKill).contains(killedPlayer)){
            playerKillButton.setText("Attempt to Kill Has Been Logged");
            mafia.killPlayer(killedPlayer);
        }
        else{
            playerKillButton.setText("Please enter a valid player");
        }
    }


}
