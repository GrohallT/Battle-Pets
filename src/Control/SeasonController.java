package Control;

import Boundary.IOManager;
import Entity.*;

import java.util.List;
import java.util.Random;

/**
 * Controls the game loop for a Season. Repeatedly iterates over a Season given
 * a list of Playables in 1v1 Battles until all possible matchups have been
 * exhausted.
 */
public class SeasonController {
    private Season season;
    private final BattleController battleController;
    private final IOManager ioManager;
    private final Random rng;

    /**
     * Constructs a new SeasonController.
     * @param rng The random number generator used to determine the base damage.
     * @param battleController The BattleController to use for each SeasonRound
     */
    public SeasonController(Random rng, BattleController battleController) {
        this.battleController = battleController;
        this.ioManager = IOManager.getInstance();
        this.rng = rng;
    }

    /**
     * Starts the battle and displays fight information following the finish of
     * each fight.
     * @param playables The list of players active in this Battle.
     */
    public Season start(List<Playable> playables) {
        season = new Season(playables);
        int roundCounter = 1;

        for (SeasonRound sr : season) {
            ioManager.getOutputStream().writeOutput("Season Round #" + (roundCounter++));
            int roundBattleCounter = 0;

            for (List<Playable> matchup : sr.getParticipantsList()) {
                season.incrementBattleNumber(); // Total, cumulative battle count
                roundBattleCounter++; // Local counter for this SeasonRound

                // 2-Player Match-Up
                if (!matchup.contains(null)) {
                    displayBattleInfo(matchup, roundBattleCounter);

                    Battle battle = battleController.start(matchup);
                    season.getBattleList().add(battle);

                    Playable battleWinner = battle.getWinner();
                    ioManager.getOutputStream().writeOutput(battleWinner.getPetName() + " won Season Battle #" + roundBattleCounter + "!");
                    season.incrementWins(season.getPlayers().indexOf(battleWinner));
                }
                else {
                    int playerIndex = matchup.get(0) != null ? 0 : 1;
                    Playable player = season.getPlayers().get(season.getPlayers().indexOf(matchup.get(playerIndex)));
                    ioManager.getOutputStream().writeOutput("\nSeason Battle #" + roundBattleCounter + "\n" + player.getPetName() + " received a Bye!");
                }
            }
        }

        displaySeasonResults();

        return season;
    }

    /**
     * Displays the current Battle number and its participants.
     */
    private void displayBattleInfo(List<Playable> playables, int battleNumber)
    {
        ioManager.getOutputStream().writeOutput("\nSeason Battle #" + battleNumber + "\nCurrent matchup:");

        for (Playable player : playables) {
            ioManager.getOutputStream().writeOutput(player.getPetName());
        }
    }

    /**
     * Calculates and displays the name of the pet that had the most wins after
     * the last Battle in the Season.
     */
    private void displaySeasonWinner()
    {
        ioManager.getOutputStream().writeOutput("\n" + season.getWinner().getPetName() + " won the Season!");
    }

    /**
     * Displays the number of Battles that each Playable won following the
     * conclusion of the Season.
     */
    private void displaySeasonResults()
    {
        ioManager.getOutputStream().writeOutput("\nThe Season has ended! \nPet Win Counts: \n");
        for (int i = 0; i < season.getPlayers().size(); i++)
        {
            ioManager.getOutputStream().writeOutput(season.getPlayers().get(i).getPetName() + ": " + season.getWins(i) + " wins");
        }

        displaySeasonWinner();
    }
}