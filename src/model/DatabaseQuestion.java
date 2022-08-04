package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.sqlite.SQLiteDataSource;

public class DatabaseQuestion {
	  public static void main(String[] args) {
	        SQLiteDataSource ds = null;

	        //establish connection (creates db file if it does not exist :-)
	        try {
	            ds = new SQLiteDataSource();
	            ds.setUrl("jdbc:sqlite:mazeQuestions.db");
	        } catch ( Exception e ) {
	            e.printStackTrace();
	            System.exit(0);
	        }

	        System.out.println( "Opened database successfully" );
	        
	        
	        //now create a table
	        String query = "CREATE TABLE IF NOT EXISTS MultipleChoice ( " +
	        		"ID TEXT NOT NULL, " +
	                "QUESTION TEXT NOT NULL, " +
	                "OPTIONS TEXT NOT NULL, " +
	                "ANSWER TEXT NOT NULL )";
	        try ( Connection conn = ds.getConnection();
	                Statement stmt = conn.createStatement(); ) {
	              int rv = stmt.executeUpdate( query );
	              System.out.println( "executeUpdate() returned " + rv );
	          } catch ( SQLException e ) {
	              e.printStackTrace();
	              System.exit( 0 );
	          }
	          System.out.println( "Created questions table successfully" );
	        
	        //next insert two rows of data
//	        System.out.println( "Attempting to insert two rows into questions table" );
//
//	        String query1 = "INSERT INTO MultipleChoice ( ID, QUESTION, OPTIONS, ANSWER ) VALUES ( '1', 'what is ', 'what is ', 'Yes' )";
//	        String query2 = "INSERT INTO MultipleChoice ( ID, QUESTION, OPTIONS, ANSWER ) VALUES ( '2', 'what is the', 'what is ', 'True' )";
//
//	        try ( Connection conn = ds.getConnection();
//	              Statement stmt = conn.createStatement(); ) {
//	            int rv = stmt.executeUpdate( query1 );
//	            System.out.println( "1st executeUpdate() returned " + rv );
//
//	            rv = stmt.executeUpdate( query2 );
//	            System.out.println( "2nd executeUpdate() returned " + rv );
//	         
//	        } catch ( SQLException e ) {
//	            e.printStackTrace();
//	            System.exit( 0 );
//	        }
	        
	          
	        
	        //now query the database table for all its contents and display the results
	        System.out.println( "Selecting all rows from test table" );
	        query = "SELECT * FROM MultipleChoice";

	        try ( Connection conn = ds.getConnection();
	              Statement stmt = conn.createStatement(); ) {
	            
	            ResultSet rs = stmt.executeQuery(query);
	            
	            //walk through each 'row' of results, grab data by column/field name
	            // and print it
	            while ( rs.next() ) {
	                String question = rs.getString( "QUESTION" );
	                String answer = rs.getString( "ANSWER" );
	                String options = rs.getString( "OPTIONS" );

	                System.out.println( "Result: Question = " + question + " Options = " + options +
	                    ", Answer = " + answer );
	            }
	        } catch ( SQLException e ) {
	            e.printStackTrace();
	            System.exit( 0 );
	        }
	    }
}

