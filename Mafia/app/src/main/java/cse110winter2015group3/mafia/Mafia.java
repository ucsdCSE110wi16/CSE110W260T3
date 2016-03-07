package cse110winter2015group3.mafia;

/**
 * Created by Stan on 2/20/2016.
 */
public class Mafia extends Player {

    // MAFIA PLAYER CAN KILL OTHER PLAYER CLASSES
    boolean canKill;

    public Mafia() {
    }
    public void initializeMafiaPlayer(){
        role = "Mafia";
        canMessage = true;
        canVote = true;
        canKill = true;
    }

    public String getRoleType() { return role; }
    public boolean getCanMessage(){
        return canMessage;
    }
    public boolean getCanVote(){
        return canVote;
    }
    public boolean getCanKill(){
        return canKill;
    }

    public void killPlayer(Player player) {
        // IMPLEMENT THIS TO "KILL" OTHER PLAYER
        // THAT PLAYER SHOULD BE "DISABLED"
    }
}
