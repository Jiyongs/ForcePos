package com.kitri.pos.stat;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
	ViewStatDay : 일별 통계 패널
*/

public class ViewStatDay extends JPanel {

	JComboBox comboYear;
	JComboBox comboMonth;
	JComboBox comboDay;

	JButton btnSearch;

	JScrollPane spShowTable;

	JTable tableResult;
	DefaultTableModel tmodel;

	ViewStatDayService vds;

	public ViewStatDay() {
		setSize(new Dimension(1144, 535));
		setLayout(null);

		// #검색조건 패널#
		JPanel pSetSearch = new JPanel();
		pSetSearch.setBounds(12, 27, 1120, 37);
		add(pSetSearch);
		pSetSearch.setLayout(null);

		JLabel lbShowDate = new JLabel("조회일자 :");
		lbShowDate.setBounds(12, 0, 101, 37);
		pSetSearch.add(lbShowDate);
		lbShowDate.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

		comboYear = new JComboBox();
		comboYear.setBounds(111, 0, 114, 37);
		pSetSearch.add(comboYear);

		JLabel lbYear = new JLabel("년");
		lbYear.setBounds(227, 2, 38, 32);
		pSetSearch.add(lbYear);
		lbYear.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

		comboMonth = new JComboBox();
		comboMonth.setBounds(261, 0, 114, 37);
		pSetSearch.add(comboMonth);

		JLabel lbMonth = new JLabel("월");
		lbMonth.setBounds(375, 0, 38, 32);
		pSetSearch.add(lbMonth);
		lbMonth.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

		comboDay = new JComboBox();
		comboDay.setBounds(405, 0, 114, 37);
		pSetSearch.add(comboDay);

		JLabel lbDay = new JLabel("일");
		lbDay.setBounds(524, -2, 38, 32);
		pSetSearch.add(lbDay);
		lbDay.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

		btnSearch = new JButton("조회");
		btnSearch.setBounds(552, 0, 101, 37);
		pSetSearch.add(btnSearch);
		btnSearch.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// #테이블 스크롤 패널#
		spShowTable = new JScrollPane();
		spShowTable.setBounds(12, 74, 694, 58);
		add(spShowTable);

		// [테이블]
		// 테이블 열 세팅
		Vector<String> col = new Vector<String>(); // 열
		col.add("매출날짜");
		col.add("매출합계");
		col.add("부가세");
		col.add("현금매출");
		col.add("카드매출");
		col.add("고객수");
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
		pShowGraph.setBounds(12, 157, 1120, 368);
		add(pShowGraph);

		// #기본 패널#
		// [라디오 버튼]
		ButtonGroup bgp = new ButtonGroup(); // 버튼그룹 생성

		JRadioButton rdBtnCard = new JRadioButton("카드");
		rdBtnCard.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rdBtnCard.setBounds(1071, 128, 65, 23);
		add(rdBtnCard);

		JRadioButton rdBtnCash = new JRadioButton("현금");
		rdBtnCash.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rdBtnCash.setBounds(1002, 128, 65, 23);
		add(rdBtnCash);

		JRadioButton rdBtnNetIncome = new JRadioButton("순매출");
		rdBtnNetIncome.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rdBtnNetIncome.setBounds(913, 128, 85, 23);
		add(rdBtnNetIncome);

		JRadioButton rdBtnTotalPrice = new JRadioButton("매출합계");
		rdBtnTotalPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rdBtnTotalPrice.setBounds(804, 128, 105, 23);
		add(rdBtnTotalPrice);

		// 버튼 그룹에 라디오버튼 등록
		bgp.add(rdBtnTotalPrice);
		bgp.add(rdBtnCash);
		bgp.add(rdBtnNetIncome);
		bgp.add(rdBtnTotalPrice);

		// #이벤트 등록#
		vds = new ViewStatDayService(this);
		btnSearch.addActionListener(vds);

	}
}
