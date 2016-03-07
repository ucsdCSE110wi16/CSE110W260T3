package cse110winter2015group3.mafia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.List;

public interface NightPhase{

    // THIS IS TO BE CHANGED TO EITHER AN INTERFACE OR A SUPERCLASS W/ MAFIANIGHTPHASE / ETC

    // NIGHTPHASE: MAFIA VOTE TO KILL PLAYER
    public void setValidPlayers();
    public boolean isPrompted();
    public void setObject();
    public void performAction();
}
