package com.kitri.pos.sale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.Option;

import com.kitri.pos.*;
import com.kitri.pos.calc.*;
import com.kitri.pos.db.*;

public class SalesInputService implements KeyListener, ActionListener {

	SaleBtn salebtn = new SaleBtn();
	MainFrame mainframe;
	SalesInputDao salesInputDao;
	public static boolean key;
	Vector<PosDto> salesList;
	Vector<Object> getgoods;
	int overlapRow;
	boolean memshipcheck = false;

	public SalesInputService(MainFrame mainframe) {
		super();
		this.mainframe = mainframe;
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

		if (ob == mainframe.salebtn.sBtnPdChange) {
			int row = mainframe.viewSalesInput.table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(mainframe, "수정할 상품을 클릭해 주세요.", "상품 수정 오류", JOptionPane.WARNING_MESSAGE);

				return;
			}
			if (!Integer.toString(row).equals(null)) {
				String cnt = JOptionPane.showInputDialog(mainframe, "변경할 수량을 입력하세요.", "수량변경",
						JOptionPane.QUESTION_MESSAGE);

				if (cnt == null || !isNumber(cnt) || cnt.isEmpty() || cnt.trim().equals("")) {
					return;
				} else {
					int price = Integer.parseInt(String.valueOf(mainframe.viewSalesInput.model.getValueAt(row, 3)));
					mainframe.viewSalesInput.model.setValueAt(Integer.parseInt(cnt), row, 4);
					mainframe.viewSalesInput.model.setValueAt(price * Integer.parseInt(cnt), row, 5);
				}
			} else {
				JOptionPane.showMessageDialog(mainframe, "수정할 상품을 선택해주세요", "선택오류", JOptionPane.ERROR_MESSAGE);
			}
			totalApply();
		} else if (ob == mainframe.salebtn.sBtnAcencel) {
			int row = mainframe.viewSalesInput.table.getRowCount();
			if (row < 0) {
				JOptionPane.showMessageDialog(mainframe, "결제 취소할 상품이 없습니다.", "결제 취소 오류", JOptionPane.WARNING_MESSAGE);

				return;
			}
			setblank();
			payEnd();

		} else if (ob == mainframe.salebtn.sBtnPdCancel) {
			int row = mainframe.viewSalesInput.table.getSelectedRow();
			if (row != -1) {
				int del = JOptionPane.showConfirmDialog(mainframe, "선택한 상품을 취소하시겠습니까?", "상품취소",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (del == 0) {
					mainframe.viewSalesInput.model.removeRow(row);
					totalApply();
				}
//				int price = Integer.parseInt(String.valueOf(mainframe.viewSalesInput.model.getValueAt(row, 3)));
//				mainframe.viewSalesInput.model.setValueAt(Integer.parseInt(cnt), row, 4);
//				mainframe.viewSalesInput.model.setValueAt(price * Integer.parseInt(cnt), row, 5);
			} else {
				JOptionPane.showMessageDialog(mainframe, "취소할 상품을 선택해주세요", "선택오류", JOptionPane.ERROR_MESSAGE);
			}
		} else if (ob == mainframe.salebtn.sBtnPay) {
			if (mainframe.viewSalesInput.model.getRowCount() > 0) {
				mainframe.payment_1.setVisible(true);
				mainframe.payment_1.tfP1BeforePrice.setText(mainframe.viewSalesInput.total_price_input.getText());
				String item = String.valueOf(mainframe.payment_1.cbP1Cooperation.getSelectedItem());
				cooperDCProcess(item);
				cpCalc();
			} else {
				JOptionPane.showMessageDialog(mainframe, "선택한 상품이 없습니다.", "상품미선정", JOptionPane.ERROR_MESSAGE);
			}
		} else if (ob == mainframe.payment_1.cbP1Cooperation) {

			String item = String.valueOf(mainframe.payment_1.cbP1Cooperation.getSelectedItem());

			cooperDCProcess(item);
		} else if (ob == mainframe.payment_1.btnP1Apply) {
			cpCalc();

		} else if (ob == mainframe.payment_1.btnP1Before) {
			mainframe.payment_1.setVisible(false);
			mainframe.payment_2.setVisible(false);
			mainframe.payment_4.setVisible(false);
			mainframe.payment_3.setVisible(false);
		} else if (ob == mainframe.payment_1.btnP1Next) {
			if (mainframe.payment_1.tfP1Afterprice.getText().equals(""))
				JOptionPane.showMessageDialog(mainframe.payment_1, "할인을 적용하시오.", "할인미적용", JOptionPane.ERROR_MESSAGE);
			else {
				mainframe.payment_1.setVisible(false);
				mainframe.payment_2.setVisible(true);
				mainframe.payment_2.tfP2Aftertotal.setText(mainframe.payment_1.tfP1Afterprice.getText());
			}
		} else if (ob == mainframe.payment_2.btnP2Reference) {
			membershipRef();

		} else if (ob == mainframe.payment_2.btnP2Apply) {
			if (!mainframe.payment_2.tfP2point.getText().equals("")) {
				if (mainframe.payment_2.tfP2UsePoint.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(mainframe.payment_2, "사용할 포인트를 입력하시오.", "입력오류",
							JOptionPane.ERROR_MESSAGE);

				} else if (isNumber(mainframe.payment_2.tfP2UsePoint.getText().trim()) == false) {
					JOptionPane.showMessageDialog(mainframe.payment_2, "숫자만 입력가능 합니다.", "입력오류",
							JOptionPane.ERROR_MESSAGE);
					mainframe.payment_2.tfP2UsePoint.setText("");
				} else if (Integer.parseInt(mainframe.payment_2.tfP2point.getText().trim()) < Integer
						.parseInt(mainframe.payment_2.tfP2UsePoint.getText())) {
					JOptionPane.showMessageDialog(mainframe.payment_2, "포인트가 충분하지않습니다.", "입력오류",
							JOptionPane.ERROR_MESSAGE);
					mainframe.payment_2.tfP2UsePoint.setText("");
				} else if (Integer.parseInt(mainframe.payment_2.tfP2UsePoint.getText().trim()) > Integer
						.parseInt(mainframe.payment_1.tfP1Afterprice.getText())) {
					JOptionPane.showMessageDialog(mainframe.payment_2, "총 가격만큼만 사용할 수 있습니다.", "입력오류",
							JOptionPane.WARNING_MESSAGE);
					mainframe.payment_2.tfP2UsePoint.setText(mainframe.payment_1.tfP1Afterprice.getText());

				} else {

					int total = Integer.parseInt(mainframe.payment_1.tfP1Afterprice.getText())
							- Integer.parseInt(mainframe.payment_2.tfP2UsePoint.getText());
					mainframe.payment_2.tfP2Aftertotal.setText(Integer.toString(total));
				}
			} else {
				JOptionPane.showMessageDialog(mainframe.payment_2, "먼저 멤버쉽조회를 해주십시오.", "조회오류",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (ob == mainframe.payment_2.btnP2Next) {
			if (memshipcheck == false) {
				salesInputDao.posDto.setMembershipId("nomember");
			}
			mainframe.payment_3.setVisible(true);
			mainframe.payment_2.setVisible(false);
			mainframe.payment_3.lbP3PaymentView.setText(mainframe.payment_2.tfP2Aftertotal.getText());
			mainframe.payment_3.lbP3PointView
					.setText(String.valueOf((Integer.parseInt(mainframe.payment_3.lbP3PaymentView.getText()) / 100)));
			mainframe.payment_3.lbP3FinalPayView.setText("0");

		} else if (ob == mainframe.payment_3.btnP3Input) {
			int card = 0;
			int cash = 0;

			if (mainframe.payment_3.tfP3CardP.getText().trim().equals("")
					&& mainframe.payment_3.tfP3CashP.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(mainframe.payment_3, "현금 또는 카드를 선택해주십시오.", "결제유형오류",
						JOptionPane.ERROR_MESSAGE);

			} else if (isNumber(mainframe.payment_3.tfP3CardP.getText().trim()) != true
					|| isNumber(mainframe.payment_3.tfP3CashP.getText().trim()) != true) {
				JOptionPane.showMessageDialog(mainframe.payment_3, "숫자만 입력해주십시오.", "입력오류오류",
						JOptionPane.WARNING_MESSAGE);

			} else {

				if (!mainframe.payment_3.tfP3CardP.getText().trim().equals("")) {
					card = Integer.parseInt(mainframe.payment_3.tfP3CardP.getText().trim());
					salesInputDao.posDto.setCardPrice(card);
					System.out.println(salesInputDao.posDto.getCardPrice());
				}

				if (!mainframe.payment_3.tfP3CashP.getText().trim().equals("")) {
					cash = Integer.parseInt(mainframe.payment_3.tfP3CashP.getText().trim());
					salesInputDao.posDto.setCashPrice(cash);
					System.out.println(salesInputDao.posDto.getCashPrice());
				}
				if (card + cash != Integer.parseInt(mainframe.payment_3.lbP3PaymentView.getText())) {
					System.out.println(card + cash);
					mainframe.payment_3.lbP3FinalPayView.setText(String.valueOf(card + cash));
					JOptionPane.showMessageDialog(mainframe.payment_3, "결제금액과 맞지않습니다.", "결제금액대소오류",
							JOptionPane.WARNING_MESSAGE);
				} else {
					salesInputDao.posDto.setTotalPrice(card + cash);
					mainframe.payment_3.lbP3FinalPayView.setText(String.valueOf(salesInputDao.posDto.getTotalPrice()));
					mainframe.payment_3.tfP3SM.setText("결제금액이 충족되었습니다");
				}
			}
//----------------------------------------------------------------------------------------------

		} else if (ob == mainframe.payment_3.btnP3Next) {

			if (!mainframe.payment_3.tfP3SM.getText().equals("")) {
				if (JOptionPane.showConfirmDialog(mainframe.payment_3, "결제하시겠습니까?", "결제확인",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
					paymentProcess();
					JOptionPane.showMessageDialog(mainframe.payment_3, "결제가완료되었습니다.", "결제완료",
							JOptionPane.INFORMATION_MESSAGE);

					mainframe.payment_1.setVisible(false);
					mainframe.payment_2.setVisible(false);
					mainframe.payment_3.setVisible(false);
					mainframe.payment_4.setVisible(true);

					salesInputDao.SellIdDate();
					payNPrint();

					setblank();
					payEnd();

//				} else if (JOptionPane.showMessageDialog(mainframe.payment_3, "결제조건이 충족되지 않았습니다.", "조건부족",
//						JOptionPane.ERROR_MESSAGE)) {
//					return;
				} else {
					JOptionPane.showMessageDialog(mainframe.payment_3, "결제조건이 충족되지 않았습니다.", "조건부족",
							JOptionPane.ERROR_MESSAGE);
				
				}

			
		}
	}else if(ob==mainframe.payment_4.btnP4Payment)

	{

		mainframe.payment_4.setVisible(false);
		mainframe.payment_4.taP4details.setText("");

	}
	}

	public void StocksProcess() {

		getgoods = new Vector<Object>();
		int size = mainframe.viewSalesInput.table.getRowCount();
		for (int i = 0; i < size; i++) {
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 1));// p_code
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 6));// in_date
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 4));// p_cnt
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 2));// p_name
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 3));// p_price
		}

		salesInputDao.registerHisDetail(size, getgoods);
		salesInputDao.reflectStocks(size, getgoods);

	}

	private void paymentProcess() {
		salesInputDao.registerHistory();
		StocksProcess();

//		if()유저이름이 없다면 멤버쉽등록이 안됬으므로 포인트처리 생략
		if (memshipcheck == true)
			pointreflect();

	}

	private void pointreflect() {

		int point = Integer.parseInt(mainframe.payment_2.tfP2point.getText());
		int savePoint = Integer.parseInt(mainframe.payment_3.lbP3PointView.getText());
		int usePoint = 0;
		if (!mainframe.payment_2.tfP2UsePoint.getText().equals(""))
			usePoint = Integer.parseInt(mainframe.payment_2.tfP2UsePoint.getText());

		salesInputDao.posDto.setPoint(point - usePoint + savePoint);
		salesInputDao.pointUpdate();
	}

	private void payNPrint() {
		mainframe.payment_4.taP4details.setText("판매코드 : " + ForcePos.selldto.getSellId() + "\t판매일자 : "
				+ ForcePos.selldto.getSellDate() + "\n\t판매원코드 : " + ForcePos.usercodeDto.getUserCode()
				+ "\n==========================================\n");

		int x = mainframe.viewSalesInput.table.getRowCount();
		for (int i = 0; i < x; i++) {
			for (int j = 1; j < 6; j++) {
				mainframe.payment_4.taP4details.append(mainframe.viewSalesInput.model.getValueAt(i, j) + "\t");
			}
			mainframe.payment_4.taP4details.append("\n");
		}

		mainframe.payment_4.taP4details.append("\n==========================================\n"
				+ (salesInputDao.posDto.getDiscountCode().equals("d4") ? "\n"
						: "할인코드 : " + salesInputDao.posDto.getDiscountCode() + " 제휴사 : "
								+ salesInputDao.posDto.getCooperateName() + " 할인율 : "
								+ mainframe.payment_1.tfP1DiscountPercent.getText() + "\n"));

		if (salesInputDao.posDto.getMembershipId().equals("nomember")) {

		} else {
			if (mainframe.payment_2.tfP2UsePoint.getText().isEmpty()) {
				mainframe.payment_4.taP4details.append("멤버쉽 : " + salesInputDao.posDto.getMembershipId()
						+ "\t적립된 포인트 : " + mainframe.payment_3.lbP3PointView.getText() + "\n\t\t현재 포인트 : "
						+ (salesInputDao.posDto.getPoint()
								+ Integer.parseInt(mainframe.payment_3.lbP3PointView.getText()))
						+ "\n");
			} else {
				mainframe.payment_4.taP4details.append("멤버쉽 : " + salesInputDao.posDto.getMembershipId() + "\t사용포인트 : "
						+ mainframe.payment_2.tfP2UsePoint.getText() + "\n적립된 포인트 : "
						+ mainframe.payment_3.lbP3PointView.getText() + "\t현재 포인트 : "
						+ (salesInputDao.posDto.getPoint()
								- Integer.parseInt(mainframe.payment_2.tfP2UsePoint.getText())
								+ Integer.parseInt(mainframe.payment_3.lbP3PointView.getText()))
						+ "\n");
			}

		}

		mainframe.payment_4.taP4details.append(("\n----------------------------------------------------------------\n"
				+ "\t 총결제금액 : " + salesInputDao.posDto.getTotalPrice() + "\n"
				+ (salesInputDao.posDto.getCardPrice() == 0 ? ""
						: "\t 카드결제금액 : " + salesInputDao.posDto.getCardPrice() + "\n")
				+ (salesInputDao.posDto.getCashPrice() == 0 ? ""
						: "\t 현금결제금액 : " + salesInputDao.posDto.getCashPrice() + "\n")));

	}

	public void membershipRef() {
		if (mainframe.payment_2.tfP2phoneNum.getText().equals("")) {
			JOptionPane.showMessageDialog(mainframe.payment_2, "번호를 입력하시오.", "입력오류", JOptionPane.WARNING_MESSAGE);
		} else {

			if (salesInputDao.costomerRef(mainframe.payment_2.tfP2phoneNum.getText()) == true) {
				mainframe.payment_2.tfP2SM.setText(salesInputDao.posDto.getMemberName() + "("
						+ salesInputDao.posDto.getMembershipId() + ")님의 멤버쉽이 확인되었습니다.");
				mainframe.payment_2.tfP2point.setText(String.valueOf(salesInputDao.posDto.getPoint()));
				memshipcheck = true;
			} else {
				mainframe.payment_2.tfP2SM.setText(mainframe.payment_2.tfP2phoneNum.getText() + "님의 멤버쉽을 찾을 수 없습니다.");
			}
		}

	}

	public void cpCalc() {
		mainframe.payment_1.tfP1Afterprice
				.setText(Integer.toString((int) (((Integer.parseInt(mainframe.payment_1.tfP1BeforePrice.getText())
						* ((100 - Integer.parseInt(mainframe.payment_1.tfP1DiscountPercent.getText())))) / 100))));
		mainframe.payment_1.tfP1SM.setText("할인이 적용되었습니다.");
	}

	public void cooperDCProcess(String item) {

		salesInputDao.searchByCP(item);

		mainframe.payment_1.tfP1DiscountPercent
				.setText(String.valueOf((int) (salesInputDao.posDto.getDiscountPct() * 100)));

		mainframe.payment_1.tfP1Afterprice.setText("");

	}

	public void listAdd(Vector<PosDto> salesList) {
		if (salesList == null)
			JOptionPane.showMessageDialog(mainframe, "상품이 존재하지않습니다.", "상품없음", JOptionPane.ERROR_MESSAGE);
		else {
			int size = salesList.size();

			Vector<String> rows = new Vector<String>();

			rows.addElement(Integer.toString(salesList.get(0).getListNum()));
			rows.addElement(salesList.get(0).getProductCode());
			rows.addElement(salesList.get(0).getProductName());
			rows.addElement(Integer.toString(salesList.get(0).getPrice()));
			rows.addElement(String.valueOf(salesList.get(0).getSellCount()));
			rows.addElement(String.valueOf(salesList.get(0).getPricensellCount()));
			rows.addElement(salesList.get(0).getInDate());

			mainframe.viewSalesInput.model.addRow(rows);
			System.out.println("반영완료");

		}
	}

	public boolean checkOverlap(String identifier, int col) {
		System.out.println("check" + identifier);
		int h = mainframe.viewSalesInput.model.getRowCount();
		for (int i = 0; i < h; i++) {
			System.out.println(mainframe.viewSalesInput.model.getValueAt(i, col));
			if (mainframe.viewSalesInput.model.getValueAt(i, col).equals(identifier)) {
				System.out.println("중복");
				overlapRow = i;

				return false;
			}

		}

		return true;
	}

	public void goodsListProcess() {
		if (mainframe.viewSalesInput.code_input.getText().trim().length() > 0) {

			if (checkOverlap(mainframe.viewSalesInput.code_input.getText().trim().toUpperCase(), 1)) {
				key = true;
				System.out.println(mainframe.viewSalesInput.code_input.getText().trim().toUpperCase());
				System.out.println("코드로검색" + mainframe.viewSalesInput.code_input.getText().length());

				listAdd(salesInputDao.searchBy(mainframe.viewSalesInput.code_input.getText().trim().toUpperCase()));

			} else {
				// 수량 변경
				System.out.println("코드중복 발생");
				mainframe.viewSalesInput.model.setValueAt(
						Integer.valueOf(String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 4))) + 1,
						overlapRow, 4);
				// 단가 * 수량
				mainframe.viewSalesInput.model.setValueAt(
						Integer.valueOf(String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 3)))
								* Integer.valueOf(
										String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 4))),
						overlapRow, 5);
			}
			mainframe.viewSalesInput.product_name_input.setText("");
		} else if (mainframe.viewSalesInput.product_name_input.getText().trim().length() > 0) {

			if (checkOverlap(mainframe.viewSalesInput.product_name_input.getText().trim().toUpperCase(), 2)) {
				key = false;
				System.out.println("이름으로검색" + mainframe.viewSalesInput.product_name_input.getText().length());
				listAdd(salesInputDao
						.searchBy(mainframe.viewSalesInput.product_name_input.getText().trim().toUpperCase()));

			} else {
				System.out.println("이름중복 발생");
				// 수량만증가
				mainframe.viewSalesInput.model.setValueAt(
						Integer.valueOf(String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 4))) + 1,
						overlapRow, 4);
				// 단가 * 수량 적용
				mainframe.viewSalesInput.model.setValueAt(
						Integer.valueOf(String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 3)))
								* Integer.valueOf(
										String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 4))),
						overlapRow, 5);
			}

		} else {
			JOptionPane.showMessageDialog(mainframe, "해당 상품은 없습니다.", "검색실패", JOptionPane.WARNING_MESSAGE);

		}
		totalApply();
		mainframe.viewSalesInput.code_input.setText("");
		mainframe.viewSalesInput.product_name_input.setText("");
	}

	public void totalApply() {
		int tp = 0;
		int row = mainframe.viewSalesInput.table.getRowCount();
		for (int i = 0; i < row; i++) {
			tp += Integer.parseInt(String.valueOf(mainframe.viewSalesInput.model.getValueAt(i, 5)));
		}
		mainframe.viewSalesInput.total_price_input.setText(String.valueOf(tp));
	}

	public boolean isNumber(String obStr) {
		boolean flag = true;
		int len = obStr.length();
		int num;
		for (int i = 0; i < len; i++) {
			num = obStr.charAt(i) - 48;
			if (num < 0 || num > 9)
				flag = false;
		}
		return flag;
	}

	public void isNumError(boolean b, JPanel p) {
		if (b == false)
			JOptionPane.showMessageDialog(p, "숫자만 입력가능합니다.", "입력오류", JOptionPane.ERROR_MESSAGE);
	}

	public static void clearRows(int rowCount, DefaultTableModel model) {
		if (rowCount > 0) {
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
	}

	private void setblank() {
		clearRows(mainframe.viewSalesInput.table.getRowCount(), mainframe.viewSalesInput.model);
		mainframe.viewSalesInput.total_price_input.setText("0");
		mainframe.payment_1.tfP1SM.setText("");
		mainframe.payment_1.tfP1BeforePrice.setText("");
		mainframe.payment_1.tfP1DiscountPercent.setText("");
		mainframe.payment_1.tfP1Afterprice.setText("");
		mainframe.payment_1.lbP1Afterprice.setText("");
		mainframe.payment_1.cbP1Cooperation.setSelectedItem("없음");
		mainframe.payment_1.lbP1Discountpercent.setText("");
		mainframe.payment_2.tfP2SM.setText("");
		mainframe.payment_2.tfP2phoneNum.setText("");
		mainframe.payment_2.tfP2UsePoint.setText("");
		mainframe.payment_2.lbP2Aftertotal.setText("");
		mainframe.payment_2.lbP2point.setText("");
		mainframe.payment_2.lbP2UsePoint.setText("");
		mainframe.payment_2.tfP2Aftertotal.setText("");
		mainframe.payment_2.tfP2point.setText("");
		mainframe.payment_3.tfP3SM.setText("");
		mainframe.payment_3.tfP3CardP.setText("");
		mainframe.payment_3.lbP3FinalPay.setText("");
		mainframe.payment_3.lbP3Payment.setText("");
		mainframe.payment_3.tfP3CashP.setText("");
		mainframe.payment_3.lbP3PointView.setText("");
		mainframe.payment_3.lbP3FinalPayView.setText("");
		mainframe.payment_3.lbP3PaymentView.setText("");

	}

	public void payEnd() {
		salesInputDao.posDto.setMembershipId("");
		salesInputDao.posDto.setMemberName("");
		salesInputDao.posDto.setPoint(0);
		salesInputDao.posDto.setDiscountCode("");
		salesInputDao.posDto.setCooperateName("");
		salesInputDao.posDto.setDiscountPct(0);

		salesInputDao.posDto.setListNum(0);
		salesInputDao.posDto.setCardPrice(0);
		salesInputDao.posDto.setCashPrice(0);
		salesInputDao.posDto.setTotalPrice(0);

	}

}
