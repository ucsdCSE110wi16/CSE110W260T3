package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class UserLogin extends AppCompatActivity {

    public static EditText userEmail;
    public static EditText userPassword;
    Button enterButton;
    public static AuthData tempAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        userEmail = (EditText) findViewById(R.id.emailAddress);
        userPassword = (EditText) findViewById(R.id.password);

        /**
        MainActivity.mFirebaseRef.authWithPassword(userEmail.getText().toString(),
                userPassword.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        // Store this authData in a global temp storage so that we can access the
                        // current users information (USER UID, EMAIL, PASSWORD)
                        tempAuth = authData;

                        System.out.println("LOCAL Email: "
                                + authData.getProviderData().get("mail").toString()
                                + ", User ID: " + authData.getUid()
                                + ", Password: " + authData.getProvider());
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // there was an error
                    }
                });
         */
        //MainActivity.login = true;

        enterButton = (Button) findViewById(R.id.enterButton);
    }

    // Button to jump out of logging in page
    public void exitLoginScript(View v) {
        finish();
        //startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}
