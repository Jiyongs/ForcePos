package com.kitri.pos.calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class CalcService implements ActionListener {
	Cmain cmain;
//	PCalc pCalc;
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

	public CalcService(Cmain cmain) {
		super();
		// @@@@@@@@@@@@@@@
		this.cmain = cmain;
		calcDao = new CalcDao();

	}

	public void cashCaclEach() {
		/////////////////////////////////// 각각의 권종 합에 반영

		for (int i = 0; i < 8; i++) {
			numStr = "";
//			Cmain.pCalc.cashTable.setValueAt(numStr, i, 1);
//			tmp = String.valueOf(Cmain.pCalc.model.getValueAt(i, 1));
//			System.out.println(tmp);

//			if(Cmain.pCalc.model.getValueAt(i, 1) != "") {
			v1 = String.valueOf(Cmain.pCalc.model.getValueAt(i, 0));
			v2 = String.valueOf(Cmain.pCalc.model.getValueAt(i, 1));
//			v2 = String.valueOf(Cmain.pCalc.data[i][1]);
//			value = Integer.parseInt(String.valueOf(Cmain.pCalc.data[i][1]));
			if (!v2.equals("")) {
				value = Integer.parseInt(v1) * Integer.parseInt(v2);
				Cmain.pCalc.model.setValueAt(value, i, 2);
				sum += value;
			}
		}
		///////////////////////////////////// 정산금액에 반영

		Cmain.pCalc.tfCashCheck.setText(String.valueOf(sum));
		sum = 0;
		//////////////////////////////////// 차액반영
		Cmain.pCalc.tfCalcResult.setText(String.valueOf((Integer.parseInt(Cmain.pCalc.tfCashCheck.getText())
				- Integer.parseInt(Cmain.pCalc.tfCashState.getText())))); // '-'��ȣ��
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		obStr = e.getActionCommand();


		if (ob == cmain.mBtnCalc) {

			cmain.card.show(cmain.pMonitor, "Calc");
			
			Cmain.pCalc.tfCashState.setText(Integer.toString(calcDao.inputComs_Calc()));

		} else if (isNumber(obStr) == true) {
			if(Cmain.pCalc.cashTable.getSelectedColumn() == 1) {
				
			row = Cmain.pCalc.cashTable.getSelectedRow();
			column = Cmain.pCalc.cashTable.getSelectedColumn();
			if (Cmain.pCalc.model.isCellEditable(row, column) == false) {
				return;
			}
			numStr = String.valueOf(Cmain.pCalc.cashTable.getValueAt(row, column));
			if (numStr.equals("0")) {
				Cmain.pCalc.cashTable.setValueAt(obStr, row, column);
			} else {
				numStr += obStr;
				Cmain.pCalc.cashTable.setValueAt(numStr, row, column);
			}
			cashCaclEach();
			}
		} else if (ob == Cmain.pCalc.btnCalc_del) {
			if(Cmain.pCalc.cashTable.getSelectedColumn() == 1) {
//			맨뒤 한숫자 삭제
			row = Cmain.pCalc.cashTable.getSelectedRow();
			column = Cmain.pCalc.cashTable.getSelectedColumn();
			if(row == -1) 
				return;
			
			String value = String.valueOf(Cmain.pCalc.model.getValueAt(row, column));
			int len = value.length();
			if (len > 1) {
				value = value.substring(0, len - 1);
				Cmain.pCalc.model.setValueAt(value, row, column);
				numStr = value;
			} else {
				Cmain.pCalc.model.setValueAt("0", row, column);
			}
			cashCaclEach();
			}
		} else if (ob == Cmain.pCalc.btnCalc_C) {
			if(Cmain.pCalc.cashTable.getSelectedColumn() == 1) {
			row = Cmain.pCalc.cashTable.getSelectedRow();
			column = Cmain.pCalc.cashTable.getSelectedColumn();
			if(row == -1) 
				return;
			Cmain.pCalc.cashTable.setValueAt("0", row, column);
			cashCaclEach();
			}
		} else if (ob == Cmain.pCalc.btnCalc_Apply) {
			if (Cmain.pCalc.tfCashCheck.getText().equals("") || Cmain.pCalc.tfCalcResult.getText().equals("")) {
				JOptionPane.showMessageDialog(cmain, "정산처리할 값이 부족합니다.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				calcDao.posDto.setComsCalc(Integer.parseInt(Cmain.pCalc.tfCashState.getText()));
				calcDao.posDto.setCurrentMoney(Integer.parseInt(Cmain.pCalc.tfCashCheck.getText()));
				calcDao.posDto.setTotalCalc(Integer.parseInt(Cmain.pCalc.tfCalcResult.getText()));
				calcDao.calc_Apply();
			
			
		}

	}else if(ob==Cmain.pCalc.btnCalc_Cancel){
		cmain.card.show(cmain.pMonitor, "Test");
		System.out.println("정산창 비활성화, 판매창 활성화");


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

//	public void isNumError(boolean b) {
//		if(b == false)
//		JOptionPane.showMessageDialog(cmain, "숫자만 입력가능합니다.", "입력오류", JOptionPane.ERROR_MESSAGE);
//	}

}
