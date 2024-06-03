/*
 * TeamCAK TriviaMaze Game - View Package
 * Fall 2023
 */

package src.view;

import src.controller.AnswerController;
import src.controller.GameController;
import src.model.TriviaMaze;
import javax.swing.*;
import java.io.IOException;

/**
 * A class that is being used to display the base GUI that was implemented
 *
 * @author Alex Thompson, Calvin Beardemphl, Koji Yoshiyama
 * @version 15/12/2023
 */
public class MainEntry {

    /**
     * This is the method that starts up the entire program
     *
     * @param theArgs default param for main method
     */
    public static void main(final String theArgs[]) {
        SwingUtilities.invokeLater(() -> createInitialMenu());
    }

    /**
     * This method creates the initial menu that asks the user if they want to start a new game or load one in
     */
    public static void createInitialMenu() {
        InitialMenu menu = new InitialMenu();
    }

    /**
     * This method should only be called when a new game is started, when a load game happens
     */
    public static void createAndShowGUI() throws IOException, ClassNotFoundException {
        TriviaMaze triviaMaze = new TriviaMaze();
        BaseGUI gui = new BaseGUI(triviaMaze);
        triviaMaze.addPCLToRoom(gui.myGameBoard);
        triviaMaze.addPCLToDoor(gui.myGameBoard);
        triviaMaze.addPCLToTriviaHelper(gui.myTriviaBoard);
        triviaMaze.addPCLToStatistics(gui.myScoreBoard);
        GameController gameController = new GameController(triviaMaze.getDoor(), gui, triviaMaze, triviaMaze.getTriviaHelper());
        AnswerController ac = new AnswerController(triviaMaze, gui);
    }

    /**
     * This method is called by the Initial Menu when Load Game is clicked
     * This method loads the most recently saved game by getting the filepath where the save is stored, and calling the
     * loadGameState method from TriviaMaze
     */
    public static void onLoadGameClicked() throws IOException, ClassNotFoundException {
        TriviaMaze theMaze = new TriviaMaze();
        TriviaMaze loadedMaze = theMaze.loadGameState();
        if (loadedMaze != null) {
            theMaze = loadedMaze; // Replace the current game model with the loaded one
            updateView(theMaze); // You should implement this to refresh your game's view with the loaded game state
        } else {
            System.out.println("Load game has failed");
        }
    }

    /**
     * This method is a helper method. when the game is properly loaded in, the GUI is created and added as a property change listener.
     * Then, all the needed firePropertyChange() methods to update the views state are called
     *
     * @param theMaze the maze brought in from onLoadGameClicked so other components being initialized can get a reference.
     */
    public static void updateView(final TriviaMaze theMaze) {
        BaseGUI gui = new BaseGUI(theMaze);
        GameController gameController = new GameController(theMaze.getDoor(), gui, theMaze, theMaze.getTriviaHelper());
        AnswerController ac = new AnswerController(theMaze, gui);
        theMaze.addPCLToRoom(gui.myGameBoard);
        theMaze.addPCLToDoor(gui.myGameBoard);
        theMaze.addPCLToTriviaHelper(gui.myTriviaBoard);
        theMaze.addPCLToStatistics(gui.myScoreBoard);
        theMaze.fireLoadedPropertyChanges();
    }
}
