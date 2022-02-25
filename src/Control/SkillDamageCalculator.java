package Control;

/**
 * Base interface for all Skill-specific DamageCalculators. Responsible for determining
 * the conditional damage for this attack. Good for special attacks where all
 * PetTypes do the same thing.
 */
public interface SkillDamageCalculator {

    /**
     * Calculates the conditional damage for this attack.
     * @return The conditional damage.
     */
    double calculateDamage();
}