package com.kitri.pos.calc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

public class CalcDao {

//	Dto part(inner class)
	public class CalcDto {
		private String count_date;
		private int user_code;
		private int coms_clac;
		private int current_money;
		private int total_calc;
		
		public String getCount_date() {
			return count_date;
		}
		public void setCount_date(String count_date) {
			this.count_date = count_date;
		}
		public int getUser_code() {
			return user_code;
		}
		public void setUser_code(int user_code) {
			this.user_code = user_code;
		}
		public int getComs_clac() {
			return coms_clac;
		}
		public void setComs_clac(int coms_clac) {
			this.coms_clac = coms_clac;
		}
		public int getCurrent_money() {
			return current_money;
		}
		public void setCurrent_money(int current_money) {
			this.current_money = current_money;
		}
		public int getTotal_calc() {
			return total_calc;
		}
		public void setTotal_calc(int total_calc) {
			this.total_calc = total_calc;
		}
		
	}
/////////////////////////////////////////////////////////////////

	// 쿼리문 결과 (1행) 담을 Dto 객체
	CalcDto calcDto = null;

	// DB연결된 상태(세션)을 담은 객체
	Connection conn = null;

	// 쿼리문에 사용하는 state객체
	PreparedStatement ps = null;

	Statement st = null;
	ResultSet rs = null;

	Vector<CalcDto> vec = new Vector<CalcDto>();

//	private static String zeroPlus(int num) {
//		return (num < 10 ? "0" + num : "" + num);
//		
//	}
//
//	Calendar cal = new GregorianCalendar();
//	int y = cal.get(Calendar.YEAR);
//	int m = cal.get(Calendar.MONTH) + 1;
//	int d = cal.get(Calendar.DAY_OF_MONTH);
//	System.out.println(y+"."+zeroPlus(m)+"."+zeroPlus(d));

	
	
	public CalcDao() {
		super();

	}

	
	

	public int inputComs_Calc() {
		int sumCash = 0;
		try {
			conn = DBManager.getConnection();
			
			String query = "select sum(cash_price)  from history where to_char(sell_date,'yyyy.mm.dd') = to_char(sysdate, 'yyyy.mm.dd')";
			
			st = conn.createStatement();
			rs = st.executeQuery(query);

			
			PCalc pCalc = new PCalc();
			
			while(rs.next()) {
				sumCash = rs.getInt(1);
				System.out.println(sumCash);					
			}
			pCalc.tfCashState.setText(Integer.toString(sumCash));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(rs, st, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return sumCash;

	}

//	정산결과값 테이블에 insert
	public void calc_Apply() {
		
		
//		vec.add()
		try {
			conn = DBManager.getConnection();
			
			String query = "insert into money value(?,?,?,?,?)";
			
			ps = conn.prepareStatement(query);
			
			

			
			PCalc pCalc = new PCalc();
			
			while(rs.next()) {
								
			}
//			pCalc.tfCashState.setText(Integer.toString(sumCash));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(rs, st, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		

	}
	
}
