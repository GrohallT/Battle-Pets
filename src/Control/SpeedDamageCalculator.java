package Control;

import Entity.Playable;
import Entity.Skills;

/**
 * Determines the conditional damage for pets with the Speed type.
 */
public class SpeedDamageCalculator implements TypeDamageCalculator {
    private static final double EXTRA_DAMAGE = 12.5;
    private static final double LOWER_THRESHOLD = 0.25;
    private static final double UPPER_THRESHOLD = 0.75;

    private final Playable opponent;
    private final Skills opponentSkill;
    private final ShootTheMoonDamageCalculator shootTheMoon;
    private final ReversalOfFortuneDamageCalculator reversalOfFortune;

    /**
     * Constructs a new SpeedDamageCalculator.
     * @param opponent The opponent in the round, used to determine
     *                 conditional damage.
     * @param opponentSkill The skill choice used by the opponent.
     */
    public SpeedDamageCalculator(Playable opponent, Skills opponentSkill, ShootTheMoonDamageCalculator shootTheMoon, ReversalOfFortuneDamageCalculator reversalOfFortune) {
        this.opponent = opponent;
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
        if ((opponent.calculateHpPercent() >= 0 && opponent.calculateHpPercent() < LOWER_THRESHOLD) &&
                (opponentSkill == Skills.ROCK_THROW || opponentSkill == Skills.SCISSORS_POKE))
            return EXTRA_DAMAGE;

        return 0;
    }

    /**
     * Calculates the conditional damage for a Rock Throw attack.
     * @return The conditional damage.
     */
    @Override
    public double calculateRockThrowDamage() {
        if (opponent.calculateHpPercent() >= UPPER_THRESHOLD &&
                (opponentSkill == Skills.SCISSORS_POKE || opponentSkill == Skills.PAPER_CUT))
            return EXTRA_DAMAGE;

        return 0;
    }

    /**
     * Calculates the conditional damage for a Scissor Poke attack.
     * @return The conditional damage.
     */
    @Override
    public double calculateScissorPokeDamage() {
        if ((opponent.calculateHpPercent() >= LOWER_THRESHOLD && opponent.calculateHpPercent() < UPPER_THRESHOLD) &&
                (opponentSkill == Skills.ROCK_THROW || opponentSkill == Skills.PAPER_CUT))
            return EXTRA_DAMAGE;

        return 0;
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