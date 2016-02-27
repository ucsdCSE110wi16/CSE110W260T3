package cse110winter2015group3.mafia;

/**
 * Created by Stan on 2/20/2016.
 */
public class Doctor extends Player {
    boolean canHeal;

    public Doctor() {
        canHeal = true;
        canMessage = true;
        canVote = true;
        canDie = true;
    }

    public void healPlayer() {
        // HEAL OTHER PLAYER
    }
}
