package com.kitri.pos.stat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import com.kitri.pos.PosDto;

public class ViewStatMonthService implements ActionListener {

	private ViewStatMonth vm;

	// [생성자]
	public ViewStatMonthService(ViewStatMonth vm) {
		this.vm = vm;
	}

	// [ActionListener override]
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == vm.btnSearch) {
			search();
		}
	}

	// [기능 메소드]
	// <조회 버튼 클릭> 이벤트
	public void search() {

		// TODO findMonthSell()의 인자값을 필드에서 받아오면 됨 & 조회 버튼 생성 후 이벤트 지정한 곳에서 수행
		System.out.println("월별 조회 버튼이 눌렸습니다.");

		// 콤보박스의 값을 가져옴
		// (시작년도)
//		int startYear = Integer.parseInt(vm.comboYear.getActionCommand());
		
		StatDao statDao = new StatDao(); // Dao 객체
		Vector<PosDto> results = new Vector<PosDto>(); // select 결과가 세팅된 Dto Vector   //@@@@@@@

		// select 결과 저장
//		results = statDao.findMonthSell(startYear); // DB select 결과 저장 변수
		results = statDao.findMonthSell(2019); // DB select 결과 저장 변수
		
		// 테이블 행 세팅
		int size = results.size();

		for(int i = 0; i < size; i++) {
			Vector<String> rows = new Vector<String>(); // 행
			rows.addElement(results.get(i).getSellDate());
			rows.addElement(Integer.toString(results.get(i).getStatTotalPrice()));
			rows.addElement(Integer.toString(results.get(i).getTotalTax()));
			rows.addElement(Integer.toString(results.get(i).getCashPrice()));
			rows.addElement(Integer.toString(results.get(i).getCardPrice()));
			rows.addElement(Integer.toString(results.get(i).getCustomerCount()));		
			
			vm.tmodel.addRow(rows);
		}

		// 결과 테이블 띄우기
		vm.spShowTable.setViewportView(vm.tableResult);
	}

	// <그래프 생성> 이벤트
//	public JFreeChart getChart() {
//		return null;
//	}

}
