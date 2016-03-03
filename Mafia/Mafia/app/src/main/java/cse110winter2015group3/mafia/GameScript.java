package cse110winter2015group3.mafia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.Random;

public class GameScript extends AppCompatActivity {

    // At the start of the game, every mafioso is given the identities of their teammates,
    // whereas the innocents only receive the number of mafiosi in the game
    // There are two phases: night and day. At night, certain players secretly perform special
    // actions; during day, players discuss and vote to "lynch," or eliminate, one player.
    // These phases alternate with each other until all mafiosi have been eliminated or until the
    // mafia outnumbers the innocents.

    Firebase firebase = MainActivity.firebase;
    public static Player player1;
    public static Player player2;
    public static Player player3;
    public static Player player4;
    public static Player player5;
    public static int numPlayers = 5; // while > 0 , keep playing
    public static Mafia mafiaPlayer;
    public static Doctor doctorAI;
    public static Cop copAI;
    public static Civilian civilianAI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_script);
        generateRoles();

        // HOW DO WE GENERATE THE CORRECT LAYOUT PAGE FOR THE CURRENT USERS ROLE?
        // IE IF THE THEY ARE MAFIA NIGHT PHASE WORKS FOR THEM BUT DAYPHASE GIVES THEM A PAUSE
        // PAGE UNTIL THE NEXT NIGHTPHASE


        startActivity(new Intent(getApplicationContext(), NightPhase.class));
            // then upon voting, call finish()
        //startActivity(new Intent(getApplicationContext(), DayPhase.class));

    }

    // Check the number of players
    // For this instance example, we will count the user as a player and create 4 AI as the
    // other roles/players.
    public void createPlayers() {
        player1 = new Player(firebase.getAuth().getProviderData().get("email").toString());
        player2 = new Player("AI player #1");
        player3 = new Player("AI player #2");
        player4 = new Player("AI player #3");
        player5 = new Player("AI player #4");
    }

    public void generateRoles() {
        createPlayers();
        // Later on this will be randomized!
        player1 = mafiaPlayer = new Mafia(firebase.getAuth().getProviderData().get("email").toString());
        player2 = doctorAI = new Doctor();
        player3 = copAI = new Cop();
        player4 = civilianAI = new Civilian();

        Toast.makeText(GameScript.this,
                "Generated Roles: User = Mafia, 4 AI's (Cop/Doctor/Moderator/Civilian)",
                Toast.LENGTH_SHORT).show();

    }
}
