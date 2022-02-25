/**
 * Manages an individual round.  Gets input from both players and checks if selected skills are valid.
 * Takes in a random number and passes it to DamageCalculator to calculate total damage inflicted on each player.
 */

/**
 * @author Peter Humphreys
 */

package Control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Entity.*;
import Boundary.IOManager;
import Entity.Skills;

public class RoundController
{
    private final Random rng;
    private Round round;
    private List<Round> previousRounds;
    private List <Playable> playables;
    private final DamageCalculator damageCalculator;
    private final IOManager ioManager;

    /**
     * Parameterized constructor, initializes a RoundController instance with rng, DamageCalculator and ioManager.
     * round and playables are initialized as null.
     * @param rng of type Random
     */
    public RoundController(Random rng)
    {
        this.rng = rng;
        this.round = null;
        this.playables = null;
        this.damageCalculator = new DamageCalculator(rng);
        this.ioManager = IOManager.getInstance();
    }

    /**
     * Creates an instance of Round, sets its attributes and returns the Round.
     * @param playables of type List<Playables>
     * @return this RoundController's Round instance
     */
    public Round getInfo(List<Playable> playables, List<Round> previousRounds)
    {
        this.playables = playables;
        round = new Round();

        //temp lists to store data in this Round
        List <RoundMatchup.Contender> contenders = new ArrayList<>();

        //iterate through playables list and set skills
        for (int i = 0; i < playables.size(); i ++)
        {
            RoundMatchup.Contender.ContenderBuilder contenderBuilder = new RoundMatchup.Contender.ContenderBuilder();
            Playable player = playables.get(i);

            contenderBuilder.withPlayable(player);

            Skills skill = getSkillChoice(player);
            contenderBuilder.withSkillChosen(skill);
            round.getSkillsChosen().put(player, skill);

            if (skill == Skills.SHOOT_THE_MOON)
                contenderBuilder.withSkillPrediction(getSkillPrediction(player));

            contenders.add(contenderBuilder.build());
        }

        //iterate through playables list and set damages
        for (int i = 0; i < playables.size(); i ++)
        {
            int next = (i + 1) % playables.size();
            round.getDamagesDealt().put(playables.get(i), calculateDamage(contenders.get(i), contenders.get(next), previousRounds));
        }

        return round;
    }

    /**
     * Prompts player for Skill and checks its validity. Loops until player has chosen valid Skill and
     * then returns that Skill.
     * @param playable of type Playable
     * @return Skill selected by Playable
     */
    private Skills getSkillChoice(Playable playable)
    {
        boolean skillChosen = false;
        Skills skillType = null;

        while (!skillChosen)
        {
            //Get Skill choice from the Pet
            ioManager.getOutputStream().writeOutput(playable.getPetName() + ", please select a Skill:");
            Skills[] skills = Skills.values();
            for (int i = 0; i < skills.length; i++)
                ioManager.getOutputStream().writeOutput("\t" + (i + 1) + ") " + skills[i].toString());

            skillType = playable.chooseSkill();

            //Determine if Skill is able to be used
            if (isValidSkill(playable, skillType))
                skillChosen = true;
            else
                ioManager.getOutputStream().writeOutput("That Skill needs to finish recharging before being used again.");
        }

        return skillType;
    }

    /**
     * Prompts player for Skill that they think the opponent will use in this Round.
     * @param playable of type Playable
     * @return Skill prediction selected by Playable
     */
    private Skills getSkillPrediction(Playable playable)
    {
        //Get Skill prediction from the Pet
        ioManager.getOutputStream().writeOutput(playable.getPetName() + ", please predict your opponent's Skill:");
        Skills[] skills = Skills.values();
        for (int i = 0; i < skills.length; i++)
            ioManager.getOutputStream().writeOutput("\t" + (i + 1) + ") " + skills[i].toString());

        return playable.chooseSkill();
    }

    /*private Skills getComputerInput()
    {
        return new Skill(skillType);
    }*/

    /**
     * Calculates the damages dealt by each pet.
     * @param player The player dealing damage
     * @param opponent The opponent taking damage
     * @param previousRounds The list of all previous Rounds for damage calculations
     * @return The Damage instance storing the Damage the player dealt to the opponent
     */
    private Damage calculateDamage(RoundMatchup.Contender player, RoundMatchup.Contender opponent, List<Round> previousRounds)
    {
        return damageCalculator.calculateDamage(new RoundMatchup(player, opponent, previousRounds));
    }

    /**
     * Checks if the passed skill needs to recharge for the specified pet.
     * @param playable of type Playable, skillType of type Skills
     * @return true if skill doesn't need to recharge, false otherwise.
     */
    private boolean isValidSkill(Playable playable, Skills skillType)
    {
        return playable.getSkillRechargeTime(skillType) == 0;
    }
}