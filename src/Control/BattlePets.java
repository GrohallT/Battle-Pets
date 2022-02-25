package Control;

import Boundary.IOManager;
import Entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Primary driver class for the BattlePets program.
 */
public class BattlePets {
    private static final int OPTION_NEW_BATTLE = 1;
    private static final int OPTION_NEW_SEASON = 2;
    private static final int OPTION_QUIT = 3;
    private static final String TITLE =
            "=====================================================\n" +
            "    ____        __  __  __        ____       __      \n" +
            "   / __ )____ _/ /_/ /_/ /__     / __ \\___  / /______\n" +
            "  / __  / __ `/ __/ __/ / _ \\   / /_/ / _ \\/ __/ ___/\n" +
            " / /_/ / /_/ / /_/ /_/ /  __/  / ____/  __/ /_(__  ) \n" +
            "/_____/\\__,_/\\__/\\__/_/\\___/  /_/    \\___/\\__/____/\n" +
            "=====================================================";

    private BattleController battleController;
    private IOManager ioManager;

    public static void main(String[] args) {
        // Initialize
        BattlePets game = new BattlePets();
        game.ioManager = IOManager.getInstance();

        // Main Menu
        int response = game.doMainMenu();

        switch (response) {
            case OPTION_NEW_BATTLE:
            case OPTION_NEW_SEASON:
                game.startGame(game, response);
                break;
            case OPTION_QUIT:
                System.exit(0);
                break;
        }
    }

    /**
     * Displays the main menu and waits for the player's selection.
     * @return An integer representing any of the valid menu options.
     */
    private int doMainMenu() {
        System.out.println(TITLE);
        System.out.println("-=MAIN MENU=-\n" +
                "1) New Battle\n" +
                "2) New Season\n" +
                "3) Quit");

        String optionResponse = ioManager.getInputStream().readInput();
        while (!InputUtils.isIntegerInRange(optionResponse, OPTION_NEW_BATTLE, OPTION_QUIT)) {
            System.out.println("Input must be an integer between " + OPTION_NEW_BATTLE + " and " + OPTION_QUIT + ".");
            optionResponse = ioManager.getInputStream().readInput();
        }

        return Integer.parseInt(optionResponse);
    }

    /**
     * Allows the user to replay the game, or to break out of the main gameplay loop.
     * @return True if the user wishes to play again, false otherwise.
     */
    private boolean replay() {
        // Prompt the user if they want to play again
        ioManager.getOutputStream().writeOutput("Would you like to play again? [Y/N]: ");
        String replayResponse = ioManager.getInputStream().readInput();

        // Keep asking for input, so long as their response is invalid
        while (!InputUtils.isCharacters(replayResponse, 'Y', 'N')) {
            System.out.println("Please enter a valid response [Y/N]:");
            replayResponse = ioManager.getInputStream().readInput();
        }

        return replayResponse.toUpperCase().charAt(0) == 'Y';
    }

    /**
     * Begins a new game of BattlePets. Players initialize their game settings,
     * players, and pets before starting their battle.
     * @param game The instance of a BattlePets game to run.
     * @param gameType The type of game the player wishes to use:
     *                 1 = Battle
     *                 2 = Season
     */
    private void startGame(BattlePets game, int gameType) {
        // Initialize game settings
        GameSettings gameSettings = GameInitializer.initializeGame();
        int playerCount = gameSettings.getPlayerCount();

        // Initialize contenders
        Playable[] playables = new Playable[playerCount];
        for (int i = 0; i < playerCount; i++) {
            game.ioManager.getOutputStream().writeOutput("Currently setting up Player " + (i + 1) + "...");

            Player p = GameInitializer.initializePlayer();
            if (p.getType() == PlayerTypes.HUMAN)
                playables[i] = GameInitializer.initializePet(p);
            else
                playables[i] = GameInitializer.initializeAIPet(p);
        }

        // Start the game
        do {
            game.battleController = new BattleController(new Random(gameSettings.getSeed()), gameSettings.getFightsPerBattle());
            if (gameType == 1)
                game.battleController.start(Utils.arrayToList(playables));
            else {
                SeasonController seasonController = new SeasonController(new Random(gameSettings.getSeed()), game.battleController);
                seasonController.start(Utils.arrayToList(playables));
            }
        } while (replay());
    }
}