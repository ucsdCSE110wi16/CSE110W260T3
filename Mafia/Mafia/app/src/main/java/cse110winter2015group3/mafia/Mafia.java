package cse110winter2015group3.mafia;

/**
 * Created by Stan on 2/20/2016.
 */
public class Mafia extends Player {

    // MAFIA PLAYER CAN KILL OTHER PLAYER CLASSES
    boolean canKill;

    public Mafia() {
        //canDie = true;
        canMessage = true;
        canVote = true;
        canKill = true;
    }

    public Mafia(String uid) {
        userUID = uid;
        //canDie = true;
        canMessage = true;
        canVote = true;
        canKill = true;
    }



    public void killPlayer() {
        // IMPLEMENT THIS TO "KILL" OTHER PLAYER
        // THAT PLAYER SHOULD BE "DISABLED"
    }
}
