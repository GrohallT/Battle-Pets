package Control;

import Boundary.IOManager;
import Entity.*;

/**
 * Initializes all settings related to a game of BattlePets.
 */
public class GameInitializer {
    private static final IOManager ioManager = IOManager.getInstance();

    /**
     * Initializes all general game settings for a BattlePets game. This includes
     * player count, random seed, and the number of fights per battle.
     * @return A GameSettings class which contains all the initialized settings.
     */
    public static GameSettings initializeGame() {
        GameSettings.GameSettingsBuilder gameSettingsBuilder = new GameSettings.GameSettingsBuilder();

        // Initial prompt
        ioManager.getOutputStream().writeOutput("\nWelcome to Doug's Unicorn Knights' Battle Pets!");

        // Setup number of players
        ioManager.getOutputStream().writeOutput("Please enter the number of players:");

        // Keep asking for input, so long as their response is invalid
        String playersResponse = ioManager.getInputStream().readInput();
        while (!InputUtils.isIntegerAndAbove(playersResponse, 1)) {
            ioManager.getOutputStream().writeOutput("Number of players must be an integer above 1.");
            playersResponse = ioManager.getInputStream().readInput();
        }

        gameSettingsBuilder.withPlayerCount(Integer.parseInt(playersResponse));

        // Get game seed
        ioManager.getOutputStream().writeOutput("Please enter a game seed:");

        // Keep asking for input, so long as their response is invalid
        String seedResponse = ioManager.getInputStream().readInput();
        while (!InputUtils.isInteger(seedResponse)) {
            ioManager.getOutputStream().writeOutput("Please enter a valid integer seed.");
            seedResponse = ioManager.getInputStream().readInput();
        }

        gameSettingsBuilder.withSeed(Integer.parseInt(seedResponse));

        // Get number of fights per battle
        ioManager.getOutputStream().writeOutput("Please enter the number of Fights per Battle:");

        // Keep asking for input, so long as their response is invalid
        String fpbResponse = ioManager.getInputStream().readInput();
        while (!InputUtils.isIntegerAndAbove(fpbResponse, 0)) {
            ioManager.getOutputStream().writeOutput("Fights per battle must be an integer above 0.");
            fpbResponse = ioManager.getInputStream().readInput();
        }

        gameSettingsBuilder.withFightsPerBattle(Integer.parseInt(fpbResponse));

        return gameSettingsBuilder.build();
    }

    /**
     * Initializes all settings related to a Pet instance.
     * @param player The player associated with this pet.
     * @return An initialized Pet instance with the specified settings.
     */
    public static Pet initializePet(Player player) {
        Pet.PetBuilder petBuilder = new Pet.PetBuilder();

        // Set Player
        petBuilder.withPlayer(player);

        // Get Pet Type
        ioManager.getOutputStream().writeOutput("Please enter a pet type:\n" +
                "\t1) Power\n" +
                "\t2) Speed\n" +
                "\t3) Intelligence");

        // Keep asking for input, so long as their response is invalid
        String petTypeResponse = ioManager.getInputStream().readInput();
        while (!InputUtils.isCharacters(petTypeResponse, '1', '2', '3')) {
            ioManager.getOutputStream().writeOutput("Input must be between an integer 1 and 3.");
            petTypeResponse = ioManager.getInputStream().readInput();
        }

        petBuilder.withType(PetTypes.values()[Integer.parseInt(petTypeResponse) - 1]);

        // Get Pet Name
        ioManager.getOutputStream().writeOutput("Please enter your pet's name:");

        String petNameResponse = ioManager.getInputStream().readInput();
        while(petNameResponse.length() == 0) {
            ioManager.getOutputStream().writeOutput("Please enter a valid name.");
            petNameResponse = ioManager.getInputStream().readInput();
        }

        petBuilder.withName(petNameResponse.trim());

        // Get Pet Starting HP
        ioManager.getOutputStream().writeOutput("Please enter your pet's starting HP:");
        String startingHPResponse = ioManager.getInputStream().readInput();

        // Keep asking for input, so long as their response is invalid
        while (!InputUtils.isDoubleAndAbove(startingHPResponse, 0)) {
            ioManager.getOutputStream().writeOutput("Starting HP must be more than 0.");
            startingHPResponse = ioManager.getInputStream().readInput();
        }

        petBuilder.withStartingHp(Double.parseDouble(startingHPResponse));

        return petBuilder.build();
    }

