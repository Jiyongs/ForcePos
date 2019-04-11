package com.kitri.pos.stat;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
 	ViewStatYear : 연도별 통계 패널
 */

public class ViewStatYear extends JPanel {

	JComboBox comboStartYear;
	JComboBox comboEndYear;
	JButton btnSearch;

	JScrollPane spShowTable;
	JTable tableResult;
	DefaultTableModel tmodel;

	ViewStatYearService vys;

	public ViewStatYear() {
		setLayout(null);
		setSize(new Dimension(1144, 535));

		// #검색조건 패널#
		JPanel pSetSearch = new JPanel();
		pSetSearch.setLayout(null);
		pSetSearch.setBounds(12, 27, 780, 37);
		add(pSetSearch);

		JLabel lbShowDate = new JLabel("조회년도 :");
		lbShowDate.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lbShowDate.setBounds(12, 0, 101, 37);
		pSetSearch.add(lbShowDate);

		btnSearch = new JButton("조회");
		btnSearch.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnSearch.setBounds(379, 0, 101, 37);
		pSetSearch.add(btnSearch);
		
		comboStartYear = new JComboBox();
		comboStartYear.setBounds(113, 0, 114, 37);
		pSetSearch.add(comboStartYear);
		
		JLabel label = new JLabel("-");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		label.setBounds(236, 2, 15, 32);
		pSetSearch.add(label);
		
		comboEndYear = new JComboBox();
		comboEndYear.setBounds(251, 0, 114, 37);
		pSetSearch.add(comboEndYear);

		// #테이블 스크롤 패널#
		spShowTable = new JScrollPane();
		spShowTable.setBounds(12, 74, 554, 451);
		add(spShowTable);

		// [테이블]
		// 임시 테이블 모델 생성
//		String header[] = { "매출날짜", "매출합계", "순매출", "부가세", "현금매출", "카드매출", "고객수" };
//		String contents[][] = { { "2019-01", "632,000", "590,000", "59,000", "230,000", "402,000", "50" },
//				{ "2019-02", "632,000", "590,000", "59,000", "230,000", "402,000", "50" },
//				{ "2019-03", "632,000", "590,000", "59,000", "230,000", "402,000", "50" },
//				{ "2019-04", "632,000", "590,000", "59,000", "230,000", "402,000", "50" },
//				{ "2019-05", "632,000", "590,000", "59,000", "230,000", "402,000", "50" },
//				{ "2019-06", "632,000", "590,000", "59,000", "230,000", "402,000", "50" },
//				{ "2019-07", "632,000", "590,000", "59,000", "230,000", "402,000", "50" },
//				{ "2019-08", "632,000", "590,000", "59,000", "230,000", "402,000", "50" },
//				{ "2019-09", "632,000", "590,000", "59,000", "230,000", "402,000", "50" },
//				{ "2019-10", "632,000", "590,000", "59,000", "230,000", "402,000", "50" },
//				{ "2019-11", "632,000", "590,000", "59,000", "230,000", "402,000", "50" },
//				{ "2019-12", "632,000", "590,000", "59,000", "230,000", "402,000", "50" } };
//
//		DefaultTableModel tmodel = new DefaultTableModel(contents, header);

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
		pShowGraph.setBounds(578, 74, 554, 451);
		add(pShowGraph);

		// #기본 패널#
		// [라디오 버튼]
		ButtonGroup bgp = new ButtonGroup(); // 버튼그룹 생성

		JRadioButton rdBtnTotalPrice = new JRadioButton("매출합계");
		rdBtnTotalPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rdBtnTotalPrice.setBounds(800, 41, 105, 23);
		add(rdBtnTotalPrice);

		JRadioButton rdBtnNetIncome = new JRadioButton("순매출");
		rdBtnNetIncome.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rdBtnNetIncome.setBounds(909, 41, 85, 23);
		add(rdBtnNetIncome);

		JRadioButton rdBtnCash = new JRadioButton("현금");
		rdBtnCash.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rdBtnCash.setBounds(998, 41, 65, 23);
		add(rdBtnCash);

		JRadioButton rdBtnCard = new JRadioButton("카드");
		rdBtnCard.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rdBtnCard.setBounds(1067, 41, 65, 23);
		add(rdBtnCard);

		// 버튼 그룹에 라디오버튼 등록
		bgp.add(rdBtnTotalPrice);
		bgp.add(rdBtnCash);
		bgp.add(rdBtnNetIncome);
		bgp.add(rdBtnTotalPrice);

		// #이벤트 등록#
		vys = new ViewStatYearService(this);
		btnSearch.addActionListener(vys);

	}

}
