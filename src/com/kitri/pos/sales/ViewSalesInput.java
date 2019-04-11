package com.kitri.pos;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class ViewSalesInput extends JPanel {
	private JTextField code_input;
	private JTextField product_name_input;
	private JTextField total_price_input;

	/**
	 * Create the panel.
	 */
	public ViewSalesInput() {
		setBounds(new Rectangle(0, 0, 1144, 535));
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel backgroundpanel = new JPanel();
		backgroundpanel.setBounds(0, 0, 1144, 535);
		add(backgroundpanel);
		backgroundpanel.setLayout(null);
		
		String header[] = {"번호", "상품명", "단가", "수량", "금액", "상품코드", "유통기한"};
		String contents[][]= {
				{"1", "바나나킥", "1000", "1", "1000", "FIS-105", "21.04.05"}	
		};
		DefaultTableModel model = new DefaultTableModel(contents, header);
		JTable table = new JTable(new DefaultTableModel(
			new Object[][] {
				{"1", "\uBC14\uB098\uB098\uD0A5", "1000", "1", "1000", "FIS-105", "21.04.05"},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"\uBC88\uD638", "\uC0C1\uD488\uBA85", "\uB2E8\uAC00", "\uC218\uB7C9", "\uAE08\uC561", "\uC0C1\uD488\uCF54\uB4DC", "\uC720\uD1B5\uAE30\uD55C"
			}
		));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(Color.WHITE);
		table.setRowMargin(10);
		table.setRowHeight(30);
		JScrollPane enroll = new JScrollPane(table);
		enroll.setBounds(0, 0, 1144, 380);
		backgroundpanel.add(enroll);

		
		
		JPanel searchpanel = new JPanel();
		searchpanel.setBackground(Color.WHITE);
		searchpanel.setBounds(0, 379, 1144, 156);
		backgroundpanel.add(searchpanel);
		searchpanel.setLayout(null);
		
		JLabel product_code = new JLabel("\uC0C1\uD488\uCF54\uB4DC");
		product_code.setFont(new Font("굴림", Font.PLAIN, 15));
		product_code.setHorizontalAlignment(SwingConstants.CENTER);
		product_code.setBounds(51, 31, 80, 50);
		searchpanel.add(product_code);
		
		JLabel product_name = new JLabel("\uC0C1\uD488\uBA85");
		product_name.setHorizontalAlignment(SwingConstants.CENTER);
		product_name.setBounds(new Rectangle(10, 10, 10, 10));
		product_name.setFont(new Font("굴림", Font.PLAIN, 15));
		product_name.setBounds(369, 31, 80, 50);
		searchpanel.add(product_name);
		
		JLabel total_price = new JLabel("\uCD1D \uACB0\uC81C \uAE08\uC561");
		total_price.setHorizontalAlignment(SwingConstants.CENTER);
		total_price.setFont(new Font("굴림", Font.PLAIN, 15));
		total_price.setBounds(763, 31, 91, 50);
		searchpanel.add(total_price);
		
		code_input = new JTextField();
		code_input.setBounds(128, 37, 205, 40);
		searchpanel.add(code_input);
		code_input.setColumns(10);
		
		product_name_input = new JTextField();
		product_name_input.setColumns(10);
		product_name_input.setBounds(475, 41, 237, 40);
		searchpanel.add(product_name_input);
		
		total_price_input = new JTextField();
		total_price_input.setColumns(10);
		total_price_input.setBounds(866, 41, 205, 40);
		searchpanel.add(total_price_input);

	}

		  
}
