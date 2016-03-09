package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getResults();
        int delay = 5000;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), DayPhase.class);
                startActivity(intent);
            }
        }, delay);
    }
    //function needs to pull from the Results section of the Game in Firebase. This will check if the game is over
    // if the game is over, it will go to the final page where it will display the winner of the game.
    //if the game is not over, it will display the results of the last round (which player was killed, which was saved,
    //and which was arrested), and will move back to the night phase.
    //come into this page when
    public void getResults(){
        Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/Game/Results");
        mFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,String> resultsMap = (Map<String,String>) dataSnapshot.getValue();
                String gameResults = "";
                for(Map.Entry<String,String> entry:resultsMap.entrySet()){
                    String occurence = entry.getKey();
                    String player = entry.getValue();
                    gameResults += occurence + " : " + player + "\n";
                }
                TextView resultsOutput = (TextView) findViewById(R.id.results);
                resultsOutput.setText(gameResults);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }
}
