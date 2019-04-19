package com.kitri.pos.stat;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.TextAnchor;

import com.kitri.pos.db.PosDto;



public class ViewStatYearService implements ActionListener, ItemListener {

	private ViewStatYear vy;

	private Vector<PosDto> results;  //조건에 따른 검색 결과
	
	private String statType = "매출합계";        //라디오버튼 값 (디폴트는 "매출합계")

	// [생성자]
	public ViewStatYearService(ViewStatYear vy) {
		this.vy = vy;
		
		setChart(statType, results);
	}

	// [ActionListener override/조회버튼]
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == vy.btnSearch) {
			search();
		}
	}

	// [ItemListener override/라디오버튼]
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object ob = e.getSource();
		JRadioButton rb = (JRadioButton) ob;
		statType = rb.getText();

		// 선택된 라디오 버튼의 이름 비교 후, 맞는 차트 설정
		if (statType.equals("매출합계")) {
			setChart(statType, results); // 차트 생성 & pShowGraph패널의 카드 레이아웃으로 show()
		} else if (statType.equals("순매출")) {
			setChart(statType, results);
		} else if (statType.equals("현금")) {
			setChart(statType, results);
		} else if (statType.equals("카드")) {
			setChart(statType, results);
		}

	}

	// [기능 메소드]
	// <조회 버튼 클릭> 이벤트
	public void search() {

		// 테이블 행 화면 리셋
		StatDao.clearRows(vy.tmodel.getRowCount(), vy.tmodel);

		// 콤보박스의 값을 가져옴
		// (시작년도)
		int startYear = (int) vy.comboStartYear.getItemAt(vy.comboStartYear.getSelectedIndex());
		int endYear = (int) vy.comboEndYear.getItemAt(vy.comboEndYear.getSelectedIndex());

		StatDao statDao = new StatDao(); // Dao 객체
		results = new Vector<PosDto>(); // 쿼리 결과

		// select 결과 저장
		results = statDao.findYearSell(startYear, endYear); // DB select 결과 저장 변수

		if (results.isEmpty()) { // 조회 결과 없으면, 알림창 날림
			JOptionPane.showMessageDialog(null, "조회할 데이터가 없습니다.");
		} else { // 조회 결과 있으면, 결과 보이기

			// 테이블 행 세팅
			int size = results.size();

			for (int i = 0; i < size; i++) {
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

			// 디폴트 그래프인 매출합계도 같이 띄워줌
			setChart(statType, results);
		}

	}

	// <라디오버튼 값에 따른 막대 그래프 세팅> 이벤트
	// option : 1 - 매출합계 / 2 - 순매출 / 3 - 현금매출 / 4 - 카드매출
	public void setChart(String type, Vector<PosDto> results) {

		// #차트 생성#
		// [데이터 생성]
		DefaultCategoryDataset dataset;

		// [데이터 세팅]
		// type(통계 분류)에 따라 다르게 세팅됨
		dataset = getGraphDataset(type, results);

		// [렌더링]
		// 렌더링 생성
		final BarRenderer renderer = new BarRenderer();

		// 공통 옵션 정의
		final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
		final ItemLabelPosition p_center = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
		final ItemLabelPosition p_below = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
		Font f = new Font("맑은고딕", Font.BOLD, 16);
		Font axisF = new Font("맑은고딕", Font.BOLD, 16);

		// 렌더링 세팅
		renderer.setBaseItemLabelGenerator(generator);
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBasePositiveItemLabelPosition(p_center);
		renderer.setBaseItemLabelFont(f);
		renderer.setBaseItemLabelPaint(Color.white);         //막대 글씨 색
		renderer.setSeriesPaint(0, new Color(152, 84, 147));  //막대 색

		// [plot]
		// plot 생성
		final CategoryPlot plot = new CategoryPlot();

		// plot 에 데이터 적재
		plot.setDataset(dataset);
		plot.setRenderer(renderer);

		// plot 기본 설정
		plot.setOrientation(PlotOrientation.VERTICAL); // 그래프 표시 방향
		plot.setRangeGridlinesVisible(true); // X축 가이드 라인 표시여부
		plot.setDomainGridlinesVisible(true); // Y축 가이드 라인 표시여부

		// X축 세팅
		plot.setDomainAxis(new CategoryAxis()); // X축 종류 설정
		plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
		plot.getDomainAxis().setTickLabelPaint(Color.WHITE); // X축 눈금라벨 폰트 색상 조정
		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 카테고리 라벨 위치 조정

		// Y축 세팅
		plot.setRangeAxis(new NumberAxis()); // Y축 종류 설정
		plot.getRangeAxis().setTickLabelFont(axisF); // Y축 눈금라벨 폰트 조정
		plot.getRangeAxis().setTickLabelPaint(Color.WHITE); // X축 눈금라벨 폰트 색상 조정

		// 세팅된 plot을 바탕으로 chart 생성
		JFreeChart chart = new JFreeChart(plot);
		chart.setBackgroundPaint(Color.DARK_GRAY);
		chart.getPlot().setBackgroundPaint(Color.DARK_GRAY );

		// #생성된 차트로 차트 패널 생성#
		ChartPanel chartp = new ChartPanel(chart);
		chartp.setSize(554, 451); // 사이즈 지정 필수!

		switch (type) {
		case "매출합계":
			vy.pShowGraph.add("graphTotal", chartp);
			vy.graphCard.show(vy.pShowGraph, "graphTotal");
			break;
		case "순매출":
			renderer.setSeriesPaint(0, new Color(75, 84, 147));
			vy.pShowGraph.add("graphRealTotal", chartp);
			vy.graphCard.show(vy.pShowGraph, "graphRealTotal");
			break;
		case "현금":
			renderer.setSeriesPaint(0, new Color(217, 79, 99));
			vy.pShowGraph.add("graphCash", chartp);
			vy.graphCard.show(vy.pShowGraph, "graphCash");
			break;
		default:
			renderer.setSeriesPaint(0, new Color(80, 107, 102));
			vy.pShowGraph.add("graphCard", chartp);
			vy.graphCard.show(vy.pShowGraph, "graphCard");
		}
	
		vy.pShowGraph.setVisible(true);
		
	}

	// <타입(매출합계, 순매출, 현금, 카드)에 따른 그래프 데이터셋 리턴> 메소드
	public static DefaultCategoryDataset getGraphDataset(String type, Vector<PosDto> results) {

		DefaultCategoryDataset dataset = null;

		if (results != null) { // 조회 결과 있을 때만 그래프 값 설정
			System.out.println("그래프 값 설정!!!!!");
			Vector<String> date = new Vector<String>(); // 날짜 (한 행)
			Vector<Integer> values = new Vector<Integer>(); // 분류별 값 (한 행)

			dataset = new DefaultCategoryDataset();

			int size = results.size();

			switch (type) {
			case "매출합계":
				for (int i = 0; i < size; i++) {
					date.addElement(results.get(i).getSellDate()); // 20190407
					values.addElement(results.get(i).getStatTotalPrice()); // 매출합계 (int)

					// 값, 범례, 카테고리 지정
					dataset.addValue(values.get(i), type, date.get(i));
				}
				break;
			case "순매출":
				for (int i = 0; i < size; i++) {
					date.addElement(results.get(i).getSellDate()); // 2019
					values.addElement((int) (results.get(i).getStatTotalPrice() * 0.4)); // 순매출(매출합계*0.4) (int)

					dataset.addValue(values.get(i), type, date.get(i));
				}
				break;
			case "현금":
				for (int i = 0; i < size; i++) {
					date.addElement(results.get(i).getSellDate()); // 2019
					values.addElement(results.get(i).getCashPrice()); // 현금매출 (int)

					dataset.addValue(values.get(i), type, date.get(i));
				}
				break;
			default:
				for (int i = 0; i < size; i++) {
					date.addElement(results.get(i).getSellDate()); // 2019
					values.addElement(results.get(i).getCardPrice()); // 카드매출 (int)

					dataset.addValue(values.get(i), type, date.get(i));
				}
			}

		}
		return dataset;
	}

}
