package de.naumovs;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

public class View {

	private JFrame frame;
	protected Model model = new Model();
	private Controller controller = new Controller(model);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
		model.init();
		controller.init();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 756, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(75, 55, 539, 2);
		frame.getContentPane().add(separator_1);

		JLabel title = new JLabel("");
		title.setBounds(181, 19, 311, 20);
		model.modelMap.put(Constants.TITLE, title);
		frame.getContentPane().add(title);

		JTextPane question = new JTextPane();
		question.setText("");
		question.setEditable(false);
		question.setBounds(75, 101, 539, 183);
		model.modelMap.put(Constants.QUESTION, question);
		frame.getContentPane().add(question);

		JCheckBox answer1 = new JCheckBoxWithID("");
		answer1.setBounds(68, 350, 139, 29);
		model.modelMap.put(Constants.ANSWER1, answer1);
		frame.getContentPane().add(answer1);

		JCheckBox answer2 = new JCheckBoxWithID("");
		answer2.setBounds(68, 390, 139, 29);
		model.modelMap.put(Constants.ANSWER2, answer2);
		frame.getContentPane().add(answer2);

		JCheckBox answer3 = new JCheckBoxWithID("");
		answer3.setBounds(68, 430, 139, 29);
		model.modelMap.put(Constants.ANSWER3, answer3);
		frame.getContentPane().add(answer3);

		JCheckBox answer4 = new JCheckBoxWithID("");
		answer4.setBounds(68, 470, 139, 29);
		model.modelMap.put(Constants.ANSWER4, answer4);
		frame.getContentPane().add(answer4);

		JCheckBox answer5 = new JCheckBoxWithID("");
		answer5.setBounds(68, 510, 139, 29);
		model.modelMap.put(Constants.ANSWER5, answer5);
		frame.getContentPane().add(answer5);

		JButton back = new JButton(Constants.BACK_TEXT);
		back.setBounds(71, 590, 115, 29);
		model.modelMap.put(Constants.BACK, back);
		frame.getContentPane().add(back);

		JButton exam = new JButton(Constants.EXAM_TEXT);
		exam.setBounds(287, 590, 115, 29);
		model.modelMap.put(Constants.EXAM, exam);
		frame.getContentPane().add(exam);

		JButton along = new JButton(Constants.ALONG_TEXT);
		along.setBounds(499, 590, 115, 29);
		model.modelMap.put(Constants.ALONG, along);
		frame.getContentPane().add(along);

		JSeparator separator = new JSeparator();
		separator.setBounds(75, 328, 533, 2);
		frame.getContentPane().add(separator);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(84, 558, 546, 2);
		frame.getContentPane().add(separator_2);

	}

	class JCheckBoxWithID extends JCheckBox {
		private static final long serialVersionUID = 1L;

		private int id;

		public JCheckBoxWithID(String text) {
			super(text);
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

	}
}
