package de.naumovs;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import de.naumovs.Model.Answer;
import de.naumovs.Model.Exam;
import de.naumovs.View.JCheckBoxAnswer;

public class Controller {

	Model model;

	private boolean isStarted = false;
	private int allQuestionCount = 0;
	private int currentQuestion = 1;

	private JLabel title;
	private JTextPane question;

	private JCheckBoxAnswer answer1;
	private JCheckBoxAnswer answer2;
	private JCheckBoxAnswer answer3;
	private JCheckBoxAnswer answer4;
	private JCheckBoxAnswer answer5;
	private int checkboxCount = 0;

	private JButton back;
	private JButton exam;
	private JButton next;

	public Controller(Model model) {
		this.model = model;
	}

	public void init() {
		title = (JLabel) this.model.modelMap.get(Constants.TITLE);
		allQuestionCount = model.examMap.size();

		question = (JTextPane) this.model.modelMap.get(Constants.QUESTION);
		question.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!isStarted) {
					start();
					initExam(currentQuestion);
				}
			}
		});

		answer1 = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER1);
		answer2 = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER2);
		answer3 = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER3);
		answer4 = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER4);
		answer5 = (JCheckBoxAnswer) this.model.modelMap.get(Constants.ANSWER5);
		resetAnswers();

		back = (JButton) this.model.modelMap.get(Constants.BACK);
		AbstractAction actionBack = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				backExam();
			}
		};
		back.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),
				Constants.BACK);
		back.getActionMap().put(Constants.BACK, actionBack);
		back.addActionListener(actionBack);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				backExam();
			}
		});

		exam = (JButton) this.model.modelMap.get(Constants.EXAM);

		next = (JButton) this.model.modelMap.get(Constants.NEXT);
		AbstractAction actionNext = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				nextExam();
			}
		};
		next.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
				Constants.NEXT);
		next.getActionMap().put(Constants.NEXT, actionNext);
		next.addActionListener(actionNext);
		next.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				nextExam();
			}
		});
	}

	protected void start() {
		isStarted = true;

		back.setVisible(true);
		exam.setVisible(true);
		exam.setEnabled(false);
		next.setVisible(true);
	}

	private void backExam() {
		currentQuestion--;
		currentQuestion = (currentQuestion <= 0) ? allQuestionCount : currentQuestion;
		verifyAnswers();
		initExam(currentQuestion);
	}

	private void nextExam() {
		currentQuestion++;
		currentQuestion = (currentQuestion >= allQuestionCount) ? 1 : currentQuestion;
		verifyAnswers();
		initExam(currentQuestion);
	}

	private void initExam(int examNumber) {
		resetAnswers();
		Exam exam = model.examMap.get(examNumber);

		title.setText(Constants.QUESTION_COUNT + examNumber + Constants.OFF + allQuestionCount);
		question.setText(exam.question);
		initAnswers(exam.answersSet);
	}

	private void resetAnswers() {
		answer1.setVisible(false);
		answer1.setSelected(false);
		answer2.setVisible(false);
		answer2.setSelected(false);
		answer3.setVisible(false);
		answer3.setSelected(false);
		answer4.setVisible(false);
		answer4.setSelected(false);
		answer5.setVisible(false);
		answer5.setSelected(false);
	}

	private void initAnswers(Set<Answer> answersSet) {
		checkboxCount = 0;
		for (Answer answer : answersSet) {
			checkboxCount++;
			switch (checkboxCount) {
			case 1:
				answer1.setAnswer(answer);
				answer1.setText(answer.text);
				answer1.setVisible(true);
				answer1.setSelected(answer.isAnswerFromUserChecked);
				break;
			case 2:
				answer2.setAnswer(answer);
				answer2.setText(answer.text);
				answer2.setVisible(true);
				answer2.setSelected(answer.isAnswerFromUserChecked);
				break;
			case 3:
				answer3.setAnswer(answer);
				answer3.setText(answer.text);
				answer3.setVisible(true);
				answer3.setSelected(answer.isAnswerFromUserChecked);
				break;
			case 4:
				answer4.setAnswer(answer);
				answer4.setText(answer.text);
				answer4.setVisible(true);
				answer4.setSelected(answer.isAnswerFromUserChecked);
				break;
			case 5:
				answer5.setAnswer(answer);
				answer5.setText(answer.text);
				answer5.setVisible(true);
				answer5.setSelected(answer.isAnswerFromUserChecked);
				break;
			default:
				// TODO: error
				break;
			}
		}

	}

	private void verifyAnswers() {
		for (int i = 1; i <= checkboxCount; i++) {
			switch (i) {
			case 1:
				verify(answer1);
				break;
			case 2:
				verify(answer2);
				break;
			case 3:
				verify(answer3);
				break;
			case 4:
				verify(answer4);
				break;
			case 5:
				verify(answer5);
				break;
			default:
				// TODO: error
				break;
			}
		}

	}

	private void verify(JCheckBoxAnswer answerCheckBox) {
		answerCheckBox.getAnswer().isAnswerFromUserChecked = answerCheckBox.isSelected();
//		if (answerCheckBox.isSelected() == answerCheckBox.getAnswer().is) {
//			//answerCheckBox.setBackground(Color.GREEN);
//		} else {
//			//answerCheckBox.setBackground(Color.RED);
//		}

	}
}
