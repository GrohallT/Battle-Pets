package Entity;

/**
 * Contains all general game settings necessary to run a game of BattlePets.
 */
public class GameSettings {
    private final int fightsPerBattle;
    private final int playerCount;
    private final int seed;

    /**
     * Constructs a new GameSettings.
     * @param gameSettingsBuilder The corresponding Builder with all the
     *                            initialized settings to be used.
     */
    private GameSettings(GameSettingsBuilder gameSettingsBuilder) {
        // State check
        if (gameSettingsBuilder.fightsPerBattle <= 0)
            throw new IllegalStateException("Number of fights per battle must be more than 0.");
        if (gameSettingsBuilder.playerCount <= 1)
            throw new IllegalStateException("Number of players must be more than 1.");

        // Object construction
        this.fightsPerBattle = gameSettingsBuilder.fightsPerBattle;
        this.playerCount = gameSettingsBuilder.playerCount;
        this.seed = gameSettingsBuilder.seed;
    }

    public int getFightsPerBattle() {
        return fightsPerBattle;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public int getSeed() {
        return seed;
    }

    /**
     * Builder class for GameSettings.
     */
    public static class GameSettingsBuilder {
        private int fightsPerBattle;
        private int playerCount;
        private int seed;

        /**
         * Creates a new instance of GameSettings with the settings initialized
         * in this class.
         * @return The new GameSettings instance.
         */
        public GameSettings build() {
            return new GameSettings(this);
        }

        public GameSettingsBuilder withFightsPerBattle(int fightsPerBattle) {
            this.fightsPerBattle = fightsPerBattle;
            return this;
        }

        public GameSettingsBuilder withPlayerCount(int playerCount) {
            this.playerCount = playerCount;
            return this;
        }

        public GameSettingsBuilder withSeed(int seed) {
            this.seed = seed;
            return this;
        }
    }
}