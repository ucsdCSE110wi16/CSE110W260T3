package cse110winter2015group3.mafia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class JoinGameActivity extends AppCompatActivity {
    private Firebase mFirebaseRef;
    public String entryCodeInput = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

    }

    public void submitCode(View v) {
        EditText editName = (EditText) findViewById(R.id.userInput);
        final EditText your_name = (EditText)findViewById(R.id.player_name);
        editName.setOnKeyListener(null);
        entryCodeInput = editName.getText().toString();
        editName.setText("");
        TextView tView;
        tView = (TextView)findViewById(R.id.button3);
        tView.setText("Thank You!");
        Button button1 = (Button) findViewById(R.id.button3);
        button1.setClickable(false);
        mFirebaseRef = new Firebase("https://radiant-torch-4018.firebaseio.com");
        Firebase codeRef = mFirebaseRef.child("gameCode");
        codeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String code = dataSnapshot.getValue().toString();
                if (entryCodeInput.equals(code)){
                    startActivity(new Intent(getApplicationContext(),EnterGame.class));
                    String name;
                    mFirebaseRef.child("player").setValue(your_name.getText().toString());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}