package Control;

import Entity.Playable;
import Entity.Skills;

/**
 * Determines the conditional damage for pets with the Intelligence type.
 */
public class IntelligenceDamageCalculator implements TypeDamageCalculator {
    private static final int HIGH_DAMAGE = 3;
    private static final int LOW_DAMAGE = 2;

    private final Playable opponent;
    private final ShootTheMoonDamageCalculator shootTheMoon;
    private final ReversalOfFortuneDamageCalculator reversalOfFortune;

    /**
     * Constructs a new IntelligenceDamageCalculator.
     * @param opponent The opponent in the round, used to determine conditional damage.
     */
    public IntelligenceDamageCalculator(Playable opponent, ShootTheMoonDamageCalculator shootTheMoon, ReversalOfFortuneDamageCalculator reversalOfFortune) {
        this.opponent = opponent;
        this.shootTheMoon = shootTheMoon;
        this.reversalOfFortune = reversalOfFortune;
    }

    /**
     * Calculates the conditional damage for a Paper Cut attack.
     * @return The conditional damage.
     */
    @Override
    public double calculatePaperCutDamage() {
        int conditionalDamage = 0;

        if (opponent.getSkillRechargeTime(Skills.SHOOT_THE_MOON) > 0)
            conditionalDamage += LOW_DAMAGE;

        if (opponent.getSkillRechargeTime(Skills.ROCK_THROW) > 0)
            conditionalDamage += HIGH_DAMAGE;

        if (opponent.getSkillRechargeTime(Skills.PAPER_CUT) > 0)
            conditionalDamage += LOW_DAMAGE;

        return conditionalDamage;
    }

    /**
     * Calculates the conditional damage for a Rock Throw attack.
     * @return The conditional damage.
     */
    @Override
    public double calculateRockThrowDamage() {
        int conditionalDamage = 0;

        if (opponent.getSkillRechargeTime(Skills.SHOOT_THE_MOON) > 0)
            conditionalDamage +=  LOW_DAMAGE;

        if (opponent.getSkillRechargeTime(Skills.SCISSORS_POKE) > 0)
            conditionalDamage +=  HIGH_DAMAGE;

        if (opponent.getSkillRechargeTime(Skills.ROCK_THROW) > 0)
            conditionalDamage +=  LOW_DAMAGE;

        return conditionalDamage;
    }

    /**
     * Calculates the conditional damage for a Scissor Poke attack.
     * @return The conditional damage.
     */
    @Override
    public double calculateScissorPokeDamage() {
        int conditionalDamage = 0;

        if (opponent.getSkillRechargeTime(Skills.SHOOT_THE_MOON) > 0)
            conditionalDamage += LOW_DAMAGE;

        if (opponent.getSkillRechargeTime(Skills.PAPER_CUT) > 0)
            conditionalDamage += HIGH_DAMAGE;

        if (opponent.getSkillRechargeTime(Skills.SCISSORS_POKE) > 0)
            conditionalDamage += LOW_DAMAGE;

        return  conditionalDamage;
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