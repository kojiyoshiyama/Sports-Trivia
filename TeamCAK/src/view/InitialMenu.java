/*
 * TeamCAK TriviaMaze Game - View Package
 * Fall 2023
 */

package src.view;

import static src.view.MainEntry.createAndShowGUI;
import static src.view.MainEntry.onLoadGameClicked;
import src.controller.InitialMenuController;
import javax.swing.*;
import java.io.IOException;

/**
 * This class is the initial menu that appears before the game is played.
 *
 * @author Koji Yoshiyama, Calvin Beardemphl, Alex Thompson
 * @version 15/12/2023
 */
public class InitialMenu extends JFrame {

    /**
     * This is a constant that determines the X value fo the window dimension
     */
    public static final int FRAME_DIMENSIONS_X = 200;

    /**
     * This is a constant that determines the Y value fo the window dimension
     */
    public static final int FRAME_DIMENSIONS_Y = 100;

    /**
     * This is a button that, when pressed, starts a new game
     */
    public static final JButton myNewGameButton = new JButton("New Game");

    /**
     * This is a JFrame holding the components, needed to be able to close the window
     */
    private transient final JFrame myFrame;

    /**
     * This field is a reference to the controller that handles user input from this menu.
     */
    private transient final InitialMenuController myController;

    /**
     * Constructor for the initial menu class
     */
    public InitialMenu() {
        this.myFrame = this;
        createView();
        setTitle("Trivia Maze Game");
        setSize(FRAME_DIMENSIONS_X, FRAME_DIMENSIONS_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // makes the window visible
        myController = new InitialMenuController(this);
    }

    /**
     * Made for a more modular design, it creates the button panel with the buttons
     */
    private void createView() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createNewGameButton());
        buttonPanel.add(createLoadGameButton());
        this.add(buttonPanel);
    }

    /**
     * This method creates the New Game button and uses InitialMenuController to handle what happens
     *
     * @return the JButton that was created
     */
    private JButton createNewGameButton() {
        myNewGameButton.addActionListener(e -> myController.newGameClicked());
        return myNewGameButton;
    }

    /**
     * This method creates the Load Game button and uses InitialMenuController to handle what happens
     *
     * @return the JButton that was created
     */
    private JButton createLoadGameButton() {
        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(e -> myController.loadGameClicked());
        return loadGameButton;
    }

    /**
     * This method takes care of what to do when new game is clicked. Closes window and opens up a new game
     */
    public void onNewGame() throws IOException, ClassNotFoundException {
        myFrame.dispose();
        createAndShowGUI();
        System.out.println("New Game button clicked");
    }

    /**
     * This method takes care of what happens when load game is clicked. Closes window and loads game
     */
    public void onLoadGame() throws IOException, ClassNotFoundException {
        myFrame.dispose();
        onLoadGameClicked();
        System.out.println("Load Game button clicked");
    }

}

