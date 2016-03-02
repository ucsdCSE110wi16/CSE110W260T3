package cse110winter2015group3.mafia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class StartGameActivity extends AppCompatActivity {
    private Firebase mFirebaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        mFirebaseRef = new Firebase("https://radiant-torch-4018.firebaseio.com");
        final EditText your_name = (EditText)findViewById(R.id.player_name);
        String name;
        mFirebaseRef.child("player").setValue(your_name.getText().toString());
    }

}
