package cse110winter2015group3.mafia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignInSignUpActivity extends AppCompatActivity {

    Button loginButton;
    Button signupButton;
    String usernameTxt;
    String passwordTxt;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        loginButton = (Button) findViewById(R.id.Login);
        signupButton = (Button) findViewById(R.id.signup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameTxt = username.getText().toString();
                passwordTxt = password.getText().toString();

                ParseUser.logInInBackground(usernameTxt, passwordTxt, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Intent intent = new Intent(SignInSignUpActivity.this,
                                    WelcomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "This user doesn't exit. Please signup",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameTxt = username.getText().toString();
                passwordTxt = password.getText().toString();

                if (usernameTxt.equals("") &&  passwordTxt.equals("")) {

                    Toast.makeText(
                            getApplicationContext(),
                            "Please enter a valid username and password!",
                            Toast.LENGTH_SHORT).show();

                } else {

                    ParseUser user = new ParseUser();
                    user.setUsername(usernameTxt);
                    user.setPassword(passwordTxt);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {

                                Toast.makeText(
                                        getApplicationContext(),
                                        "You have successfully signed up!",
                                        Toast.LENGTH_SHORT).show();

                            } else {

                                Toast.makeText(
                                        getApplicationContext(),
                                        "Signup has failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });

    }

}
