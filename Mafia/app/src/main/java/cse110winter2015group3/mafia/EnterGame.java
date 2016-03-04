package cse110winter2015group3.mafia;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aneeshnatarajan on 3/2/16.
 */
public class EnterGame extends Activity{
    public int playerCount;
    Firebase mFirebaseRef = new Firebase("https://radiant-torch-4018.firebaseio.com");
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_game);
        Firebase playerCountRef = mFirebaseRef.child("playerCount");
        playerCountRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playerCount = Integer.parseInt(dataSnapshot.getValue().toString());
                if (playerCount >= 4){
                    Button button1 = (Button) findViewById(R.id.ready_to_start);
                    button1.setClickable(true);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public void goAssignRoles(View view){
        startActivity(new Intent(this, AssignRoles.class));
    }



}
