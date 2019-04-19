package com.kitri.pos.account;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AccountBtn extends JPanel {

	public JButton userInsert;
	public JButton userUpdate;
	public JButton userDelete;
	

	/**
	 * Create the panel.
	 */
	public AccountBtn() {
		setBackground(Color.WHITE);
		setLayout(null);

		userInsert = new JButton("\uC720\uC800\uB4F1\uB85D");
		userInsert.setForeground(new Color(255, 255, 255));
		userInsert.setBackground(new Color(0, 0, 128));
		userInsert.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		userInsert.setBounds(0, 0, 164, 120);
		add(userInsert);

		userUpdate = new JButton("\uC720\uC800\uC218\uC815");
		userUpdate.setBackground(new Color(100, 149, 237));
		userUpdate.setForeground(new Color(255, 255, 255));
		userUpdate.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		userUpdate.setBounds(0, 130, 164, 120);
		add(userUpdate);

		userDelete = new JButton("\uC720\uC800\uC0AD\uC81C");
		userDelete.setBackground(new Color(0, 0, 128));
		userDelete.setForeground(new Color(255, 255, 255));
		userDelete.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		userDelete.setBounds(0, 260, 164, 120);
		add(userDelete);

		
	}

}
