package Entity;

/**
 * Data related to the ShootTheMoon Skill.
 */
public class ShootTheMoon extends Skill {

    /**
     * Constructs a new ShootTheMoon Skill.
     */
    public ShootTheMoon() {
        super(Skills.SHOOT_THE_MOON);
    }

    @Override
    public int getMaximumRecharge() { return 6; }
}
