/*
 * TeamCAK TriviaMaze Game - Model Package
 * Fall 2023
 */

package src.model;

import org.sqlite.SQLiteDataSource;

//import java.io.Serial;
//import java.io.Serializable;
import java.sql.*;
import java.util.*;

/**
 * This class creates a database of the trivia
 * questions and answers to be used in the game.
 *
 * @author Alex Thompson, Calvin Beardemphl, Koji Yoshiyama
 * @version 15/12/2023
 */
public class SQLite {

    /**
     * A Statement used to communicate with the Databases.
     */
    private Statement myStmt;

    /**
     * Used to make sure there is only one instance of SQLite.
     */
    private static SQLite myInstance;

    /**
     * Constructor for the SQLite class.
     */
    public SQLite() {
        myStmt = null;
        setUpTables();
    }

    /**
     * A getter method to ensure there is only
     * one instance of SQLite.
     * @return the SQLite object
     */
    public static SQLite getInstance(){
        if (myInstance == null) {
            myInstance = new SQLite();
        }
        return myInstance;
    }

    /**
     * This method creates 3 new tables of Sports
     * Trivia questions and answers.
     * If the database(s) already exists, it will not make a new one!!!
     */
    private void setUpTables() {
        SQLiteDataSource ds;

        try {
            // establish connection
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:SportsTriviaMC.db");

            // create table
            // starting with multiple choice Questions
            String query = "CREATE TABLE IF NOT EXISTS SportsTriviaMC " +
                    "(QUESTION TEXT NOT NULL, " +
                    "ANSWER_ONE TEXT NOT NULL," +
                    "ANSWER_TWO TEXT NOT NULL," +
                    "ANSWER_THREE TEXT NOT NULL," +
                    "ANSWER_FOUR TEXT NOT NULL," +
                    "CORRECT TEXT NOT NULL)";

            Connection conn = ds.getConnection();
            myStmt = conn.createStatement();
            myStmt.executeUpdate(query);

            addDataMC();

            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:SportsTriviaTF.db");

            query = "CREATE TABLE IF NOT EXISTS SportsTriviaTF " +
                    "(QUESTION TEXT NOT NULL, " +
                    "ANSWER_ONE TEXT NOT NULL," +
                    "ANSWER_TWO TEXT NOT NULL," +
                    "CORRECT TEXT NOT NULL)";

            conn = ds.getConnection();
            myStmt = conn.createStatement();
            myStmt.executeUpdate(query);

            addDataTF();

            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:SportsTriviaSA.db");

            query = "CREATE TABLE IF NOT EXISTS SportsTriviaSA " +
                    "(QUESTION TEXT NOT NULL, " +
                    "CORRECT TEXT NOT NULL)";

            conn = ds.getConnection();
            myStmt = conn.createStatement();
            myStmt.executeUpdate(query);

            addDataSA();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }


    /**
     * Adds questions and their respective answers
     * to the Multiple Choice Trivia table.
     * If this data is already in the table, it will add it again!!!
     */
    private void addDataMC() {

        try {
            //insert data into table
            String query = "INSERT INTO SportsTriviaMC (QUESTION, ANSWER_ONE, ANSWER_TWO, ANSWER_THREE, ANSWER_FOUR, CORRECT) " +
                    "VALUES ('Who was the first father-son duo to hit back-to-back home runs?', " +
                    "'Ken Griffey Sr. and Ken Griffey Jr.', 'Bobby Bonds and Barry Bonds', " +
                    "'Fernando Tatis and Fernando Tatis Jr.', 'Tim “Rock” Raines and Tim Raines Jr.'," +
                    "'Ken Griffey Sr. and Ken Griffey Jr.')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaMC (QUESTION, ANSWER_ONE, ANSWER_TWO, ANSWER_THREE, ANSWER_FOUR, CORRECT) " +
                    "VALUES ('Who is the only athlete to hit a home run in the MLB and score a touchdown in the NFL in the same week?', " +
                    "'Deion Sanders', 'Bo Jackson', 'Michael Jordan', 'Tim Tebow', 'Deion Sanders')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaMC (QUESTION, ANSWER_ONE, ANSWER_TWO, ANSWER_THREE, ANSWER_FOUR, CORRECT) " +
                    "VALUES ('What mascot is known as the \"most sued mascot\" in sports?', " +
                    "'The Philly Phanatic', 'Gritty', 'Otto the Orange', 'Sammy the Slug', 'The Philly Phanatic')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaMC (QUESTION, ANSWER_ONE, ANSWER_TWO, ANSWER_THREE, ANSWER_FOUR, CORRECT) " +
                    "VALUES ('What country won the first World Cup?', " +
                    "'Uruguay', 'Argentina', 'Brazil', 'USA', 'Uruguay')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaMC (QUESTION, ANSWER_ONE, ANSWER_TWO, ANSWER_THREE, ANSWER_FOUR, CORRECT) " +
                    "VALUES ('What sport did astronaut Alan Shepard play on the Moon in 1971?', " +
                    "'Golf', 'Bowling', 'Archery', 'Galactic Frisbee', 'Golf')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaMC (QUESTION, ANSWER_ONE, ANSWER_TWO, ANSWER_THREE, ANSWER_FOUR, CORRECT) " +
                    "VALUES ('What does NBA stand for? ', " +
                    "'National Basketball Association', 'National Baseball Association'," +
                    "'National Boxing Association','Nothing But Air', 'National Basketball Association')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaMC (QUESTION, ANSWER_ONE, ANSWER_TWO, ANSWER_THREE, ANSWER_FOUR, CORRECT) " +
                    "VALUES ('What color flag is waved in motor racing to indicate the winner?', " +
                    "'Checkered', 'Striped', 'Green', 'White', 'Checkered')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaMC (QUESTION, ANSWER_ONE, ANSWER_TWO, ANSWER_THREE, ANSWER_FOUR, CORRECT) " +
                    "VALUES ('In what sport is body checking allowed?', " +
                    "'Ice Hockey', 'Figure Skating', 'Speed Skating', 'Curling', 'Ice Hockey')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaMC (QUESTION, ANSWER_ONE, ANSWER_TWO, ANSWER_THREE, ANSWER_FOUR, CORRECT) " +
                    "VALUES ('What baseball team did Jackie Robinson play for when he broke the MLB color barrier in 1947?', " +
                    "'Brooklyn Dodgers', 'New York Yankees', 'St. Louis Cardinals', 'New York Giants', 'Brooklyn Dodgers')";

            myStmt.executeUpdate(query);


        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

    /**
     * Adds questions and their respective answers
     * to the True or False Trivia table.
     * If this data is already in the table, it will add it again!!!
     */
    private void addDataTF() {
        //insert data into table
        try {
            String query = "INSERT INTO SportsTriviaTF (QUESTION, ANSWER_ONE, ANSWER_TWO, CORRECT) " +
                            "VALUES ('Football (soccer) is the most popular sport in the world.', " +
                            "'TRUE', 'FALSE', 'TRUE')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaTF (QUESTION, ANSWER_ONE, ANSWER_TWO, CORRECT) " +
                    "VALUES ('The St. Louis Cardinals (MLB) and the Arizona Cardinals (NFL) are the only professional" +
                    " sports team to share a name.', " +
                    "'TRUE', 'FALSE', 'FALSE')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaTF (QUESTION, ANSWER_ONE, ANSWER_TWO, CORRECT) " +
                    "VALUES ('Billie Jean King beat Bobby Riggs in the 1973 \"Battle of the Sexes\"', " +
                    "'TRUE', 'FALSE', 'TRUE')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaTF (QUESTION, ANSWER_ONE, ANSWER_TWO, CORRECT) " +
                    "VALUES ('Backstroke is the slowest swimming stroke.', " +
                    "'TRUE', 'FALSE', 'FALSE')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaTF (QUESTION, ANSWER_ONE, ANSWER_TWO, CORRECT) " +
                    "VALUES ('Chloe Kim was the youngest woman to win a gold medal in Olympic snowboarding.', " +
                    "'TRUE', 'FALSE', 'TRUE')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaTF (QUESTION, ANSWER_ONE, ANSWER_TWO, CORRECT) " +
                    "VALUES ('A touchdown in American football is worth seven points.', " +
                    "'TRUE', 'FALSE', 'FALSE')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaTF (QUESTION, ANSWER_ONE, ANSWER_TWO, CORRECT) " +
                    "VALUES ('In tennis, \"Love\" means zero.', " +
                    "'TRUE', 'FALSE', 'TRUE')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaTF (QUESTION, ANSWER_ONE, ANSWER_TWO, CORRECT) " +
                    "VALUES ('The longest cricket match was over 15 days long.', " +
                    "'TRUE', 'FALSE', 'FALSE')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaTF (QUESTION, ANSWER_ONE, ANSWER_TWO, CORRECT) " +
                    "VALUES ('The Olympics are held every 4 years.', " +
                    "'TRUE', 'FALSE', 'TRUE')";

            myStmt.executeUpdate(query);


        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Adds questions and their respective answers
     * to the Short Answer table.
     * If this data is already in the table, it will add it again!!!
     */
    private void addDataSA() {

        //insert data into table
        try {
            String query = "INSERT INTO SportsTriviaSA (QUESTION, CORRECT) " +
                    "VALUES ('What six-letter word names an exercise that combines a squat, a pushup, and a jump in the air?', " +
                    "'burpee')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaSA (QUESTION, CORRECT) " +
                    "VALUES ('What year where women finally allowed to compete in the Olympic Games?', " +
                    "'1900')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaSA (QUESTION, CORRECT) " +
                    "VALUES ('What sport uses a broom?', " +
                    "'curling')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaSA (QUESTION, CORRECT) " +
                    "VALUES ('How many seconds was the fastest UFC knockout of all time?', " +
                    "'5')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaSA (QUESTION, CORRECT) " +
                    "VALUES ('How many Grand Slam singles titles does Serena Williams have? ', " +
                    "'23')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaSA (QUESTION, CORRECT) " +
                    "VALUES ('How many bases are there are on a baseball field?', " +
                    "'4')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaSA (QUESTION, CORRECT) " +
                    "VALUES ('What is the bodyweight exercise where you start in a plank, lower your chest to the ground, and lift your chest back up?', " +
                    "'pushup')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaSA (QUESTION, CORRECT) " +
                    "VALUES ('How many minutes is a professional soccer game?', " +
                    "'90')";

            myStmt.executeUpdate(query);

            query = "INSERT INTO SportsTriviaSA (QUESTION, CORRECT) " +
                    "VALUES ('What is the sport that involves two teams competing on a rectangular field, trying to advance an oblong ball towards their opponents end zone?', " +
                    "'football')";

            myStmt.executeUpdate(query);


        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * A getter method for the Statement object.
     *
     * @return my Statement
     */
    public Statement getMyStmt() {
        return myStmt;
    }

    /**
     * Used to bring data from database into the Model
     * as new Question objects are created.
     *
     * @param theType the type of Question
     * @return an ArrayList containing the question, answers,
     *         and the correct answer
     */
    public ArrayList getTrivia(final String theType){

        ArrayList trivia = new ArrayList<String>();
        String database;

        switch (theType) {
            case "MC" -> {
                database = "SportsTriviaMC";
                trivia = getTrivia(database, theType);
            }
            case "TF" -> {
                database = "SportsTriviaTF";
                trivia = getTrivia(database, theType);
            }
            case "SA" -> {
                database = "SportsTriviaSA";
                trivia = getTrivia(database, theType);
            }
        }

        return trivia;
    }

    /**
     * Helper method used to create an ArrayList containing
     * the question, answers, and the correct answer for a question.
     *
     * @param theDatabase to grab the data from
     * @param theType the type of Question
     * @return an ArrayList containing the question, answers,
     *         and the correct answer for an MC question
     */
    private ArrayList<String> getTrivia(final String theDatabase, final String theType) {
        ArrayList<String> trivia = new ArrayList<>();
        SQLiteDataSource ds;

        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:" + theDatabase + ".db");
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM " + theDatabase + " LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);

            // Add question and answers to the trivia list
            trivia.add(rs.getString("QUESTION"));
            if(!theType.equals("SA")){
                trivia.add(rs.getString("ANSWER_ONE"));
                trivia.add(rs.getString("ANSWER_TWO"));
            } if (theType.equals("MC")) {
            trivia.add(rs.getString("ANSWER_THREE"));
            trivia.add(rs.getString("ANSWER_FOUR"));
            }
            trivia.add(rs.getString("CORRECT"));

            // Delete the row from the database
            query = "DELETE FROM " + theDatabase + " WHERE QUESTION = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, (String) trivia.get(0));
                pstmt.executeUpdate();
            }

            rs.close();
            conn.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return trivia;
    }
}