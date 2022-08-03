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
	        String query = "CREATE TABLE IF NOT EXISTS questions ( " +
	                "QUESTION TEXT NOT NULL, " +
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

//	        String query1 = "INSERT INTO questions ( QUESTION, ANSWER ) VALUES ( 'The 9/11 terror in New York, happened on September 11th, 2001.', 'True' )";
//	        String query2 = "INSERT INTO questions ( QUESTION, ANSWER ) VALUES ( 'The person depicted on the 100 dollar bill is Donald Trump.', 'False' )";
//	        String query3 = "INSERT INTO questions ( QUESTION, ANSWER ) VALUES ( 'The first president of USA is George Washington.', 'True' )";
//
//	        try ( Connection conn = ds.getConnection();
//	              Statement stmt = conn.createStatement(); ) {
//	            int rv = stmt.executeUpdate( query1 );
//	            System.out.println( "1st executeUpdate() returned " + rv );
//
//	            rv = stmt.executeUpdate( query2 );
//	            System.out.println( "2nd executeUpdate() returned " + rv );
//	          
//	            rv = stmt.executeUpdate( query3 );
//              System.out.println( "3nd executeUpdate() returned " + rv );
//	        } catch ( SQLException e ) {
//	            e.printStackTrace();
//	            System.exit( 0 );
//	        }
	        
	        
	        //now query the database table for all its contents and display the results
	        System.out.println( "Selecting all rows from test table" );
	        query = "SELECT * FROM questions";

	        try ( Connection conn = ds.getConnection();
	              Statement stmt = conn.createStatement(); ) {
	            
	            ResultSet rs = stmt.executeQuery(query);
	            
	            //walk through each 'row' of results, grab data by column/field name
	            // and print it
	            while ( rs.next() ) {
	                String question = rs.getString( "QUESTION" );
	                String answer = rs.getString( "ANSWER" );

	                System.out.println( "Result: Question = " + question +
	                    ", Answer = " + answer );
	            }
	        } catch ( SQLException e ) {
	            e.printStackTrace();
	            System.exit( 0 );
	        }
	    }
}

