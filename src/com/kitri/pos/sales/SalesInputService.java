package com.kitri.pos.sales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
			totalApply();
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
			if (sales.viewSalesInput.model.getRowCount() > 0) {
				sales.payment_1.setVisible(true);
				sales.payment_1.tfP1BeforePrice.setText(sales.viewSalesInput.total_price_input.getText());
				String item = String.valueOf(sales.payment_1.cbP1Cooperation.getSelectedItem());
				cooperDCProcess(item);
				cpCalc();
			} else {
				JOptionPane.showMessageDialog(sales, "선택한 상품이 없습니다.", "상품미선정", JOptionPane.ERROR_MESSAGE);
			}
		} else if (ob == sales.payment_1.cbP1Cooperation) {
			String item = String.valueOf(sales.payment_1.cbP1Cooperation.getSelectedItem());
			cooperDCProcess(item);
		} else if (ob == sales.payment_1.btnP1Apply) {
			cpCalc();

		} else if (ob == sales.payment_1.btnP1Before) {
			sales.payment_1.setVisible(false);
			sales.payment_2.setVisible(false);
			sales.payment_4.setVisible(false);
			sales.payment_3.setVisible(false);
		} else if (ob == sales.payment_1.btnP1Next) {
			if (sales.payment_1.tfP1Afterprice.getText().equals(""))
				JOptionPane.showMessageDialog(sales.payment_1, "할인을 적용하시오.", "할인미적용", JOptionPane.ERROR_MESSAGE);
			else {
				sales.payment_1.setVisible(false);
				sales.payment_2.setVisible(true);
				sales.payment_2.tfP2Aftertotal.setText(sales.payment_1.tfP1Afterprice.getText());
			}
		} else if (ob == sales.payment_2.btnP2Reference) {
			membershipRef();
			System.out.println(salesInputDao.posDto.getMembershipId());
		} else if (ob == sales.payment_2.btnP2Apply) {

			if (sales.payment_2.tfP2UsePoint.getText().equals("")) {
				JOptionPane.showMessageDialog(sales.payment_2, "사용할 포인트를 입력하시오.", "입력오류", JOptionPane.ERROR_MESSAGE);

			} else if (Integer.parseInt(sales.payment_2.tfP2point.getText()) < Integer
					.parseInt(sales.payment_2.tfP2UsePoint.getText())) {
				JOptionPane.showMessageDialog(sales.payment_2, "포인트가 충분하지않습니다.", "입력오류", JOptionPane.ERROR_MESSAGE);

			} else if (Integer.parseInt(sales.payment_2.tfP2UsePoint.getText()) > Integer
					.parseInt(sales.payment_1.tfP1Afterprice.getText())) {
				JOptionPane.showMessageDialog(sales.payment_2, "총 가격만큼만 사용할 수 있습니다.", "입력오류",
						JOptionPane.WARNING_MESSAGE);
				sales.payment_2.tfP2UsePoint.setText(sales.payment_1.tfP1Afterprice.getText());

			} else {

				int total = Integer.parseInt(sales.payment_1.tfP1Afterprice.getText())
						- Integer.parseInt(sales.payment_2.tfP2UsePoint.getText());
				sales.payment_2.tfP2Aftertotal.setText(Integer.toString(total));
			}

		} else if (ob == sales.payment_2.btnP2Next) {
			sales.payment_3.setVisible(true);
			sales.payment_2.setVisible(false);
			sales.payment_3.lbP3PaymentView.setText(sales.payment_2.tfP2Aftertotal.getText());
			sales.payment_3.lbP3PointView
					.setText(String.valueOf((Integer.parseInt(sales.payment_3.lbP3PaymentView.getText()) / 100)));
			sales.payment_3.lbP3FinalPayView.setText("0");

		} else if (ob == sales.payment_3.btnP3Input) {
			int card = 0;
			int cash = 0;
			if (!sales.payment_3.tfP3CardP.getText().equals("")) {
				card = Integer.valueOf(sales.payment_3.tfP3CardP.getText());

			}
			salesInputDao.posDto.setCardPrice(card);
			if (!sales.payment_3.tfP3CashP.getText().equals("")) {
				cash = Integer.valueOf(sales.payment_3.tfP3CashP.getText());
			}
			salesInputDao.posDto.setCashPrice(cash);

//				int card = Integer.valueOf(sales.payment_3.tfP3CardP.getText());
//				int cash = Integer.valueOf(sales.payment_3.tfP3CashP.getText());
			if (sales.payment_3.tfP3CardP.getText().equals("") && sales.payment_3.tfP3CashP.getText().equals("")) {
				JOptionPane.showMessageDialog(sales.payment_3, "현금 또는 카드를 선택해주십시오.", "결제유형오류",
						JOptionPane.ERROR_MESSAGE);
			} else if (card + cash > Integer.parseInt(sales.payment_3.lbP3PaymentView.getText())
					|| (card + cash < Integer.parseInt(sales.payment_3.lbP3PaymentView.getText()))) {
				sales.payment_3.lbP3FinalPayView.setText(String.valueOf(card + cash));
				JOptionPane.showMessageDialog(sales.payment_3, "결제금액과 맞지않습니다.", "결제금액대소오류",
						JOptionPane.WARNING_MESSAGE);
			} else {
				salesInputDao.posDto.setTotalPrice(card + cash);
				sales.payment_3.lbP3FinalPayView.setText(String.valueOf(salesInputDao.posDto.getTotalPrice()));
				sales.payment_3.tfP3SM.setText("결제금액이 충족되었습니다");
			}

		} else if (ob == sales.payment_3.btnP3Next) {
			if (!sales.payment_3.tfP3SM.getText().equals("")) {
				sales.payment_3.setVisible(false);
				sales.payment_4.setVisible(true);
				payNPrint();
			} else {
				JOptionPane.showMessageDialog(sales.payment_3, "결제조건이 충족되지 않았습니다.", "조건부족", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void payNPrint() {
		sales.payment_4.taP4details.setText("판매코드 : " + salesInputDao.posDto.getSellId() + "\t판매일자 : "
				+ salesInputDao.posDto.getSellDate() + "\n\t판매원코드 : " + salesInputDao.posDto.getUserCode()
				+ "\n==========================================\n");
		
	
		int x = sales.viewSalesInput.table.getRowCount();
		for(int i = 0 ; i<x;i++) {
			for(int j = 1; j<6 ; j++) {
				sales.payment_4.taP4details.append(sales.viewSalesInput.model.getValueAt(i, j) + "\t");
			}
			sales.payment_4.taP4details.append("\n");
		}
			
//			salesList.get(i).getProductName()+ " "+
//			Integer.toString(salesList.get(i).getPrice())+ " "+
//			String.valueOf(salesList.get(i).getSellCount())+ " "+
//			String.valueOf(salesList.get(i).getPricensellCount())+ "\n");
		
//		int cnt = sales.viewSalesInput.table.getRowCount();
//		for(int i = 0; i < cnt ; i++) {
////			Vector<String> rows = new Vector<String>();
//			sales.payment_4.taP4details.append(
//					Integer.toString(salesList.get(i).getListNum()) + " "+
//							salesList.get(i).getProductCode()+ " "+
//							salesList.get(i).getProductName()+ " "+
//							Integer.toString(salesList.get(i).getPrice())+ " "+
//							String.valueOf(salesList.get(i).getSellCount())+ " "+
//							String.valueOf(salesList.get(i).getPricensellCount())+ "\n");
//		}
		
		
				

				
				
				
				
				
		sales.payment_4.taP4details.append("\n==========================================\n" + salesInputDao.posDto.getDiscountCode()
				+ salesInputDao.posDto.getMembershipId() + "\n"
				+ (salesInputDao.posDto.getDiscountCode().equals("d4") ? "\n"
						: "할인코드 : " + salesInputDao.posDto.getDiscountCode() + " 제휴사 : "
								+ salesInputDao.posDto.getCooperateName() + " 할인율 : "
								+ sales.payment_1.tfP1DiscountPercent.getText() + "\n"));

		if (salesInputDao.posDto.getMembershipId() == null) {
			
		}else {
			if (sales.payment_2.tfP2UsePoint.getText().isEmpty()) {
				sales.payment_4.taP4details.append("멤버쉽 : " + salesInputDao.posDto.getMembershipId() + "\t적립된 포인트 : "
						+ sales.payment_3.lbP3PointView.getText() + "\n\t현재 포인트 : "
						+ (salesInputDao.posDto.getPoint() + Integer.parseInt(sales.payment_3.lbP3PointView.getText()))
						+ "\n");
			} else {
				sales.payment_4.taP4details.append("멤버쉽 : " + salesInputDao.posDto.getMembershipId() + "\t사용포인트 : "
						+ sales.payment_2.tfP2UsePoint.getText() + "\n\t적립된 포인트 : "
						+ sales.payment_3.lbP3PointView.getText() + "\t현재 포인트 : "
						+ (salesInputDao.posDto.getPoint() - Integer.parseInt(sales.payment_2.tfP2UsePoint.getText())
								+ Integer.parseInt(sales.payment_3.lbP3PointView.getText()))
						+ "\n");
			}

		}

		sales.payment_4.taP4details.append(
				("\n----------------------------------------------------------------\n" + "\t 총결제금액 : " + salesInputDao.posDto.getTotalPrice() + "\n"
						+ (salesInputDao.posDto.getCardPrice() == 0 ? ""
								: "\t 카드결제금액 : " + salesInputDao.posDto.getCardPrice() + "\n")
						+ (salesInputDao.posDto.getCashPrice() == 0 ? ""
								: "\t 현금결제금액 : " + salesInputDao.posDto.getCashPrice() + "\n")));

	}

	public void membershipRef() {
		if (sales.payment_2.tfP2phoneNum.getText().equals("")) {
			JOptionPane.showMessageDialog(sales.payment_2, "번호를 입력하시오.", "입력오류", JOptionPane.WARNING_MESSAGE);
		} else {

			if (salesInputDao.costomerRef(sales.payment_2.tfP2phoneNum.getText()) == true) {
				sales.payment_2.tfP2SM.setText(salesInputDao.posDto.getMemberName() + "("
						+ salesInputDao.posDto.getMembershipId() + ")님의 멤버쉽이 확인되었습니다.");
				sales.payment_2.tfP2point.setText(String.valueOf(salesInputDao.posDto.getPoint()));
			} else {
				sales.payment_2.tfP2SM.setText(sales.payment_2.tfP2phoneNum.getText() + "님의 멤버쉽을 찾을 수 없습니다.");
			}
		}

	}

	public void cpCalc() {
		sales.payment_1.tfP1Afterprice
				.setText(Integer.toString((int) (((Integer.parseInt(sales.payment_1.tfP1BeforePrice.getText())
						* ((100 - Integer.parseInt(sales.payment_1.tfP1DiscountPercent.getText())))) / 100))));
		sales.payment_1.tfP1SM.setText("할인이 적용되었습니다.");
	}

	public void cooperDCProcess(String item) {

		salesInputDao.searchByCP(item);
//			salesInputDao.posDto.setDiscountCode();
//			salesInputDao.posDto.setCooperateName();
		sales.payment_1.tfP1DiscountPercent
				.setText(String.valueOf((int) (salesInputDao.posDto.getDiscountPct() * 100)));
//			sales.payment_1.tfP1Afterprice.setText(
//					sales.payment_1.tfP1BeforePrice.getText());

		sales.payment_1.tfP1Afterprice.setText("");

//			int dis_int = (int) (salesInputDao.posDto.getDiscountPct() * 100);
//			sales.payment_1.tfP1DiscountPercent.setText(Integer.toString(dis_int));
//		sales.payment_1.tfP1Afterprice.setText(Integer.toString((int)(Integer.parseInt(
//				sales.payment_1.tfP1BeforePrice.getText()) * (1-dis_pct_dob))));

	}

	public void listAdd(Vector<PosDto> salesList) {
		int size = salesList.size();

//		for (int i = 0; i < size; i++) {
//			Vector<String> rows = new Vector<String>();
//
//			rows.addElement(Integer.toString(salesList.get(i).getListNum()));
//			rows.addElement(salesList.get(i).getProductCode());
//			rows.addElement(salesList.get(i).getProductName());
//			rows.addElement(Integer.toString(salesList.get(i).getPrice()));
//			rows.addElement(String.valueOf(salesList.get(i).getSellCount()));
//			rows.addElement(String.valueOf(salesList.get(i).getPricensellCount()));
////			rows.addElement(salesList.get(i).getRealExp());
//
//			sales.viewSalesInput.model.addRow(rows);
//			System.out.println("반영완료");
//		}

		Vector<String> rows = new Vector<String>();

		rows.addElement(Integer.toString(salesList.get(0).getListNum()));
		rows.addElement(salesList.get(0).getProductCode());
		rows.addElement(salesList.get(0).getProductName());
		rows.addElement(Integer.toString(salesList.get(0).getPrice()));
		rows.addElement(String.valueOf(salesList.get(0).getSellCount()));
		rows.addElement(String.valueOf(salesList.get(0).getPricensellCount()));
//			rows.addElement(salesList.get(i).getRealExp());

		sales.viewSalesInput.model.addRow(rows);
		System.out.println("반영완료");

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
//@@@@@				listAdd(salesInputDao.searchBy(sales.viewSalesInput.code_input.getText().trim().toUpperCase()));
//@@@@@				salesList = salesInputDao.searchBy(sales.viewSalesInput.code_input.getText().trim().toUpperCase());
				listAdd(salesList = salesInputDao.searchBy(sales.viewSalesInput.code_input.getText().trim().toUpperCase()));

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

	public boolean isNumber(String obStr) {
		boolean flag = true;
		int num = obStr.charAt(0) - 48;
		if (num < 0 || num > 9) {
			flag = false;
		}
		return flag;
	}

	public void isNumError(boolean b, JPanel p) {
		if (b == false)
			JOptionPane.showMessageDialog(p, "숫자만 입력가능합니다.", "입력오류", JOptionPane.ERROR_MESSAGE);
	}

}
