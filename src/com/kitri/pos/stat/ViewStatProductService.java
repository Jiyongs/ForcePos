package com.kitri.pos.stat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import com.kitri.pos.PosDto;

public class ViewStatProductService implements ActionListener {

	private ViewStatProduct vp;

	// [생성자]
	public ViewStatProductService(ViewStatProduct vp) {
		this.vp = vp;
	}

	// [ActionListener override]
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == vp.btnSearch) {
			search();
		}
	}

	// [기능 메소드]
	// <조회 버튼 클릭> 이벤트
	public void search() {
		// TODO findProductSell()의 인자값을 필드에서 받아오면 됨 & 조회 버튼 생성 후 이벤트 지정한 곳에서 수행
				System.out.println("조회 버튼이 눌렸습니다.");

				// 콤보박스의 값을 가져옴
				// (시작년도)
//				int startYear = Integer.parseInt(vm.comboYear.getActionCommand());
				
				
				StatDao statDao = new StatDao(); // Dao 객체
				Vector<PosDto> results = new Vector<PosDto>(); // select 결과가 세팅된 Dto Vector   //@@@@@@@

				// select 결과 저장
//				results = statDao.findMonthSell(startYear); // DB select 결과 저장 변수
				results = statDao.findProductSell("소주", "2019", "05"); // DB select 결과 저장 변수
				
				// 테이블 행 세팅
				int size = results.size();

				for(int i = 0; i < size; i++) {
					Vector<String> rows = new Vector<String>(); // 행
//					String header[] = { "매출순위", "상품코드", "상품분류", "상품명", "판매가", "판매수량", "매출합계", "제조사" };
					rows.addElement(Integer.toString(results.get(i).getRanking()));
					rows.addElement(results.get(i).getProductCode());
					rows.addElement(results.get(i).getMajorLevel());					
					rows.addElement(results.get(i).getProductName());
					rows.addElement(Integer.toString(results.get(i).getPrice()));
					rows.addElement(Integer.toString(results.get(i).getPurchase()));
					rows.addElement(Integer.toString(results.get(i).getSellCount()));
					rows.addElement(Integer.toString(results.get(i).getStatTotalPrice()));		
					rows.addElement(results.get(i).getCompany());		
					
					vp.tmodel.addRow(rows);
				}

				// 결과 테이블 띄우기
				vp.spShowTable.setViewportView(vp.tableResult);
	}
	
	// <그래프 생성> 이벤트
//	public JFreeChart getChart() {
//		return null;
//	}

}
