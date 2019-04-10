package com.kitri.pos.calc;

public class Tak_Test {
//	static int[][] data;
	static Object[][] data;
	int i;
	public static void main(String[] args) {
//		String[] headings = new String[] { "권종", "수량", "금액" };
//		Object[][] 
		data = new Object[][] { { "", "" , "" }, { 10000, 5, 0 }, { 5000, 5, 0 }, { 1000, 5, 0 }, { 500, 5, 0 },
				{ 100, 5, 0 }, { 50, 5, 0 }, { 10, 5, 0 } };
//				data = new Object[][] { { 50000, "5", data[0][0]) }, { 10000, "", 0 }, { 5000, "", 0 }, { 1000, "", 0 }, { 500, "", 0 },
//					{ 100, "", 0 }, { 50, "", 0 }, { 10, "", 0 } };
				
				data[0][2] = 50;
				System.out.println(data[0][2]);
	
	}

}
//(data[0][0] * Integer.parseInt(data[0][1]))