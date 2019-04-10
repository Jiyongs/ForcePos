package com.kitri.pos.stat;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JPanel;

/*
	ViewStatButtons : Åë°è ¿À¸¥ÂÊ ¹öÆ°µé ÆÐ³Î
*/

public class ViewStatButtons extends JPanel {

	public ViewStatButtons() {
		setSize(new Dimension(164, 655));
		setLayout(null);
		setBackground(new Color(0,0,128));
		
		JButton sBtnProduct = new JButton("\uC0C1\uD488\uBCC4");
		sBtnProduct.setForeground(Color.WHITE);
		sBtnProduct.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		sBtnProduct.setBackground(new Color(0, 0, 128));
		sBtnProduct.setBounds(0, 0, 164, 120);
		add(sBtnProduct);
		
		JButton sBtnYear = new JButton("\uC5F0\uB3C4\uBCC4");
		sBtnYear.setForeground(Color.WHITE);
		sBtnYear.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		sBtnYear.setBackground(new Color(100, 149, 237));
		sBtnYear.setBounds(0, 130, 164, 120);
		add(sBtnYear);
		
		JButton sBtnMonth = new JButton("\uC6D4\uBCC4");
		sBtnMonth.setForeground(Color.WHITE);
		sBtnMonth.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		sBtnMonth.setBackground(new Color(0, 0, 128));
		sBtnMonth.setBounds(0, 260, 164, 120);
		add(sBtnMonth);
		
		JButton sBtnDay = new JButton("\uC77C\uBCC4");
		sBtnDay.setForeground(Color.WHITE);
		sBtnDay.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		sBtnDay.setBackground(new Color(100, 149, 237));
		sBtnDay.setBounds(0, 390, 164, 120);
		add(sBtnDay);

	}
}
