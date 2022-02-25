package Entity;

/**
 * Data related to the ReversalOfFortune Skill.
 */
public class ReversalOfFortune extends Skill {

    /**
     * Constructs a new ReversalOfFortune Skill.
     */
    public ReversalOfFortune() {
        super(Skills.REVERSAL_OF_FORTUNE);
    }

    @Override
    public int getMaximumRecharge() { return 6; }
}
