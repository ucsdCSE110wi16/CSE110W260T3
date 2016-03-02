package cse110winter2015group3.mafia;

/**
 * Created by Stan on 2/20/2016.
 */
public class Cop extends Player {
    boolean canArrest;

    public Cop() {
        canDie = true;
        canVote = true;
        canMessage = true;
        canArrest = true;
    }

    public void investigatePlayer() {
        // "GUESS" IF PLAYER IS MAFIA
        /**
         if (OTHER PLAYER IS MAFIA) {
            // CALL FUNCTION TO ARREST AND DISABLE OTHER PLAYER
            otherPlayer.arrestPlayer();
         } else {
            // IF NOT MAFIA THEN DO NOTHING
         }
         */
    }

    public void arrestPlayer() {
        // ARREST MAFIA & DISABLE
        // otherPlayer.disablePlayer();
    }
}
