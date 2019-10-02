package pdfDocWr;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * Created by MaxErshov on 21/08/2018.
 */

final class GUI extends JPanel {

	final JTextArea log = new JTextArea();
	JTextField nameField;
	JTextField birthField;
	JTextField dateField;

	JLabel nameLabel;
	JLabel birthLabel;
	JLabel dateLabel;
	JLabel myNameLabel;
	JLabel datePlusLabel;
	static JButton doneButton;

	GUI() {

		super(new BorderLayout());

		// Create buttons and add listener
		doneButton = new JButton("Готово");
		doneButton.setPreferredSize(new Dimension(120, 40));
		String filename = System.getProperty("user.dir") + System.getProperty("file.separator") + "pass.pdf";

		doneButton.addActionListener(e -> append(filename));
		doneButton.addActionListener(e -> buttonClicked());
		JButton dateButt = new JButton("Сегодня");
		dateButt.setPreferredSize(new Dimension(100, 40));
		dateButt.addActionListener(e -> setDate(getDay("a")));
		JButton tomorrowButt = new JButton("Завтра");
		tomorrowButt.setPreferredSize(new Dimension(100, 40));
		tomorrowButt.addActionListener(e -> setDate(getDay("tomorrow")));

		// Create fields for input text
		nameField = new JTextField("");
		doneButton.addActionListener(e -> append(nameField.getText()));
		birthField = new JTextField("");
		doneButton.addActionListener(e -> append(birthField.getText()));
		dateField = new JTextField("");
		doneButton.addActionListener(e -> append(dateField.getText()));

		// Create labels
		nameLabel = new JLabel("ФИО: ");
		birthLabel = new JLabel("Дата рождения: ");
		dateLabel = new JLabel("Дата пропуска:");
		myNameLabel = new JLabel("by ErshovMaksim");
		datePlusLabel = new JLabel("(\"день\".\"месяц\")");

		// Change fonts
		String classicFont = nameLabel.getFont().getName();
		nameLabel.setFont(new Font(classicFont, Font.PLAIN, 19));
		birthLabel.setFont(new Font(classicFont, Font.PLAIN, 19));
		dateLabel.setFont(new Font(classicFont, Font.PLAIN, 19));
		myNameLabel.setFont(new Font(classicFont, Font.PLAIN, 11));
		datePlusLabel.setFont(new Font(classicFont, Font.PLAIN, 13));

		Box buttBox = Box.createHorizontalBox();
		buttBox.add(dateButt);
		buttBox.add(tomorrowButt);

		// Add to box
		Box list = Box.createVerticalBox();
		list.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		list.add(nameLabel);
		list.add(nameField);
		list.add(Box.createVerticalStrut(10));
		list.add(birthLabel);
		list.add(birthField);
		list.add(Box.createVerticalStrut(10));
		list.add(dateLabel);
		list.add(datePlusLabel);
		list.add(dateField);
		list.add(Box.createVerticalStrut(2));

		// create layout and add all
		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p.add(list.add(buttBox));
		p.add(myNameLabel, BorderLayout.PAGE_END);
		p.add(doneButton);
		add(list, BorderLayout.NORTH);
		add(new JScrollPane(log));
		add(p, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(420, 400));
	}

	void append(String text) {
		/* For log */
		log.append(text + "\n");
		log.setCaretPosition(log.getDocument().getLength());
	}

	void buttonClicked() {
		/* If button clicked - get text from fields and get PDFPaser */
		String name = nameField.getText();
		String birth = birthField.getText();
		String date = dateField.getText();
		System.out.println("Strings =" + name + birth + date);
		try {
			@SuppressWarnings("unused")
			Writer parser = new Writer(name, birth, date);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void setDate(String text) {
		/* Set today or tomorrow date to date field */
		dateField.setText(text);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				createGUI();
			}
		});
	}

	static String getDay(String where) {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM");
		String today = dateFormat.format(new Date()).toString();
		String tomorrow = dateFormat.format(new Date().getTime() + 86400000).toString();
		if (where.equals("tomorrow")) {
			return tomorrow;
		}
		return today;
	}

	static void createGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
			Toolkit.getDefaultToolkit().beep();
		}
		JFrame frame = new JFrame("PDFWriter");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GUI());
		frame.getRootPane().setDefaultButton(doneButton);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}