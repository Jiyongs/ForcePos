package com.kitri.pos.calc;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

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
//	Thread t;
	
	
	CalcDao dao;

	public CalcService(Cmain cmain) {
		super();
		// @@@@@@@@@@@@@@@
		this.cmain = cmain;

	}

	public void cashCaclEach() {
		///////////////////////////////////// 각각의 권종 합에 반영
		System.out.println("메소드입장");
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
//		JButton b = (JButton) ob;
//		obStr = b.getLabel();

		if (ob == cmain.mBtnCalc) {
//			t = new Thread(this);
//			t.start();
			
			cmain.card.show(cmain.pMonitor, "Calc");
			dao = new CalcDao();
			Cmain.pCalc.tfCashState.setText(Integer.toString(dao.inputComs_Calc()));
			
		} else if (isNumber(obStr) == true) {
			int row = Cmain.pCalc.cashTable.getSelectedRow();
			int column = Cmain.pCalc.cashTable.getSelectedColumn();
			numStr = String.valueOf(Cmain.pCalc.cashTable.getValueAt(row, column));
			if(numStr.isEmpty()) {
				Cmain.pCalc.cashTable.setValueAt(obStr, row, column);
			}else {
				numStr += obStr;
				Cmain.pCalc.cashTable.setValueAt(numStr, row, column);
				
			}
			
//			Cmain.pCalc.model.

//			테이블에 선택한 칸에 입력되게끔.calculator.numL.setText(numStr);
//				
////		}else if{ob == pCalc.btnCalc_C){

		} else if (ob == Cmain.pCalc.btnCalc_Input) {
//			입력된 값 추가와 동시에 입력된 값으로 금액계산함
			System.out.println("반응");
			cashCaclEach();

		} else if (ob == Cmain.pCalc.btnCalc_del) {
//			맨뒤 한숫자 삭제
			int row = Cmain.pCalc.cashTable.getSelectedRow();
			int column = Cmain.pCalc.cashTable.getSelectedColumn();
			String value = String.valueOf(Cmain.pCalc.model.getValueAt(row, column));
			int len = value.length();
			if (len > 1) {
				value = value.substring(0, len - 1);
				Cmain.pCalc.model.setValueAt(value, row, column);
				numStr = value;
			} else {
				Cmain.pCalc.model.setValueAt("", row, column);
			}
		} else if (ob == Cmain.pCalc.btnCalc_C) {
			int row = Cmain.pCalc.cashTable.getSelectedRow();
			int column = Cmain.pCalc.cashTable.getSelectedColumn();
			Cmain.pCalc.cashTable.setValueAt("", row, column);
//			
		} else if (ob == Cmain.pCalc.btnCalc_Apply) {
//			Cmain.판매창.card.show(~~)
			System.out.println("정산창 비활성화, 판매창 활성화");

		} else if (ob == Cmain.pCalc.btnCalc_Cancel) {
//			Cmain.판매창.card.show(~~)
			System.out.println("정산창 비활성화, 판매창 활성화");
//			t.stop();
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




//	@Override
//	public void run() {
//	while(true) {
//		cashCaclEach();
//	}
//	}

}
