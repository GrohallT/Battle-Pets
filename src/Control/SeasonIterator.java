package Control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Entity.*;

/**
 * Concrete Iterator meant to iterate through an instance of Season.  Iterates through rounds of the Season and
 * returns a SeasonRound object with all matchups for that round, following the Round Robin circle method.  Checks if
 * current round is within number of rounds in season.
 */
public class SeasonIterator implements Iterator <SeasonRound>
{
    private Season season;
    List<Playable> participantList;
    private int n; //the number of participants in the season
    private int currentRound;
    private int numRounds; //number of rounds in the season

    /**
     * Parameterized constructor, instantiates SeasonIterator using provided Season.  Calculates the number of
     * participants in Season, rounds and initializes currentRound to 1.
     *
     * @param season of type Season
     */
    public SeasonIterator(Season season)
    {
        this.season = season;
        this.n = this.season.getPlayers().size();

        this.participantList = new ArrayList<Playable>();
        //Deep copy list of playables
        for (int i = 0; i < n; i ++)
            this.participantList.add(season.getPlayers().get(i));

        //If n is odd, add dummy to end of participantList and increment n by 1
        if (!(n % 2 == 0))
        {
            this.n++;
            this.participantList.add(null);
        }
        this.currentRound = 1;
        this.numRounds = n - 1; //There will be n-1 rounds
    }

    /**
     * Fixes element at index 0 and shifts others to right 1 index.  Moves last element to index 1.
     */
    private void shift()
    {
        //Store last participant in temp
        Playable temp = participantList.get(n - 1);

        //Move participants 2 through n up 1 index in list
        for (int i = n - 1; i > 1; i--)
            participantList.set(i,participantList.get(i - 1));

        //Move nth participant to 2nd index
        participantList.set(1, temp);
    }

    /**
     * Overrides hasNext() method to return whether current round is within number of rounds in season.
     *
     * @return true if currentRound is <= numRounds, false otherwise
     */
    @Override
    public boolean hasNext() { return currentRound <= numRounds; }

    /**
     * Overrides next() method to return a SeasonRound instance.  Produces n / 2 matchups of Playables to
     * create the SeasonRound.
     *
     * @return new SeasonRound containing a list of all matchups for that round
     */
    @Override
    public SeasonRound next()
    {
        //List of all battles in a SeasonRound
        List<List<Playable>> roundList = new ArrayList<List<Playable>>();

        //Loop through and make n / 2 lists of matchups to pass to SeasonRound.
        // Matchups always consist of ith index vs jth index
        for (int i = 0, j = n - 1; i < n / 2 ; i++)
        {
            //Create list of participants in battle and add to roundList
            List <Playable> battleList = new ArrayList<Playable>();
            battleList.add(participantList.get(i));
            battleList.add(participantList.get(j));
            roundList.add(battleList);
            j--;
        }
        //Shift list and increment round before returning new SeasonRound
        shift();
        currentRound++;
        return new SeasonRound(roundList);
    }
}
