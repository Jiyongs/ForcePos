package com.kitri.pos;

import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.kitri.pos.db.DBManager;

//유저등록할때 받아야하는 값
//유저 ID, 유저 패스워드, 이름 , 권한

public class UserDao {

	// 회원리스트 클래스
	UserList mList;
	UserDto userDto;

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
		// 보여지는 테이블에 넣는 값들 : 유저코드, 패스워드, 아이디, 권한, 이름
		Vector<UserDto> row = new Vector<UserDto>();

		con = DBManager.getConnection();

		String select = "select * " + "from members " + "order by name asc";

		try {

			ps = con.prepareStatement(select);
			rs = ps.executeQuery();

			while (rs.next()) {

				int user_code = rs.getInt(1);
				String pw = rs.getString(2);
				String id = rs.getString(3);
				String authority = rs.getNString(4);
				String name = rs.getString(5);

				userDto = new UserDto();

				userDto.setUserCode(user_code);
				userDto.setPw(pw);
				userDto.setId(id);
				userDto.setAuthority(authority);
				userDto.setName(name);

				row.add(userDto);
			}

		} catch (SQLException e1) {
			System.out.println("테이블 출력 오류");
			e1.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(ps, con);
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		// TODO Auto-generated method stub
		return row;
	}

	// 회원수정
	public boolean updateMember(UserDto userDto) throws SQLException {

		boolean result = false;

		con = DBManager.getConnection();

		String update = "update members set name= ?, pw= ? " + "where id = ? and pw = ?";

		try {

			ps = con.prepareStatement(update);

			ps.setString(1, userDto.getName());
			ps.setString(2, userDto.getPw());
			ps.setString(3, userDto.getId());
			ps.setString(4, userDto.getPw());

			int r = ps.executeUpdate();

			if (r > 0) {
				System.out.println("DB 수정이 완료되었습니다.");
				result = true;
			}
		} catch (SQLException e) {
			System.out.println("DB 수정이 실패했습니다.");
			e.printStackTrace();
		} finally {
			DBManager.dbClose(ps, con);
		}
		return result;
	}

	public void userSelectAll(DefaultTableModel tm) throws SQLException {

		con = DBManager.getConnection();

		String select = "select *" + "from members " + "order by name ase";

		try {
			ps = con.prepareStatement(select);
			rs = ps.executeQuery();

			for (int i = 0; i < tm.getRowCount();) {
				tm.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = {

						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5) };

				tm.addRow(data);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {

			DBManager.dbClose(ps, con);
		}

	}

	// 회원정보삭제
	public boolean deleteMember(String id)) throws SQLException {

		boolean result = false;

		con = DBManager.getConnection();

		String delete = "delete" + "from members " + "where id = ?";

		try {

			ps = con.prepareStatement(delete);

			ps.setString(1, id);

			int r = ps.executeUpdate();

			if (r > 0) {
				result = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			DBManager.dbClose(ps, con);
		}
		return result;
	}

	// 회원등록
	public boolean insertMember(UserDto userdto) throws SQLException {

		boolean result = false;

		con = DBManager.getConnection();

		String insert = "insert into members(user_code, pw, id, authority, name) "
				+ "values(USER_CODE_SEQ.NEXTVAL, ?, ?, ?, ?)";
		try {

			ps = con.prepareStatement(insert);

			ps.setString(1, userdto.getPw());
			ps.setString(2, userdto.getId());
			ps.setString(3, userdto.getAuthority());
			ps.setString(4, userdto.getName());

			int r = ps.executeUpdate(); // 실행 >> 저장

			if (r > 0) {
				System.out.println("회원가입 성공 ");
				result = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			DBManager.dbClose(ps, con);
		}
		return result;
	}

}
