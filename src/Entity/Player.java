package Entity;

public class Player
{
    private PlayerTypes type;
    private String name;

    /**
     * private constructor for Player that utilizes the Builder pattern.
     * @param: an object of PlayerBuilder (from the Builder Pattern).
     */
    private Player(PlayerBuilder playerBuilder)
    {
        //state validations
        if (playerBuilder.type == null)
            throw new IllegalStateException("The player type cannot be null");
        if (playerBuilder.name == null)
            throw new IllegalStateException("The player name cannot be null");

        this.type = playerBuilder.type;
        this.name = playerBuilder.name;
    }

    public PlayerTypes getType()
    {
        return type;
    }

    public void setType(PlayerTypes type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    // -------------------------- PLAYER BUILDER CLASS ------------------------- \\

    public static class PlayerBuilder
    {
        private PlayerTypes type;
        private String name;

        public PlayerBuilder() {}

        public Player build() { return new Player(this); }

        public PlayerBuilder withType(PlayerTypes type)
        {
            this.type = type;
            return this;
        }

        public PlayerBuilder withName(String name)
        {
            this.name = name;
            return this;
        }
    }
}
