package com.kitri.pos.calc;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;

import javafx.scene.control.DialogEvent;

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
	CalcDao dao;

	public CalcService(Cmain cmain) {
		super();
		// @@@@@@@@@@@@@@@
		this.cmain = cmain;

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
			dao = new CalcDao();
			Cmain.pCalc.tfCashState.setText(Integer.toString(dao.inputComs_Calc()));

		} else if (isNumber(obStr) == true) {
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


//			테이블에 선택한 칸에 입력되게끔.calculator.numL.setText(numStr);

		} else if (ob == Cmain.pCalc.btnCalc_Input) {
//			입력된 값 추가와 동시에 입력된 값으로 금액계산함
			
			cashCaclEach();

		} else if (ob == Cmain.pCalc.btnCalc_del) {
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

		} else if (ob == Cmain.pCalc.btnCalc_C) {
			row = Cmain.pCalc.cashTable.getSelectedRow();
			column = Cmain.pCalc.cashTable.getSelectedColumn();
			if(row == -1) 
				return;
			Cmain.pCalc.cashTable.setValueAt("0", row, column);
			cashCaclEach();

		} else if (ob == Cmain.pCalc.btnCalc_Apply) {
			if (Cmain.pCalc.tfCashCheck.getText().equals("") || Cmain.pCalc.tfCalcResult.getText().equals("")) {
				JOptionPane.showMessageDialog(cmain, "정산처리할 값이 부족합니다.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
			dao.calc_Apply(Integer.parseInt(Cmain.pCalc.tfCashState.getText()),
					Integer.parseInt(Cmain.pCalc.tfCashCheck.getText()),
					Integer.parseInt(Cmain.pCalc.tfCalcResult.getText()));
		}
//			Cmain.판매창.card.show(~~)
//			System.out.println(Integer.parseInt(Cmain.pCalc.tfCashState.getText()));
//			System.out.println("2");
//			System.out.println(Integer.parseInt(Cmain.pCalc.tfCashCheck.getText()));
//			System.out.println("3");
//			System.out.println(Integer.parseInt(Cmain.pCalc.tfCalcResult.getText()));

	}else if(ob==Cmain.pCalc.btnCalc_Cancel){
			cmain.card.show(cmain.pMonitor, "Test");
			System.out.println("정산창 비활성화, 판매창 활성화");

		}

	}

	boolean isNumber(String obStr) {
		boolean flag = true;
		int num = obStr.charAt(0) - 48;
		if (num < 0 || num > 9) {
			flag = false;
		}
		return flag;
	}

}
