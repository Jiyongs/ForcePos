package com.kitri.pos.calc;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcService implements ActionListener {
	Cmain cmain;
//	PCalc pCalc;
	String numStr;
	String obStr;
	
	
	int[] coin = new int[8];
	public CalcService(Cmain cmain) {
		super();
		//@@@@@@@@@@@@@@@
		this.cmain = cmain;
		
	}
	
	public void cashCaclEach() {

		for(int i = 0 ; i<8;i++) {
			if(!Cmain.pCalc.data[i][1].isEmpty())
			Cmain.pCalc.data[i][2] = (String.valueOf((Integer.parseInt(Cmain.pCalc.data[i][0])*Integer.parseInt(Cmain.pCalc.data[i][1]))));
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
//		JButton b = (JButton) ob;
//		obStr = b.getLabel();
		
		if(ob == cmain.mBtnCalc) {
			
			cmain.card.show(cmain.pMonitor,"Calc");
			
			
			
//		}else if(isNumber(obStr) == true) {
//			numStr += obStr;
//			System.out.println(obStr);
////			테이블에 선택한 칸에 입력되게끔.calculator.numL.setText(numStr);
//				
////		}else if{ob == pCalc.btnCalc_C){
			
			
		
		}else if(ob == Cmain.pCalc.btnCalc_Input) {
//			입력된 값 추가와 동시에 입력된 값으로 금액계산함
			System.out.println("반응");
			cashCaclEach();
		}else if(ob == Cmain.pCalc.btnCalc_del) {
			System.out.println("백스페이스");
//			맨뒤 한숫자 삭제
		}else if(ob == Cmain.pCalc.btnCalc_C) {
			System.out.println("수량 초기화");
//			Jtable.setValueAt();
		}else if(ob == Cmain.pCalc.btnCalc_Cancel) {
//			Cmain.판매창.card.show(~~)
			System.out.println("정산창 비활성화, 판매창 활성화");
		}else if(ob == Cmain.pCalc.btnCalc_Apply) {
//			Cmain.판매창.card.show(~~)
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
