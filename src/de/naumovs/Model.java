package de.naumovs;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class Model {

	protected HashMap<Integer, Exam> examMap = new HashMap<Integer, Model.Exam>();
	protected HashMap<String, Component> modelMap = new HashMap<String, Component>();
	//
	private JLabel title;
	private JTextPane question;
	private JCheckBox answer1;
	private JCheckBox answer2;
	private JCheckBox answer3;
	private JCheckBox answer4;
	private JCheckBox answer5;
	private JButton along;
	private JButton back;

	/**
	 * init at first start
	 */
	protected void init() {

		readQuestions();

		title = (JLabel) this.modelMap.get(Constants.TITLE);
		title.setText(Constants.TITLE_INIT);

		question = (JTextPane) this.modelMap.get(Constants.QUESTION);
		question.setText(Constants.QUESTION_INIT);

		answer1 = (JCheckBox) this.modelMap.get(Constants.ANSWER1);
		answer2 = (JCheckBox) this.modelMap.get(Constants.ANSWER2);
		answer3 = (JCheckBox) this.modelMap.get(Constants.ANSWER3);
		answer4 = (JCheckBox) this.modelMap.get(Constants.ANSWER4);
		answer5 = (JCheckBox) this.modelMap.get(Constants.ANSWER5);
		answer1.setVisible(false);
		answer2.setVisible(false);
		answer3.setVisible(false);
		answer4.setVisible(false);
		answer5.setVisible(false);

		along = (JButton) this.modelMap.get(Constants.ALONG);
		along.setVisible(false);

		back = (JButton) this.modelMap.get(Constants.BACK);
		back.setVisible(false);
	}

	private void readQuestions() {
		BufferedReader br;
		String line = null;
		int answersId;

		try {
			br = new BufferedReader(new FileReader(Constants.FILE_QUESTIONS));
			try {
				while ((line = br.readLine()) != null) {
					String str[] = line.split(";");
					
					answersId = 0;
					Exam exam = new Exam();
					List<Answer> answerList = new ArrayList<>();					
					
					for (int i = 0; i < str.length; i++) {
						System.out.println(str[i]);

						switch (i) {
						case 0:
							// set id
							exam.id = Integer.parseInt(str[i]);
							break;
						case 1:
							// set question
							exam.question = str[i];
							break;
						default:
							// set answers
							Answer answer = new Answer();
							answer.id = answersId;
							answer.text = str[i];
							i++;
							answer.correct = Boolean.parseBoolean(str[i]);
							
							answerList.add(answer);
							break;
						}
					}
					
					exam.answersMap.put(exam.id, answerList);
					examMap.put(exam.id, exam);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	class Exam {
		Integer id;
		String question;
		HashMap<Integer, List<Answer>> answersMap = new HashMap<Integer, List<Answer>>();
	}

	class Answer {
		int id;
		boolean correct;
		String text;
	}
}
