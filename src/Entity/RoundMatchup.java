package Entity;

import Entity.Skills;

import java.util.List;

/**
 * Data container for all relevant details that must be communicated when
 * calulating damage between a player and their opponent in a Round.
 */
public class RoundMatchup {

    /**
     * Data container for all information, per player, in a Round needed to
     * calculate damage dealt.
     */
    public static class Contender {
        private final Playable playable;
        private final Skills skillChosen;
        private final Skills skillPrediction;

        private Contender(ContenderBuilder builder) {
            playable = builder.playable;
            skillChosen = builder.skillChosen;
            skillPrediction = builder.skillPrediction;
        }

        public Playable getPlayable() {
            return playable;
        }

        public Skills getSkillChosen() {
            return skillChosen;
        }

        public Skills getSkillPrediction() {
            return skillPrediction;
        }

        /**
         * Builder for the Contender class.
         */
        public static class ContenderBuilder {
            private Playable playable;
            private Skills skillChosen;
            private Skills skillPrediction;

            /**
             * Builds a new instance of Contender with these settings.
             * @return The new Contender instance.
             */
            public Contender build() {
                return new Contender(this);
            }

            public ContenderBuilder withPlayable(Playable playable) {
                this.playable = playable;
                return this;
            }

            public ContenderBuilder withSkillChosen(Skills skillChosen) {
                this.skillChosen = skillChosen;
                return this;
            }

            public ContenderBuilder withSkillPrediction(Skills skillPrediction) {
                this.skillPrediction = skillPrediction;
                return this;
            }
        }
    }

    private final Contender opponent;
    private final Contender player;
    private final List<Round> previousRounds;

    public RoundMatchup(Contender player, Contender opponent, List<Round> previousRounds) {
        this.player = player;
        this.opponent = opponent;
        this.previousRounds = previousRounds;
    }

    public Contender getOpponent() { return this.opponent; }

    public Contender getPlayer() { return this.player; }

    public List<Round> getPreviousRounds() { return this.previousRounds; }
}