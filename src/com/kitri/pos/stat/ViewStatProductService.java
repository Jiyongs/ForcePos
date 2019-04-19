package com.kitri.pos.stat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.Rotation;

import com.kitri.pos.db.PosDto;



public class ViewStatProductService implements ActionListener {

	Vector<PosDto> bests = null; // Best5 상품 그래프용 저장 백터

	Vector<PosDto> results = null; // 검색 결과 상품 테이블용 저장 백터
	StatDao statDao = new StatDao(); // Dao 객체

	private String minorLevel = null; // 콤보 박스 저장 변수
	private String year = null;
	private String month = null;

	private ViewStatProduct vp;

	// [생성자]
	public ViewStatProductService(ViewStatProduct vp) {
		this.vp = vp;
		setGraphbestProducts(); //빈 그래프 띄우기
	}

	// [ActionListener override/조회버튼]
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == vp.btnSearch) {
			search();
		}
	}

	// [기능 메소드]
	// <조회 버튼 클릭> 메소드
	public void search() {

		// 테이블 행 화면 리셋
		StatDao.clearRows(vp.tmodel.getRowCount(), vp.tmodel);
		
		// 콤보박스의 값을 가져옴
		// (소분류, 시작년도, 끝년도)
		minorLevel = vp.comboMinorLevel.getItemAt(vp.comboMinorLevel.getSelectedIndex()).toString();
		year = vp.comboYear.getItemAt(vp.comboYear.getSelectedIndex()).toString();
		month = vp.comboMonth.getItemAt(vp.comboMonth.getSelectedIndex()).toString();

		results = new Vector<PosDto>(); // select 결과가 세팅된 Dto Vector

		// select 결과 저장
		results = statDao.findProductSell(minorLevel, year, month); // DB select 결과 저장 변수

		if (results.isEmpty()) { // 조회 결과 없으면, 알림창 날림
			JOptionPane.showMessageDialog(null, "조회할 데이터가 없습니다.");
		} else { // 조회 결과 있으면, 결과 보이기

			// 테이블 행 세팅
			int size = results.size();
			for (int i = 0; i < size; i++) {
				Vector<String> rows = new Vector<String>(); // 행
				rows.addElement(Integer.toString(results.get(i).getRanking()));
				rows.addElement(results.get(i).getProductCode());
				rows.addElement(results.get(i).getMinorLevel());
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

			// 그래프도 같이 띄우기
			setGraphbestProducts();

		}
	}

	// <조건에 맞는 Best5 상품 그래프 세팅> 메소드
	public void setGraphbestProducts() {
				
		DefaultPieDataset pieDataset = null;
		JFreeChart chart;
		
		bests = new Vector<PosDto>();
		if(results != null) {
			bests = statDao.findProductSellBestFive(minorLevel, year, month); // 랭킹 5 뽑기 sql문 결과 담음

			pieDataset = new DefaultPieDataset(); // 파이 차트 데이터셋 생성

			Vector<String> productName = new Vector<String>(); // 딸기샌드위치
			Vector<Integer> totalSellPrice = new Vector<Integer>(); // 20000

			// 행 세팅
			int size = bests.size();
			for (int i = 0; i < size; i++) {

				productName.addElement(bests.get(i).getProductName());
				totalSellPrice.addElement(bests.get(i).getStatTotalPrice());

				// 값, 범례, 카테고리 지정
				pieDataset.setValue(productName.get(i), totalSellPrice.get(i));
			}
			
			// 3d 파이차트 생성 (조건별 이름 지정)
			chart = ChartFactory.createPieChart3D
					(year + "." + month + " BEST 5", pieDataset, false, true,true);
		} else {
			//3d 파이차트 생성 (빈 그래프 용)
			chart = ChartFactory.createPieChart3D
					("BEST 5", pieDataset, false, true,true);			
		}
		chart.setBackgroundPaint(Color.lightGray); // 차트 제목 배경색

		PiePlot3D p = (PiePlot3D) chart.getPlot();
		p.setBaseSectionOutlinePaint(Color.WHITE); // 차트 테두리 색
		p.setDepthFactor(0.08f); // 차트 두께
		p.setLabelLinkPaint(Color.white); // 차트 라벨선 색
		p.setLabelLinkStyle(PieLabelLinkStyle.QUAD_CURVE); // 차트 라벨선 스타일
		p.setForegroundAlpha(0.7f); // 차트 투명도
		p.setBackgroundPaint(Color.DARK_GRAY); // 차트 배경색
		p.setLabelBackgroundPaint(Color.WHITE);// 차트 라벨 배경색
		p.setLabelFont(new Font("맑은고딕", Font.BOLD, 14)); // 차트 값 폰트

		// 만든 차트로 차트 판넬 생성
		ChartPanel pChart = new ChartPanel(chart);
		pChart.setVisible(true);
		pChart.setSize(554, 451); // 크기 지정

		vp.pShowGraph.add("bestGraph", pChart);
		vp.graphCard.show(vp.pShowGraph, "bestGraph");

		vp.pShowGraph.setVisible(true);

	}

}
