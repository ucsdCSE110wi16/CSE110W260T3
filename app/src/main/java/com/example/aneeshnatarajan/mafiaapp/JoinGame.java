package com.example.aneeshnatarajan.mafiaapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by aneeshnatarajan on 2/7/16.
 */
public class JoinGame extends Activity{
    public String entryCodeInput = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_game);
    }

    public void submitCode(View view){
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
