package cse110winter2015group3.mafia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NightPhase extends AppCompatActivity {

    // NIGHTPHASE: MAFIA VOTE TO KILL PLAYER

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_phase);
    }

    // Kill player 2 then go back to previous
    public void onClickRadioButton1(View v) {
        GameScript.player2.canVote = false;
        GameScript.player2.isDead = true;
        GameScript.player2.canMessage = false;
        GameScript.player2.disablePlayer();
        GameScript.numPlayers--;
        finish();
    }

    // Kill player 3 then go to Day Phase
    public void onClickRadioButton2(View v) {
        GameScript.player3.canVote = false;
        GameScript.player3.isDead = true;
        GameScript.player3.canMessage = false;
        GameScript.player3.disablePlayer();
        GameScript.numPlayers--;
        finish();
    }

    // Kill player 4 then go to Day Phase
    public void onClickRadioButton3(View v) {
        GameScript.player4.canVote = false;
        GameScript.player4.isDead = true;
        GameScript.player4.canMessage = false;
        GameScript.player4.disablePlayer();
        GameScript.numPlayers--;
        finish();
    }
}
