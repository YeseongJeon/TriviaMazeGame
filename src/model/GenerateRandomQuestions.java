package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * This will generate the random question that will be displayed on the users screen
 * It will create random index to choose random Question list. 
 * 
 * @author Yeseong Jeon
 * @version 1.0 TCSS 360(Summer 2022)
 */

public class GenerateRandomQuestions {
	private static int index = 1;
	
	/**
	 * Selects a random question list 
     * And returns the question from the list by using generateQuestion method
	 * @return the selected random question
	 */
	public static Question createQuestion() {
		final Random ran = new Random();
		int option = ran.nextInt(3);
		String[] choose = {"TrueOrFalse", "ShortAnswer", "MultipleChoice"};
		return generateQuestion(choose[option]);
	}
	
	/**
	 * Generates a question depending on the argument
     * And returns the question that's randomly generated
	 * @return the generated question
	 */
	public static Question generateQuestion(final String theType) {
		final DBConnection db = DBConnection.getInstance();
		final Question question;
		if (theType.equals("MultipleChoice")) {
			ArrayList<Question> multQ = db.getMultQuestions();
			if (index >= multQ.size()) {
				index = 0;
			}
			question = multQ.get(index++);
		} else if (theType.equals("TrueOrFalse")) {
			ArrayList<Question> tfQ = db.getTrueFalseQuestions();
			if (index >= tfQ.size()) {
				index = 0;
			}
			question = tfQ.get(index++);
		} else if (theType.equals("ShortAnswer")) {
			ArrayList<Question> saQ = db.getShortAnswerQuestions();
			if (index >= saQ.size()) {
				index = 0;
			}
			question = saQ.get(index++);
		} else {
			throw new IllegalArgumentException("Invalid Question");
		}
		return question;
	}
}
