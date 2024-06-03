/*
 * TeamCAK TriviaMaze Game - Model Package
 * Fall 2023
 */

package src.model;

/**
 * This is the MCQuestion Class that creates object of multiple choice questions
 *
 * @author Alex Thompson, Calvin Beardemphl, Koji Yoshiyama
 * @version 15/12/2023
 */

public class MCQuestion extends Question {

    /**
     * Default Constructor for MCQuestion
     */
    public MCQuestion(){

    }

    /**
     * Used to create questions of type multiple choice.
     */
    @Override
    public void prepare() {
        getTrivia("MC");
    }
}

