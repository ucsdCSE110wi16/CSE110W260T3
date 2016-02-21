package com.example.aneeshnatarajan.mafiaapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Intent;
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
    public String player_name = "";
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
        EditText name = (EditText) findViewById(R.id.userName);
        name.setOnClickListener(null);
        player_name = name.getText().toString();
        name.setText("");
        //Intent intent = new Intent(this, EnterGame.class);
        //intent.putExtra("enteredCode",entryCodeInput);
        //intent.putExtra("player_name",player_name);
        //startActivity(intent);

    }




}