    /**
     * Initializes all settings related to an AIPet instance.
     * @param player The player associated with this pet.
     * @return An initialized Pet instance with the specified settings.
     */
    public static AIPet initializeAIPet(Player player) {
        AIPet.AIPetBuilder petBuilder = new AIPet.AIPetBuilder();

        // Set Player
        petBuilder.withPlayer(player);

        // Get Pet Type
        ioManager.getOutputStream().writeOutput("Please enter a pet type:\n" +
                "\t1) Power\n" +
                "\t2) Speed\n" +
                "\t3) Intelligence");

        // Keep asking for input, so long as their response is invalid
        String petTypeResponse = ioManager.getInputStream().readInput();
        while (!InputUtils.isCharacters(petTypeResponse, '1', '2', '3')) {
            ioManager.getOutputStream().writeOutput("Input must be between an integer 1 and 3.");
            petTypeResponse = ioManager.getInputStream().readInput();
        }

        petBuilder.withType(PetTypes.values()[Integer.parseInt(petTypeResponse) - 1]);

        // Get Pet Name
        ioManager.getOutputStream().writeOutput("Please enter your pet's name:");

        String petNameResponse = ioManager.getInputStream().readInput();
        while(petNameResponse.length() == 0) {
            ioManager.getOutputStream().writeOutput("Please enter a valid name.");
            petNameResponse = ioManager.getInputStream().readInput();
        }

        petBuilder.withName(petNameResponse.trim());

        // Get Pet Starting HP
        ioManager.getOutputStream().writeOutput("Please enter your pet's starting HP:");
        String startingHPResponse = ioManager.getInputStream().readInput();

        // Keep asking for input, so long as their response is invalid
        while (!InputUtils.isDoubleAndAbove(startingHPResponse, 0)) {
            ioManager.getOutputStream().writeOutput("Starting HP must be more than 0.");
            startingHPResponse = ioManager.getInputStream().readInput();
        }

        petBuilder.withStartingHp(Double.parseDouble(startingHPResponse));

        // Get Pet Random Seed
        ioManager.getOutputStream().writeOutput("Please enter your pet's random seed:");
        String seedResponse = ioManager.getInputStream().readInput();

        // Keep asking for input, so long as their response is invalid
        while (!InputUtils.isInteger(seedResponse)) {
            ioManager.getOutputStream().writeOutput("Please enter a valid integer seed.");
            seedResponse = ioManager.getInputStream().readInput();
        }

        petBuilder.withSeed(Integer.parseInt(seedResponse));

        return petBuilder.build();
    }

    /**
     * Initializes all settings related to a Player instance.
     * @return An initialized Player instance with the specified settings.
     */
    public static Player initializePlayer() {
        Player.PlayerBuilder playerBuilder = new Player.PlayerBuilder();

        // Get Player Type
        ioManager.getOutputStream().writeOutput("Please enter a player type:\n" +
                "\t1) Human\n" +
                "\t2) Computer");

        // Keep asking for input, so long as their response is invalid
        String playerTypeResponse = ioManager.getInputStream().readInput();
        while (!InputUtils.isCharacters(playerTypeResponse, '1', '2')) {
            ioManager.getOutputStream().writeOutput("Player Type must be a 1 or 2.");
            playerTypeResponse = ioManager.getInputStream().readInput();
        }

        PlayerTypes playerType = PlayerTypes.values()[Integer.parseInt(playerTypeResponse) - 1];
        playerBuilder.withType(playerType);

        // Get Player Name
        if (playerType == PlayerTypes.HUMAN) {
            ioManager.getOutputStream().writeOutput("Please enter your name:");

            String playerNameResponse = ioManager.getInputStream().readInput();
            while(playerNameResponse.length() == 0) {
                ioManager.getOutputStream().writeOutput("Please enter a valid name.");
                playerNameResponse = ioManager.getInputStream().readInput();
            }

            playerBuilder.withName(playerNameResponse.trim());
        }
        else {
            playerBuilder.withName("Computer");
        }

        return playerBuilder.build();
    }
}