package com.kitri.pos.sale;

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
	public JButton btnP4Payment;
	public JButton btnP4PrintReceipt;
	public JTextArea taP4details;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Payment_4 frame = new Payment_4();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Payment_4() {
		setTitle("영수증 출력");
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(Payment_4, BorderLayout.CENTER);
		Payment_4.setLayout(null);
		
		btnP4PrintReceipt = new JButton("\uC601\uC218\uC99D \uCD9C\uB825");
		btnP4PrintReceipt.setBounds(394, 70, 150, 100);
		Payment_4.add(btnP4PrintReceipt);

		btnP4Payment = new JButton("\uC644\uB8CC");
		btnP4Payment.setBounds(394, 190, 150, 100);
		Payment_4.add(btnP4Payment);

		taP4details = new JTextArea();
		taP4details.setBounds(50, 10, 300, 360);
		Payment_4.add(taP4details);
	}
}
