package com.kitri.pos.sales;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Payment_4 extends JFrame {

	private JPanel contentPane;
	final JPanel Payment_4 = new JPanel();
	JButton btnP4Before;
	JButton btnP4Cancel;
	JButton btnP4Payment;
	JButton btnP4PrintReceipt;
	JTextArea taP4details;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment_4 frame = new Payment_4();
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
	public Payment_4() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(Payment_4, BorderLayout.CENTER);
		Payment_4.setLayout(null);
		
		btnP4PrintReceipt = new JButton("\uC601\uC218\uC99D \uCD9C\uB825");
		btnP4PrintReceipt.setBounds(394, 70, 150, 50);
		Payment_4.add(btnP4PrintReceipt);

		btnP4Before = new JButton("\uC774\uC804\uC73C\uB85C");
		btnP4Before.setBounds(394, 300, 150, 50);
		Payment_4.add(btnP4Before);

		btnP4Cancel = new JButton("\uAC70\uB798\uCDE8\uC18C");
		btnP4Cancel.setBounds(394, 130, 150, 50);
		Payment_4.add(btnP4Cancel);

		btnP4Payment = new JButton("\uACB0\uC81C");
		btnP4Payment.setBounds(394, 190, 150, 100);
		Payment_4.add(btnP4Payment);

		taP4details = new JTextArea();
		taP4details.setText(
				"\uD560\uC778\uC720\uBB34 : \"\"\\n*\uC774\uC804\uACB0\uC81C\uAE08\uC561 : \"\"\\n\uCE74\uB4DC\uB791 \uD604\uAE08\uC740 \uC5B4\uB514\uC11C \uAD6C\uBD84?");
		taP4details.setBounds(50, 10, 300, 360);
		Payment_4.add(taP4details);
	}
}
