/*
 * TeamCAK TriviaMaze Game - Model Package
 * Fall 2023
 */

package src.model;

import src.view.GameBoard;
import src.view.ScoreBoard;
import src.view.TriviaBoard;

import java.beans.PropertyChangeListener;
import java.io.*;

/**
 * This class is the connection point of all model objects for ease of initialization and serialization
 *
 * @author Alex Thompson, Calvin Beardemphl, Koji Yoshiyama
 * @version 15/12/2023
 */

public class TriviaMaze implements Serializable {

    /**
     * This is the serial UID that allows for class identification during serialization
     */
    @Serial
    private static final long serialVersionUID = 57329532L;

    /**
     * This is a reference to the door object
     */
    private final Door myDoor;

    /**
     * This is a reference to the room object
     */
    private final Room myRoom;

    /**
     * This is a reference to the TriviaHelper object
     */
    private final TriviaHelper myTriviaHelper;

    /**
     * This is a reference to the Statistics object
     */
    private final Statistics myStatistics;

    /**
     * This is a reference to the SQLite object
     */
    //public for testing
    public final transient SQLite mySQLite;


    /**
     * This constructor for TriviaMaze Class instantiates all parts of the model correctly.
     */
    public TriviaMaze() {
        myTriviaHelper = new TriviaHelper();
        myRoom = new Room();
        myDoor = new Door(myRoom, myTriviaHelper);
        myStatistics = new Statistics();
        mySQLite = SQLite.getInstance();
    }

    /**
     * This method loads the most recently saved game state
     *
     * @return a TriviaMaze object that includes all the referenced objects states as well
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static TriviaMaze loadGameState() throws IOException, ClassNotFoundException {
        File file = new File("ModelState.ser");
        if (!file.exists()) {
            System.out.println("No saved game file found");
            return null; // or handle this case as you see fit
        }
        TriviaMaze gameState = null;
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        gameState = (TriviaMaze) in.readObject();
        in.close();
        fileIn.close();
        System.out.println("Game successfully loaded in");
        return gameState;
    }

    /**
     * This method is called by MainEntry to add the game board as a PCL for the room
     *
     * @param theGameBoard game board passed in as the listener
     */
    public void addPCLToRoom(final GameBoard theGameBoard) {
        myRoom.addPropertyChangeListener(theGameBoard);
    }

    /**
     * This method is called by MainEntry to add the game board as a PCL for the door
     *
     * @param theGameBoard game board passed in as the listener
     */
    public void addPCLToDoor(final GameBoard theGameBoard) {
        myDoor.addPropertyChangeListener(theGameBoard);
    }

    /**
     * This method is called by MainEntry to add the trivia board
     * as a PCL for the TriviaHelper.
     *
     * @param theTriviaBoard trivia board passed in as the listener
     */
    public void addPCLToTriviaHelper(final TriviaBoard theTriviaBoard) {
        myTriviaHelper.addPropertyChangeListener(theTriviaBoard);
    }

    /**
     * This method is called by MainEntry to add the score board
     * as a PCL for Statistics.
     *
     * @param theScoreBoard score board passed in as the listener
     */
    public void addPCLToStatistics(final ScoreBoard theScoreBoard) {
        myStatistics.addPropertyChangeListener(theScoreBoard);
    }

    /**
     * A getter method for returning the reference to the Question Factory
     *
     * @return reference to myTriviaHelper
     */
    public TriviaHelper getTriviaHelper() {
        return myTriviaHelper;
    }

    /**
     * This getter returns the reference to door.
     *
     * @return reference to myDoor
     */
    public Door getDoor() {
        return myDoor;
    }

    /**
     * This getter returns the reference to room.
     *
     * @return reference to myRoom
     */
    public Room getRoom() {
        return myRoom;
    }

    /**
     * This getter returns the reference Statistics.
     *
     * @return reference to myStatistics
     */
    public Statistics getStatistics() {
        return myStatistics;
    }


    /**
     * This method serializes the current game state
     */
    public void saveGameState() throws IOException {
        FileOutputStream fileOut = new FileOutputStream("ModelState.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
        System.out.println("game state saved");
    }

    /**
     * This method is still incomplete due to the incomplete nature of a lot of model components. this method is called by
     * main and fires all property changes required to update all parts of the view after loading a game
     */
    public void fireLoadedPropertyChanges() {
        myDoor.myPcs.firePropertyChange("doors", null, myDoor.getDoors());
        myRoom.myPcs.firePropertyChange("gameBoard", null, myRoom.getPlayersLocation());
        myStatistics.myPcs.firePropertyChange("score", null, myStatistics.getCurrentScore());
        myStatistics.myPcs.firePropertyChange("correct", null, myStatistics.getMyCorrectQuestions());
        myStatistics.myPcs.firePropertyChange("total", null, myStatistics.getMyTotalQuestions());
        myTriviaHelper.myPcs.firePropertyChange("new game", null, null);
    }

}
