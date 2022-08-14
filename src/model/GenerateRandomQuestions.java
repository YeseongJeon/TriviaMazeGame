package model;

import java.util.ArrayList; 
import java.util.Random;

public class GenerateRandomQuestions{
	private static int optionNumber = 1;
	private static int index = 1;
	
	public static Question createQuestion() {
		final Random ran = new Random();
		int option = ran.nextInt(3);
		String[] choose = {"TrueOrFalse", "ShortAnswer", "MultipleChoice" };
		optionNumber = option;
		return generateQuestion(choose[option]);
	}


	public static int getRandomSelection() {
		return optionNumber;
	}

	public static Question generateQuestion(final String type) {
		final DBConnection db = DBConnection.getInstance();
		final Question question;
		if (type.equals("MultipleChoice")) {
			ArrayList<Question> multQ = db.getMultQuestions();
			if (index >= multQ.size()) {
				index = 0;
			}
			question = multQ.get(index++);
		} else if (type.equals("TrueOrFalse")) {
			ArrayList<Question> tfQ = db.getTrueFalseQuestions();
			if (index >= tfQ.size()) {
				index = 0;
			}
			question = tfQ.get(index++);
		} else if (type.equals("ShortAnswer")) {
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
