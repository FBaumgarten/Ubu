package de.naumovs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class Controller {

	Model model;
	boolean isStarted = false;
	int questionCount = 0;

	private JLabel title;
	private JTextPane question;

	private JCheckBox answer1;
	private JCheckBox answer2;
	private JCheckBox answer3;
	private JCheckBox answer4;
	private JCheckBox answer5;
	private JButton along;
	private JButton back;

	public Controller(Model model) {
		this.model = model;
	}

	public void init() {
		this.questionCount = model.examMap.size();
		
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

//		answer1 = (JCheckBox) this.controllerMap.get(Constants.ANSWER1);
//		answer2 = (JCheckBox) this.controllerMap.get(Constants.ANSWER2);
//		answer3 = (JCheckBox) this.controllerMap.get(Constants.ANSWER3);
//		answer4 = (JCheckBox) this.controllerMap.get(Constants.ANSWER4);
//		answer5 = (JCheckBox) this.controllerMap.get(Constants.ANSWER5);
//		
//		along = (JButton) this.controllerMap.get(Constants.ALONG);		
//		back = (JButton) this.controllerMap.get(Constants.BACK);

	}

	protected void start() {
		isStarted = true;

		// set Title
		title.setText("TODO haben Fragen: " + questionCount);

	}

}
