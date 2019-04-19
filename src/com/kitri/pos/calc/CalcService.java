package com.kitri.pos.calc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.kitri.pos.*;

public class CalcService implements ActionListener {
	MainFrame mainframe;
	String numStr = "";
	String obStr;
	String v1;
	String v2;
	int value;
	String tmp;
	int sum = 0;
	String sumStr;
	int row = -1;
	int column = -1;
	CalcDao calcDao;

	public CalcService(MainFrame mainframe) {
		this.mainframe = mainframe;
		calcDao = new CalcDao();

	}

	public void cashCaclEach() {
		/////////////////////////////////// 각각의 권종 합에 반영

		for (int i = 0; i < 8; i++) {
			numStr = "";

			v1 = String.valueOf(mainframe.pCalc.model.getValueAt(i, 0));
			v2 = String.valueOf(mainframe.pCalc.model.getValueAt(i, 1));

			if (!v2.equals("")) {
				value = Integer.parseInt(v1) * Integer.parseInt(v2);
				mainframe.pCalc.model.setValueAt(value, i, 2);
				sum += value;
			}
		}
		///////////////////////////////////// 정산금액에 반영

		mainframe.pCalc.tfCashCheck.setText(String.valueOf(sum));
		sum = 0;
		//////////////////////////////////// 차액반영
		mainframe.pCalc.tfCalcResult.setText(String.valueOf((Integer.parseInt(mainframe.pCalc.tfCashCheck.getText())
				- Integer.parseInt(mainframe.pCalc.tfCashState.getText())))); // '-'��ȣ��
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		obStr = e.getActionCommand();

		if (ob == mainframe.mBtnCalc) {

			mainframe.mBtnCalc.setBackground(new Color(255, 69, 0));
			mainframe.mBtnAccount.setBackground(new Color(28, 94, 94));
			mainframe.mBtnInven.setBackground(new Color(28, 94, 94));
			mainframe.mBtnSale.setBackground(new Color(99, 166, 166));
			mainframe.mBtnStat.setBackground(new Color(99, 166, 166));

			mainframe.monitor.show(mainframe.pMonitor, "Calc");
			mainframe.btn.show(mainframe.pFBtn, "Calcbtn");

			mainframe.pCalc.tfCashState.setText(Integer.toString(calcDao.inputComs_Calc()));

		} else if (isNumber(obStr) == true) {
			if (mainframe.pCalc.cashTable.getSelectedColumn() == 1) {

				row = mainframe.pCalc.cashTable.getSelectedRow();
				column = mainframe.pCalc.cashTable.getSelectedColumn();
				if (mainframe.pCalc.model.isCellEditable(row, column) == false) {
					return;
				}
				numStr = String.valueOf(mainframe.pCalc.cashTable.getValueAt(row, column));
				if (numStr.equals("0")) {
					mainframe.pCalc.cashTable.setValueAt(obStr, row, column);
				} else {
					numStr += obStr;
					mainframe.pCalc.cashTable.setValueAt(numStr, row, column);
				}
				cashCaclEach();
			}
		} else if (ob == mainframe.pCalc.btnCalc_del) {
			if (mainframe.pCalc.cashTable.getSelectedColumn() == 1) {
//			맨뒤 한숫자 삭제
				row = mainframe.pCalc.cashTable.getSelectedRow();
				column = mainframe.pCalc.cashTable.getSelectedColumn();
				if (row == -1)
					return;

				String value = String.valueOf(mainframe.pCalc.model.getValueAt(row, column));
				int len = value.length();
				if (len > 1) {
					value = value.substring(0, len - 1);
					mainframe.pCalc.model.setValueAt(value, row, column);
					numStr = value;
				} else {
					mainframe.pCalc.model.setValueAt("0", row, column);
				}
				cashCaclEach();
			}
		} else if (ob == mainframe.pCalc.btnCalc_C) {
			if (mainframe.pCalc.cashTable.getSelectedColumn() == 1) {
				row = mainframe.pCalc.cashTable.getSelectedRow();
				column = mainframe.pCalc.cashTable.getSelectedColumn();
				if (row == -1)
					return;
				mainframe.pCalc.cashTable.setValueAt("0", row, column);
				cashCaclEach();
			}
		} else if (ob == mainframe.pCalc.btnCalc_Apply) {
			if (mainframe.pCalc.tfCashCheck.getText().equals("") || mainframe.pCalc.tfCalcResult.getText().equals("")) {
				JOptionPane.showMessageDialog(mainframe, "정산처리할 값이 부족합니다.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
//				if())

				if (JOptionPane.showConfirmDialog(mainframe.pCalc, "정산하시겠습니까?", "정산확인",
						JOptionPane.OK_CANCEL_OPTION) == 0) {
					calcDao.posDto.setComsCalc(Integer.parseInt(mainframe.pCalc.tfCashState.getText()));
					calcDao.posDto.setCurrentMoney(Integer.parseInt(mainframe.pCalc.tfCashCheck.getText()));
					calcDao.posDto.setTotalCalc(Integer.parseInt(mainframe.pCalc.tfCalcResult.getText()));
					calcDao.calc_Apply();
					mainframe.pCalc.model.setDataVector(mainframe.pCalc.data, mainframe.pCalc.header);
					mainframe.pCalc.tfCalcResult.setText("");
					mainframe.pCalc.tfCashCheck.setText("");
					mainframe.mBtnSale.setBackground(new Color(255, 69, 0));
					mainframe.mBtnAccount.setBackground(new Color(28, 94, 94));
					mainframe.mBtnCalc.setBackground(new Color(28, 94, 94));
					mainframe.mBtnInven.setBackground(new Color(28, 94, 94));
					mainframe.mBtnStat.setBackground(new Color(99, 166, 166));
					mainframe.monitor.show(mainframe.pMonitor, "ViewSalesInput");
					mainframe.btn.show(mainframe.pFBtn, "salebtn");
					
					
				}

			}

		} 
	}

	public boolean isNumber(String obStr) {
		boolean flag = true;
		int num = obStr.charAt(0) - 48;
		if (num < 0 || num > 9) {
			flag = false;
		}
		return flag;
	}
	

}// 클래스 끝
