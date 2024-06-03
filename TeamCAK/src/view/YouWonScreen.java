/*
 * TeamCAK TriviaMaze Game - View Package
 * Fall 2023
 */

package src.view;

import static src.view.MainEntry.createAndShowGUI;
import src.controller.YouWonScreenController;
import javax.swing.*;
import java.io.IOException;

/**
 * This class creates the screen that appears when you won the game
 */
public class YouWonScreen extends JFrame {

    /**
     * This is a constant that determines the X value fo the window dimension
     */
    public static final int FRAME_DIMENSIONS_X = 200;

    /**
     * This is a constant that determines the Y value fo the window dimension
     */
    public static final int FRAME_DIMENSIONS_Y = 100;

    /**
     * This is a JFrame holding the components, needed to be able to close the window
     */
    private final JFrame myFrame;

    /**
     * This is a reference to the controller that handles the input for this menu.
     */
    private final YouWonScreenController myController;

    /**
     * Constructor for the YouWonScreen class
     */
    public YouWonScreen() {
        this.myFrame = this;
        createView();
        setTitle("You Win!!");
        setSize(FRAME_DIMENSIONS_X, FRAME_DIMENSIONS_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // makes the window visible
        myController = new YouWonScreenController(this);
    }

    /**
     * Made for a more modular design, it creates the button panel with the buttons
     */
    private void createView() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createNewGameButton());
        buttonPanel.add(createExitGameButton());
        this.add(buttonPanel);
    }

    /**
     * This method creates the New Game button and uses GameOverScreenController to handle what happens
     *
     * @return the JButton that was created
     */
    private JButton createNewGameButton() {
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> myController.newGameClicked());
        return newGameButton;
    }

    /**
     * This method creates the exit game button and uses GameOverScreenController to handle what happens
     *
     * @return the JButton that was created
     */
    private JButton createExitGameButton() {
        JButton loadGameButton = new JButton("Exit Game");
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
     * This method takes care of what happens when exit game is clicked. Closes window and ends program
     */
    public void onExitGame() throws IOException, ClassNotFoundException {
        myFrame.dispose();
        System.exit(0);
        System.out.println("Exit Game button clicked");
    }

}
