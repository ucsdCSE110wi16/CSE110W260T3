package cse110winter2015group3.mafia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    }
    //function needs to pull from the Results section of the Game in Firebase. This will check if the game is over
    // if the game is over, it will go to the final page where it will display the winner of the game.
    //if the game is not over, it will display the results of the last round (which player was killed, which was saved,
    //and which was arrested), and will move back to the night phase.
    public void getResults(){

    }
}
