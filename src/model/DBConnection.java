package model;

import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteDataSource;

public class DBConnection {

	static DBConnection myQuestionDatabase; 

	final static ArrayList<Question> trueFalseQuestions = new ArrayList<>();
	final static ArrayList<Question> shortAnswerQuestions = new ArrayList<>();
	final static ArrayList<Question> multQuestions = new ArrayList<>();

	public static DBConnection getInstance() {
		if(myQuestionDatabase == null) {
			myQuestionDatabase = new DBConnection();
			myQuestionDatabase.getConnection();
			return myQuestionDatabase;
		}
		return myQuestionDatabase;
	}


	private void getConnection() {
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:mazeQuestions.db");

		String query1 = "SELECT * FROM TrueOrFalse";
		String query2 = "SELECT * FROM ShortResponse";
		String query3 = "SELECT * FROM MultipleChoice";

		try ( Connection conn = ds.getConnection();
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
				final Question q = new Question(question, answer, "True or False");
				trueFalseQuestions.add(q);	   

			}

			while ( shortRs.next() ) {
				String question = shortRs.getString( "QUESTION" );
				String answer = shortRs.getString( "ANSWER" );
				final Question q = new Question(question, answer, "Short Answer");
				shortAnswerQuestions.add(q);  

			}

			while ( multRs.next() ) {
				String question = multRs.getString( "QUESTION" );
				String options = multRs.getString( "OPTIONS" );
				String answer = multRs.getString( "ANSWER" );
				final Question q = new Question(question, answer, options);
				multQuestions.add(q);  


			}
		} catch ( SQLException e ) {
			e.printStackTrace();
			System.exit( 0 );
		}
	}

	public ArrayList<Question> getTrueFalseQuestions() {
		return trueFalseQuestions;
	}
	
	public ArrayList<Question> getShortAnswerQuestions() {
		return shortAnswerQuestions;
	}
	
	public ArrayList<Question> getMultQuestions() {
		return multQuestions;
	}
}
