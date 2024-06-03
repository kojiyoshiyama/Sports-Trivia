/*
 * TeamCAK TriviaMaze Game - Controller Package
 * Fall 2023
 */

package src.controller;

import src.model.TriviaMaze;
import src.view.Menu;

import javax.swing.*;
import java.io.IOException;

import static src.view.MainEntry.createAndShowGUI;
import static src.view.MainEntry.onLoadGameClicked;

/**
 * This class is the controller for the menu bar, and handles mouse clicks associated with it.
 *
 * @author Koji Yoshiyama, Calvin Beardemphl, Alex Thompson
 * @verison 15/12/2023
 */

public class MenuController {

    /**
     * A reference to the menu class
     */
    private final Menu myMenu;

    /**
     * A reference to the triviaMaze class
     */
    private final TriviaMaze myMaze;

    /**
     * This is a reference to a JMenu component from the Menu class that is necessary for the menu to function
     */
    private final JMenu myM;

    /**
     * This is a reference to a JMenu component from the Menu class that is necessary for the menu to function
     */
    private final JMenu myHelp;

    /**
     * This is a reference to a JMenu component from the Menu class that is necessary for the menu to function
     */
    private final JMenu myGameOptions;

    /**
     * Constructor for the menu controller
     *
     * @param theMenu reference to the menu class
     */
    public MenuController(final Menu theMenu, final TriviaMaze theMaze, final JMenu theM, final JMenu theHelp, final JMenu theGameOptions) {
        myM = theM;
        myHelp = theHelp;
        myGameOptions = theGameOptions;
        myMenu = theMenu;
        myMaze = theMaze;
    }

    /**
     * This is the event handler for when "controls" is clicked
     */
    public void controlsClicked() {
        JOptionPane.showMessageDialog(null, myMenu.theControls(), "Controls",
                JOptionPane.INFORMATION_MESSAGE);
    }

//    /**
//     * This is the event handler for when "hints" is clicked
//     */
//    public void hintsClicked() {
//        JOptionPane.showMessageDialog(null, "Hints", "Hints",
//                JOptionPane.INFORMATION_MESSAGE);
//    }


    /**
     * This is the event handler for when "rules" is clicked
     */
    public void rulesClicked() {
        JOptionPane.showMessageDialog(null, myMenu.theRules(), "Rules",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This is the event handler for when "about" is clicked
     */
    public void aboutClicked() {
        JOptionPane.showMessageDialog(null, myMenu.theAbout(), "About",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This is the event handler for when "New Game" is clicked
     */
    public void newGameClicked() {
        try {
            createAndShowGUI(); // Call the method to start a new game
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * This is the event handler for when "Load Game" is clicked
     */
    public void loadGameClicked() {
        try {
            myM.removeAll();
            myGameOptions.removeAll();
            myHelp.removeAll();
            onLoadGameClicked(); // Call the method to load a game
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * This is the event handler for when "Save Game" is clicked
     */
    public void saveGameClicked() {
        try {
            myMaze.saveGameState(); // Call the method to save a game
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
