package com.kitri.pos.stat;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;

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
		//임시 테이블 모델 생성
		String header[] = {"매출날짜", "매출합계", "순매출", "부가세", "현금매출", "카드매출", "고객수"};
		String contents[][] = {
					{"2019-01-25", "632,000", "590,000", "59,000", "230,000", "402,000", "50"}
				};
		
		DefaultTableModel tmodel = new DefaultTableModel(contents, header);
		
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
