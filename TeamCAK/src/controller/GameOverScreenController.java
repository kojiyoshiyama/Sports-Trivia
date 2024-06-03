/*
 * TeamCAK TriviaMaze Game - Controller Package
 * Fall 2023
 */

package src.controller;

import src.view.GameOverScreen;
import java.io.IOException;

/**
 * This class is the controller for the mouse clicked involved with the game over screen.
 *
 * @author Koji Yoshiyama, Calvin Beardemphl, Alex Thompson
 * @verison 15/12/2023
 */

public class GameOverScreenController {

    /**
     * This is a reference to the GameOverScreen, which is the class that this is a controller for
     */
    private final GameOverScreen myGOS;

    /**
     * Constructor of this class.
     *
     * @param theGOS passing in a reference to the GameOverScreen
     */
    public GameOverScreenController(final GameOverScreen theGOS) {
        myGOS = theGOS;
    }

    /**
     * This is the code for when new game is clicked.
     */
    public void newGameClicked() {
        try {
            myGOS.onNewGame();
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * This is the code for when exit game is clicked
     */
    public void loadGameClicked() {
        try {
            myGOS.onExitGame();
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}