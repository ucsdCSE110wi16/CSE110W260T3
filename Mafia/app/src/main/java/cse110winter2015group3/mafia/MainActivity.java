package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.FirebaseListAdapter;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;


public class MainActivity extends FirebaseLoginBaseActivity {

    // Connection to our data
    public static Firebase mFirebaseRef;
    FirebaseListAdapter<ChatMessage> mListAdapter;
    public static boolean login = false;
    AuthData tempHold;
    EditText userEmail;
    EditText userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // allows Firebase client to keep its context
        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_main);

        // initialize database
        mFirebaseRef = new Firebase("https://shining-inferno-5525.firebaseio.com/");

        final EditText textEdit = (EditText) this.findViewById(R.id.text_edit);
        Button sendButton = (Button) this.findViewById(R.id.send_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textEdit.getText().toString();
                // HARD-CODED FOR NOW AND ALLOWS USER TO LOGIN

                if (UserLogin.userEmail != null) {
                    userEmail = UserLogin.userEmail;
                    userPassword = UserLogin.userPassword;
                    MainActivity.mFirebaseRef.authWithPassword(userEmail.getText().toString(),
                            userPassword.getText().toString(), new Firebase.AuthResultHandler() {
                                @Override
                                public void onAuthenticated(AuthData authData) {
                                    // Store this authData in a global temp storage so that we can access the
                                    // current users information (USER UID, EMAIL, PASSWORD)
                                    tempHold = authData;
                                }

                                @Override
                                public void onAuthenticationError(FirebaseError firebaseError) {
                                    // there was an error
                                }
                            });
                    login = true;
                }
                if (login == true && tempHold != null) {
                    ChatMessage message = new ChatMessage(tempHold.getProviderData().get("email").toString(), text);
                    mFirebaseRef.push().setValue(message);
                    textEdit.setText("");
                } else { // Else false, the user is not logged in
                    ChatMessage message = new ChatMessage("Anonymous User", text);
                    mFirebaseRef.push().setValue(message);
                    textEdit.setText("");
                }
            }
        });

        Button loginButton = (Button) this.findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showFirebaseLoginPrompt();
                login = true;
                startActivity(new Intent(getApplicationContext(), UserLogin.class));
            }
        });

;

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
