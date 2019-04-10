package com.kitri.pos;

import java.io.*;
import java.sql.*;

//유저등록할때 받아야하는 값
//유저 ID, 유저 패스워드, 이름 , 권한

public class UserDao {

	private BufferedReader in;
	PosDto posdto;
	// 관리자 클래스 객체생성 후 생성자 호출
	Administrator ad = new Administrator();
	Connection conn = null; // DB연결된 상태(세션)을 담은 객체
	PreparedStatement pstm = null; // SQL 문을 나타내는 객체
	ResultSet rs = null; // 쿼리문을 날린것에 대한 반환값을 담을 객체

	// 기본 생성자
	public UserDao() {
		super();
		in = new BufferedReader(new InputStreamReader(System.in));
		registerMenu();
	}

	public void registerMenu() {

		int USER_CODE = 0;
		String pw = null;
		String id = null;
		String authority = null;
		String name = null;

		try {
			System.out.print("유저코드입력하세요.");
			USER_CODE = Integer.parseInt(in.readLine());
			System.out.print("비밀번호입력하세요.");
			pw = in.readLine().trim();
			System.out.println();
			System.out.print("아이디입력하세요.");
			id = in.readLine().trim();
			System.out.print("권한입력하세요.");
			authority = in.readLine().trim();
			System.out.print("이름입력하세요.");
			name = in.readLine().trim();
//			System.out.print("실행중");
		
			register(posdto);
		} catch (IOException e) {

			e.printStackTrace();
		}

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
		} finally {

			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
				}
				conn.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
			this.posdto = posdto;
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
//		System.out.println("실행중");
//		System.out.println();
	}
}
