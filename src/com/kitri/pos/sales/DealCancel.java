package com.kitri.pos.sales;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DealCancel extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DealCancel frame = new DealCancel();
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
	public DealCancel() {
		setLocation(new Point(500, 600));
		setSize(new Dimension(450, 300));
		setTitle("\uAC70\uB798\uCDE8\uC18C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uAC70\uB798\uCDE8\uC18C");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(133, 10, 157, 64);
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel label = new JLabel("\uAC70\uB798\uBC88\uD638");
		label.setFont(new Font("±¼¸²", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 10, 98, 64);
		panel_2.add(label);

		textField = new JTextField();
		textField.setBounds(110, 10, 288, 64);
		panel_2.add(textField);
		textField.setColumns(10);

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);

		JButton receipt_reprint = new JButton("\uC601\uC218\uC99D \uC7AC\uCD9C\uB825");
		receipt_reprint.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		receipt_reprint.setBounds(220, 23, 128, 51);
		panel.add(receipt_reprint);

		JButton cancel = new JButton("\uD658\uBD88");
		cancel.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		cancel.setBounds(87, 23, 121, 51);
		panel.add(cancel);
	}


}
