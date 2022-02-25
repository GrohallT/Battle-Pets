package Control;

import Entity.Skills;

/**
 * Determines the conditional damage for all ShootTheMoon attacks.
 */
public class ShootTheMoonDamageCalculator implements SkillDamageCalculator {
    private static final int EXTRA_DAMAGE = 20;

    private final Skills opponentSKill;
    private final Skills skillPrediction;

    /**
     * Constructs a new ShootTheMoonDamageCalculator.
     * @param skillPrediction The skill the player predicted for the opponent.
     * @param opponentSKill The skill the opponent actually used.
     */
    public ShootTheMoonDamageCalculator(Skills skillPrediction, Skills opponentSKill) {
        this.skillPrediction = skillPrediction;
        this.opponentSKill = opponentSKill;
    }

    /**
     * Calculates the conditional damage for this attack.
     * @return The conditional damage.
     */
    @Override
    public double calculateDamage() {
        if (skillPrediction == opponentSKill)
            return EXTRA_DAMAGE;

        return 0;
    }
}