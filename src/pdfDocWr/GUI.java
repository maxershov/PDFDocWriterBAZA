package pdfDocWr;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;

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
	JButton stopButton;
	static JButton doneButton;

	GUI() throws URISyntaxException {

		super(new BorderLayout());

		// Create buttons and add listener
		doneButton = new JButton("Готово");
		doneButton.setPreferredSize(new Dimension(90, 40));
		String filename = PropertiesGet.getFilePath() + "/passOut.pdf";
		doneButton.addActionListener(e -> append(filename));
		doneButton.addActionListener(e -> {
			try {
				buttonClicked();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		JButton dateButt = new JButton("Сегодня");
		dateButt.setPreferredSize(new Dimension(100, 40));
		dateButt.addActionListener(e -> setDate(getDay("a")));
		JButton tomorrowButt = new JButton("Завтра");
		tomorrowButt.setPreferredSize(new Dimension(100, 40));
		tomorrowButt.addActionListener(e -> setDate(getDay("tomorrow")));

		stopButton = new JButton("Стоп");
		stopButton.setPreferredSize(new Dimension(90, 40));
		stopButton.addActionListener(e -> stopAction());

		// Create fields for input text
		nameField = new JTextField("");
		birthField = new JTextField("");
		dateField = new JTextField("");

		// Create labels
		nameLabel = new JLabel("ФИО: ");
		birthLabel = new JLabel("Дата рождения: ");
		dateLabel = new JLabel("Дата пропуска:");
		myNameLabel = new JLabel("github.com/maxershov/");
		datePlusLabel = new JLabel("(\"день\".\"месяц\")");

		// Change fonts
		String classicFont = nameLabel.getFont().getName();
		nameLabel.setFont(new Font(classicFont, Font.PLAIN, 19));
		birthLabel.setFont(new Font(classicFont, Font.PLAIN, 19));
		dateLabel.setFont(new Font(classicFont, Font.PLAIN, 19));
		myNameLabel.setFont(new Font(classicFont, Font.PLAIN, 9));
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
		list.add(myNameLabel);

		// create layout and add all
		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p.add(list.add(buttBox));
		p.add(stopButton);
		p.add(doneButton);
		add(list, BorderLayout.NORTH);
		add(new JScrollPane(log));
		add(p, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(420, 450));
	}

	void stopAction() {
		System.exit(0);
	}

	void append(String text) {
		/* For log */
		log.append(text + "\n");
		log.setCaretPosition(log.getDocument().getLength());
	}

	void buttonClicked() throws URISyntaxException {
		/* If button clicked - get text from fields and get PDFPaser */
		String name = nameField.getText();
		String birth = birthField.getText();
		String date = dateField.getText();
		append(name + " " + birth + " " + date);
		System.out.println("Strings =" + name + birth + date);
		Thread t = new Thread() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					Writer parser = new Writer(name, birth, date);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	void setDate(String text) {
		/* Set today or tomorrow date to date field */
		dateField.setText(text);
	}

	public static void main(String[] args)
			throws InvalidPropertiesFormatException, FileNotFoundException, URISyntaxException, IOException {
		/* Load properties and create GUI */
		PropertiesGet.loadProperties();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					createGUI();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	static String getDay(String where) {
		/* Get today and tomorrow date formatted */
		DateFormat dateFormat = new SimpleDateFormat("dd.MM");
		String today = dateFormat.format(new Date()).toString();
		String tomorrow = dateFormat.format(new Date().getTime() + 86400000).toString();
		if (where.equals("tomorrow")) {
			return tomorrow;
		}
		return today;
	}

	static void createGUI() throws URISyntaxException {
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
