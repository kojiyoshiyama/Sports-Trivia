/*
 * TeamCAK TriviaMaze Game - Controller Package
 * Fall 2023
 */

package src.controller;

import src.view.InitialMenu;
import java.io.IOException;

/**
 * This class is the controller that handles the mouse clicked involved with the interaction of the initial screen.
 *
 * @author Koji Yoshiyama, Calvin Beardemphl, Alex Thompson
 * @version 15/12/2023
 */

public class InitialMenuController {

    /**
     * This is a reference to the InitialMenu, which is the class that this is a controller for
     */
    private final InitialMenu myInitialMenu;

    /**
     * Constructor of this class.
     * @param theInitialMenu passing in a reference to InitialMenu
     */
    public InitialMenuController(final InitialMenu theInitialMenu) {
        myInitialMenu = theInitialMenu;
    }

    /**
     * This is the code for when new game is clicked
     */
    public void newGameClicked() {
        try {
            myInitialMenu.onNewGame();
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * This is the code for when load game is clicked
     */
    public void loadGameClicked() {
        try {
            myInitialMenu.onLoadGame();
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
