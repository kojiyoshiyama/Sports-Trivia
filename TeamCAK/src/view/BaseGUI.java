/*
 * TeamCAK TriviaMaze Game - View Package
 * Fall 2023
 */

package src.view;

import src.model.TriviaMaze;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static src.view.ScoreBoard.TIMER;

/**
 * This is a general base GUI that is the connection point of all the view components. Everything is initialized
 * and put onto the same GUI through this class
 *
 * @author Alex Thompson, Calvin Beardemphl, Koji Yoshiyama
 * @version 15/12/2023
 */
public class BaseGUI extends JFrame implements FocusListener {

    /**
     * This is a constant that sets the dimension of the Window (Width)
     */
    public static final int FRAME_DIMENSIONS_X = 800;

    /**
     * This is a constant that sets the dimension of the Window (Height)
     */
    public static final int FRAME_DIMENSIONS_Y = 650;

    /**
     * This is the X dimension for the game board within the window
     */
    public static final int GAMEBOARD_DIMENSION_X = 500;

    /**
     * This is the Y dimension for the game board within the window
     */
    public static final int GAMEBOARD_DIMENSION_Y = 600;

    /**
     * This is the dimensions of the scoreboard panel
     */
    public static final int SCOREBOARD_DIMENSION = 300;

    /**
     * This is the dimensions of the trivia questions panel
     */
    public static final int TRIVIA_DIMENSION = 300;

    /**
     * This is the base frame that is responsible for holding all GUI components
     */
    private transient final JFrame myFrame;

    /**
     * The gameboard object
     */
    protected transient GameBoard myGameBoard;

    /**
     * The trivia board object.
     */
    protected transient TriviaBoard myTriviaBoard;

    /**
     * The score board object.
     */
    protected transient ScoreBoard myScoreBoard;

    /**
     * the menuBar object
     */
    protected transient JMenuBar myMenuBar;

    /**
     * This keeps track of the amount of time that has elapsed
     */
    private transient int myElapsedTime;

    /**
     * This keeps track of how many minutes have passed
     */
    private transient int myMinutes;

    /**
     * This keeps track of the number of seconds that have passed
     */
    private transient int mySeconds;

    /**
     * This is the timer itself
     */
    private transient Timer myTimer;

    /**
     * Constructor method that initializes the GUI window and handles various init commands
     */
    public BaseGUI(final TriviaMaze theTriviaMaze) {
        myFrame = new JFrame(); // creates frame
        myFrame.setTitle("Trivia Maze"); // sets frame name
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows you to exit window when 'X' is clicked
        myFrame.setResizable(false); // makes it so the window is not resizeable
        myFrame.setSize(FRAME_DIMENSIONS_X, FRAME_DIMENSIONS_Y); // sets window dimensions
        myFrame.setLayout(null);
        addScoreBoard();
        addTriviaQuestionPanel();
        addGameBoardPanel();
        addMenuBar(theTriviaMaze);
        myGameBoard.addFocusListener(this);
        myTriviaBoard.addFocusListener(this);
        SoundHandler.RunMusic("Sound Library/I Exist.wav");

        // both lines directly under this comment are here for KeyListener functionality
        myFrame.setFocusableWindowState(true); // made so the GUI responds to key presses
        requestFocusInWindow(); // made so the GUI responds to key presses
        myFrame.setVisible(true); // makes the window visible
    }

    /**
     * This method allows the MVC to connect to the GUI window so the GUI is able to take in keystroke input
     *
     * @param theListener the MVC is the listener that will be passed through
     */
    public void setKeyListener(final KeyListener theListener) {
        myFrame.addKeyListener(theListener);
    }


    /**
     * This method creates a gameBoard object (JPanel) and adds to the frame
     */
    private void addGameBoardPanel() {
        GameBoard gameBoard = new GameBoard();
        myGameBoard = gameBoard;
        gameBoard.setBounds(0, 0, GAMEBOARD_DIMENSION_X, GAMEBOARD_DIMENSION_Y);
        myFrame.add(gameBoard);
    }

    /**
     * This method creates the score board panel and adds to the frame
     * (Will likely be replaced when score board class is implemented)
     */
    private void addScoreBoard() {
        myTimer = new Timer(1000, theEvent -> {
        });
        myScoreBoard = new ScoreBoard(myTimer);
        timerStartStopReset();
        myScoreBoard.setBackground(Color.blue); // subject to change
        myScoreBoard.setBounds(500, 0, SCOREBOARD_DIMENSION, SCOREBOARD_DIMENSION);
        myFrame.add(myScoreBoard);
    }

    /**
     * This field is the timer logic. This field is out of order because of its coupling with the actionPerformed method.
     */
    private final Timer myTimerLogic = new Timer(1000, new ActionListener() {

        /**
         * This method handles the logic behind all the timer stuff
         *
         * @param theE the event to be processed
         */
        @Override
        public void actionPerformed(final ActionEvent theE) {
            final String numberFormat = "%02d";
            myElapsedTime += 1000;
            myMinutes = (myElapsedTime/60000) % 60;
            mySeconds = (myElapsedTime/1000) % 60;
            final String minsString = String.format(numberFormat, myMinutes);
            final String secondsString = String.format(numberFormat, mySeconds);
            TIMER.setText(minsString + ":" + secondsString);
            //System.out.println("Timer Is going");
        }
    });

    /**
     * This method is here to start the timer
     */
    public void timerStartStopReset() {
        startTimer();
    }

    /**
     * This is a helper method to start the timer
     */
    private void startTimer() {
        myTimer.start();
        myTimerLogic.start();
    }

    /**
     * No usages yet, but this is used to stop the timer
     */
    private void stopTimer() {
        myTimer.stop();
        myTimerLogic.stop();
    }

    /**
     * This method creates the trivia question panel and adds to the frame
     */
    private void addTriviaQuestionPanel() {
        myTriviaBoard = new TriviaBoard();
        myTriviaBoard.setBackground(Color.pink); // subject to change
        myTriviaBoard.setBounds(500, 300, TRIVIA_DIMENSION, TRIVIA_DIMENSION);
        myFrame.add(myTriviaBoard);
    }

    /**
     * Getter method for the TriviaBoard object.
     *
     * @return the TriviaBoard object
     */
    public TriviaBoard getMyTriviaBoard(){
        return myTriviaBoard;
    }

    /**
     * This method creates the menu bar and adds to the frame
     *
     * @param theMaze a reference to the maze because the menu controller needs it
     */
    private void addMenuBar(final TriviaMaze theMaze) {
        myMenuBar = new JMenuBar();
        myFrame.setJMenuBar(myMenuBar);
        myMenuBar.add(new Menu(theMaze));
    }

    /**
     * NEED FOR FOCUSLISTENER INTERFACE
     * default implementation
     *
     * @param theEvt the event to be processed
     */
    @Override
    public void focusGained(final FocusEvent theEvt) {
        System.out.println("Focus gained " + theEvt.getComponent());
    }

    /**
     * NEED FOR FOCUSLISTENER INTERFACE
     * default implementation
     *
     * @param theEvt the event to be processed
     */
    @Override
    public void focusLost(final FocusEvent theEvt) {
        System.out.println("Focus lost " + theEvt.getComponent());
    }



    /**
     * Returns the frame used to hold all GUI components.
     *
     * @return the main frame
     */
    public JFrame getMyFrame() {
        return myFrame; // NEED FOR TRANSFERRING FOCUS
    }
}
