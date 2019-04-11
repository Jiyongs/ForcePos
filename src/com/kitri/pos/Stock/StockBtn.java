package com.kitri.pos;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;

public class StockBtn extends JPanel {

	JButton stockChg;
	JButton stockRefresh;
	JButton stockIn;
	JButton stockSearch;

	/**
	 * Create the panel.
	 */
	public StockBtn() {
		setLayout(null);
		
		stockSearch = new JButton("\uC7AC\uACE0\uC870\uD68C");
		stockSearch.setBounds(0, 10, 164, 120);
		add(stockSearch);
		stockSearch.setForeground(Color.WHITE);
		stockSearch.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		stockSearch.setBackground(new Color(0, 0, 128));
		
		stockIn = new JButton("\uC785\uACE0");
		stockIn.setBounds(0, 145, 164, 120);
		add(stockIn);
		stockIn.setForeground(Color.WHITE);
		stockIn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		stockIn.setBackground(new Color(100, 149, 237));
		
		stockChg = new JButton("\uC7AC\uACE0\uC218\uC815");
		stockChg.setBounds(0, 278, 164, 120);
		add(stockChg);
		stockChg.setForeground(Color.WHITE);
		stockChg.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		stockChg.setBackground(new Color(0, 0, 128));
		
		stockRefresh = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		stockRefresh.setBounds(0, 412, 164, 120);
		add(stockRefresh);
		stockRefresh.setForeground(Color.WHITE);
		stockRefresh.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		stockRefresh.setBackground(new Color(100, 149, 237));

	}

}
