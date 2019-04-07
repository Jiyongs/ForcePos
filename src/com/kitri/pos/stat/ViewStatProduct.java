package com.kitri.pos.stat;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;

public class ViewStatProduct extends JPanel {
	private JTable tableResult;

	public ViewStatProduct() {
		setLayout(null);
		
		//#검색조건 패널#
		JPanel pSetSearch = new JPanel();
		pSetSearch.setBounds(12, 27, 1120, 37);
		add(pSetSearch);
		pSetSearch.setLayout(null);
		
		JLabel lbProductLevel = new JLabel("상품분류 :");
		lbProductLevel.setBounds(0, 0, 101, 37);
		pSetSearch.add(lbProductLevel);
		lbProductLevel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JComboBox cboxMidiumLevel = new JComboBox();
		cboxMidiumLevel.setBounds(101, 0, 170, 37);
		pSetSearch.add(cboxMidiumLevel);
		
		JLabel lbdash1 = new JLabel("-");
		lbdash1.setBounds(279, 0, 13, 37);
		pSetSearch.add(lbdash1);
		lbdash1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JComboBox cboxMinorLevel = new JComboBox();
		cboxMinorLevel.setBounds(295, 0, 170, 37);
		pSetSearch.add(cboxMinorLevel);
		
		JLabel lbShowDate = new JLabel("조회기간 :");
		lbShowDate.setBounds(483, 0, 101, 37);
		pSetSearch.add(lbShowDate);
		lbShowDate.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JSpinner spinStartYear = new JSpinner();
		spinStartYear.setBounds(580, 0, 114, 37);
		pSetSearch.add(spinStartYear);
		spinStartYear.setToolTipText("");
		
		JLabel lbdash2 = new JLabel("-");
		lbdash2.setBounds(699, 0, 13, 37);
		pSetSearch.add(lbdash2);
		lbdash2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JSpinner spinEndYear = new JSpinner();
		spinEndYear.setBounds(713, 0, 114, 37);
		pSetSearch.add(spinEndYear);
		spinEndYear.setToolTipText("");
		
		JButton btnSearch = new JButton("조회");
		btnSearch.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnSearch.setBounds(839, 0, 101, 37);
		pSetSearch.add(btnSearch);
		
		// #테이블 스크롤 패널#
		JScrollPane spShowTable = new JScrollPane();
		spShowTable.setBounds(12, 74, 554, 451);
		add(spShowTable);
		
		//[테이블]	
		//임시 테이블 모델 생성
		String header[] = {"매출순위", "상품분류", "상품명", "판매가", "판매수량", "매출합계", "제조사"};
		String contents[][] = {
				{"1", "Fresh Food", "참치마요", "800", "500", "400000", "GS25"},
				{"2", "Fresh Food", "딸기샌드위치", "800", "400", "320000", "GS25"},
				{"3", "Fresh Food", "스팸마요", "800", "300", "240000", "GS25"},
				{"4", "Fresh Food", "계란듬뿍샌드", "800", "200", "160000", "GS25"},
				{"5", "Fresh Food", "아이돌샌드위치", "800", "100", "80000", "GS25"}
				};
		
		DefaultTableModel tmodel = new DefaultTableModel(contents, header);
		
		tableResult = new JTable(tmodel);
		spShowTable.setViewportView(tableResult);
		
		
		// #그래프 패널#
		JPanel pShowGraph = new JPanel();
		pShowGraph.setBounds(578, 74, 554, 451);
		add(pShowGraph);

	}
}
