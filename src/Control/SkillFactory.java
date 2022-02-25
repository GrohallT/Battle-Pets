package Control;

import Entity.*;

/**
 * Implementation of the Simple Factory Idiom to create concrete instantiations
 * of all Skill classes.
 */
public class SkillFactory {

    /**
     * Factory method to return a concrete class instance associated with a Skills enum.
     * @param skillType The type of Skill class to create.
     * @return The instantiated class instance associated with the provided Skills enum.
     */
    public static Skill getSkill(Skills skillType) {
        switch (skillType) {
            case ROCK_THROW: return new RockThrow();
            case SCISSORS_POKE: return new ScissorPoke();
            case PAPER_CUT: return new PaperCut();
            case SHOOT_THE_MOON: return new ShootTheMoon();
            case REVERSAL_OF_FORTUNE: return new ReversalOfFortune();
        }

        throw new IllegalArgumentException("Invalid Skills enum provided.");
    }
}