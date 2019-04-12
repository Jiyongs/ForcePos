package com.kitri.pos;

import java.io.*;
import java.sql.*;
import java.util.Vector;

import com.kitri.pos.db.DBManager;

//유저등록할때 받아야하는 값
//유저 ID, 유저 패스워드, 이름 , 권한

public class UserDao {

	// 회원리스트 클래스
	UserList mList;

	// DB연결시 필요
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// 기본생성자
	public UserDao() {

	}

	//
	public UserDao(UserList mList) {
		this.mList = mList;
	}
	// 리스트에 담은 값들을 얻어온다.

	// 회원 검색
	public Vector<UserDto> getMemberList() {

		Vector<UserDto> row = new Vector<UserDto>(); // Jtable에 넣을 값 //유저코드, 이름, 분류

//			Connection con = null; //연결
//			PreparedStatement ps = null; //명령
//			ResultSet rs = null; //결과

		con = DBManager.getConnection();

		String select = "select *" + "from members";

		try {

			ps = con.prepareStatement(select);
			rs = ps.executeQuery();

			while (rs.next()) {

				int user_code = rs.getInt(1);
				String pw = rs.getString(2);
				String id = rs.getString(3);
				String authority = rs.getNString(4);
				String name = rs.getString(5);

				UserDto userdto = new UserDto();

				userdto.setUserCode(user_code);
				userdto.setPw(pw);
				userdto.setId(id);
				userdto.setAuthority(authority);
				userdto.setName(name);

				row.add(userdto);

			}

		} catch (SQLException e1) {
			System.out.println("등록 오류");
			e1.printStackTrace();
		} //finally {
//			try {
//				DBManager.dbClose(ps, con);
//			} catch (SQLException e) {
//
//				e.printStackTrace();
//			}
//		}

		// TODO Auto-generated method stub
		return row;
	}

	// 회원수
	public boolean updateMember(UserDto dto) throws SQLException {

		boolean result = false;

//			Connection con = null;
//			PreparedStatement ps = null;
//			
		con = DBManager.getConnection();

		String update = "update members set name= ?, pw= ?" + "where id = ? and pw = ?";

		try {

			ps = con.prepareStatement(update);

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getId());
			ps.setString(4, dto.getPw());

			int r = ps.executeUpdate();

			if (r > 0) {
				System.out.println("DB수정이 완료되었습니다.");
				result = true;
			}
		} catch (SQLException e) {
			System.out.println("DB수정이 실패했습니다.");
			e.printStackTrace();
		} finally {
			DBManager.dbClose(ps, con);
		}
		return result;
	}

	// 회원정보삭제
	public boolean deleteMember(String id, String pw) {

		boolean result = false;
//			Connection con = null;
//			PreparedStatement ps = null;

		con = DBManager.getConnection();
		String delete = "delete" + "from members " + "where id = ? and pw = ?";

		try {

			ps = con.prepareStatement(delete);
			ps.setString(1, id);
			ps.setString(2, pw);

			int r = ps.executeUpdate();

			if (r > 0) {
				result = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(ps, con);
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return result;
	}

	// 회원등록
	public boolean insertMember(UserDto userdto) {

		boolean result = false;

//			Connection con = null;
//			PreparedStatement ps = null;

		con = DBManager.getConnection();

		String insert = "insert into members(user_code, pw, id, authority, name) values(USER_CODE_SEQ.NEXTVAL, ?, ?, ?, ?)";
		try {

			ps = con.prepareStatement(insert);

//			ps.setInt(1, userdto.getUserCode());
			ps.setString(1, userdto.getPw());
			ps.setString(2, userdto.getId());
			ps.setString(3, userdto.getAuthority());
			ps.setString(4, userdto.getName());
						
			int r = ps.executeUpdate(); // 실행 >> 저장
			String str = Integer.toString(r);
			if (str == null) {
				System.out.println("회원가입 실패");
			}
			
			if (r > 0) {
				System.out.println("회원가입 성공 ");
				result = true;
			} 

		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}  finally {
			try {
				DBManager.dbClose(ps, con);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return result;
	}

}
