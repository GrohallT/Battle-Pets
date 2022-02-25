package Entity;

import Control.SeasonIterator;
import java.util.*;

/**
 * The Season class is created by the SeasonController and keeps track of all
 * relevant information pertaining to the season, such as the players participating, the win counts,
 * the list of previous battles, and what battle is currently being fought.
 */
public class Season implements Iterable <SeasonRound>
{
    private final List<Playable> players;
    private List<Battle> battleList;
    private final HashMap<Integer, Integer> winMap;
    private int battleNumber;

    /**
     * The constructor for the Season object takes a list of participating players as a parameter
     * and initializes a place for them in the HashMap that keeps track of win counts (initialized to 0 wins).
     * Also initializes the list of Battles that will hold previous Battles for recall info if necessary, and
     * sets the BattleNumber to 0.
     * @param players
     */
    public Season(List<Playable> players)
    {
        this.players = players;
        battleList = new ArrayList<>();
        winMap = new HashMap<>();
        battleNumber = 0;

        // Add each pet in the list of Playables to the HashMap and start them all at 0 wins.
        for (int i = 0; i < players.size(); i++)
            this.winMap.put(i, 0);
    }

    /**
     *
     * @return the list of players who are participating in the Season
     */
    public List<Playable> getPlayers() { return players; }

    /**
     *
     * @return the list of Battles that have taken place so far in the Season
     */
    public List<Battle> getBattleList() { return battleList; }

    /**
     * Increments the counter that keeps track of what Battle is currently in play for the Season
     */
    public void incrementBattleNumber() { battleNumber++; }

    /**
     *
     * @return the integer that corresponds to what the Battle count currently is in the Season
     */
    public int getBattleNumber() { return this.battleNumber; }

    /**
     *
     * @param key is the index value for the winMap to determine which player has their win count incremented
     */
    public void incrementWins(int key) { this.winMap.put(key, this.winMap.get(key) + 1); }

    /**
     *
     * @param key is the index of the player to return the wins for
     * @return the integer corresponding to how many Battle wins that player has in the Season.
     */
    public int getWins(int key) { return this.winMap.get(key); }

    /**
     *
     * @return the player who has the most Battle wins at the conclusion of the Season.
     * If there is a tie, currently the foremost player in the list will be treated as the winner.
     */
    public Playable getWinner()
    {
        int maxIndex = 0;
        for (int i = 1; i < players.size(); i++)
        {
            if (winMap.get(i) > winMap.get(maxIndex))
                maxIndex = i;
        }
        return players.get(maxIndex);
    }

    @Override
    public Iterator<SeasonRound> iterator() { return new SeasonIterator(this); }
}
