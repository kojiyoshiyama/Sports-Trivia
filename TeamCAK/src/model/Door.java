/*
 * TeamCAK TriviaMaze Game - Model Package
 * Fall 2023
 */

package src.model;

import src.view.GameOverScreen;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

/**
 * This class has all the logic associated with the doors in the game board.
 *
 * @author Koji Yoshiyama, Calvin Beardemphl, Alex Thompson
 * @version 15/12/2023
 */

public class Door implements Serializable {

    /**
     * This is the serial UID that allows identification of classes in serialization process
     */
    @Serial
    private static final long serialVersionUID = 23542354312L;

    /**
     * The jagged 2d Array holding the doors (0 = unlocked, 1 = locked)
     */
    private final int[][] myDoors;

    /**
     * A 'list' of all the property change listeners.
     */
    //public for testing
    public final PropertyChangeSupport myPcs;

    /**
     * This string holds the direction that the player is currently trying to move
     */
    private String myDirection;

    /**
     * This is a reference to the
     */
    private final Room myRoom;

    /**
     * This is a reference to the TriviaHelper Object.
     */
    private final TriviaHelper myTriviaHelper;

    /**
     * This is the constructor for the door class. nothing special going on here
     *
     * @param theRoom a reference to the room object is passed through
     * @param theTriviaHelper a reference to the TriviaHelper Object
     */
    public Door(final Room theRoom, final TriviaHelper theTriviaHelper) {
        myTriviaHelper = theTriviaHelper;
        myRoom = theRoom;
        myDirection = "";
        myDoors = new int[9][];
        for (int i = 0; i < myDoors.length; i++) {
            // Alternating lengths of 3 and 4 elements per sub array
            myDoors[i] = new int[i % 2 == 0 ? 3 : 4];
        }
        //myCurrentRoom
        myPcs = new PropertyChangeSupport(this);
    }


    /**
     * Will be called when a question is answered incorrectly.
     * This method will update the 2D array that holds the doors' locations
     * and the door that has been locked will become a '1' in the int[][] array
     *
     * * @throws IOException
     * @throws ClassNotFoundException
     */
    public void updateDoors() throws IOException, ClassNotFoundException {
        int row = myRoom.getPlayersRow();
        int col = myRoom.getPlayersCol();
        if (myDirection.equals("up")) {
            myDoors[row * 2 - 1][col] = 1;
        } else if (myDirection.equals("down")) {
            myDoors[row * 2 + 1][col] = 1;
        } else if (myDirection.equals("left")) {
            myDoors[row * 2][col - 1] = 1;
        } else { // right
            myDoors[row * 2][col] = 1;
        }
        myPcs.firePropertyChange("doors", null, getDoors());
        youLose(row, col);

    }

    /**
     * Checks all surrounding doors, if all of them are locked, this class identifies that you have lost the game.
     * @param theRow the row of the player's current location
     * @param theCol the column of the player's current location
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void youLose(final int theRow, final int theCol) throws IOException, ClassNotFoundException {
        int[] doorCheck = surroundingDoors(theRow, theCol);
        int unlockedCnt = 0;
        for (int i = 0; i < doorCheck.length; i++) {
            if (doorCheck[i] == 0) {
                unlockedCnt++;
            }
        }
        if (unlockedCnt == 0) {
            System.out.println("You Lost");
            GameOverScreen gameOver= new GameOverScreen();
            //myGameOver.showGameOverScreen();
        }
    }

    private int[] surroundingDoors(int theRow, int theCol) {
        // this int[] tracks if the doors surrounding a room are locked or unlocked
        // if a door doesn't exist, final int[] will label that door as -1
        // doorVals[0] = top
        // doorVals[1] = bottom
        // doorVals[2] = left
        // doorVals[3] = right
        int[] doorVals = {-1, -1, -1, -1};
        // if room has a top door
        if (theRow > 0) {
            doorVals[0] = myDoors[theRow * 2 - 1][theCol];
        }
        // if room has a bottom door
        if (theRow < 4) {
            doorVals[1] = myDoors[theRow * 2 + 1][theCol];
        }
        // if room has a left door
        if (theCol > 0) {
            doorVals[2] = myDoors[theRow * 2][theCol - 1];
        }
        // if room has a right door
        if (theCol < 3) {
            doorVals[3] = myDoors[theRow * 2][theCol];
        }
        return doorVals;
    }

    /**
     * This will be called from within the updatePlayersLocation() method to determine if the move is valid.
     * This method will take in the current player's attempted
     * move and cross-reference with myBoard to see if it is a valid movement
     *
     * @param theDirection the direction that the player is trying to move as a string
     */
    public void doorLockedOrUnlocked(String theDirection) {
        myDirection = theDirection;
        int newRow = newPositionCalculator()[0];
        int newCol = newPositionCalculator()[1];
        int currCol = myRoom.getPlayersCol();
        int currRow = myRoom.getPlayersRow();
        int lockedOrUnlocked = 0;
        // checks if the direction the player is trying to move is valid in terms of staying in bounds
        if (newRow >= 0 && newRow < myRoom.getPlayersLocation().length && newCol >= 0 && newCol < myRoom.getPlayersLocation()[0].length) {

            // These 'IF' statements check the door associated with the player's current location and desired move
            lockedOrUnlocked = switch (theDirection) {
                case "up" -> myDoors[currRow * 2 - 1][currCol];
                case "down" -> myDoors[currRow * 2 + 1][currCol];
                case "left" -> myDoors[currRow * 2][currCol - 1];
                default ->  // right
                        myDoors[currRow * 2][currCol];
            };
        }

        if (lockedOrUnlocked == 0) {
            myTriviaHelper.updateQuestion();
        } else {
            System.out.println("Cannot pass through a locked door");
        }
    }

    /**
     * This is a helper method for doorLockedOrUnlocked() that checks if the desired location
     * change is within the bounds of the game board
     *
     * @return an int[] that holds 2 values. the newRow and the newCol
     */
    private int[] newPositionCalculator() {
        int[] array = new int[2];
        int newRow;
        int newCol;
        int currRow = myRoom.getPlayersRow();
        int currCol = myRoom.getPlayersCol();
        // takes in the inputted desired location change and
        // calculates the new position, doesn't actually change anything, just a hypothetical checker
        switch (myDirection) {
            case "up" -> {
                newRow = currRow - 1;
                newCol = currCol;
            }
            case "down" -> {
                newRow = currRow + 1;
                newCol = currCol;
            }
            case "left" -> {
                newRow = currRow;
                newCol = currCol - 1;
            }
            default -> {
                newRow = currRow;
                newCol = currCol + 1;
            }
        }
        array[0] = newRow;
        array[1] = newCol;
        return array;
    }

    public int[][] getDoors() {
        int[][] copy = new int[myDoors.length][];
        for (int i = 0; i < myDoors.length; i++) {
            // Create a new subarray of the same length as the original
            copy[i] = new int[myDoors[i].length];
            // Copy the elements of the original subarray into the new subarray
            System.arraycopy(myDoors[i], 0, copy[i], 0, myDoors[i].length);
        }
        return copy;
    }

    public String getDirection() {
        return myDirection;
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
