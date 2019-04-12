package com.kitri.pos.sales;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

public class DealCancel extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DealCancel dialog = new DealCancel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public DealCancel() {
		
		setTitle("\uAC70\uB798\uCDE8\uC18C");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(3, 1, 0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblNewLabel = new JLabel("\uAC70\uB798\uCDE8\uC18C");
				lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel, BorderLayout.CENTER);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel_1 = new JLabel("\uAC70\uB798\uBC88\uD638");
				lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 20));
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setBounds(24, 10, 84, 52);
				panel.add(lblNewLabel_1);
			}
			
			textField = new JTextField();
			textField.setBounds(120, 13, 252, 52);
			panel.add(textField);
			textField.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JButton refund = new JButton("\uD658\uBD88");
			refund.setBounds(101, 21, 97, 53);
			panel.add(refund);
			{
				JButton reprint = new JButton("\uC601\uC218\uC99D \uC7AC\uCD9C\uB825");
				reprint.setBounds(210, 21, 111, 53);
				panel.add(reprint);
			}
		}
		setModal(true);
	}
	

}
