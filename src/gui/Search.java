package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import facade.Facade;

public class Search extends JFrame implements ItemListener {

	private JFrame frame;
	private JTextField textField;
	private Facade facade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search window = new Search();
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
	public Search() {
		initialize();
		facade = new Facade();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Type your search:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(92, 11, 297, 44);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(66, 120, 323, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JCheckBox btnStemming = new JCheckBox("with Stemming", false);
		JCheckBox btnStopWords = new JCheckBox("without Stop Words", false);
		btnStopWords.setBounds(66, 150, 150, 23);
		btnStemming.setBounds(230, 150, 135, 23);

		frame.getContentPane().add(btnStopWords);
		frame.getContentPane().add(btnStemming);
		
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String searchQuery = textField.getText();
				try {
					ArrayList<String> res = facade.searchFiles(searchQuery, btnStemming.isSelected(), btnStopWords.isSelected());
					Result result = new Result(res);
					result.setLocationRelativeTo(frame.getContentPane());
					result.setResizable(true);
					result.setVisible(true);
					Search.this.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(155, 211, 135, 23);
		frame.getContentPane().add(btnSearch);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
