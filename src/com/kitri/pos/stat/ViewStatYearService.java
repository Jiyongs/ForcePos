package com.kitri.pos.stat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import com.kitri.pos.PosDto;

public class ViewStatYearService implements ActionListener {

	private ViewStatYear vy;

	// [생성자]
	public ViewStatYearService(ViewStatYear vy) {
		this.vy = vy;
	}

	// [ActionListener override]
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == vy.btnSearch) {
			search();
		}
	}
	
	// [기능 메소드]
	// <조회 버튼 클릭> 이벤트
		public void search() {
			// TODO findYearSell()의 인자값을 필드에서 받아오면 됨 & 조회 버튼 생성 후 이벤트 지정한 곳에서 수행
			System.out.println("연도별 조회 버튼이 눌렸습니다.");

			// 콤보박스의 값을 가져옴
			// (시작년도)
//			int startYear = Integer.parseInt(vy.comboStartYear.getActionCommand());
//			int endYear = Integer.parseInt(vy.comboEndYear.getActionCommand());
			
			StatDao statDao = new StatDao(); // Dao 객체
			Vector<PosDto> results = new Vector<PosDto>(); // 쿼리 결과

			// select 결과 저장
//			results = statDao.findYearSell(startYear, endYear); // DB select 결과 저장 변수
			results = statDao.findYearSell(2019, 2020); // DB select 결과 저장 변수
			
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
				vy.tmodel.addRow(rows);
			}
			
			// 결과 테이블 띄우기
			vy.spShowTable.setViewportView(vy.tableResult);
		}
		
		// <그래프 생성> 이벤트
//		public JFreeChart getChart() {
//			return null;
//		}

}
