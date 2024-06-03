/*
 * TeamCAK TriviaMaze Game - Model Package
 * Fall 2023
 */

package src.model;

import src.view.YouWonScreen;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

/**
 * This class has all the logic for the rooms. Stored in a int[][] and keeps track of player's current location
 */

public class Room implements Serializable {

    /**
     * This is the serial UID. Allows for class identification during serialization
     */
    @Serial
    private static final long serialVersionUID = 9765434567L;

    /**
     * An array holding the player's location.
     */
    //public for testing
    public int[][] myPlayersLocation;

    /**
     * The number of rows in myPlayersLocation[][].
     */
    //public for testing
    public final int myNumRows;

    /**
     * The number of columns in myPlayersLocation[][].
     */
    //public for testing
    public final int myNumCols;

    /**
     * The row of myPlayersLocation[][] where the player is.
     */
    //public for testing
    public int myPlayersRow;

    /**
     * The column of myPlayersLocation[][] where the player is.
     */
    //public for testing
    public int myPlayersCol;

    /**
     * A 'list' of all the property change listeners.
     */
    //public for testing
    public PropertyChangeSupport myPcs;

    /**
     * This si the constructor for the Room class.
     */
    public Room() {
        myPcs = new PropertyChangeSupport(this);
        myNumRows = 5; // default can be changed!!
        myNumCols = 4;
        myPlayersLocation = new int[myNumRows][myNumCols];
        myPlayersLocation[0][0] = 1;
        myPlayersRow = 0;
        myPlayersCol = 0;
    }


    /**
     * Updates myPlayersLocation[][] to
     * reflect the players' movement.
     *
     * @param theRowChange the amount to change the row
     * @param theColChange the amount to change the column
     */
    public void updatePlayersLocation(final int theRowChange, final int theColChange) {
        // Calculate new potential location
        int newRow = myPlayersRow + theRowChange;
        int newCol = myPlayersCol + theColChange;

        // checks if the direction the player is trying to move is valid in terms of staying in bounds
        if (newRow >= 0 && newRow < myPlayersLocation.length && newCol >= 0 && newCol < myPlayersLocation[0].length) {
            // change the old location of the player to a 0 in myPlayersLocation[][]
            myPlayersLocation[myPlayersRow][myPlayersCol] = 0;

            // Update the player's location
            myPlayersRow = newRow;
            myPlayersCol = newCol;

            // change the new location of the player to a 1 in myPlayersLocation[][]
            myPlayersLocation[myPlayersRow][myPlayersCol] = 1;

            // Notify observers of the change
            myPcs.firePropertyChange("gameBoard", null, getPlayersLocation());
            if (myPlayersLocation[4][3] == 1) {
                System.out.println("You Won");
                YouWonScreen youWon = new YouWonScreen();
            }
        }
    }

    /**
     * Getter method for the players location
     * on the gameboard.
     *
     * @return the players location as a string
     */
    public String getLocation() {
        if(myPlayersLocation[myPlayersRow][myPlayersCol] == 1) {
            return myPlayersRow + ", " + myPlayersCol;
        } else {
            // look for the player, and reassign myPlayersRow
            // and myPlayersCol to the correct values
            for (int row = 0; row < myNumRows; row++) {
                for (int col = 0; col < myNumCols; col++) {
                    if (myPlayersLocation[row][col] == 1) {
                        myPlayersRow = row;
                        myPlayersCol = col;
                        return row + ", " + col; // can be changed
                    }
                }
            }
        }
        return "Player not found";
    }

    /**
     * This is a getter method to retrieve the player's current Row, mainly used by Door class
     *
     * @return the player's row as an int
     */
    public int getPlayersRow() {
        return myPlayersRow;
    }

    /**
     * This is a getter method to retrieve the player's current Column, mainly used by Door class
     *
     * @return the player's column as an int
     */
    public int getPlayersCol() {
        return myPlayersCol;
    }

    /**
     * This is a getter method for the player's current location. The int[][] is copied to ensure that any class calling
     * this method is unable to change the myPlayersLocation field. This method is mostly going to be used by the door class
     *
     * @return a copy of myPlayersLocation as an int[][]
     */
    public int[][] getPlayersLocation() {
        int[][] copy = new int[myPlayersLocation.length][];
        for (int i = 0; i < myPlayersLocation.length; i++) {
            copy[i] = Arrays.copyOf(myPlayersLocation[i], myPlayersLocation[i].length);
        }
        return copy;
    }



    /**
     * Adds each new property change listener that is created
     * to the property change support.
     *
     * @param theListener the new listener
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    /**
     * Removes a property change listener as needed
     * from the property change support.
     *
     * @param theListener the new listener to be deleted
     */
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }

}
