/*
 * TeamCAK TriviaMaze Game - Model Package
 * Fall 2023
 */

package src.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;

/**
 * This class is the model side of the scoreBoard. Has all relevant data associated with it.
 */
public class Statistics implements Serializable {

    /**
     * This is the serial UID that allows for class identification during serialization
     */
    @Serial
    private static final long serialVersionUID = 345678765432L;

    /**
     * The players score.
     */
    private int myScore;


    /**
     * The number of times the player has
     * answered questions correctly.
     */
    private int myCorrectQuestions;



    /**
     * The total number of questions the player
     * has answered.
     */
    private int myTotalQuestions;

    /**
     * A 'list' of all the property change listeners.
     */
    //public for testing
    public final PropertyChangeSupport myPcs;

    /**
     * Constructor for Statistics.
     */
    public Statistics() {
        myScore = 0;
        myCorrectQuestions = 0;
        myPcs = new PropertyChangeSupport(this);
    }


    /**
     * Getter method for the Scores.
     *
     * @return the players current Scores
     */
    public int getCurrentScore() {
        return myScore;
    }

    /**
     * Getter method for the number of Correct Questions.
     * @return the number of Correct Questions.
     */
    public int getMyCorrectQuestions() {
        return myCorrectQuestions;
    }

    /**
     * Getter method for the total number of Questions.
     * @return the total number of Questions.
     */
    public int getMyTotalQuestions() {
        return myTotalQuestions;
    }

    /**
     * Update method for the Scores.
     *
     * @param theScore the amount to adjust the score
     */
    public void updateScore(final int theScore) {
        myScore += theScore;
        // works with negative numbers too
        // because adding a negative actually subtracts it
        // I forgot that lol
        myPcs.firePropertyChange("score", null, myScore);
    }

    /**
     * Update method for the number of Correct Questions.
     */
    public void updateCorrectQuestions() {
        myCorrectQuestions++;
        //should only be incremented by one anyway

        myPcs.firePropertyChange("correct", null, myCorrectQuestions);
    }

    /**
     * This method increases the total number of questions answered, and fires a property change
     */
    public void updateTotalQuestions(){
        myTotalQuestions++;
        //should only be incremented by one anyway

        myPcs.firePropertyChange("total", null, myTotalQuestions);
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