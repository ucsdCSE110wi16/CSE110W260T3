package cse110winter2015group3.mafia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.Arrays;
import java.util.Map;

public class CopNightPhase extends AppCompatActivity implements NightPhase{
    private Cop cop;
    private Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/");
    private Map<String, Player> validPlayersToArrest;
    private boolean isPromptedToArrest;
    private String playerToArrest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cop_night_phase);
        setValidPlayers();
        Button playerArrestButton = (Button) findViewById(R.id.playerArrestButton);
        playerArrestButton.setClickable(false);
        setObject();
        setValidPlayers();
        if (isPrompted() == true){
            performAction();
        }

    }
    @Override
    public void setValidPlayers() {
        Firebase playerRef = mFirebaseRef.child("Game/player");
        playerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                validPlayersToArrest = (Map<String, Player>) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        String playersToArrest = "";
        for (Map.Entry<String, Player> entry : validPlayersToArrest.entrySet()) {
            playersToArrest += entry.getKey();
        }
        TextView playerToArrestList = (TextView) findViewById(R.id.listPotentialArrested);
        playerToArrestList.setText(playersToArrest);
    }

    public void setObject(){
        String uID = mFirebaseRef.getAuth().getProviderData().get("email").toString();
        String [] strArray = uID.split("@");
        String userName = strArray[0];
        Firebase doctorRef = mFirebaseRef.child("Game/player/" + userName + "/CopObject");
        doctorRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cop = dataSnapshot.getValue(Cop.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public boolean isPrompted(){
        Firebase promptRef = mFirebaseRef.child("Game/Moderator/");
        final Query queryRef = promptRef.orderByChild("allowCopToArrest").equalTo("true");
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                isPromptedToArrest = (Boolean) dataSnapshot.child("allowDoctorToSave").getValue();
                queryRef.removeEventListener(this);
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
        return isPromptedToArrest;
    }
    public void performAction(){
        if (cop.canArrest == false){
            Toast.makeText(getApplicationContext(), "Cop Cannot Kill", Toast.LENGTH_LONG);
            return;
        }
        EditText editName = (EditText) findViewById(R.id.playerToArrest);
        editName.setOnKeyListener(null);
        playerToArrest = editName.getText().toString();
        editName.setText("");
        Button playerArrestButton = (Button) findViewById(R.id.playerArrestButton);
        if(Arrays.asList(validPlayersToArrest).contains(playerToArrest)){
            playerArrestButton.setText("The player you have selected has been killed");
            cop.investigatePlayer(playerToArrest);
        }
        else{
            playerArrestButton.setText("Please enter a valid player");
        }
    }
}
