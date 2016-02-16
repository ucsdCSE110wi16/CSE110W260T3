package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class GameHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_home_page);

    }

    public void onStartGameButtonClick(View v) {
        startActivity(new Intent(getApplicationContext(), StartGameActivity.class));
    }

    public void onJoinGameButtonClick(View v) {
        startActivity(new Intent(getApplicationContext(), JoinGameActivity.class));

    }

}
