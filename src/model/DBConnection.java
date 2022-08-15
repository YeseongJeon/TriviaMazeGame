package model;

import java.sql.Connection;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteDataSource;

/**
 * This will connects the database to the system and brings the questions, answers, options
 * and created Question objects and put them into their specific array-list.
 * It also contains the get method so the other class can get the question objects when generating the questions.
 * 
 * @author Yeseong Jeon
 * @version 1.0 TCSS 360(Summer 2022)
 */
public class DBConnection {

	static DBConnection myQuestionDatabase; 

	final static ArrayList<Question> trueFalseQuestions = new ArrayList<>(); //List that True or False Questions will be stored
	final static ArrayList<Question> shortAnswerQuestions = new ArrayList<>(); //List that Short Answer Questions will be stored
	final static ArrayList<Question> multQuestions = new ArrayList<>(); //List that Multiple Choice Questions will be stored

	/**
	 * This method will get the Instance of the database
	 * 
	 * @return instance
	 */
	public static DBConnection getInstance() {
		if(myQuestionDatabase == null) {
			myQuestionDatabase = new DBConnection();
			myQuestionDatabase.getConnection();
			return myQuestionDatabase;
		}
		return myQuestionDatabase;
	}

	/**
	 * This method will connect the system with database
	 * And reads and get data from the each table and each columns
	 * Creates new Question object and put the data into it
	 */
	private void getConnection() {
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:mazeQuestions.db"); // Reads the file name and connects the database file to the system.

		String query1 = "SELECT * FROM TrueOrFalse";
		String query2 = "SELECT * FROM ShortResponse";
		String query3 = "SELECT * FROM MultipleChoice";

		try ( Connection conn = ds.getConnection();
				
				// Statements for each type of questions 
				Statement tOfStmt = conn.createStatement();
				Statement shortStmt = conn.createStatement();
				Statement multStmt = conn.createStatement();) {

			ResultSet tOfRs = tOfStmt.executeQuery(query1);
			ResultSet shortRs = shortStmt.executeQuery(query2);
			ResultSet multRs = multStmt.executeQuery(query3);

			//walk through each 'row' of results, grab data by column/field name

			while ( tOfRs.next() ) {
				String question = tOfRs.getString( "QUESTION" );
				String answer = tOfRs.getString( "ANSWER" );
				final Question q = new Question(question, answer, "True or False"); // "True or False" because options does not exist in the TrueOrFalse table.
				trueFalseQuestions.add(q);	   

			}

			while ( shortRs.next() ) {
				String question = shortRs.getString( "QUESTION" );
				String answer = shortRs.getString( "ANSWER" );
				final Question q = new Question(question, answer, "Short Answer"); // "Short Answer" because options does not exist in the ShortAnswer table.
				shortAnswerQuestions.add(q);  // adds the created question object into the list

			}

			while ( multRs.next() ) {
				String question = multRs.getString( "QUESTION" );
				String options = multRs.getString( "OPTIONS" );
				String answer = multRs.getString( "ANSWER" );
				final Question q = new Question(question, answer, options);// options because options does exist in the MultipleChoice table.
				multQuestions.add(q);  


			}
		} catch ( SQLException e ) {
			e.printStackTrace();
			System.exit( 0 );
		}
	}

	/**
	 * Returns the True or False Question list
	 * 
	 * @return List of the True or False question object
	 */
	
	public static ArrayList<Question> getTrueFalseQuestions() {
		return trueFalseQuestions;
	}
	
	/**
	 * Returns the Short Answer Question list
	 * 
	 * @return List of the Short Answer question object
	 */
	public static ArrayList<Question> getShortAnswerQuestions() {
		return shortAnswerQuestions;
	}
	
	/**
	 * Returns the Multiple Choice Question list
	 * 
	 * @return List of the Multiple Choice question object
	 */
	public static ArrayList<Question> getMultQuestions() {
		return multQuestions;
	}
}
