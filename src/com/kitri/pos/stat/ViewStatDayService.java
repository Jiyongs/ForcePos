package com.kitri.pos.stat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.kitri.pos.PosDto;

public class ViewStatDayService implements ActionListener {

	private ViewStatDay vd;

	// [생성자]
	public ViewStatDayService(ViewStatDay vd) {
		this.vd = vd;
	}

	// [ActionListener override]
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == vd.btnSearch) {
			search();
		}
	}

	// [기능 메소드]
	// <조회 버튼 클릭> 이벤트
	public void search() {
		
		// TODO findDaySell()의 인자값을 필드에서 받아오면 됨 & 조회 버튼 생성 후 이벤트 지정한 곳에서 수행
		System.out.println("일별 조회 버튼이 눌렸습니다.");
				
		// 콤보박스의 값을 가져옴 (년도, 월, 일)
		String year = vd.comboYear.getActionCommand();
		System.out.println(year);
		
		StatDao statDao = new StatDao(); // Dao 객체
		Vector<String> rows = new Vector<String>(); // 행
		
		// select 결과 저장
		PosDto result = statDao.findDaySell("2020", "08", "12"); // DB select 결과 저장 변수 //@@@@@@@@@@
				
		// 테이블 행 세팅
		rows.addElement(result.getSellDate());
		rows.addElement(Integer.toString(result.getStatTotalPrice()));
		rows.addElement(Integer.toString(result.getTotalTax()));
		rows.addElement(Integer.toString(result.getCashPrice()));
		rows.addElement(Integer.toString(result.getCardPrice()));
		rows.addElement(Integer.toString(result.getCustomerCount()));

		vd.tmodel.addRow(rows);

		// 결과 테이블 띄우기
		vd.spShowTable.setViewportView(vd.tableResult);
		
	}

	// <그래프 생성> 이벤트
//		public JFreeChart getChart() {
//			return null;
//		}

}
