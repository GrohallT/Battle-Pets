package Control;

import Entity.*;

import java.util.Random;

/**
 * Calculates damages for each round of a battle based on a Playable's type
 * and attack choice.
 */
public class DamageCalculator {
    private static final int MAX_BASE_DAMAGE = 5;

    private final Random rng;

    /**
     * Constructs a new DamageCalculator.
     * @param rng The random number generator used to determine the base damage.
     */
    public DamageCalculator(Random rng) {
        this.rng = rng;
    }

    /**
     * Initializes a new Damage instance to store the base damage and conditional damage
     * of an attack.
     * @param matchup A RoundMatchup instance containing all pertinent data related to
     *                damage calculations for this Round between a player and their single opponent.
     * @return A new Damage instance that stores the base damage and conditional damage
     * dealt by the player.
     */
    public Damage calculateDamage(RoundMatchup matchup) {
        RoundMatchup.Contender player = matchup.getPlayer();
        RoundMatchup.Contender opponent = matchup.getOpponent();

        double randomDamage = rng.nextDouble() * MAX_BASE_DAMAGE;
        double conditionalDamage = 0;
        PetTypes playerPetType = player.getPlayable().getPetType();

        ShootTheMoonDamageCalculator shootTheMoonDamageCalculator = new ShootTheMoonDamageCalculator(player.getSkillPrediction(), opponent.getSkillChosen());
        ReversalOfFortuneDamageCalculator reversalOfFortuneDamageCalculator = new ReversalOfFortuneDamageCalculator(matchup.getPreviousRounds(), player.getPlayable());
        IntelligenceDamageCalculator intelligenceDamageCalculator = new IntelligenceDamageCalculator(opponent.getPlayable(), shootTheMoonDamageCalculator, reversalOfFortuneDamageCalculator);
        PowerDamageCalculator powerDamageCalculator = new PowerDamageCalculator(randomDamage, opponent.getSkillChosen(), shootTheMoonDamageCalculator, reversalOfFortuneDamageCalculator);
        SpeedDamageCalculator speedDamageCalculator = new SpeedDamageCalculator(opponent.getPlayable(), opponent.getSkillChosen(), shootTheMoonDamageCalculator, reversalOfFortuneDamageCalculator);

        // Determine how to calculate damage based on Pet type
        switch (playerPetType) {
            case INTELLIGENCE:
                conditionalDamage = calculateConditionalDamage(intelligenceDamageCalculator, player.getSkillChosen());
                break;
            case POWER:
                conditionalDamage = calculateConditionalDamage(powerDamageCalculator, player.getSkillChosen());
                break;
            case SPEED:
                conditionalDamage = calculateConditionalDamage(speedDamageCalculator, player.getSkillChosen());
                break;
        }

        // Add extra conditional damage to random damage if Reversal of Fortune was used
        if (player.getSkillChosen() == Skills.REVERSAL_OF_FORTUNE)
            randomDamage += conditionalDamage;

        return new Damage(randomDamage, conditionalDamage);
    }

    /**
     * Calculates the conditional damage of an attack.
     * @param calculator The type of DamageCalculator to use based on the Pet's type.
     * @param playerSkill The skill the player chose.
     * @return The conditional damage dealt by the player.
     */
    private double calculateConditionalDamage(TypeDamageCalculator calculator, Skills playerSkill) {
        // Determine how to calculate damage based on the Skill used
        switch (playerSkill) {
            case PAPER_CUT:
                return calculator.calculatePaperCutDamage();
            case ROCK_THROW:
                return calculator.calculateRockThrowDamage();
            case SCISSORS_POKE:
                return calculator.calculateScissorPokeDamage();
            case SHOOT_THE_MOON:
                return calculator.calculateShootTheMoonDamage();
            case REVERSAL_OF_FORTUNE:
                return calculator.calculateReversalOfFortuneDamage();
        }

        return 0;
    }
}