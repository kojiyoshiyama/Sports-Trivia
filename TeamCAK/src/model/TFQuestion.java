/*
 * TeamCAK TriviaMaze Game - Model Package
 * Fall 2023
 */

package src.model;

/**
 * This is the TFQuestion Class.
 *
 * @author Alex Thompson, Calvin Beardemphl, Koji Yoshiyama
 * @version 15/12/2023
 */
public class TFQuestion extends Question {

    /**
     * Default constructor for TFQuestion
     */
    public TFQuestion(){

    }

    /**
     * Used to create questions of type true or false.
     */
    @Override
    public void prepare() {
        getTrivia("TF");
    }
}
