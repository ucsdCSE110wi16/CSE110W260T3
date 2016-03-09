package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/*
*Class: ModeratorNightPhase.java
* Description: This Activity class represents the activity of the Moderator during the Night Phase of the Game
* The moderator defines and implements methods that allows the Moderator to prompt the Mafia, the Doctor,
* and the Cop to perform their actions. The logic ensures that the first player who is selected by any
* Cop, Mafia, or Doctor are deleting the correct people
*
* Fields                                                    Description
* ------------------------------------------------------------------------------------------------------------
* Firebase mFirebaseRef                                 reference to DB
* Moderator moderator                                   moderator object represents curr moderator player
* */

public class ModeratorNightPhase extends AppCompatActivity {

    private Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/Game");
    private Moderator moderator;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moderator_night_phase);
        Button buttonMafia = (Button) findViewById(R.id.promptMafia);
        Button buttonCop = (Button) findViewById(R.id.promptCop);
        setModerator();
        buttonMafia.setClickable(false);
        buttonCop.setClickable(false);
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
    public void setModerator(){
        Firebase moderatorRef = mFirebaseRef.child("Moderator");
        moderatorRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                moderator = dataSnapshot.getValue(Moderator.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void promptDoctor(View view){
        moderator.allowDoctorToSave = true;
        Firebase modRef = mFirebaseRef.child("Moderator");
        modRef.setValue(moderator);
        //check if player was properly deleted

        //set buttons clickable appropriately
        Button button = (Button) findViewById(R.id.promptMafia);
        button.setClickable(true);
        Button button1 = (Button) findViewById(R.id.promptDoctor);
        button1.setClickable(false);


    }
    public void promptMafia(View view){
        moderator.allowMafiaToKill = true;
        Firebase modRef = mFirebaseRef.child("Moderator");
        modRef.setValue(moderator);
        //check if player was properly deleted

        //set buttons clickable
        Button button = (Button) findViewById(R.id.promptCop);
        button.setClickable(true);
        Button button1 = (Button) findViewById(R.id.promptMafia);
        button1.setClickable(false);

    }
    public void promptCop(View view){
        moderator.allowCopToInvestigate = true;
        Firebase modRef = mFirebaseRef.child("Moderator");
        modRef.setValue(moderator);
        //check if player was properly investigated

    }
}
