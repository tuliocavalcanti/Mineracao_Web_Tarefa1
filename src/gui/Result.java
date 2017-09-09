package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Result extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Result frame = new Result(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Result(ArrayList<String> urls) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		File[] files = new File[urls.size()];
		for(int i = 0; i < files.length; i++){
			files[i] = new File(urls.get(i));
		}
				
		JList list = new JList(files);
		list.setVisibleRowCount(100);
		list.setBounds(58, 112, 308, 138);
		list.setLayoutOrientation(JList.VERTICAL);
        JScrollPane scrollPane = new JScrollPane(list);
		contentPane.add(scrollPane);
		
		JLabel lblEmDocumentos = new JLabel(urls.size() +  " documento(s) encontrado(s)");
		scrollPane.setColumnHeaderView(lblEmDocumentos);
		lblEmDocumentos.setBounds(10, 62, 414, 39);
		lblEmDocumentos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmDocumentos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
	}

}
