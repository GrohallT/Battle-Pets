/**
 * @author Taryn Grohall
 */
package Control;

import Boundary.IOManager;
import Entity.*;
import Entity.Skills;

import java.util.*;

/**
 * Controls the process of going through a fight in the game, goes until at least one pet is asleep.
 */
public class FightController {
    private Fight fight;
    private Round round;
    private final RoundController roundController;
    private final IOManager ioManager;

    /**
     * Parameterized constructor, initializes a FightController instance with a Random object and a List of Playable
     * objects. Assigns the randomNumber to the one passed in and does the same with the Playable List. Also creates the
     * roundController object.
     *
     * @param randomNumber of type Random
     */
    public FightController(Random randomNumber) {
        roundController = new RoundController(randomNumber);
        ioManager = IOManager.getInstance();
    }

    /**
     * The loop of the fightController that keeps going until at least one pet is asleep. Also shows who one the fight.
     * Resets the round at the beginning of each loop.
     *
     * @param playables as a List of Playable
     * @return fight as a Fight
     */
    public Fight start(List<Playable> playables) {
        fight = new Fight(playables);

        for (Playable p : fight.getPlayers())
            p.reset();

        while (petsAwake() >= 2) {
            fight.setRoundNumber(fight.getRoundNumber() + 1);
            updateActivePlayers();
            displayPreRoundInfo();

            round = roundController.getInfo(fight.getActivePlayers(), fight.getRoundList());
            fight.getRoundList().add(round);

            updateRechargeTimes();
            applyDamage();
            displayPostRoundInfo();
        }

        int winningPlayer = decideWinner();
        fight.setWinningPlayer(winningPlayer);

        return fight;
    }

    /**
     * Calls the decrement recharge time in the pet class to lower all of the recharge times not at zero, also sets the
     * skill that was used to be its maximum recharge value.
     */
    private void updateRechargeTimes() {
        for (int i = 0; i < fight.getActivePlayers().size(); i++) {
            Playable player = fight.getActivePlayers().get(i);
            player.decrementRechargeTimes();

            Skills skillChosen = round.getSkillsChosen().get(player);
            fight.getActivePlayers().get(i).setRechargeTime(skillChosen, SkillFactory.getSkill(skillChosen).getMaximumRecharge());
        }
    }

    /**
     * Sets the HP of each pet down to the amount it is after the damage has been calculated.
     */
    private void applyDamage() {
        // Sets each pets hp to the new hp after damage
        for (int i = 0; i < fight.getActivePlayers().size(); i++) {
            Playable player = fight.getActivePlayers().get(i);
            Playable opponent = fight.getActivePlayers().get((i + 1) % fight.getActivePlayers().size());
            opponent.updateHp(round.getDamagesDealt().get(player).calculateTotalDamage());
        }
    }

    /**
     * Checks to see how many pets are still awake
     * @return Number of pets awake
     */
    private int petsAwake() {
        int count = 0;

        for (int i = 0; i < fight.getPlayers().size(); i++)
            if (fight.getPlayers().get(i).isAwake())
                count++;

        return count;
    }

    /**
     * Updates the list of active players so that we can focus only on the pets that are not asleep.
     */
    private void updateActivePlayers(){
        List<Playable> updatedList = new ArrayList<>();

        for (int i = 0; i < fight.getPlayers().size(); i++) {
            if (fight.getPlayers().get(i).isAwake()) {
                updatedList.add(fight.getPlayers().get(i));
            }
        }

        fight.setActivePlayers(updatedList);
    }

    /**
     * Loops through to figure out the winning player.
     * @return The winning player number
     */
    private int decideWinner() {
        Playable playerWithMostHP = fight.getActivePlayers().get(0);

        for (int i = 1; i < fight.getActivePlayers().size(); i++) {
            Playable player = fight.getActivePlayers().get(i);

            if (player.getCurrentHp() > playerWithMostHP.getCurrentHp())
                playerWithMostHP = player;
        }

        // Convert active player index to overall player index
        return fight.getPlayers().indexOf(playerWithMostHP);
    }

    /**
     * Displays the pet stats of HP and recharge times at the beginning of each round, the order of these is displayed based
     * on the amount of HP each pet has. Higher HP's appear at the top.
     */
    private void displayPreRoundInfo() {
        List<Playable> hpOrderedPlayables = new ArrayList<>(fight.getActivePlayers());
        hpOrderedPlayables.sort(Comparator.comparingDouble(Playable::getCurrentHp));

        ioManager.getOutputStream().writeOutput("\nRound #" + fight.getRoundNumber());

        for (Playable p : hpOrderedPlayables) {
            ioManager.getOutputStream().writeOutput(p.getPetName() + "'s Info:");
            ioManager.getOutputStream().writeOutput("HP: " + p.getCurrentHp());
            ioManager.getOutputStream().writeOutput("Rock Throw Recharge: " + p.getSkillRechargeTime(Skills.ROCK_THROW));
            ioManager.getOutputStream().writeOutput("Scissor Poke Recharge: " + p.getSkillRechargeTime(Skills.SCISSORS_POKE));
            ioManager.getOutputStream().writeOutput("Paper Cut Recharge: " + p.getSkillRechargeTime(Skills.PAPER_CUT));
            ioManager.getOutputStream().writeOutput("Shoot the Moon Recharge: " + p.getSkillRechargeTime(Skills.SHOOT_THE_MOON));
            ioManager.getOutputStream().writeOutput("Reversal of Fortune Recharge: " + p.getSkillRechargeTime(Skills.REVERSAL_OF_FORTUNE) + "\n");
        }
    }

    /**
     * Displays the amount of damage each pet dealt at the end of each round.
     */
    private void displayPostRoundInfo() {
        for (int i = 0; i < fight.getActivePlayers().size(); i++) {
            Playable currentPlayer = fight.getActivePlayers().get(i);

            ioManager.getOutputStream().writeOutput(currentPlayer.getPetName() + "'s Skill Chosen: " + round.getSkillsChosen().get(currentPlayer));
            ioManager.getOutputStream().writeOutput(currentPlayer.getPetName() + "'s Random Damage Dealt: " + round.getDamagesDealt().get(currentPlayer).getRandomDamage());
            ioManager.getOutputStream().writeOutput(currentPlayer.getPetName() + "'s Conditional Damage Dealt: " + round.getDamagesDealt().get(currentPlayer).getConditionalDamage());
            ioManager.getOutputStream().writeOutput(currentPlayer.getPetName() + "'s Total Damage Dealt: " + round.getDamagesDealt().get(currentPlayer).calculateTotalDamage());

            Playable previousPlayer;
            if(i - 1 >= 0)
                previousPlayer = fight.getActivePlayers().get(i - 1);
            else
                previousPlayer = fight.getActivePlayers().get(fight.getActivePlayers().size() - 1);
            ioManager.getOutputStream().writeOutput(currentPlayer.getPetName() + "'s Random Damage Difference: " +
                    (round.getDamagesDealt().get(currentPlayer).getRandomDamage() - round.getDamagesDealt().get(previousPlayer).getRandomDamage()) + "\n");
        }
    }
}
