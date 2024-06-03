/*
 * TeamCAK TriviaMaze Game - Model Package
 * Fall 2023
 */

package src.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is the TriviaHelper Class.
 *
 * @author Team CAK - Alex, Calvin, Koji
 * @version 12/08/23
 */
public class TriviaHelper implements Serializable{

    /**
     * This is the serial UID that allows for class identification during serialization
     */
    @Serial
    private static final long serialVersionUID = -7947886313284633071L;

    /**
     * A 'list' of all the property change listeners.
     */
    //public for testing
    public final PropertyChangeSupport myPcs;


    /**
     * Constructor for TriviaHelper
     */
    public TriviaHelper() {
        myPcs = new PropertyChangeSupport(this);
    }

    /**
     * Used to update the questions on the GUI.
     */
    public void updateQuestion() {
        QuestionFactory QF = new QuestionFactory();
        Question q = QF.makeQuestion(randomQType());

        myPcs.firePropertyChange("question", null, q);
    }


    /**
     * Helper method to generate a
     * different random type of Question.
     *
     * @return the type of Question to be made as a string
     */
    // public for testing
    public String randomQType(){
        Random rand = new Random();
        int num = rand.nextInt(3);
        String result = "";

        if(num == 0){
            result = "MC";
        } else if (num == 1){
            result = "TF";
        } else {
            result = "SA";
        }

        return result;

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
