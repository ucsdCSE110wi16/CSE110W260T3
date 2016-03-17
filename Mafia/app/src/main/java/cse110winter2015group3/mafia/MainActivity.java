package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
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
    Button signupButton;
    String emailtxt;
    String passwordtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // allows Firebase client to keep its context
        Firebase.setAndroidContext(this);

        // initialize database
        firebase = new Firebase("https://shining-inferno-5525.firebaseio.com/Game");

        // intialize playerCount = 0.
        // WE NEED TO RETHINK THIS. EACH TIME A USER OPENS APP IT WILL SET PLAYER COUNT TO ZERO!
        Firebase playerCountRef = firebase.child("playerCount");
        playerCountRef.setValue(0);

        //Delete entries from firebase. Setting a location value to null == removing data from db
        Firebase toRemovePrevGameCode =
                new Firebase("https://shining-inferno-5525.firebaseio.com/Game/gamecode");
        toRemovePrevGameCode.removeValue();
        Firebase toRemovePrevChat = new Firebase("https://shining-inferno-5525.firebaseio.com/Game/Chat");
        toRemovePrevChat.removeValue();

        // IN END GAME WE CAN DELETE EERYTHING INCLUDING PLAYERS!

        firebase.unauth();

        setContentView(R.layout.activity_main);

        // Prompt user to log in

        final EditText userEmail = (EditText) findViewById(R.id.emailAddress);
        final EditText userPassword = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);
        signupButton = (Button) findViewById(R.id.signup);

        loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emailtxt = userEmail.getText().toString();
                    passwordtxt = userPassword.getText().toString();
                    firebase.authWithPassword(emailtxt, passwordtxt, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {

                            globalAuth = authData;
                            startActivity(new Intent(getApplicationContext(), ChatApp.class));
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
                                case FirebaseError.INVALID_EMAIL:
                                    Toast.makeText(getApplicationContext(), "Invalid email!",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(getApplicationContext(),
                                            "Error authenticating (default)!",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
                }
            });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailtxt = userEmail.getText().toString();
                passwordtxt = userPassword.getText().toString();
                firebase.createUser(emailtxt, passwordtxt, new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(),
                                "Successfully created account! Please login.",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        switch (firebaseError.getCode()) {
                            case FirebaseError.EMAIL_TAKEN:
                                Toast.makeText(getApplicationContext(),
                                        "This email is already being used!",
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case FirebaseError.INVALID_EMAIL:
                                Toast.makeText(getApplicationContext(),
                                        "Invalid Email!", Toast.LENGTH_SHORT).show();
                                break;
                            case FirebaseError.INVALID_PASSWORD:
                                Toast.makeText(getApplicationContext(),
                                        "Invalid Password!", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(getApplicationContext(),
                                        "Error signing up!", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }
                });
            }
        });

        //} else {
            // User is now logged in, proceed to next Activity (should be chat)
           // startActivity(new Intent(getApplicationContext(), ChatApp.class));
       // }
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
