package com.kitri.pos.stat;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.kitri.pos.PosDto;

/*
	ViewStatDay : 일별 통계 패널
*/

public class ViewStatDay extends JPanel {
	private JTable tableResult;

	public ViewStatDay() {
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
		
		JSpinner spinYear = new JSpinner();
		spinYear.setBounds(111, 0, 114, 37);
		pSetSearch.add(spinYear);
		spinYear.setToolTipText("");
		
		JLabel lbYear = new JLabel("년");
		lbYear.setBounds(227, 2, 38, 32);
		pSetSearch.add(lbYear);
		lbYear.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JSpinner spinMonth = new JSpinner();
		spinMonth.setBounds(261, 0, 114, 37);
		pSetSearch.add(spinMonth);
		spinMonth.setToolTipText("");
		
		JLabel lbMonth = new JLabel("월");
		lbMonth.setBounds(375, 0, 38, 32);
		pSetSearch.add(lbMonth);
		lbMonth.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JSpinner spinDay = new JSpinner();
		spinDay.setBounds(405, 0, 114, 37);
		pSetSearch.add(spinDay);
		spinDay.setToolTipText("");
		
		JLabel lbDay = new JLabel("일");
		lbDay.setBounds(524, -2, 38, 32);
		pSetSearch.add(lbDay);
		lbDay.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JButton btnSearch = new JButton("조회");
		btnSearch.setBounds(552, 0, 101, 37);
		pSetSearch.add(btnSearch);
		btnSearch.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		// #테이블 스크롤 패널#
		JScrollPane spShowTable = new JScrollPane();
		spShowTable.setBounds(12, 74, 694, 44);
		add(spShowTable);
		
		//[테이블]	
		// TODO findDaySell()의 인자값을 필드에서 받아오면 됨 & 조회 버튼 생성 후 이벤트 지정한 곳에서 수행
		StatDao statDao = new StatDao();
		PosDto result = statDao.findDaySell("2020", "08", "12"); // DB select 결과 저장 변수
		Vector<String> col = new Vector<String>();    // 열
		Vector<String> data = new Vector<String>();  // 행

		// 테이블 열 세팅
		col.add("매출날짜");
		col.add("매출합계");
		col.add("부가세");
		col.add("현금매출");
		col.add("카드매출");
		col.add("고객수");
		
		DefaultTableModel tmodel = new DefaultTableModel(col, 0);
		
		// 테이블 행 세팅
		data.addElement(result.getSellDate());
		data.addElement(Integer.toString(result.getStatTotalPrice()));
		data.addElement(Integer.toString(result.getTotalTax()));
		data.addElement(Integer.toString(result.getCashPrice()));
		data.addElement(Integer.toString(result.getCardPrice()));
		data.addElement(Integer.toString(result.getCustomerCount()));
		
		tmodel.addRow(data);
		
		// 테이블 보이기
		tableResult = new JTable(tmodel);
		spShowTable.setViewportView(tableResult);
		
		// #그래프 패널#
		JPanel pShowGraph = new JPanel();
		pShowGraph.setBounds(12, 157, 1120, 368);
		add(pShowGraph);
		
		// #기본 패널#
		// [라디오 버튼]
		ButtonGroup bgp = new ButtonGroup(); //버튼그룹 생성
		
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
		
		//버튼 그룹에 라디오버튼 등록
		bgp.add(rdBtnTotalPrice);
		bgp.add(rdBtnCash);
		bgp.add(rdBtnNetIncome);
		bgp.add(rdBtnTotalPrice);
		
	}
}
