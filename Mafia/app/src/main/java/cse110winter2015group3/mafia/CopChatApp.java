package cse110winter2015group3.mafia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

public class CopChatApp extends AppCompatActivity {

    public static Firebase firebase;
    FirebaseListAdapter<ChatMessage> mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cop_chat_app);

        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://shining-inferno-5525.firebaseio.com/Game/Chat/Cop Chat");

        final EditText textEdit = (EditText) this.findViewById(R.id.text_edit);
        Button sendButton = (Button) this.findViewById(R.id.send_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textEdit.getText().toString();
                ChatMessage message = new ChatMessage(firebase.getAuth().getProviderData().get("email").toString(), text);
                firebase.push().setValue(message);
                textEdit.setText("");
            }
        });

        final ListView listView = (ListView) findViewById(android.R.id.list);
        mListAdapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                android.R.layout.two_line_list_item, firebase) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                ((TextView)v.findViewById(android.R.id.text1)).setText(model.getName());
                ((TextView)v.findViewById(android.R.id.text2)).setText(model.getText());
            }
        };
        listView.setAdapter(mListAdapter);

        Button returnButton = (Button) findViewById(R.id.returnPage);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // OK , now we need to list the current users in the chat!
        // It can just be a textView under the return button

        Firebase pullUsers = new Firebase("https://shining-inferno-5525.firebaseio.com/Game/player");

        String listOfPlayers = "";
        listOfPlayers = pullUsers.getAuth().getProviderData().get("email").toString();
        TextView showPlayers = (TextView) findViewById(R.id.showPlayers);
        showPlayers.setText(listOfPlayers);
    }
}
