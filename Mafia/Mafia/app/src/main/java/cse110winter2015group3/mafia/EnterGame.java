package cse110winter2015group3.mafia;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.FirebaseListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by aneeshnatarajan on 2/27/16.
 */
public class EnterGame extends AppCompatActivity {
    // initialize the fireBase
    //private Firebase mFirebaseRef= new Firebase("https://radiant-torch-4018.firebaseio.com");
/*    private Firebase mFirebaseRef= new Firebase("https://dark-night.firebaseio.com");

    EditText mEdit;
    private ListView lv;
    public String playerName = "";
    // Instantiating an array list
    List<String> playerList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_game);
        Firebase.setAndroidContext(this);
        mEdit = (EditText)findViewById(R.id.enter_name);
        Firebase ref = mFirebaseRef.child("player").child(mEdit.getText().toString());
        // Get a reference to our posts
        mFirebaseRef.addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to the database
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                Player newPlayer = snapshot.getValue(Player.class);
                playerName = newPlayer.getUserUID(); //UserUID is player
                playerList.add(playerName);
                // fresh list view when player is added
                lv.invalidateViews();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
            //... ChildEventListener also defines onChildChanged, onChildRemoved,
            //    onChildMoved and onCanceled, covered in later sections.
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, playerList);
        lv.setAdapter(adapter);

    }*/
}