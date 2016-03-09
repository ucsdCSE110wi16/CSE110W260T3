package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class GameHomePage extends AppCompatActivity {

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_home_page);
    }

    public void onLogoutClick(View v) {
        logoutButton = (Button) findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FIGURE OUT HOW TO LOGOUT WITH FIREBASE
                Intent intent = new Intent(GameHomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onStartGameButtonClick(View v) {
        startActivity(new Intent(getApplicationContext(), StartGameActivity.class));
    }

    public void onJoinGameButtonClick(View v) {
        startActivity(new Intent(getApplicationContext(), JoinGameActivity.class));

    }

}
