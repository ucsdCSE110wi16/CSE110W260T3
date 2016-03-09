package cse110winter2015group3.mafia;

import android.support.v7.app.AppCompatActivity;


public interface NightPhase{

    // THIS IS TO BE CHANGED TO EITHER AN INTERFACE OR A SUPERCLASS W/ MAFIANIGHTPHASE / ETC

    // NIGHTPHASE: MAFIA VOTE TO KILL PLAYER

    public void setValidPlayers();
    public boolean isPrompted();
    public void setObject();
    public void performAction();
}
