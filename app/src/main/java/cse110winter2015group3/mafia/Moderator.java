package cse110winter2015group3.mafia;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by aneeshnatarajan on 3/6/16.
 */
public class Moderator extends Player {
    public boolean allowMafiaToKill;
    public boolean allowDoctorToSave;
    public boolean allowCopToInvestigate;

    public Moderator(){}
    public void initializeModeratorPlayer(){
        canVote = false;
        canMessage = false;
        isDead = false;
        isArrested = false;
        allowMafiaToKill = false;
        allowDoctorToSave = false;
        allowCopToInvestigate = false;

    }
    public boolean getCanVote(){
        return canVote;
    }
    public boolean getCanMessage(){
        return canMessage;
    }
    public boolean getIsArrested(){
        return isArrested;
    }
    public boolean getAllowMafiaToKill(){
        return allowMafiaToKill;
    }
    public boolean getAllowDoctorToSave(){
        return allowDoctorToSave;
    }
    public boolean getAllowCopToInvestigate(){
        return allowCopToInvestigate;
    }




}
