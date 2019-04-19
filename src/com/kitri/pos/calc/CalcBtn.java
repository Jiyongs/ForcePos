package com.kitri.pos.calc;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CalcBtn extends JPanel {

	
	/**
	 * Create the panel.
	 */
	public CalcBtn() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("정산은 하루에");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 164, 148);
		add(lblNewLabel);
		
		JLabel label = new JLabel("한번만 가능");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(0, 168, 164, 148);
		add(label);

	}
}
