package cse110winter2015group3.mafia;

/**
 * Created by Stan on 2/20/2016.
 */
public class Doctor extends Player {
    boolean canHeal;

    public Doctor() {

    }
    public void initializeDoctorPlayer(){
        canHeal = true;
        canMessage = true;
        canVote = true;
        canDie = true;
    }
    public boolean getCanHeal(){
        return canHeal;
    }
    public boolean getCanMessage(){
        return canMessage;
    }
    public boolean getCanVote(){
        return canVote;
    }
    public boolean getCanDie(){
        return canDie;
    }

    public void healPlayer() {
        // HEAL OTHER PLAYER
    }
}
