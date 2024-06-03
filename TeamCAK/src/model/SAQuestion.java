/*
 * TeamCAK TriviaMaze Game - Model Package
 * Fall 2023
 */

package src.model;

/**
 * This is the SAQuestion Class.
 *
 * @author Alex Thompson, Calvin Beardemphl, Koji Yoshiyama
 * @version 15/12/2023
 */

public class SAQuestion extends Question {

    /**
     * Default constructor for SAQuestion
     */
    public SAQuestion(){
    }

    /**
     * Used to create questions of type short answer.
     */
    @Override
    public void prepare() {
        getTrivia("SA");
    }
}
