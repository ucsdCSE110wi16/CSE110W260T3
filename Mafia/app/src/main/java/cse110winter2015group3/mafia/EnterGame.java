package cse110winter2015group3.mafia;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.firebase.client.Firebase;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by aneeshnatarajan on 2/27/16.
 */
public class EnterGame extends AppCompatActivity {
    // initialize the fireBase
    private Firebase mFirebaseRef;

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        //initialize FireBase database
        mFirebaseRef = new Firebase("https://radiant-torch-4018.firebaseio.com");

        setContentView(R.layout.enter_game);

        lv = (ListView) findViewById(R.id.listView);

        // Instantiating an array list
        List<String> players = new ArrayList<String>();

        // need to retrieve players from fireBase
        players.add("Luc");
        players.add("Aneesh");
        players.add("Aneesh");

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, players);

        lv.setAdapter(arrayAdapter);
    }
}
