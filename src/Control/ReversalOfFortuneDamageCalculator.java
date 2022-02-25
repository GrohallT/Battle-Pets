package Control;

import Entity.Damage;
import Entity.Playable;
import Entity.Round;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Determines the conditional damage for all ReversalOfFortune attacks.
 */
public class ReversalOfFortuneDamageCalculator implements SkillDamageCalculator {

    private final List<Round> roundHistory;
    private final Playable playable;

    /**
     * Constructs a new ReversalOfFortuneDamageCalculator.
     * @param roundHistory A list of all previous Round data
     * @param playable The playable dealing the damage
     */
    public ReversalOfFortuneDamageCalculator(List<Round> roundHistory, Playable playable) {
        this.roundHistory = roundHistory;
        this.playable = playable;
    }

    /**
     * Calculates the conditional damage for this attack.
     * @return The conditional damage.
     */
    @Override
    public double calculateDamage() {
        double randomDamageTaken = 0;
        double randomDamageDealt = 0;

        for (Round round : roundHistory) {
            Map<Playable, Damage> damages = round.getDamagesDealt();
            List<Playable> players = new ArrayList<>(damages.keySet());
            int indexOfOpponent = players.indexOf(playable) == 0 ? players.size() - 1 : players.indexOf(playable) - 1;
            Playable prevPlayer = players.get(indexOfOpponent);

            randomDamageDealt += damages.get(playable).getRandomDamage();
            randomDamageTaken += damages.get(prevPlayer).getRandomDamage();
        }

        return -(randomDamageDealt - randomDamageTaken);
    }
}
