package com.kitri.pos.stat;

import java.awt.*;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.kitri.pos.*;


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
	
	JRadioButton rdBtnTotalPrice;
	JRadioButton rdBtnCustomerCount;

	JPanel pShowGraph;
	CardLayout graphCard = new CardLayout(); //그래프용 카드 레이아웃

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

		// [콤보박스]
		// 콤보박스 안의 값 설정
		Vector<Integer> YearValues = new Vector<Integer>(); // 년도 저장할 벡터
		// 월 저장할 벡터
		String MonthValues[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		Vector<Integer> DayValues = new Vector<Integer>(); // 일 저장할 벡터
		// sYearValues에 현재 년도 - 1990년까지 넣기
		Calendar oCalendar = Calendar.getInstance(); // 현재 날짜/시간 등의 각종 정보 얻기
		// 현재 날짜
		int toyear = oCalendar.get(Calendar.YEAR);

		for (int i = toyear; i >= 1990; i--) {
			YearValues.add(i);
		}
		for (int i = 1; i < 32; i++) {
			DayValues.add(i);
		}

		// 콤보박스 세팅
		comboYear = new JComboBox(YearValues);
		comboYear.setBounds(111, 0, 114, 37);
		pSetSearch.add(comboYear);

		JLabel lbYear = new JLabel("년");
		lbYear.setBounds(227, 2, 38, 32);
		pSetSearch.add(lbYear);
		lbYear.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

		comboMonth = new JComboBox(MonthValues);
		comboMonth.setBounds(261, 0, 114, 37);
		pSetSearch.add(comboMonth);

		JLabel lbMonth = new JLabel("월");
		lbMonth.setBounds(375, 0, 38, 32);
		pSetSearch.add(lbMonth);
		lbMonth.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

		comboDay = new JComboBox(DayValues);
		comboDay.setBounds(405, 0, 114, 37);
		pSetSearch.add(comboDay);

		JLabel lbDay = new JLabel("일");
		lbDay.setBounds(524, -2, 38, 32);
		pSetSearch.add(lbDay);
		lbDay.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

		btnSearch = new JButton("조회");
		btnSearch.setBounds(552, 0, 101, 37);
		pSetSearch.add(btnSearch);
		btnSearch.setFont(ViewSetting.sbtnFont);

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
		MainFrame.tableCellCenter(tableResult);

		spShowTable.setViewportView(tableResult);

		// #그래프 패널#
		pShowGraph = new JPanel();
		pShowGraph.setLayout(graphCard);
		pShowGraph.setBounds(12, 157, 1120, 368);
		add(pShowGraph);

		// #기본 패널#
		// [라디오 버튼]
		ButtonGroup bgp = new ButtonGroup();


		rdBtnTotalPrice = new JRadioButton("매출합계");
		rdBtnTotalPrice.setSelected(true);
		rdBtnTotalPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rdBtnTotalPrice.setBounds(915, 126, 118, 23);
		add(rdBtnTotalPrice);

		rdBtnCustomerCount = new JRadioButton("고객수");
		rdBtnCustomerCount.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rdBtnCustomerCount.setBounds(1039, 126, 95, 23);
		add(rdBtnCustomerCount);

		// 버튼 그룹에 라디오버튼 등록
		bgp.add(rdBtnTotalPrice);
		bgp.add(rdBtnCustomerCount);

		// #이벤트 등록#
		vds = new ViewStatDayService(this);
		btnSearch.addActionListener(vds);
		
		rdBtnTotalPrice.addItemListener(vds);
		rdBtnCustomerCount.addItemListener(vds);

	}
}
