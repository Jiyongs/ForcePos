package com.kitri.pos.stat;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
	ViewStatProduct : 상품별 통계 패널
*/

public class ViewStatProduct extends JPanel {

	JButton btnSearch;

	JTable tableResult;
	DefaultTableModel tmodel;

	ViewStatProductService vps;
	JScrollPane spShowTable;

	public ViewStatProduct() {
		setLayout(null);
		setSize(new Dimension(1144, 535));

		// #검색조건 패널#
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

		btnSearch = new JButton("조회");
		btnSearch.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnSearch.setBounds(839, 0, 101, 37);
		pSetSearch.add(btnSearch);

		// #테이블 스크롤 패널#
		spShowTable = new JScrollPane();
		spShowTable.setBounds(12, 74, 554, 451);
		add(spShowTable);

		// [테이블]
		// 임시 테이블 모델 생성
//		String header[] = { "매출순위", "상품분류", "상품명", "판매가", "판매수량", "매출합계", "제조사" };
//		String contents[][] = { { "1", "Fresh Food", "참치마요", "800", "500", "400000", "GS25" },
//				{ "2", "Fresh Food", "딸기샌드위치", "800", "400", "320000", "GS25" },
//				{ "3", "Fresh Food", "스팸마요", "800", "300", "240000", "GS25" },
//				{ "4", "Fresh Food", "계란듬뿍샌드", "800", "200", "160000", "GS25" },
//				{ "5", "Fresh Food", "아이돌샌드위치", "800", "100", "80000", "GS25" } };
//
//		DefaultTableModel tmodel = new DefaultTableModel(contents, header);

		// 테이블 열 세팅
		Vector<String> col = new Vector<String>(); // 열
		col.add("매출순위");
		col.add("상품코드");
		col.add("상품분류");
		col.add("상품명");
		col.add("판매가");
		col.add("매입가");
		col.add("판매수량");
		col.add("매출합계");
		col.add("제조사");
		tmodel = new DefaultTableModel(col, 0);

		// 테이블 보이기
		tableResult = new JTable(tmodel);
		tableResult.setRowMargin(10);
		tableResult.setRowHeight(30);
		// 테이블 값 가운데 정렬
		Stat.tableCellCenter(tableResult);

		spShowTable.setViewportView(tableResult);

		// #그래프 패널#
		JPanel pShowGraph = new JPanel();
		pShowGraph.setBounds(578, 74, 554, 451);
		add(pShowGraph);

		// #이벤트 등록#
		vps = new ViewStatProductService(this);
		btnSearch.addActionListener(vps);

	}
}
