package cse110winter2015group3.mafia;

/**
 * Created by Stan on 2/20/2016.
 */
public class Player {

    boolean canVote;
    boolean canDie;
    boolean canMessage;
    boolean isDead;
    boolean isArrested;

    public Player() {
        canVote = true;
        canDie = true;
        canMessage = true;
        isDead = false;
        isArrested = false;
    }

    public void disablePlayer() {
        // DISABLE PLAYER FROM VOTING & MSGING WHEN DEAD
        canVote = false;
        canMessage = false;
        canDie = false;
        // if (player.isMafia) {
        //    isArrested = true;
        // }
    }

    public void vote() {
        if (canVote) {
            // CHECKBOX VOTING SYSTEM TO BE IMPLEMENTED LATER
            // FX WILL ALLOW PLAYER CLASS TO VOTE AMONG THE OPTIONS
        }
    }

    public void msgBoardPath() {
        if (canMessage) {
            // FX LINKING PLAYER TO MSGBOARD AND VICE VERSA
        }
    }
}
