/*
 * TeamCAK TriviaMaze Game - Controller Package
 * Fall 2023
 */

package src.controller;

import src.view.YouWonScreen;
import java.io.IOException;

/**
 * This class is the controller for the "you won" screen, and handles mouse clicks associated with it.
 *
 * @author Koji Yoshiyama, Calvin Beardemphl, Alex Thompson
 * @verison 15/12/2023
 */

public class YouWonScreenController {

    /**
     * This is a reference to the GameOverScreen, which is the class that this is a controller for
     */
    private final YouWonScreen myYWS;

    /**
     * Constructor of this class.
     *
     * @param theYWS passing in a reference to the GameOverScreen
     */
    public YouWonScreenController(final YouWonScreen theYWS) {
        myYWS = theYWS;
    }

    /**
     * This is the code for when new game is clicked
     */
    public void newGameClicked() {
        try {
            myYWS.onNewGame();
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * This is the code for when exit game is clicked
     */
    public void loadGameClicked() {
        try {
            myYWS.onExitGame();
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}