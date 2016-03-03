package cse110winter2015group3.mafia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;

public class EnterGame extends AppCompatActivity {

    // initialize the fireBase
    private Firebase mFirebaseRef;

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        //initialize FireBase database
        //mFirebaseRef = new Firebase("https://radiant-torch-4018.firebaseio.com");
        mFirebaseRef = MainActivity.firebase;

        setContentView(R.layout.activity_enter_game);

        /**

        lv = (ListView) findViewById(R.id.select_dialog_listview);

        // Instantiating an array list
        List<String> players = new ArrayList<String>();

        // need to retrieve players from fireBase
        players.add("Luc");
        players.add("Aneesh");
        players.add("Aneesh");

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, players);

        lv.setAdapter(arrayAdapter);
         */
    }
}
