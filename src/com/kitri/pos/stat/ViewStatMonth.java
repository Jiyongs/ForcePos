package com.kitri.pos.stat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.kitri.pos.MainFrame;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
	ViewStatMonth : 월별 통계 패널
*/

public class ViewStatMonth extends JPanel {

	JComboBox comboYear;
	JButton btnSearch;

	JScrollPane spShowTable;
	JTable tableResult;
	DefaultTableModel tmodel;
	
	JRadioButton rdBtnTotalPrice;
	JRadioButton rdBtnNetIncome;
	JRadioButton rdBtnCash;
	JRadioButton rdBtnCard;

	JPanel pShowGraph;
	CardLayout graphCard = new CardLayout(); //그래프용 카드 레이아웃
	
	ViewStatMonthService vms;

	public ViewStatMonth() {
		setLayout(null);
		setSize(new Dimension(1144, 535));

		// #검색조건 패널#
		JPanel pSetSearch = new JPanel();
		pSetSearch.setLayout(null);
		pSetSearch.setBounds(12, 27, 780, 37);
		add(pSetSearch);

		JLabel lbShowDate = new JLabel("조회기간 :");
		lbShowDate.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lbShowDate.setBounds(12, 0, 101, 37);
		pSetSearch.add(lbShowDate);
		
		// [콤보박스]
		// 콤보박스 안의 값 설정
		Vector<Integer> YearValues = new Vector<Integer>(); // 년도 저장할 벡터
		//sYearValues에 현재 년도 - 1990년까지 넣기
		Calendar oCalendar = Calendar.getInstance( );  		// 현재 날짜/시간 등의 각종 정보 얻기
		// 현재 날짜
		 int toyear = oCalendar.get(Calendar.YEAR);
		 for(int i = toyear; i>= 1990; i--){
			  YearValues.add(i);
		 }  
		
		// 콤보박스 세팅
		comboYear = new JComboBox(YearValues);
		comboYear.setBounds(111, 0, 114, 37);
		pSetSearch.add(comboYear);

		btnSearch = new JButton("조회");
		btnSearch.setFont(ViewSetting.sbtnFont);
		btnSearch.setBounds(261, 0, 101, 37);
		pSetSearch.add(btnSearch);
		
		JLabel label = new JLabel("년");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		label.setBounds(226, 2, 38, 32);
		pSetSearch.add(label);

		// #테이블 스크롤 패널#
		spShowTable = new JScrollPane();
		spShowTable.setBounds(12, 74, 554, 451);
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
		MainFrame.tableCellCenter(tableResult);

		spShowTable.setViewportView(tableResult);

		// #그래프 패널#
		pShowGraph = new JPanel();
		pShowGraph.setLayout(graphCard);
		pShowGraph.setBounds(578, 74, 554, 451);
		add(pShowGraph);

		// #기본 패널#
		// [라디오 버튼]
		ButtonGroup bgp = new ButtonGroup(); // 버튼그룹 생성

		rdBtnTotalPrice = new JRadioButton("매출합계");
		rdBtnTotalPrice.setSelected(true);
		rdBtnTotalPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rdBtnTotalPrice.setBounds(800, 41, 105, 23);
		add(rdBtnTotalPrice);

		rdBtnNetIncome = new JRadioButton("순매출");
		rdBtnNetIncome.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rdBtnNetIncome.setBounds(909, 41, 85, 23);
		add(rdBtnNetIncome);

		rdBtnCash = new JRadioButton("현금");
		rdBtnCash.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rdBtnCash.setBounds(998, 41, 65, 23);
		add(rdBtnCash);

		rdBtnCard = new JRadioButton("카드");
		rdBtnCard.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rdBtnCard.setBounds(1067, 41, 65, 23);
		add(rdBtnCard);

		// 버튼 그룹에 라디오버튼 등록
		bgp.add(rdBtnTotalPrice);
		bgp.add(rdBtnCash);
		bgp.add(rdBtnNetIncome);
		bgp.add(rdBtnCard);

		// #이벤트 등록#
		vms = new ViewStatMonthService(this);
		btnSearch.addActionListener(vms);
		
		rdBtnTotalPrice.addItemListener(vms);
		rdBtnNetIncome.addItemListener(vms);
		rdBtnCash.addItemListener(vms);
		rdBtnCard.addItemListener(vms);

	}
}
