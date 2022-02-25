/**
 *This class holds data associated with a round during the game.
 * This data includes the skill choices of each player as well as
 * 2 Damage class instances which store the damage dealt by each pet.
 */

/**
 * @author Peter Humphreys
 */

package Entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Round
{
    private final Map<Playable, Skills> skillsChosen;
    private final Map<Playable, Damage> damagesDealt;

    public Round() {
        skillsChosen = new LinkedHashMap<>();
        damagesDealt = new LinkedHashMap<>();
    }

    public Map<Playable, Skills> getSkillsChosen() { return this.skillsChosen; }
    public Map<Playable, Damage> getDamagesDealt() { return this.damagesDealt; }
}
