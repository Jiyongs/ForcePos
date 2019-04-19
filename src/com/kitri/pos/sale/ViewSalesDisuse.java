package com.kitri.pos.sale;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class ViewSalesDisuse extends JPanel {
	//처리패널의 폐기버튼
	public JTable tableResult;//?
	
	public JTable mainTable;
	public JButton disuse;
	public DefaultTableModel tmodel;
	/**
	 * Create the panel.
	 */
	public ViewSalesDisuse() {
		setSize(new Dimension(1144, 535));
		setLayout(null);
		
		JPanel salesMainTablePanel = new JPanel();//테이블 패널
		salesMainTablePanel.setSize(new Dimension(1144, 535));
		salesMainTablePanel.setBounds(0, 0, 1144, 452);
		add(salesMainTablePanel);
		salesMainTablePanel.setLayout(null);
		
		JScrollPane mainScrollPane = new JScrollPane();//테이블 스크롤
		mainScrollPane.setSize(new Dimension(1144, 535));
		mainScrollPane.setBounds(14, 12, 1116, 428);
		salesMainTablePanel.add(mainScrollPane);
		
		mainTable = new JTable();
		mainScrollPane.setViewportView(mainTable);
		
		JPanel salesMainSearchPanel = new JPanel();//아래 패널
		salesMainSearchPanel.setBounds(0, 452, 1144, 83);
		add(salesMainSearchPanel);
		salesMainSearchPanel.setLayout(null);
		
		disuse = new JButton("폐기처리");
		disuse.setBounds(526, 0, 97, 46);
		salesMainSearchPanel.add(disuse);
		
		//[테이블]
		//임시 테이블 모델 생성
		Vector<String> header = new Vector<String>();
		header.add("상품코드");
		header.add("입고일자");
		header.add("상품명");
		header.add("판매가");
		header.add("유통기한");
		header.add("수량");
		tmodel = new DefaultTableModel(header,0) {
			
	            // Jtable 내용 편집 안되게
	            public boolean isCellEditable(int i, int c) {
	                return false;
	            }
	        };
	
		
	
		tableResult = new JTable(tmodel);
		tableResult.setRowHeight(30);
		tableResult.setSize(new Dimension(1144, 535));
		mainScrollPane.setViewportView(tableResult);

	}

	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}