package cse110winter2015group3.mafia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.List;
/*This is the Night Phase Interface. This interface is implemented by the MafiaNightPhase.java Activity
* the DoctorNightPhase.java Activity, and the CopNightPhase.java Activity. These are all the classes
* where players are propmpted to make a move, they are able to recieve a list of all the valid players
* they can perform their action upon, and are able to perform that Action. */
public interface NightPhase{

    // THIS IS TO BE CHANGED TO EITHER AN INTERFACE OR A SUPERCLASS W/ MAFIANIGHTPHASE / ETC

    // NIGHTPHASE: MAFIA VOTE TO KILL PLAYER
    public void setValidPlayers();
    public void setObject();
    public void performAction();
}
