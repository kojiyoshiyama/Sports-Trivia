/*
 * TeamCAK TriviaMaze Game - Controller Package
 * Fall 2023
 */

package src.controller;

import src.model.Door;
import src.model.Room;
import src.model.Statistics;
import src.model.TriviaMaze;
import src.view.BaseGUI;
import src.view.TriviaBoard;
import java.awt.event.*;
import java.io.IOException;

/**
 * This class is the controller for handling user input on the trivia board.
 *
 * @author Koji Yoshiyama, Calvin Beardemphl, Alex Thompson
 * @version 15/12/2023
 */

public class AnswerController {

    /**
     * This is a reference to the BaseGUI Object, needed for transferring focus.
     */
    private final transient BaseGUI myGUI; // NEED FOR TRANSFERRING FOCUS

    /**
     * This is the reference to the QuestionFactory object.
     */
    private final transient TriviaBoard myTriviaBoard;

    /**
     * This is the reference to the Statistics object.
     */
    private final transient Statistics myStatistics;

    /**
     * Reference to door object to be able to call methods from it
     */
    private final transient Door myDoor;

    /**
     * Reference to room object to be able to call methods from it
     */
    private final transient Room myRoom;

    /**
     * Constructor for AnswerControls, sets item listeners
     * for the answer buttons in TriviaBoard, and sets an
     * action listener for the enter button in TriviaBoard.
     * @param theGUI a reference to the gui object
     */
    public AnswerController(final TriviaMaze theMaze, final BaseGUI theGUI) {
        myGUI = theGUI; // NEED FOR TRANSFERRING FOCUS
        myTriviaBoard = myGUI.getMyTriviaBoard();
        myStatistics = theMaze.getStatistics();
        myDoor = theMaze.getDoor();
        myRoom = theMaze.getRoom();

        myTriviaBoard.setMyEnterButtonClickListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvt) {
                try {
                    answerSubmitted();
                } catch (IOException | ClassNotFoundException theE) {
                    throw new RuntimeException(theE);
                }
            }
        });

        myTriviaBoard.setMyContinueButtonClickListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theE) {
                continueClicked();
            }
        });
    }


    /**
     * Checks whether the user has chosen an answer
     * before trying to validate the answer.
     *
     * @return if any answer has been chosen
     */
    private boolean isAnswerSelected() {
        boolean result = false;
        if(!myTriviaBoard.getMySelectedButton().equals("")) { // if an answer has been chosen
            result = true;
        } else if (!myTriviaBoard.getMyShortAnswer().equals("")){ // if the player wrote something
            result = true;
        }
        return result;
    }

    /**
     * Properly handles a submitted player answer, letting
     * the player know if their answer was right or wrong, and
     * if the player just clicked enter WITHOUT choosing/writing
     * an answer, it displays a message.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void answerSubmitted() throws IOException, ClassNotFoundException {
        String result = "";
        //checks if the user has selected an answer or not
        if(isAnswerSelected()) {
            myTriviaBoard.remove(myTriviaBoard.getMyBox()); // removes the answers from the gui

            String correct = myTriviaBoard.getMyCorrectAnswer();
            String selected = myTriviaBoard.getMySelectedButton();
            //checks if user answer is right or wrong
            result = validateAnswer(correct, selected);
            // sets text to tell user if their answer was correct or incorrect
            myTriviaBoard.setMyQuestion(result);

            myTriviaBoard.setMyNoAnswer(""); // makes sure message is not displayed

            if(result.equals("Your Answer is Correct! \n +1000 pts")){
                myStatistics.updateScore(1000);
                myStatistics.updateCorrectQuestions();
            } else {
                myStatistics.updateScore(-500);
            }

            myStatistics.updateTotalQuestions();

            // if the user clicked enter w/o choosing an answer
        } else if (myTriviaBoard.getMyNoAnswer().equals("")) {
            myTriviaBoard.setMyNoAnswer("Please select an answer");
        }

        myTriviaBoard.repaint();

    }


    /**
     * Properly handles a click of the continue button,
     * used to clear the TriviaBoard, and transfer focus.
     */
    private void continueClicked(){
        myTriviaBoard.setMyQuestion("");
        myTriviaBoard.hideContinue();

        // NEED FOR TRANSFERRING FOCUS
        myGUI.getMyFrame().setFocusableWindowState(true);
        myGUI.getMyFrame().requestFocusInWindow();
    }


    /**
     * Checks whether the answer is correct, and returns
     * a string reflecting this, calls methods to update
     * players location, or the doors if correct or incorrect
     * respectively.
     * @param theCorrectAnswer the correct answer to the question
     * @param theChosenAnswer the answer the user chose
     * @return the string to be displayed on the GUI
     */
    public String validateAnswer(final String theCorrectAnswer, final String theChosenAnswer) throws IOException, ClassNotFoundException {
        String result = "";
        String SA = "";
        if(myTriviaBoard.getMyShortAnswer() != null){
            SA = myTriviaBoard.getMyShortAnswer();
        }

        if (theCorrectAnswer.equals(theChosenAnswer) || SA.equalsIgnoreCase(theCorrectAnswer)) {
            result = ("Your Answer is Correct! \n +1000 pts");
            updateLocation();
        } else {
            result = ("Your Answer is Incorrect. :(");
            myDoor.updateDoors();
        }

        myTriviaBoard.displayContinue(); // NEEDED FOR TRANSFERRING FOCUS

        return result;
    }

    /**
     * This method tells the model to update
     * the players location in the maze based on
     * the direction they are trying to go.
     */
    private void updateLocation() {
        String direction = myDoor.getDirection();

        switch (direction) {
            case "left":
                myRoom.updatePlayersLocation(0, -1);
                break;

            case "up":
                myRoom.updatePlayersLocation(-1, 0);
                break;

            case "right":
                myRoom.updatePlayersLocation(0, 1);
                break;

            case "down":
                myRoom.updatePlayersLocation(1, 0);
                break;
        }

    }



}
