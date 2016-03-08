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

public class DoctorNightPhase extends AppCompatActivity implements NightPhase{
    private Doctor doctor;
    private Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/");
    private Map<String, Player> validPlayersToSave;
    private String playerToSave;
    private boolean canSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_night_phase);
        Button playerSaveButton = (Button) findViewById(R.id.playerSaveButton);
        playerSaveButton.setClickable(false);
        setObject();
        setValidPlayers();
        if (isPrompted() == true){
            performAction();
        }
    }
    @Override
    public void setValidPlayers(){
        Firebase playerRef = mFirebaseRef.child("Game/player");
        playerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                validPlayersToSave = (Map<String, Player>) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        String playersToSave = "";
        for (Map.Entry<String, Player> entry : validPlayersToSave.entrySet()) {
            playersToSave += entry.getKey();
        }
        TextView playerToSaveList = (TextView) findViewById(R.id.listToBeSaved);
        playerToSaveList.setText(playersToSave);
    }

    @Override
    public void setObject(){
        String uID = mFirebaseRef.getAuth().getProviderData().get("email").toString();
        String [] strArray = uID.split("@");
        String userName = strArray[0];
        Firebase doctorRef = mFirebaseRef.child("Game/player/" + userName + "/DoctorObject");
        doctorRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                doctor = dataSnapshot.getValue(Doctor.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    @Override
    public boolean isPrompted(){
        Firebase promptRef = mFirebaseRef.child("Game/Moderator/");
        final Query queryRef = promptRef.orderByChild("allowDoctorToSave").equalTo("true");
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                canSave = (Boolean) dataSnapshot.child("allowDoctorToSave").getValue();
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
        return canSave;

    }

    @Override
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
        if(Arrays.asList(validPlayersToSave).contains(playerToSave)){
            playerSaveButton.setText("The player you have selected has been killed");
            doctor.healPlayer(playerToSave);
        }
        else{
            playerSaveButton.setText("Please enter a valid player");
        }
    }
}
