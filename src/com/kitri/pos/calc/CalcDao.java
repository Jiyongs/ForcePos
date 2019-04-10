package com.kitri.pos.calc;

import java.sql.*;

public class CalcDao {

	PCalc pCalc = new PCalc();
//	DB������¸� ���� ��ü
	Connection conn = null;
//	������ ����ϴ� state��ü
	Statement st = null;
	ResultSet rs = null;
//	�������� ���� ���� �� ����ϴ� state��ü
	PreparedStatement pstm = null;

	public CalcDao() {
		super();

	}

	public void inputComs_Calc() {

		try {
			conn = getConnection();
			String query = "select sum(cash_price)  from history where to_char(sell_date,'yyyy.mm.dd') = to_char(sysdate, 'yyyy.mm.dd')";
			st = conn.createStatement();
			rs = st.executeQuery(query);
			pCalc.tfCashState.setText(rs.getString("sum(cash_price)"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
				}
				conn.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	public Connection getConnection() {

		String user = "kitri";
		String pw = "kitri";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);

			System.out.println("Database가 연결되었습니다..\n");
		} catch (ClassNotFoundException e) {
			System.out.print("DB ����̹� �ε� ���� : ");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}

		return conn;

	}

}
