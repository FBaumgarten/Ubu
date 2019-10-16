package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
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
		
		JLabel lblWasHierStehen = new JLabel("was hier stehen muss, habe keine Ahnung");
		lblWasHierStehen.setBounds(181, 19, 311, 20);
		frame.getContentPane().add(lblWasHierStehen);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Bitte lesen die Regeln und verstehen, \t\r wenn Sie verstanden haben, drücken Sie die Taste \"Weiter\"");
		textPane.setBounds(75, 101, 539, 183);
		frame.getContentPane().add(textPane);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(75, 328, 533, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(84, 558, 546, 107);
		frame.getContentPane().add(separator_2);
		
		JButton btnWeiter = new JButton("Weiter");
		btnWeiter.setBounds(499, 603, 115, 29);
		frame.getContentPane().add(btnWeiter);
		
		JButton button = new JButton("Zurück");
		button.setBounds(71, 590, 115, 29);
		frame.getContentPane().add(button);
		
		JCheckBox chckbxAntwort = new JCheckBox("Antwort 1");
		chckbxAntwort.setBounds(68, 350, 139, 29);
		frame.getContentPane().add(chckbxAntwort);
		
		JCheckBox checkBox = new JCheckBox("Antwort 2");
		checkBox.setBounds(68, 390, 139, 29);
		frame.getContentPane().add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("Antwort 3");
		checkBox_1.setBounds(68, 430, 139, 29);
		frame.getContentPane().add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("Antwort 4");
		checkBox_2.setBounds(68, 470, 139, 29);
		frame.getContentPane().add(checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("Antwort 5");
		checkBox_3.setBounds(68, 510, 139, 29);
		frame.getContentPane().add(checkBox_3);
	}
}
