package com.kitri.pos.stat;

import java.awt.*;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.kitri.pos.*;

import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

/*
	ViewStatProduct : 상품별 통계 패널
*/

public class ViewStatProduct extends JPanel {

	JComboBox comboYear;
	JComboBox comboMonth;
	JComboBox comboMinorLevel;
	JButton btnSearch;

	JTable tableResult;
	DefaultTableModel tmodel;
	JScrollPane spShowTable;
	
	JPanel pShowGraph;
	CardLayout graphCard = new CardLayout(); //그래프용 카드 레이아웃
	
	ViewStatProductService vps;

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
		lbProductLevel.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// [콤보박스]
		// 콤보박스 안의 값 설정
		// 상품분류 저장할 배열
		String MinorLevels[] = { "소주", "맥주", "막걸리", "샌드위치", "삼각김밥", "소시지", "도시락", "우유", "요거트", "치즈", "탄산", "과즙", "커피",
				"닭다리", "호빵", "라면", "통조림", "스낵", "냉동식품", "일반", "전자담배", "주방용품", "세면용품", "여성용품", "생활용품", "기타" };

		Vector<Integer> YearValues = new Vector<Integer>(); // 년도 저장할 벡터
		// 월 저장할 벡터
		String MonthValues[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		// sYearValues에 현재 년도 - 1990년까지 넣기
		Calendar oCalendar = Calendar.getInstance(); // 현재 날짜/시간 등의 각종 정보 얻기
		// 현재 날짜
		int toyear = oCalendar.get(Calendar.YEAR);
		for (int i = toyear; i >= 1990; i--) {
			YearValues.add(i);
		}

		// 콤보박스 세팅
		comboMinorLevel = new JComboBox(MinorLevels);
		comboMinorLevel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		comboMinorLevel.setBounds(103, 0, 170, 37);
		pSetSearch.add(comboMinorLevel);

		JLabel lbShowDate = new JLabel("조회기간 :");
		lbShowDate.setBounds(334, 0, 101, 37);
		pSetSearch.add(lbShowDate);
		lbShowDate.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		comboYear = new JComboBox(YearValues);
		comboYear.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		comboYear.setBounds(432, 0, 114, 37);
		pSetSearch.add(comboYear);

		JLabel label = new JLabel("-");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		label.setBounds(555, 2, 15, 32);
		pSetSearch.add(label);

		comboMonth = new JComboBox(MonthValues);
		comboMonth.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		comboMonth.setBounds(570, 0, 114, 37);
		pSetSearch.add(comboMonth);

		btnSearch = new JButton("조회");
		btnSearch.setFont(ViewSetting.sbtnFont);
		btnSearch.setBounds(698, 0, 101, 37);
		pSetSearch.add(btnSearch);

		// #테이블 스크롤 패널#
		spShowTable = new JScrollPane();
		spShowTable.setBounds(12, 74, 554, 451);
		add(spShowTable);

		// [테이블]
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
		MainFrame.tableCellCenter(tableResult);

		spShowTable.setViewportView(tableResult);

		// #그래프 패널#
		pShowGraph = new JPanel();
		pShowGraph.setVisible(false);
		pShowGraph.setLayout(graphCard);
		pShowGraph.setBounds(578, 74, 554, 451);
		add(pShowGraph);

		// #이벤트 등록#
		vps = new ViewStatProductService(this);
		btnSearch.addActionListener(vps);

	}

}
