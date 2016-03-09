package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class CivilianNightPhase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void goToChat(View w) {
        startActivity(new Intent(getApplicationContext(),CivilianChatApp.class));
    }
}
