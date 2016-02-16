package cse110winter2015group3.mafia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class JoinGameActivity extends AppCompatActivity {

    public String entryCodeInput = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

        /**
        Button showDialog  = (Button) findViewById(R.id.EnterPasscode);
        final TextView userInputText = (TextView) findViewById(R.id.userinputtext);
        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(JoinGameActivity.this).inflate(
                        R.layout.activity_join_game, null);

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(JoinGameActivity.this);
                alertBuilder.setView(view);
                final EditText userInput = (EditText) view.findViewById(R.id.userinputtext);

                alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userInputText.setText(userInput.getText());
                    }
                });
                alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


            }
        });

        */

    }

    public void submitCode(View v) {
        EditText editName = (EditText) findViewById(R.id.userInput);
        editName.setOnKeyListener(null);
        entryCodeInput = editName.getText().toString();
        editName.setText("");
        TextView tView;
        tView = (TextView)findViewById(R.id.button3);
        tView.setText("Thank You!");
        Button button1 = (Button) findViewById(R.id.button3);
        button1.setClickable(false);
    }
}
