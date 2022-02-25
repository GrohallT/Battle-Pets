package Control;

import Entity.Skills;

/**
 * Determines the conditional damage for pets with the Power type.
 */
public class PowerDamageCalculator implements TypeDamageCalculator {
    private static final int DAMAGE_MULTIPLIER = 5;

    private final double randomDamage;
    private final Skills opponentSkill;
    private final ShootTheMoonDamageCalculator shootTheMoon;
    private final ReversalOfFortuneDamageCalculator reversalOfFortune;

    /**
     * Constructs a new PowerDamageCalculator.
     * @param randomDamage The base damage done by the Playable.
     * @param opponentSkill The skill choice used by the opponent.
     */
    public PowerDamageCalculator(double randomDamage, Skills opponentSkill, ShootTheMoonDamageCalculator shootTheMoon, ReversalOfFortuneDamageCalculator reversalOfFortune) {
        this.randomDamage = randomDamage;
        this.opponentSkill = opponentSkill;
        this.shootTheMoon = shootTheMoon;
        this.reversalOfFortune = reversalOfFortune;
    }

    /**
     * Calculates the conditional damage for a Paper Cut attack.
     * @return The conditional damage.
     */
    @Override
    public double calculatePaperCutDamage() {
        return opponentSkill == Skills.ROCK_THROW ? DAMAGE_MULTIPLIER * randomDamage : 0;
    }

    /**
     * Calculates the conditional damage for a Rock Throw attack.
     * @return The conditional damage.
     */
    @Override
    public double calculateRockThrowDamage() {
        return opponentSkill == Skills.SCISSORS_POKE ? DAMAGE_MULTIPLIER * randomDamage : 0;
    }

    /**
     * Calculates the conditional damage for a Scissor Poke attack.
     * @return The conditional damage.
     */
    @Override
    public double calculateScissorPokeDamage() {
        return opponentSkill == Skills.PAPER_CUT ? DAMAGE_MULTIPLIER * randomDamage : 0;
    }

    /**
     * Calculates the conditional damage for a Shoot the Moon attack.
     * @return The conditional damage.
     */
    @Override
    public double calculateShootTheMoonDamage() {
        return shootTheMoon.calculateDamage();
    }

    /**
     * Calculates the conditional damage for a Reversal of Fortune attack.
     * @return The conditional damage.
     */
    @Override
    public double calculateReversalOfFortuneDamage() {
        return reversalOfFortune.calculateDamage();
    }
}