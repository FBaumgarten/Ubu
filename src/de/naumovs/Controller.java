package de.naumovs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import de.naumovs.Model.Answer;
import de.naumovs.Model.Exam;

public class Controller {

	Model model;
	boolean isStarted = false;
	int allQuestionCount = 0;

	private JLabel title;
	private JTextPane question;

	private JCheckBox answer1;
	private JCheckBox answer2;
	private JCheckBox answer3;
	private JCheckBox answer4;
	private JCheckBox answer5;

	private JButton back;
	private JButton exam;
	private JButton along;

	public Controller(Model model) {
		this.model = model;
	}

	public void init() {
		this.allQuestionCount = model.examMap.size();

		title = (JLabel) this.model.modelMap.get(Constants.TITLE);

		question = (JTextPane) this.model.modelMap.get(Constants.QUESTION);
		question.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!isStarted) {
					start();
				}
			}
		});

		answer1 = (JCheckBox) this.model.modelMap.get(Constants.ANSWER1);
		answer2 = (JCheckBox) this.model.modelMap.get(Constants.ANSWER2);
		answer3 = (JCheckBox) this.model.modelMap.get(Constants.ANSWER3);
		answer4 = (JCheckBox) this.model.modelMap.get(Constants.ANSWER4);
		answer5 = (JCheckBox) this.model.modelMap.get(Constants.ANSWER5);
		resetAnswers();

		along = (JButton) this.model.modelMap.get(Constants.ALONG);
		along.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!isStarted) {
					along();
				}
			}
		});

//		back = (JButton) this.controllerMap.get(Constants.BACK);

	}

	protected void start() {
		isStarted = true;
		title.setText(Constants.QUESTION_COUNT + 1 + Constants.OFF + allQuestionCount);

		Exam exam = model.examMap.entrySet().stream().findFirst().get().getValue();
		question.setText(exam.question);

		int checkbox = 0;
		for (Answer answer : exam.answersSet) {
			checkbox++;
			switch (checkbox) {
			case 1:
				
				answer1.setText(answer.text);				
				answer1.setVisible(true);
				break;
			case 2:
				answer2.setText(answer.text);
				answer2.setVisible(true);
				break;
			case 3:
				answer3.setText(answer.text);
				answer3.setVisible(true);
				break;
			case 4:
				answer4.setText(answer.text);
				answer4.setVisible(true);
				break;
			case 5:
				answer5.setText(answer.text);
				answer5.setVisible(true);
				break;
			default:
				// TODO: error
				break;
			}
		}

		along.setVisible(true);

	}

	private void resetAnswers() {
		answer1.setVisible(false);
		answer2.setVisible(false);
		answer3.setVisible(false);
		answer4.setVisible(false);
		answer5.setVisible(false);
	}

	private void along() {
		// test answers
		
		
		

	}

}
