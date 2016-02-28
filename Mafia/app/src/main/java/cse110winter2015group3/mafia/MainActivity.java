package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;


public class MainActivity extends FirebaseLoginBaseActivity {

    // Connection to our data
    private Firebase mFirebaseRef;
    FirebaseListAdapter<ChatMessage> mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // allows Firebase client to keep its context
        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_main);

        // initialize database
        mFirebaseRef = new Firebase("https://radiant-torch-4018.firebaseio.com");
        final EditText textEdit = (EditText) this.findViewById(R.id.text_edit);
        Button sendButton = (Button) this.findViewById(R.id.send_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textEdit.getText().toString();
                // HARD-CODED FOR NOW AND ALLOWS USER TO LOGIN
                ChatMessage message = new ChatMessage("User", text);
                mFirebaseRef.push().setValue(message);
                textEdit.setText("");
            }
        });

        Button loginButton = (Button) this.findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFirebaseLoginPrompt();
            }
        });

        final ListView listView = (ListView) this.findViewById(android.R.id.list);
        mListAdapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                android.R.layout.two_line_list_item, mFirebaseRef) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                ((TextView)v.findViewById(android.R.id.text1)).setText(model.getName());
                ((TextView)v.findViewById(android.R.id.text2)).setText(model.getText());
            }
        };
        listView.setAdapter(mListAdapter);
    }

    // Button to jump to homepage
    public void onGoToButtonClick(View v) {
        startActivity(new Intent(getApplicationContext(), GameHomePage.class));
    }

    // LOGIN HELPER
    @Override
    protected void onStart() {
        super.onStart();
        setEnabledAuthProvider(AuthProviderType.PASSWORD);
    }

    // EVENT HANDLERS
    @Override
    protected Firebase getFirebaseRef() {
        return mFirebaseRef;
    }

    @Override
    protected void onFirebaseLoginProviderError(FirebaseLoginError firebaseLoginError) {

    }

    @Override
    protected void onFirebaseLoginUserError(FirebaseLoginError firebaseLoginError) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mListAdapter.cleanup();
    }
}
