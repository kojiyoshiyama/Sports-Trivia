/*
 * TeamCAK TriviaMaze Game - View Package
 * Fall 2023
 */

package src.view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class represents the game board that will show
 * the user the different rooms and doors that player
 * can go through
 *
 * @author Alex Thompson, Calvin Beardemphl, Koji Yoshiyama
 * @version 11/4/23
 */
public class GameBoard extends JPanel implements PropertyChangeListener {

    /**
     * This is a constant that represents the width of the squares representing the rooms
     */
    public static final int RECT_WIDTH = 105;

    /**
     * This is a constant that represents the height of the squares representing the rooms
     */
    public static final int RECT_HEIGHT = 100;

    /**
     * This is a constant that represents the space between each rectangle horizontally
     */
    public static final int RECT_X_SPACING = 121;

    /**
     * This is a constant that represents the space between each rectangle vertically
     */
    public static final int RECT_Y_SPACING = 116;

    /**
     * This is a constant that represents the X and Y dimensions for the doors
     */
    public static final int DOOR_DIMENSIONS = 40;

    /**
     * This is a constant that represents the dimension of the rounded corners of each square
     */
    public static final int ARC_DIMENSION = 20;

    /**
     * A 2D array representation of the game board with the current location of the player.
     * '1' represents players current location, all other elements are '0'
     */
    private static int[][] myGameBoard;
    /**
     * A 2D array of the doors in the game as well as which ones are locked - this is a jagged array.
     * '1' represents a door that has been locked. unlocked doors represented by '0'
     */
    private static int[][] myDoors;

    /**
     * The constructor of the game board.
     */
    public GameBoard() {
        this.setBackground(Color.green);
        // init the gameBoard int[][]
        myGameBoard = new int[5][4];
        myGameBoard[0][0] = 1;
        // init the doors int[][] - jagged array
        myDoors = new int[9][];
        for (int i = 0; i < myDoors.length; i++) {
            // Alternating lengths of 3 and 4 elements per sub array
            myDoors[i] = new int[i % 2 == 0 ? 3 : 4];
        }
    }

    /**
     * A setter method for the gameBoard int[][]
     *
     * @param theGameBoard the gameBoard int[][] brought in from a propertyChange
     */
    private void setGameBoard(final int[][] theGameBoard) {
        myGameBoard = theGameBoard;
    }

    /**
     * A setter method for the gameBoard int[][]
     *
     * @param theDoors the doors int[][] brought in from a propertyChange
     */
    private void setMyDoors(final int[][] theDoors) {
        myDoors = theDoors;
    }

    /**
     * A method to handle property changes from the model
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        if ("gameBoard".equals(theEvt.getPropertyName())) {  // these are the names of the property changes for gameboard, reflect this in model
            setGameBoard((int[][]) theEvt.getNewValue());
        } else if ("doors".equals(theEvt.getPropertyName())) {
            setMyDoors((int[][]) theEvt.getNewValue());
        } 
        repaint();
    }

    /**
     * Paints/draws the GUI of the game.
     *
     * @param theG the Graphics object needed to paint
     */
    @Override
    protected void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        Graphics2D g2 = (Graphics2D) theG;
        theG.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(4));
        drawRoomsUpdatePlayer(g2);
        drawAndUpdateDoors(g2);
        drawFinishText(g2);
        drawStartText(g2);
    }

    /**
     * This is th text that appears on the gameboard that shows the player where they need to get in order to win
     *
     * @param theG2 the graphics2D needed to draw this component
     */
    private void drawFinishText(final Graphics2D theG2) {
        final String finishText = "FINISH";
        theG2.setFont(new Font("Serif", Font.BOLD, 24)); // You can adjust the font size and style here
        FontMetrics metrics = theG2.getFontMetrics();
        theG2.setColor(Color.RED);
        int x = 395; // Determine the X position based on your board layout
        int y = 540; // Determine the Y position based on your board layout
        theG2.drawString(finishText, x, y);
    }

    /**
     * This is th text that appears on the gameboard that shows the player the starting location
     *
     * @param theG2 the graphics2D needed to draw this component
     */
    private void drawStartText(final Graphics2D theG2) {
        final String finishText = "START";
        theG2.setFont(new Font("Serif", Font.BOLD, 24)); // You can adjust the font size and style here
        FontMetrics metrics = theG2.getFontMetrics();
        theG2.setColor(Color.RED);
        int x = 25; // Determine the X position based on your board layout
        int y = 75; // Determine the Y position based on your board layout
        theG2.drawString(finishText, x, y);
    }


    /**
     * Helper method that draws the rooms and updates the player location
     *
     * @param theG2 the graphics2D needed to draw the rooms
     */
    private void drawRoomsUpdatePlayer(final Graphics2D theG2) {
        // Starting coordinates
        final int startX = 16;
        final int startY = 16;

        for (int i = 0; i < myGameBoard.length; i++) {
            int y = startY + i * RECT_Y_SPACING; // Calculate the Y position for the current row
            for (int j = 0; j < myGameBoard[i].length; j++) {
                int x = startX + j * RECT_X_SPACING; // Calculate the X position for the current column
                if (myGameBoard[i][j] == 0) {
                    theG2.drawRoundRect(x, y, RECT_WIDTH, RECT_HEIGHT, ARC_DIMENSION, ARC_DIMENSION);
                } else {
                    theG2.setColor(Color.YELLOW);
                    theG2.fillRoundRect(x, y, RECT_WIDTH, RECT_HEIGHT, ARC_DIMENSION, ARC_DIMENSION);
                    theG2.setColor(Color.BLACK);
                    theG2.drawRoundRect(x, y, RECT_WIDTH, RECT_HEIGHT, ARC_DIMENSION, ARC_DIMENSION);

                }

            }
        }
    }


    /**
     * Helper method that draws the doors and updates the locked and unlocked doors
     *
     * @param theG2 the graphics2D needed to draw the doors
     */
    private void drawAndUpdateDoors(final Graphics2D theG2) {
        final int startY = 48;
        int startX = 108;
        int spacingY = 58;

        for (int i = 0; i < myDoors.length; i+=2) {
            int y = startY + i * spacingY; // Multiply by 2 because we are skipping every other row
            for (int j = 0; j < myDoors[i].length; j++) {
                int x = startX + j * RECT_X_SPACING; // Calculate the X position for the current column
                if (myDoors[i][j] == 0) {
                    theG2.fillRoundRect(x, y, DOOR_DIMENSIONS, DOOR_DIMENSIONS, ARC_DIMENSION, ARC_DIMENSION);
                }
            }
        }

        startX = 48;
        for (int i = 1; i < myDoors.length; i+=2) {
            int y = startY + i * spacingY; // Multiply by 2 because we are skipping every other row
            for (int j = 0; j < myDoors[i].length; j++) {
                int x = startX + j * RECT_X_SPACING; // Calculate the X position for the current column
                if (myDoors[i][j] == 0) {
                    theG2.fillRoundRect(x, y, DOOR_DIMENSIONS, DOOR_DIMENSIONS, ARC_DIMENSION, ARC_DIMENSION);
                }
            }
        }
    }

}


