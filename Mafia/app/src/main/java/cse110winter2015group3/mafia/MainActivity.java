package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.FirebaseListAdapter;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;

/**
 * LOGIN FOR STANS FIREBASE SERVER: SHINING-INFERNO
 * ACCT #1: stan_deng@yahoo.com , pw= 1111
 * ACCT #2: aneesh@yahoo.com, pw = 12345
 *
 */

public class MainActivity extends FirebaseLoginBaseActivity {

    // Connection to our data
    public static Firebase firebase;
    public static AuthData globalAuth;
    Button loginButton;
    String emailtxt;
    String passwordtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // allows Firebase client to keep its context
        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_main);

        // initialize database
        firebase = new Firebase("https://shining-inferno-5525.firebaseio.com");

        // This is here temporarily so that each time the user logs in he/she is prompted to login
        //firebase.unauth();
        if (firebase.getAuth() == null) {
            // Prompt user to log in

            final EditText userEmail = (EditText) findViewById(R.id.emailAddress);
            final EditText userPassword = (EditText) findViewById(R.id.password);
            loginButton = (Button) findViewById(R.id.login);

            Toast.makeText(getApplicationContext(), "EMAIL: " + userEmail.getText().toString(), Toast.LENGTH_SHORT).show();

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emailtxt = userEmail.getText().toString();
                    passwordtxt = userPassword.getText().toString();
                    firebase.authWithPassword(emailtxt, passwordtxt, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            globalAuth = authData;
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {

                            switch (firebaseError.getCode()) {
                                case FirebaseError.USER_DOES_NOT_EXIST:
                                    Toast.makeText(getApplicationContext(),
                                            "User doesn't exist!", Toast.LENGTH_SHORT).show();
                                    break;
                                case FirebaseError.INVALID_PASSWORD:
                                    Toast.makeText(getApplicationContext(),
                                            "Invalid password!", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(getApplicationContext(),
                                            "Error authenticating (default)!",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
                    startActivity(new Intent(getApplicationContext(), ChatApp.class));
                }
            });

        } else {
            // User is now logged in, proceed to next Activity (should be chat)
            startActivity(new Intent(getApplicationContext(), ChatApp.class));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setEnabledAuthProvider(AuthProviderType.PASSWORD);
    }

    @Override
    protected Firebase getFirebaseRef() {
        return firebase;
    }

    @Override
    protected void onFirebaseLoginProviderError(FirebaseLoginError firebaseLoginError) {

    }

    @Override
    protected void onFirebaseLoginUserError(FirebaseLoginError firebaseLoginError) {

    }

    public void onLogin(View v) {
        // User has pressed log-in, goto ChatApp.java
        // We will make ChatApp accessible through the game. Simply have buttons on each Activity
        // that bring up ChatApp.class, and have a button on ChatApp.class that calls finish() to
        // exit the current Activity and go-back to the previous one!
        Intent intent = new Intent(this, ChatApp.class);
        startActivity(intent);
    }

}
