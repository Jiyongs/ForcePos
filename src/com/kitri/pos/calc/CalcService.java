package com.kitri.pos.calc;

public class CalcService {

	PCalc pCalc;
	int[] coin = new int[8];
	public CalcService(PCalc pCalc) {
		super();
		//@@@@@@@@@@@@@@@
		this.pCalc = new PCalc();
	}
	
	public void cashCaclEach() {
		for(int i = 0 ; i<8;i++) {
			pCalc.data[0][2] = String.valueOf((Integer.parseInt(pCalc.data[0][0])*Integer.parseInt(pCalc.data[0][1])));
		}
		
	}
	
	
	
}
