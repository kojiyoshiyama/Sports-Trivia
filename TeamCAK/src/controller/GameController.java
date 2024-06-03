/*
 * TeamCAK TriviaMaze Game - Controller Package
 * Fall 2023
 */

package src.controller;

import src.model.*;
import src.view.BaseGUI;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This is the controller for the game board. It handles input from arrow keys to move across the gameboard.
 *
 * @author Alex Thompson, Calvin Beardemphl, Koji Yoshiyama
 * @version 15/12/2023
 */

public class GameController extends KeyAdapter {

    /**
     * This is the reference to the door object
     */
    private final Door myDoor;

    /**
     * This is a reference to the BaseGUI object, needed for transferring focus.
     */
    private final BaseGUI myGui;// NEED FOR TRANSFERRING FOCUS

    /**
     * This is a reference to the TriviaMaze Object for serialization purposes
     */
    private TriviaMaze myMaze;

    /**
     * This is the reference to the TriviaHelper object.
     */
    private final TriviaHelper myTriviaHelper;

    /**
     * Constructor for the Controller
     *
     * @param theDoor a reference to Door object
     * @param theGUI a reference to BaseGUI object
     * @param theTriviaMaze a reference to the TriviaMaze Object
     * @param theTriviaHelper a reference to the TriviaHelper Object
     */
    public GameController(final Door theDoor, final BaseGUI theGUI, final TriviaMaze theTriviaMaze, final TriviaHelper theTriviaHelper) {
        myDoor = theDoor;
        myGui = theGUI;// NEED FOR TRANSFERRING FOCUS
        myMaze = theTriviaMaze;
        myTriviaHelper = theTriviaHelper;
        myGui.setKeyListener(this);// NEED FOR TRANSFERRING FOCUS
    }

    /**
     * This method handles movement of the player in the game board with the use of arrow keys
     *
     * @param theEvent the event to be processed
     */
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        System.out.println(theEvent.getKeyCode());

        switch (theEvent.getKeyCode()) {
            // left
            case 37: myDoor.doorLockedOrUnlocked("left");
//                myRoom.updatePlayersLocation(0, -1);
                break;
            // up
            case 38: myDoor.doorLockedOrUnlocked("up");
//                myRoom.updatePlayersLocation(-1, 0);
                break;
            // right
            case 39: myDoor.doorLockedOrUnlocked("right");
//                myRoom.updatePlayersLocation(0, 1);
                break;
            //down
            case 40: myDoor.doorLockedOrUnlocked("down");
//                myRoom.updatePlayersLocation(1, 0);
                break;
        }
    }

    /**
     * Needs to be here for KeyListener Implementation, kept blank because it is currently not needed
     *
     * @param theEvent the event to be processed
     */
    @Override
    public void keyReleased(final KeyEvent theEvent) {
    }

    /**
     * Needs to be here for KeyListener Implementation, kept blank because it is currently not needed
     *
     * @param theEvent the event to be processed
     */
    @Override
    public void keyTyped(final KeyEvent theEvent) {
    }
}
