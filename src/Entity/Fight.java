/**
 * @author Taryn Grohall
 */
package Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores objects that are useful to access during the course of a fight in the game, accessed by Fight Controller.
 */
public class Fight {
    private int roundNumber;
    private int winningPlayer;
    private List<Playable> players;
    private List<Playable> activePlayers;
    private List<Round> roundList;

    /**
     * Parameterized constructor, initializes a Fight instance with a List of Playable objects. Assigns the Playable List
     * to the one passed in. Also initializes the roundNumber to zero.
     *
     * @param players List of Playable
     */
    public Fight(List<Playable> players) {
        this.players = players;
        this.activePlayers = players;
        roundList = new ArrayList<>();
        roundNumber = 0;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) { this.roundNumber = roundNumber; }

    public int getWinningPlayer() {
        return winningPlayer;
    }

    public void setWinningPlayer(int winningPlayer) { this.winningPlayer = winningPlayer; }

    public List<Playable> getPlayers() { return players; }

    public void setPlayers(List<Playable> players) { this.players = players; }

    public List<Playable> getActivePlayers() { return activePlayers; }

    public void setActivePlayers(List<Playable> playerToAdd) { activePlayers = playerToAdd; }

    public List<Round> getRoundList() { return roundList; }
}
