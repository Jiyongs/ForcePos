package com.kitri.pos.stat;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JPanel;

/*
	ViewStatButtons : 통계 오른쪽 버튼들 패널
*/

public class ViewStatButtons extends JPanel {

	public JButton sBtnProduct;
	public JButton sBtnYear;
	public JButton sBtnMonth;
	public JButton sBtnDay;

	public ViewStatButtons() {
		setSize(new Dimension(164, 655));
		setLayout(null);
		setBackground(new Color(255,255,255));
		
		sBtnProduct = new JButton("\uC0C1\uD488\uBCC4");
		sBtnProduct.setForeground(Color.WHITE);
		sBtnProduct.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnProduct.setBackground(new Color(0, 0, 128));
		sBtnProduct.setBounds(0, 0, 164, 120);
		add(sBtnProduct);
		
		sBtnYear = new JButton("\uC5F0\uB3C4\uBCC4");
		sBtnYear.setForeground(Color.WHITE);
		sBtnYear.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnYear.setBackground(new Color(100, 149, 237));
		sBtnYear.setBounds(0, 130, 164, 120);
		add(sBtnYear);
		
		sBtnMonth = new JButton("\uC6D4\uBCC4");
		sBtnMonth.setForeground(Color.WHITE);
		sBtnMonth.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnMonth.setBackground(new Color(0, 0, 128));
		sBtnMonth.setBounds(0, 260, 164, 120);
		add(sBtnMonth);
		
		sBtnDay = new JButton("\uC77C\uBCC4");
		sBtnDay.setForeground(Color.WHITE);
		sBtnDay.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnDay.setBackground(new Color(100, 149, 237));
		sBtnDay.setBounds(0, 390, 164, 120);
		add(sBtnDay);

	}
}
