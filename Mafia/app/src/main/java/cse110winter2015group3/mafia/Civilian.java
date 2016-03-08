package cse110winter2015group3.mafia;

/**
 * Created by Stan on 2/20/2016.
 */
public class Civilian extends Player {

    // FIREBASE NEEDS EMPTY DEFAULT CONSTRUCTOR
    public Civilian() {

    }
    public void civilianInitializer(){
        role = "Civilian";
        canMessage = true;
        canVote = true;
    }
    public boolean canMessage(){
        return canMessage();
    }
    public boolean canVote(){
        return canVote;
    }

    // THIS PLAYER CLASS CAN'T DO ANYTHING EXCEPT VOTE & MESSAGE & DIE
}
