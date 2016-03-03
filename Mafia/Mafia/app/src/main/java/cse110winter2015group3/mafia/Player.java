package cse110winter2015group3.mafia;

/**
 * Created by Stan on 2/20/2016.
 */
public class Player {

    String userUID; // unique ID used in this case will be the uses email
    boolean canVote;
    boolean canDie;
    boolean canMessage;
    boolean isDead;
    boolean isArrested;
    String player_name;

    public Player() {
        canVote = true;
        canDie = true;
        canMessage = true;
        isDead = false;
        isArrested = false;
        player_name = "anonymous";
    }

    public Player(String user) {
        userUID = user;
        canVote = true;
        canDie = true;
        canMessage = true;
        isDead = false;
        isArrested = false;
        player_name = user;
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
    public String getName(){
        return player_name;
    }
}
