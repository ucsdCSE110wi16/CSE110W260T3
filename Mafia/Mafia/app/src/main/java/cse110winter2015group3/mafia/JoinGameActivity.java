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
        editName.setOnKeyListener(null);
        entryCodeInput = editName.getText().toString();
        editName.setText("");
        TextView tView;
        tView = (TextView)findViewById(R.id.button3);
        tView.setText("Thank You!");
        Button button1 = (Button) findViewById(R.id.button3);
        button1.setClickable(false);
        //mFirebaseRef = new Firebase("https://radiant-torch-4018.firebaseio.com");
        mFirebaseRef = MainActivity.firebase;

        Firebase codeRef = mFirebaseRef.child("gameCode");
        codeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String code = dataSnapshot.getValue().toString();
                if (entryCodeInput.equals(code)){
                    startActivity(new Intent(getApplicationContext(),EnterGame.class));
                    //need to create player object and store in the DB
                }
                else{
                    TextView tView1;
                    tView1 = (TextView)findViewById(R.id.button3);
                    tView1.setText("Try Again");
                    Button button2 = (Button) findViewById(R.id.button3);
                    button2.setClickable(true);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
