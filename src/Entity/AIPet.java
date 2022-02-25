package Entity;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class AIPet implements Playable {
    private Player player;
    private String name;
    private PetTypes type;
    private double startingHp;
    private double currHp;
    private Map<Skills, Skill> skillMap;
    private Random randomNumber;

    /**
     * private constructor for AIPet that utilizes the Builder pattern.
     * @param: an object of AIPetBuilder (from the Builder Pattern).
     */
    private AIPet(AIPetBuilder aiPetBuilder) {
        //state validations
        if (aiPetBuilder.player == null)
            throw new IllegalStateException("The player attribute cannot be null");
        if (aiPetBuilder.name == null)
            throw new IllegalStateException("The pet name cannot be null");
        if (aiPetBuilder.type == null)
            throw new IllegalStateException("The pet type cannot be null");
        if (aiPetBuilder.startingHp <= 0)
            throw new IllegalStateException("The pet's starting HP must be greater than zero");

        //initialize attribute values
        this.player = aiPetBuilder.player;
        this.name = aiPetBuilder.name;
        this.type = aiPetBuilder.type;
        this.startingHp = aiPetBuilder.startingHp;
        this.currHp = startingHp;

        // initialize Skills HashMap and fill it.
        skillMap = new EnumMap<>(Skills.class);
        skillMap.put(Skills.ROCK_THROW, new RockThrow());
        skillMap.put(Skills.SCISSORS_POKE, new ScissorPoke());
        skillMap.put(Skills.PAPER_CUT, new PaperCut());
        skillMap.put(Skills.REVERSAL_OF_FORTUNE, new ReversalOfFortune());
        skillMap.put(Skills.SHOOT_THE_MOON, new ShootTheMoon());

        // seeds the random number generator
        randomNumber = new Random(aiPetBuilder.seed);
    }

    @Override
    public String getPlayerName() {
        return this.player.getName();
    }

    @Override
    public String getPetName() {
        return this.name;
    }

    @Override
    public PlayerTypes getPlayerType() {
        return this.player.getType();
    }

    @Override
    public PetTypes getPetType() {
        return this.type;
    }

    @Override
    public double getCurrentHp() {
        return this.currHp;
    }

    /**
     * The AIPet will execute this to return a random skill to use
     * @return Returns the chosen skill
     */
    @Override
    public Skills chooseSkill() {
        // Choose a random skill
        return Skills.values()[randomNumber.nextInt(skillMap.size())];
    }

    /**
     * This method is called by the game controlling classes to update their AIPet's hp based on the damage inflicted
     *
     * @param hp: a double to subtract the pet's hp by
     */
    @Override
    public void updateHp(double hp) {
        this.currHp -= hp;
    }

    /**
     * Resets the AIPet's current hp to its starting hp
     */
    @Override
    public void resetHp() {
        this.currHp = startingHp;
    }

    /**
     * Sets the AIPet's hp to the provided value
     */
    @Override
    public void setCurrentHp(double currentHp) {
        this.currHp = currentHp;
    }

    /**
     * @return: Returns true if the AIPet's hp > 0, false otherwise
     */
    @Override
    public boolean isAwake() {
        return this.currHp > 0;
    }

    /**
     * This method is invoked when a player chooses the Shoot the Moon skill and must make a prediction for
     * their opponent's next move.
     */
    @Override
    public Skills getSkillPrediction()
    {
        // Choose a random skill
        return Skills.values()[randomNumber.nextInt(skillMap.size())];
    }

    /**
     * @return: Returns the current recharge time for the provided skill enumeration
     */
    @Override
    public int getSkillRechargeTime(Skills skill) {
        return this.skillMap.get(skill).getCurrentRecharge();
    }

    /**
     * This is somewhat of a convenience method, since there are methods to get the starting hp and current hp
     * @return: Returns the pet's current percent of hp
     */
    @Override
    public double calculateHpPercent() {
        return (currHp / startingHp);
    }

    /**
     * @return: Returns the AIPet's starting hp
     */
    @Override
    public double getStartingHp() {
        return this.startingHp;
    }

    /**
     * Called by the game controlling classes.
     * Resets the AIPet's hp to its starting hp
     * Resets all skills to what they were at the start of the fight
     */
    @Override
    public void reset() {
        this.resetHp();

        for (Skills skillType : skillMap.keySet())
            this.setRechargeTime(skillType, 0);
    }

    /**
     * Decrements the recharge times for all recharging skills
     */
    @Override
    public void decrementRechargeTimes() {
        for (Skills skillType : skillMap.keySet())
            this.setRechargeTime(skillType, this.getSkillRechargeTime(skillType) - 1);
    }

    /**
     * Sets the recharge time for the given skill
     */
    @Override
    public void setRechargeTime(Skills skill, int rechargeTime) {
        this.skillMap.get(skill).setCurrentRecharge(rechargeTime);
    }

    /**
     * Compares AIPets for equality.
     * @param o The other Object.
     * @return True if values are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AIPet aiPet = (AIPet) o;
        return Double.compare(aiPet.startingHp, startingHp) == 0 && Objects.equals(player, aiPet.player) && Objects.equals(name, aiPet.name) && type == aiPet.type;
    }

    /**
     * Generates a unique hash associated with this AIPet instance.
     * @return The unique integer hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(player, name, type, startingHp);
    }

    // -------------------------- PET BUILDER CLASS ------------------------- \\

    /**
     * The builder class for AIPets
     */
    public static class AIPetBuilder {
        private Player player;
        private String name;
        private PetTypes type;
        private double startingHp;
        private int seed;

        public AIPetBuilder() {

        }

        public AIPet build() {
            return new AIPet(this);
        }

        public AIPetBuilder withPlayer(Player player) {
            this.player = player;
            return this;
        }

        public AIPetBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AIPetBuilder withType(PetTypes type) {
            this.type = type;
            return this;
        }

        public AIPetBuilder withStartingHp(double startingHp) {
            this.startingHp = startingHp;
            return this;
        }

        public AIPetBuilder withSeed(int seed){
            this.seed = seed;
            return this;
        }
    }
}



