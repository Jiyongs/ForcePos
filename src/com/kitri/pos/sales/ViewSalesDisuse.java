package com.kitri.pos.sales;
								
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane; 
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Dimension;	

public class ViewSalesDisuse extends JPanel {
	
	private JTable tableResult;
	private JTable mainTable;
	
	
	/**
	 * Create the panel.
	 */
	public ViewSalesDisuse() {
		setSize(new Dimension(1144, 535));
		setLayout(null);
		
		JPanel salesMainTablePanel = new JPanel();//테이블 패널
		salesMainTablePanel.setBounds(0, 0, 1144, 452);
		add(salesMainTablePanel);
		salesMainTablePanel.setLayout(null);
		
		JScrollPane mainScrollPane = new JScrollPane();//테이블 스크롤
		mainScrollPane.setBounds(14, 12, 1116, 428);
		salesMainTablePanel.add(mainScrollPane);
		
		mainTable = new JTable();
		mainScrollPane.setViewportView(mainTable);
		
		JPanel salesMainSearchPanel = new JPanel();//폐기 패널
		salesMainSearchPanel.setBounds(0, 452, 1144, 83);
		add(salesMainSearchPanel);
		salesMainSearchPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("\uD3D0\uAE30 \uCC98\uB9AC"); //폐기처리 
		btnNewButton.setBackground(new Color(0, 102, 204));
		btnNewButton.setBounds(494, 12, 142, 59);
		salesMainSearchPanel.add(btnNewButton);
		
		
		
		//[테이블]
		//임시 테이블 모델 생성
		
		String header[] = {"상품코드", "입고일자", "상품명", "상품분류", "수량", "판매", "유통기한"};
		String contents[][] = {
				{"FFSW-108", "2019.04.04", "베이컨에그토마토샌드위치","샌드위치", "2", "2200", "D-5d"},
				{"FFSW-102", "2019.04.04", "계란듬뿍샌드", "샌드위치", "2", "2000", "D-15"},
				{"FFSG-102", "2019.04.04", "참치마요", "삼각김밥", "15", "800", "D-1d"},
				{"FFS-101", "2019.04.03", "베이컨소세지", "소시지", "5", "1800", "D-1d"},
				{"FFS104", "2019.04.03", "의성마늘프랑크", "소시지", "1", "1500", "D-1d"},
				{"FFD-112", "2019.04.05", "홍석천치킨도시락", "도시락", "2", "4800", "D-1d"}
		
		};
		
		DefaultTableModel tmodel = new DefaultTableModel(contents, header);
		
		tableResult = new JTable(tmodel);
		tableResult.setRowHeight(30);
		mainScrollPane.setViewportView(tableResult);

	}
}
