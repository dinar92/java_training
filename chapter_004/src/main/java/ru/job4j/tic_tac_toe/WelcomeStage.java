package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.Display;
import ru.job4j.boardGame.Input;
import ru.job4j.boardGame.Stage;

/**
 * Greeting stage.
 */
class WelcomeStage implements Stage {

    /**
     * Provides the relation with players.
     */
    private final Input input;

    /**
     * Provides the info for players.
     */
    private final Display display;

    /**
     * Default constructor.
     *
     * @param input   input method.
     * @param display display method.
     */
    WelcomeStage(Input input, Display display) {
        this.input = input;
        this.display = display;
    }

    /**
     * Shows info about the current status of the game.
     */
    @Override
    public void showInfo() {
        display.showln("Welcome to the tic-tac-toe vs. computer!");
    }

    /**
     * The response on player's enter.
     */
    @Override
    public void action() {
        String in = input.getAnswer("Please, press enter to start the fun!");
        while (!("".equals(in))) {
            this.showInfo();
            in = input.getAnswer("Please, press enter again.");
        }
    }
}