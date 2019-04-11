package com.kitri.pos.db;

import java.sql.*;

/*
  DBManger : DB의 연결, 연결해제 메소드를 가진 클래스
*/

public class DBManager {

	
	// <DB 연결> 메소드 : 연결된 DB 상태를 리턴함
	public static Connection getConnection() {
		Connection conn = null;
		try {
			String user = "kitri";
			String pw = "kitri";
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);

			System.out.println("Database에 연결되었습니다.\n");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
		return conn;
	}

	
	// *rs : ResultSet
	// st : Statement
	// ps : PreparedStatement
	// conn : Connection

	// <DB 연결 해제 1> 메소드 : ps + conn
	public static void dbClose(PreparedStatement ps, Connection conn) throws SQLException {
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
		System.out.println("자원반납, DB Close!!");
	}

	// <DB 연결 해제 2> 메소드 : st + conn
	public static void dbClose(Statement st, Connection conn) throws SQLException {
		if (st != null)
			st.close();
		if (conn != null)
			conn.close();
		System.out.println("자원반납, DB Close!!");
	}

	// <DB 연결 해제 3> 메소드 : rs + ps + conn
	public static void dbClose(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
		System.out.println("자원반납, DB Close!!");
	}

	// <DB 연결 해제 4> 메소드 : rs + st + conn
	public static void dbClose(ResultSet rs, Statement st, Connection conn) throws SQLException {
		if (rs != null)
			rs.close();
		if (st != null)
			st.close();
		if (conn != null)
			conn.close();
		System.out.println("자원반납, DB Close!!");
	}


}
