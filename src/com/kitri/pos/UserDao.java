package com.kitri.pos;

import java.sql.*;

//유저등록할때 받아야하는 값
//유저 ID, 유저 패스워드, 이름 , 권한

public class UserDao {

	Connection conn = null; // DB연결된 상태(세션)을 담은 객체
	PreparedStatement pstm = null; // SQL 문을 나타내는 객체
	ResultSet rs = null; // 쿼리문을 날린것에 대한 반환값을 담을 객체

	// 기본 생성자
	public UserDao() {

	}

	public void register(PosDto posdto) {

		conn = getConnection();
		String insert = "INSERT INTO MEMBERS(USER_CODE, PW, ID, AUTHORITY, NAME) VALUES(?, ?, ?, ?, ?)";
		try {
			pstm = conn.prepareStatement(insert);

			pstm.setInt(1, posdto.getUserCode());
			pstm.setString(2, posdto.getPw());
			pstm.setString(3, posdto.getId());
			pstm.setString(4, posdto.getAuthority());
			pstm.setString(5, posdto.getName());

			int result = pstm.executeUpdate();

			if (result > 0) {
				System.out.println(posdto.getName() + "님의 DB 저장");
			} else {
				System.out.println("DB연결실패");
			}

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
			conn = DriverManager.getConnection(url, user, pw);

			System.out.print("Database에 연결성공.\n");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
		return conn;
	};

	
	public static void main(String[] args) {
		new UserDao();
//		System.out.println();
	}
}
