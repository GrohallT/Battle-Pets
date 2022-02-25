package Entity;

import Boundary.IOManager;
import Control.InputUtils;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class Pet implements Playable {
    private Player player;
    private String name;
    private PetTypes type;
    private double startingHp;
    private double currHp;
    private Map<Skills, Skill> skillMap;

    /**
     * private constructor for Pet that utilizes the Builder pattern.
     * @param: an object of PetBuilder (from the Builder Pattern).
     */
    private Pet(PetBuilder petBuilder) {
        //state validations
        if (petBuilder.player == null)
            throw new IllegalStateException("The player attribute cannot be null");
        if (petBuilder.name == null)
            throw new IllegalStateException("The pet name cannot be null");
        if (petBuilder.type == null)
            throw new IllegalStateException("The pet type cannot be null");
        if (petBuilder.startingHp <= 0)
            throw new IllegalStateException("The pet's starting HP must be greater than zero");

        //initialize attribute values
        this.player = petBuilder.player;
        this.name = petBuilder.name;
        this.type = petBuilder.type;
        this.startingHp = petBuilder.startingHp;
        this.currHp = startingHp;

        // initialize Skills HashMap and fill it.
        skillMap = new EnumMap<>(Skills.class);
        skillMap.put(Skills.ROCK_THROW, new RockThrow());
        skillMap.put(Skills.SCISSORS_POKE, new ScissorPoke());
        skillMap.put(Skills.PAPER_CUT, new PaperCut());
        skillMap.put(Skills.REVERSAL_OF_FORTUNE, new ReversalOfFortune());
        skillMap.put(Skills.SHOOT_THE_MOON, new ShootTheMoon());
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
     * The pet will execute logic or prompt the user to choose a skill depending on the pet type
     * @return Returns the chosen skill
     */
    @Override
    public Skills chooseSkill() {
        IOManager ioManager = IOManager.getInstance();

        // Keep asking for input, so long as their response is invalid
        String skillResponse = ioManager.getInputStream().readInput();
        while (!InputUtils.isIntegerInRange(skillResponse, 1, skillMap.size())) {
            System.out.println("Input must be between " + 1 + " and " + skillMap.size() + ".");
            skillResponse = ioManager.getInputStream().readInput();
        }

        // Determine the user's Skill choice
        return Skills.values()[Integer.parseInt(skillResponse) - 1];
    }

    /**
     * This method is called by the game controlling classes to update their pet's hp based on the damage inflicted
     *
     * @param hp: a double to subtract the pet's hp by
     */
    @Override
    public void updateHp(double hp) {
        this.currHp -= hp;
    }

    /**
     * Resets the pet's current hp to its starting hp
     */
    @Override
    public void resetHp() {
        this.currHp = startingHp;
    }

    /**
     * Sets the pet's hp to the provided value
     */
    @Override
    public void setCurrentHp(double currentHp) {
        this.currHp = currentHp;
    }

    /**
     * @return: Returns true if the pet's hp > 0, false otherwise
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
        IOManager ioManager = IOManager.getInstance();

        // Keep asking for input, so long as their response is invalid
        String skillResponse = ioManager.getInputStream().readInput();
        while (!InputUtils.isIntegerInRange(skillResponse, 1, skillMap.size())) {
            System.out.println("Input must be between " + 1 + " and " + skillMap.size() + ".");
            skillResponse = ioManager.getInputStream().readInput();
        }

        // Determine the user's Skill choice
        return Skills.values()[Integer.parseInt(skillResponse) - 1];
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
     * @return: Returns the pet's starting hp
     */
    @Override
    public double getStartingHp() {
        return this.startingHp;
    }

    /**
     * Called by the game controlling classes.
     * Resets the pet's hp to its starting hp
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
     * Compares Pets for equality.
     * @param o The other Object.
     * @return True if values are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Double.compare(pet.startingHp, startingHp) == 0 && Objects.equals(player, pet.player) && Objects.equals(name, pet.name) && type == pet.type;
    }

    /**
     * Generates a unique hash associated with this Pet instance.
     * @return The unique integer hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(player, name, type, startingHp);
    }

    // -------------------------- PET BUILDER CLASS ------------------------- \\

    /**
     * The builder class for Pets
     */
    public static class PetBuilder {
        private Player player;
        private String name;
        private PetTypes type;
        private double startingHp;

        public PetBuilder() {

        }

        public Pet build() {
            return new Pet(this);
        }

        public PetBuilder withPlayer(Player player) {
            this.player = player;
            return this;
        }

        public PetBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PetBuilder withType(PetTypes type) {
            this.type = type;
            return this;
        }

        public PetBuilder withStartingHp(double startingHp) {
            this.startingHp = startingHp;
            return this;
        }
    }
}



