package com.kitri.pos.stock;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.kitri.pos.MainFrame;
import com.kitri.pos.db.PosDto;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JScrollPane;

public class StockMonitor extends JPanel {
	public JScrollPane stockScrollPane = new JScrollPane();
	public Vector col = getColum();
	public DefaultTableModel tmodel = new DefaultTableModel(col, 0);
	public JTable StockTable = new JTable(tmodel);
	StockDao dao = new StockDao();
	public Vector<PosDto> mon = new Vector<PosDto>();

	/**
	 * Create the panel.
	 */
	public StockMonitor() {
//		col = getColum();

		setLayout(null);
		
		stockScrollPane.setBounds(12, 10, 1133, 532);
		add(stockScrollPane);

//		tmodel = new DefaultTableModel(col, 0);
		
		

//		StockTable = new JTable(tmodel);
		StockTable.setRowMargin(10);
		StockTable.setRowHeight(30);
		// 테이블 값 가운데 정렬
		MainFrame.tableCellCenter(StockTable);

		StockTable.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		StockTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		showMon(dao.StockAll());
		
		stockScrollPane.setViewportView(StockTable);
		


	}// 메소드끝

	private Vector getColum() {
		col = new Vector();
		col.add("상품코드");
		col.add("입고일자");
		col.add("상품명");
		col.add("수량");
		col.add("가격");
		col.add("유통기한");

		return col;
	}
	
	public void showMon(Vector<PosDto> mon) {
		
		int size = mon.size();		
		for (int i = 0; i < size; i++) {
			Vector<String> rows = new Vector<String>();
			rows.addElement(mon.get(i).getProductCode());
			rows.addElement(mon.get(i).getInDate());
			rows.addElement(mon.get(i).getProductName());
			rows.addElement(Integer.toString(mon.get(i).getVolume()));
			rows.addElement(Integer.toString(mon.get(i).getPrice()));
			rows.addElement(mon.get(i).getRealExp());

			tmodel.addRow(rows);
		}

		stockScrollPane.setViewportView(StockTable);
	}
	
//테이블 행 지우기. 화면단에서만
	public static void clearRows(int rowSize, DefaultTableModel dtm) {
		if (rowSize > 0) {
			for (int i = rowSize - 1; i >= 0; i--) {
				dtm.removeRow(i);
			}
		}
	}

	}
