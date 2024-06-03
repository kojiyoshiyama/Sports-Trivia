/*
 * TeamCAK TriviaMaze Game - View Package
 * Fall 2023
 */

package src.view;

import src.controller.MenuController;
import src.model.TriviaMaze;
import javax.swing.*;

/**
 * This class will hold different menu objects that will be interactive
 * and show different objects based on what the user clicks on
 *
 * @author Alex Thompson, Calvin Beardemphl, Koji Yoshiyama
 * @version 15/12/2023
 */
public class Menu extends JMenuBar{

    /**
     * This is the constructor for the Menu, this is a hefty method that basically does everything
     *
     * @param theTriviaMaze a reference to the triviaMaze object needed to pass to the controller
     */
    public Menu(final TriviaMaze theTriviaMaze) {
        JMenu MENU = new JMenu("Menu");
        JMenu HELP = new JMenu("Help");
        JMenu GAME_OPTIONS = new JMenu("Game Options");
        MenuController myMc = new MenuController(this, theTriviaMaze, MENU, HELP, GAME_OPTIONS);
        String myControls = "Controls";
        JMenuItem menuControls = new JMenuItem(myControls);
        String myHints = "Hints";
        JMenuItem menuHints = new JMenuItem(myHints);
        String myRules = "Rules";
        JMenuItem menuRules = new JMenuItem(myRules);
        String myAbout = "About";
        JMenuItem menuAbout = new JMenuItem(myAbout);
        String myNewGameS = "New Game";
        JMenuItem menuNewGame = new JMenuItem(myNewGameS);
        String myPauseGameS = "Pause Game";
        JMenuItem menuPauseGame = new JMenuItem(myPauseGameS);
        String myEndGameS = "End Game";
        JMenuItem menuEndGame = new JMenuItem(myEndGameS);
        String myLoadGameS = "Load Game";
        JMenuItem menuLoadGame = new JMenuItem(myLoadGameS);
        String mySaveGameS = "Save Game";
        JMenuItem menuSaveGame = new JMenuItem(mySaveGameS);

        // MenuController is in charge of handling mouse clicks for menu options.
        // These lines of code allow MenuController to be an action listener
//        menuHints.addActionListener(e -> myMc.hintsClicked());
        menuRules.addActionListener(e -> myMc.rulesClicked());
        menuAbout.addActionListener(e -> myMc.aboutClicked());
        menuNewGame.addActionListener(e -> myMc.newGameClicked());
        menuLoadGame.addActionListener(e -> myMc.loadGameClicked());
        menuSaveGame.addActionListener(e -> myMc.saveGameClicked());
        menuControls.addActionListener(e -> myMc.controlsClicked());

        MENU.add(menuAbout);
        MENU.add(menuControls);
        GAME_OPTIONS.add(menuNewGame);
        GAME_OPTIONS.add(menuPauseGame);
        GAME_OPTIONS.add(menuLoadGame);
        GAME_OPTIONS.add(menuEndGame);
        GAME_OPTIONS.add(menuSaveGame);
//        MENU.add(menuHints);
        HELP.add(menuRules);
        this.add(GAME_OPTIONS);
        this.add(MENU);
        this.add(HELP);
    }

    /**
     * method for creation of the controls menu item.
     *
     * @return //not sure
     */
    public String theControls() {
        final StringBuilder str = new StringBuilder();
        str.append("Up Arrow or \u2191  -  Move Up \n");
        str.append("Left Arrow or \u2190  -  Move Left \n");
        str.append("Right Arrow or \u2192  -  Move Right \n");
        str.append("Down Arrow or \u2193  -  Move Down");
        return str.toString();
    }

    /**
     * method for the creation of the rules menu item.
     *
     * @return //not sure
     */
    public String theRules() {
        final StringBuilder str = new StringBuilder();
        str.append("Traverse through the maze and make it to the end! \n");
        str.append("If you get a question wrong, the door will lock so be careful! \n");
        str.append("Gather points and if you get stuck, you can use some of your points towards a hint! \n");
        str.append("If you forget the rules, they will be at the menu options at the top!");
        return str.toString();
    }

    /**
     * Method that creates the "About" menu option
     * @return //not sure
     */
    public String theAbout() {
        final StringBuilder str = new StringBuilder();
        str.append("This game was created by Alex Thompson, Calvin Beardemphl and Koji Yoshiyama");
        return str.toString();
    }

    /**
     * Method for the hints, no implementation yet
     *
     * @param theScore the score that the player currently has
     * @param theMenu the menu where we will add this
     * @param theMenuHints the Hint menu option
     */
    private void showHints(final int theScore, final JMenu theMenu, final JMenuItem theMenuHints) {
        // doesn't have to be 10,000
        // I just randomly chose it
        if(theScore > 1000) {
            theMenu.add(theMenuHints);
        }
    }

}
