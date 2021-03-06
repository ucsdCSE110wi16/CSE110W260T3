package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    public int playerCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);
        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com");
        Firebase playerCountRef2 = mFirebaseRef.child("Game/playerCount");
        playerCountRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playerCount = Integer.parseInt(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

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
        Firebase codeRef = mFirebaseRef.child("Game/gameCode");
        codeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String code = dataSnapshot.getValue().toString();
                if (entryCodeInput.equals(code)){
                    //need to create player object and add to DB
                    final Firebase playerCountRef = mFirebaseRef.child("Game/playerCount");
                    playerCountRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            playerCount = Integer.parseInt(dataSnapshot.getValue().toString());
                            playerCount = playerCount + 1;
                            playerCountRef.setValue(playerCount);
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                    playerCount = playerCount + 1;
                    String uID = mFirebaseRef.getAuth().getProviderData().get("email").toString();
                    String [] strArray = uID.split("@");
                    String userName = strArray[0];


                    String queryString = "Game/player/" + userName + "/PlayerObject";
                    System.out.println("queryString is: " + queryString);
                    Firebase playerRef1 = mFirebaseRef.child(queryString);
                    Player player = new Player();
                    player.setPlayerStatus();
                    playerRef1.setValue(player);
                    startActivity(new Intent(getApplicationContext(), EnterGame.class));
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
