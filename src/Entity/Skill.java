package Entity;

/**
 * Data related to all Skills that can be used in the game.
 */
public class Skill {
    private final Skills skillType;
    private int currentRecharge;

    /**
     * Constructs a new Skill.
     * @param skillType The type of Skill this represents.
     */
    public Skill(Skills skillType) {
        this.skillType = skillType;
    }

    public int getCurrentRecharge() { return currentRecharge; }

    public void setCurrentRecharge(int currentRecharge) { this.currentRecharge = Math.max(0, currentRecharge); }

    public int getMaximumRecharge() { return 1; }

    public Skills getSkillType() { return skillType; }
}
