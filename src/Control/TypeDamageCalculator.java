package Control;

/**
 * Base interface for all PetType-based DamageCalculators. Responsible for determining
 * the conditional damage for each type of attack for any given Pet type.
 */
public interface TypeDamageCalculator {
    /**
     * Calculates the conditional damage for a Paper Cut attack.
     * @return The conditional damage.
     */
    double calculatePaperCutDamage();

    /**
     * Calculates the conditional damage for a Rock Throw attack.
     * @return The conditional damage.
     */
    double calculateRockThrowDamage();

    /**
     * Calculates the conditional damage for a Scissor Poke attack.
     * @return The conditional damage.
     */
    double calculateScissorPokeDamage();

    /**
     * Calculates the conditional damage for a Shoot the Moon attack.
     * @return The conditional damage.
     */
    double calculateShootTheMoonDamage();

    /**
     * Calculates the conditional damage for a Reversal of Fortune attack.
     * @return The conditional damage.
     */
    double calculateReversalOfFortuneDamage();
}