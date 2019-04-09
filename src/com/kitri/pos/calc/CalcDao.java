package com.kitri.pos.calc;

import java.sql.*;

public class CalcDao {
//	DB연결상태를 담은 객체
	Connection conn = null;
//	쿼리문 사용하는 state객체
	Statement st = null;
	ResultSet rs = null;
//	쿼리문에 변수 넣을 때 사용하는 state객체
	PreparedStatement pstm = null;

	public CalcDao() {
	super();
	
	}
	
	public void inputComs_Calc() {
		
		
		try {
			conn = getConnection();
			String query = "select sum(cash_price) from history where to_char(sell_date,'yyyy.mm') = to_char(sysdate, 'yyyy.mm')";
			st = conn.createStatement();
			rs = st.executeQuery(query);
			tfCashState = rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public Connection getConnection() {
	
		String user = "kitri";
		String pw = "kitri";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn =DriverManager.getConnection(url, user, pw);
			
			System.out.println("Database에 연결되었습니다.\n");
		} catch (ClassNotFoundException e) {
			System.out.print("DB 드라이버 로딩 실패 : ");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
		
		
		return conn;
			
			
		}
		
	
}
	
	
	
	
	
	
	

