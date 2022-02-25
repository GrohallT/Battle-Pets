package Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
   The Battle class that is created according to user specified information passed from the BattlePets
   class into the BattleController class where objects of Battle are instantiated. Keeps track of data
   during the battle.
 */
public class Battle
{
    private int fightNumber;
    private final int numFights;
    private final List<Fight> fightList;
    private final List<Playable> players;
    private final HashMap<Integer, Integer> winMap;

    /**
     * The constructor for the Battle object that keeps track of the total number of fights to be fought,
     * what fight is currently being fought, and the win count for each pet.
     * Win counts are kept in a HashMap with the key being the pet's predetermined index number,
     * and the value being their number of wins.
     * @param numFights: the humber of fights that will take place in the Battle.
     * @param players: the list of all playables in this Battle.
     */
    public Battle(int numFights, List<Playable> players)
    {
        this.fightNumber = 0;
        this.numFights = numFights;
        this.players = players;
        fightList = new ArrayList<>();
        winMap = new HashMap<>();

        // Add each pet in the list of Playables to the HashMap and start them all at 0 wins.
        for (int i = 0; i < players.size(); i++)
            this.winMap.put(i, 0);
    }

    /**
     * Increments the counter that keeps track of what Fight is currently in play for the Battle
     */
    public void incrementFightNumber()
    {
        this.fightNumber++;
    }

    /**
     *
     * @param key is the index value for the winMap to determine which player has their win count incremented
     */
    public void incrementWins(int key)
    {
        this.winMap.put(key, this.winMap.get(key) + 1);
    }

    /**
     *
     * @return an integer corresponding to what fight is currently in play for the Battle
     */
    public int getFightNumber()
    {
        return this.fightNumber;
    }

    /**
     *
     * @return an integer corresponding to how many Fights are scheduled to take place in the BAttle
     */
    public int getNumFights() { return this.numFights; }

    /**
     *
     * @param key is the index of the player to return the wins for
     * @return the integer corresponding to how many Fight wins that player has in the Battle.
     */
    public int getWins(int key)
    {
        return this.winMap.get(key);
    }

    public List<Fight> getFightList() { return this.fightList; }


    public List<Playable> getPlayers() { return this.players; }

    /**
     *
     * @return the player who has the most Fight wins at the conclusion of the Battle.
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
}
