package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
/*Class Created By Aneesh Natarajan*/
public class EndGame extends AppCompatActivity {
    private int playerCount;
    private int mafiaCount;
    Firebase mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/Game");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        getPlayerCount();
        getMafiaCount();
        if (mafiaCount == playerCount){
            String result = "Mafia Has Won! " + "\n" + "Thank you for playing!";
            TextView resultView = (TextView) findViewById(R.id.gameResultOutput);
            resultView.setText(result);
        }
        else if(mafiaCount == 0){
            String result = "Civilians Have Won!" + "\n" + "Thank You For Player!";
            TextView resultView = (TextView) findViewById(R.id.gameResultOutput);
            resultView.setText(result);
        }
        else{
            String result = "Continue back to night phase!";
            int delay1 = 10000;
            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(),GameStory.class);
                    startActivity(intent);
                    //call method to disable player who was killed
                    //go to finishGame
                }
            }, delay1);
        }
    }
    //go to the DB, find out the player Count and the Mafia Count
    public void getPlayerCount(){
        Firebase playerCountRef = mFirebaseRef.child("playerCount");
        playerCountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playerCount = Integer.parseInt(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void getMafiaCount(){
        Firebase playerCountRef = mFirebaseRef.child("MafiaCount");
        playerCountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mafiaCount = Integer.parseInt(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
