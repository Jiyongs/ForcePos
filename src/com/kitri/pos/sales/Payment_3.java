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

public class Payment_3 extends JFrame {

	private JPanel contentPane;
	private final JPanel Payment_3 = new JPanel();
	private JButton btnP3Before;
	private JButton btnP3Cancel;
	private JButton btnP3Payment;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment_3 frame = new Payment_3();
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
	public Payment_3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(Payment_3, BorderLayout.CENTER);
		Payment_3.setLayout(null);
		
		JButton btnP3PrintReceipt = new JButton("\uC601\uC218\uC99D \uCD9C\uB825");
		btnP3PrintReceipt.setBounds(350, 70, 150, 50);
		Payment_3.add(btnP3PrintReceipt);
		
		btnP3Before = new JButton("\uC774\uC804\uC73C\uB85C");
		btnP3Before.setBounds(100, 341, 150, 50);
		Payment_3.add(btnP3Before);
		
		btnP3Cancel = new JButton("\uAC70\uB798\uCDE8\uC18C");
		btnP3Cancel.setBounds(350, 130, 150, 50);
		Payment_3.add(btnP3Cancel);
		
		btnP3Payment = new JButton("\uACB0\uC81C");
		btnP3Payment.setBounds(350, 190, 150, 100);
		Payment_3.add(btnP3Payment);
		
		JTextArea taP3details = new JTextArea();
		taP3details.setText("\uD560\uC778\uC720\uBB34 : \"\"\\n*\uC774\uC804\uACB0\uC81C\uAE08\uC561 : \"\"\\n\uCE74\uB4DC\uB791 \uD604\uAE08\uC740 \uC5B4\uB514\uC11C \uAD6C\uBD84?");
		taP3details.setBounds(50, 10, 250, 320);
		Payment_3.add(taP3details);
	}
}

