package com.kitri.pos.sales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.text.html.Option;

public class SalesInputService implements KeyListener, ActionListener {

	Sales sales;
	SalesInputDao salesInputDao;
	public static boolean key;
	Vector<PosDto> salesList;
	int overlapRow;

	public SalesInputService(Sales sales) {
		super();
		this.sales = sales;
		salesInputDao = new SalesInputDao();

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 10) {
			System.out.println(e);
			goodsListProcess();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == sales.sBtnPdChange) {
			int row = sales.viewSalesInput.table.getSelectedRow();
			if (!Integer.toString(row).equals(null)) {
				String cnt = JOptionPane.showInputDialog(sales, "변경할 수량을 입력하세요.", "수량변경", JOptionPane.QUESTION_MESSAGE);
				int price = Integer.parseInt(String.valueOf(sales.viewSalesInput.model.getValueAt(row, 3)));
				sales.viewSalesInput.model.setValueAt(Integer.parseInt(cnt), row, 4);
				sales.viewSalesInput.model.setValueAt(price * Integer.parseInt(cnt), row, 5);
			} else {
				JOptionPane.showMessageDialog(sales, "수정할 상품을 선택해주세요", "선택오류", JOptionPane.ERROR_MESSAGE);
			}
		} else if (ob == sales.sBtnPdCancel) {
			int row = sales.viewSalesInput.table.getSelectedRow();
			if (!Integer.toString(row).equals(null)) {
				int del = JOptionPane.showConfirmDialog(sales, "선택한 상품을 취소하시겠습니까?", "상품취소",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (del == 0) {
					sales.viewSalesInput.model.removeRow(row);
				}
//				int price = Integer.parseInt(String.valueOf(sales.viewSalesInput.model.getValueAt(row, 3)));
//				sales.viewSalesInput.model.setValueAt(Integer.parseInt(cnt), row, 4);
//				sales.viewSalesInput.model.setValueAt(price * Integer.parseInt(cnt), row, 5);
			} else {
				JOptionPane.showMessageDialog(sales, "취소할 상품을 선택해주세요", "선택오류", JOptionPane.ERROR_MESSAGE);
			}
		} else if (ob == sales.sBtnPay) {
			sales.payment_1.setVisible(true);
			sales.payment_1.tfP1BeforePrice.setText(sales.viewSalesInput.total_price_input.getText());
			sales.payment_1.tfP1Afterprice.setText(sales.viewSalesInput.total_price_input.getText());
			
		} else if (ob == sales.payment_1.cbP1Cooperation) {
			String item = String.valueOf(sales.payment_1.cbP1Cooperation.getSelectedItem());
			System.out.println(item);
			
			cooperDCProcess(item);
			
			
		} 

	}
	
	public void cooperDCProcess(String item) {
	
		if(item.equals("없음")){
			sales.payment_1.tfP1DiscountPercent.setText("0");
			sales.payment_1.tfP1Afterprice.setText(
					sales.payment_1.tfP1BeforePrice.getText());
		}else {
		double dis_pct_dob = salesInputDao.searchByCP(item);
		int dis_int = (int)(dis_pct_dob * 100);
		sales.payment_1.tfP1DiscountPercent.setText(Integer.toString(dis_int));
		sales.payment_1.tfP1Afterprice.setText(Integer.toString((int)(Integer.parseInt(
				sales.payment_1.tfP1BeforePrice.getText()) * (1-dis_pct_dob))));
		}
		
			
		
	}
	

	public void listAdd(Vector<PosDto> salesList) {
		int size = salesList.size();

		for (int i = 0; i < size; i++) {
			Vector<String> rows = new Vector<String>();

			rows.addElement(Integer.toString(salesList.get(i).getListNum()));
			rows.addElement(salesList.get(i).getProductCode());
			rows.addElement(salesList.get(i).getProductName());
			rows.addElement(Integer.toString(salesList.get(i).getPrice()));
			rows.addElement(String.valueOf(salesList.get(i).getSellCount()));
			rows.addElement(String.valueOf(salesList.get(i).getPricensellCount()));
//			rows.addElement(salesList.get(i).getRealExp());

			sales.viewSalesInput.model.addRow(rows);
			System.out.println("반영완료");
		}
	}

	public boolean checkOverlap(String identifier, int col) {
		System.out.println("check" + identifier);
		int h = sales.viewSalesInput.model.getRowCount();
		for (int i = 0; i < h; i++) {
			System.out.println(sales.viewSalesInput.model.getValueAt(i, col));
			if (sales.viewSalesInput.model.getValueAt(i, col).equals(identifier)) {
				System.out.println("중복");
				overlapRow = i;

				return false;
			}

		}

		return true;
	}

	public void goodsListProcess() {
		if (sales.viewSalesInput.code_input.getText().trim().length() > 0) {

			if (checkOverlap(sales.viewSalesInput.code_input.getText().trim().toUpperCase(), 1)) {
				key = true;
				System.out.println(sales.viewSalesInput.code_input.getText().trim().toUpperCase());
				System.out.println("코드로검색" + sales.viewSalesInput.code_input.getText().length());
				listAdd(salesInputDao.searchBy(sales.viewSalesInput.code_input.getText().trim().toUpperCase()));
			} else {
				// 수량 변경
				System.out.println("코드중복 발생");
				sales.viewSalesInput.model.setValueAt(
						Integer.valueOf(String.valueOf(sales.viewSalesInput.model.getValueAt(overlapRow, 4))) + 1,
						overlapRow, 4);
				// 단가 * 수량
				sales.viewSalesInput.model.setValueAt(
						Integer.valueOf(String.valueOf(sales.viewSalesInput.model.getValueAt(overlapRow, 3)))
								* Integer.valueOf(String.valueOf(sales.viewSalesInput.model.getValueAt(overlapRow, 4))),
						overlapRow, 5);
			}
			sales.viewSalesInput.product_name_input.setText("");
		} else if (sales.viewSalesInput.product_name_input.getText().trim().length() > 0) {

			if (checkOverlap(sales.viewSalesInput.product_name_input.getText().trim().toUpperCase(), 2)) {
				key = false;
				System.out.println("이름으로검색" + sales.viewSalesInput.product_name_input.getText().length());
				listAdd(salesInputDao.searchBy(sales.viewSalesInput.product_name_input.getText().trim().toUpperCase()));

			} else {
				System.out.println("이름중복 발생");
				// 수량만증가
				sales.viewSalesInput.model.setValueAt(
						Integer.valueOf(String.valueOf(sales.viewSalesInput.model.getValueAt(overlapRow, 4))) + 1,
						overlapRow, 4);
				// 단가 * 수량 적용
				sales.viewSalesInput.model.setValueAt(
						Integer.valueOf(String.valueOf(sales.viewSalesInput.model.getValueAt(overlapRow, 3)))
								* Integer.valueOf(String.valueOf(sales.viewSalesInput.model.getValueAt(overlapRow, 4))),
						overlapRow, 5);
			}

		} else {
			JOptionPane.showMessageDialog(sales, "해당 상품은 없습니다.", "검색실패", JOptionPane.WARNING_MESSAGE);

		}
		totalApply();
		sales.viewSalesInput.code_input.setText("");
		sales.viewSalesInput.product_name_input.setText("");
	}

	public void totalApply() {
		int tp = 0;
		int row = sales.viewSalesInput.table.getRowCount();
		for (int i = 0; i < row; i++) {
			tp += Integer.parseInt(String.valueOf(sales.viewSalesInput.model.getValueAt(i, 5)));
		}
		sales.viewSalesInput.total_price_input.setText(String.valueOf(tp));
	}

}
