package com.kitri.pos.stock;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;

public class StockBtn extends JPanel {

	public JButton stockChg;
	public JButton stockIn;
	public JButton stockSearch;
	public JButton stockevery;

	/**
	 * Create the panel.
	 */
	public StockBtn() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		stockSearch = new JButton("\uC7AC\uACE0\uC870\uD68C");
		stockSearch.setBounds(0, 0, 164, 120);
		add(stockSearch);
		stockSearch.setForeground(Color.WHITE);
		stockSearch.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		stockSearch.setBackground(new Color(0, 0, 128));
		
		stockIn = new JButton("\uC785\uACE0");
		stockIn.setBounds(0, 130, 164, 120);
		add(stockIn);
		stockIn.setForeground(Color.WHITE);
		stockIn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		stockIn.setBackground(new Color(100, 149, 237));
		
		stockChg = new JButton("\uC7AC\uACE0\uC218\uC815");
		stockChg.setBounds(0, 260, 164, 120);
		add(stockChg);
		stockChg.setForeground(Color.WHITE);
		stockChg.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		stockChg.setBackground(new Color(0, 0, 128));
		
		stockevery = new JButton("\uBAA8\uB4E0\uC7AC\uACE0");
		stockevery.setForeground(Color.WHITE);
		stockevery.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		stockevery.setBackground(new Color(100, 149, 237));
		stockevery.setBounds(0, 390, 164, 120);
		add(stockevery);

	}
}
