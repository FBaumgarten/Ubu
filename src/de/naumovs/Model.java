package de.naumovs;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import de.naumovs.View.JCheckBoxAnswer;

public class Model {

	protected Map<Integer, Exam> examMap = new TreeMap<Integer, Model.Exam>();
	protected Map<String, Component> modelMap = new HashMap<String, Component>();

	protected ArrayList<Exam> quiz = new ArrayList<>();
	//
	private JLabel title;
	private JTextPane question;

	private JCheckBoxAnswer answer1;
	private JCheckBoxAnswer answer2;
	private JCheckBoxAnswer answer3;
	private JCheckBoxAnswer answer4;
	private JCheckBoxAnswer answer5;

	private JButton back;
	private JButton exam;
	private JButton along;

	/**
	 * init at first start
	 */
	protected void init() {

		readQuestions();

		title = (JLabel) this.modelMap.get(Constants.TITLE);
		title.setText(Constants.TITLE_INIT);

		question = (JTextPane) this.modelMap.get(Constants.QUESTION);
		question.setText(Constants.QUESTION_INIT);

		answer1 = (JCheckBoxAnswer) this.modelMap.get(Constants.ANSWER1);
		answer2 = (JCheckBoxAnswer) this.modelMap.get(Constants.ANSWER2);
		answer3 = (JCheckBoxAnswer) this.modelMap.get(Constants.ANSWER3);
		answer4 = (JCheckBoxAnswer) this.modelMap.get(Constants.ANSWER4);
		answer5 = (JCheckBoxAnswer) this.modelMap.get(Constants.ANSWER5);
		answer1.setVisible(false);
		answer2.setVisible(false);
		answer3.setVisible(false);
		answer4.setVisible(false);
		answer5.setVisible(false);

		back = (JButton) this.modelMap.get(Constants.BACK);
		back.setVisible(false);

		exam = (JButton) this.modelMap.get(Constants.EXAM);
		exam.setVisible(false);

		along = (JButton) this.modelMap.get(Constants.NEXT);
		along.setVisible(false);

	}

	/**
	 * TODO: test file before read!
	 */
	private void readQuestions() {
		BufferedReader br;
		String line = null;
		try {
			br = new BufferedReader(new FileReader(Constants.FILE_QUESTIONS));
			try {
				int questionCount = 1;
				while ((line = br.readLine()) != null) {
					if (line.charAt(0) == '#') { // this is commented question?
						// yes - is comment, ignore question
						continue;
					}
					String str[] = line.split(";");

					Exam exam = new Exam();
					Set<Answer> answerSet = new HashSet<Answer>();

					exam.id = Integer.parseInt(str[0]);
					exam.question = question2HTML(str[1]);
					for (int i = 2; i < str.length; i++) {
						Answer answer = new Answer();
						answer.text = answer2HTML(str[i]);
						i++;// go next
						answer.isCorrect = Boolean.parseBoolean(str[i]);
						answerSet.add(answer);
					}
					// set answer set before put
					exam.answersSet = answerSet;
					examMap.put(Integer.valueOf(questionCount), exam);
					questionCount++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String question2HTML(String question) {
		return "<h2 style=\"text-align: center\">" + question + "</h2>";
	}

	private String answer2HTML(String answer) {
		return "<html><p style=\"color:black;\">" + answer + "</p></html>";
	}

	class Exam {
		Integer id;
		String question;
		Set<Answer> answersSet;
	}

	class Answer {
		boolean isCorrect = false;
		boolean isAnswerFromUserChecked = false;
		boolean isUserAnswerCorrect = false;
		String text;
	}
}
