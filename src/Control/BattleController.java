package Control;

import Boundary.IOManager;
import Entity.Battle;
import Entity.Fight;
import Entity.PetTypes;
import Entity.Playable;

import java.util.List;
import java.util.Random;

/**
  Controls the process of a battle, and will continue to start new fights until the specified
  number of fights per battle has been reached. Displays information following each fight and
  at the conclusion of the battle.
 */
public class BattleController
{
    private int numFights;
    private Battle battle;
    private final FightController fightController;
    private final IOManager ioManager;
    private final Random rng;

    public BattleController(Random rng, int numFights)
    {
        this.numFights = numFights;
        this.fightController = new FightController(rng);
        this.ioManager = IOManager.getInstance();
        this.rng = rng;
    }

    public void setNumFights(int numFights) { this.numFights = numFights; }

    /**
     * Starts the battle of specified number of fights, and displays fight information
     * following the finish of each fight.
     * @param playables The list of players active in this Battle.
     */
    public Battle start(List<Playable> playables)
    {
        battle = new Battle(numFights, playables);

        // continue to start new fights and update Battle info until the specified number
        // of fights per battle is reached.
        while (battle.getFightNumber() < battle.getNumFights()) {
            battle.incrementFightNumber();

            displayFightInfo();

            Fight fight = fightController.start(battle.getPlayers());
            battle.getFightList().add(fight);

            // Display the winner of the most recent fight, and what fight number it was.
            ioManager.getOutputStream().writeOutput(fight.getPlayers().get(fight.getWinningPlayer()).getPetName() + " won Fight #" + battle.getFightNumber() + "!");
            battle.incrementWins(fight.getWinningPlayer());
        }

        displayBattleResults();

        return battle;
    }

    /**
     * This method is called before each fight begins, displaying what fight number is beginning and how many wins each pet has.
     */
    private void displayFightInfo()
    {
        ioManager.getOutputStream().writeOutput("\nFight " + battle.getFightNumber() + " out of " + battle.getNumFights() +
                                                "\nPets currently fighting:");

        for (Playable player : battle.getPlayers()) {
            if (player.getPetType() == PetTypes.INTELLIGENCE)
                System.out.println("\t" + player.getPetName() + " (an " + player.getPetType().toString() + " Type)");
            else
                System.out.println("\t" + player.getPetName() + " (a " + player.getPetType().toString() + " Type)");
        }
    }

    /**
     Calculates and displays the name of the pet that had the most wins after the last fight in the battle.
     */
    private void displayBattleWinner()
    {
        ioManager.getOutputStream().writeOutput("\n" + battle.getWinner().getPetName() + " won the Battle!");
    }

    /**
     Displays the number of fights that each pet won following the conclusion of the battle.
     */
    private void displayBattleResults()
    {
        ioManager.getOutputStream().writeOutput("\nThe Battle has ended! \nPet Win Counts: \n");
        for (int i = 0; i < battle.getPlayers().size(); i++)
        {
            ioManager.getOutputStream().writeOutput(battle.getPlayers().get(i).getPetName() +
                    ": " + battle.getWins(i) + " wins");
        }

        displayBattleWinner();
    }
}
