package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CivilianNightPhase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civilian_night_phase);
    }

    public void goToChat(View w) {
        startActivity(new Intent(getApplicationContext(),CivilianChatApp.class));
    }
}
