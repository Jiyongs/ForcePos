package com.kitri.pos.sales;

import java.nio.channels.MembershipKey;
import java.sql.*;
import java.util.Vector;

import com.kitri.pos.sales.PosDto;
import com.kitri.pos.db.DBManager;

/*
	StatDao : 통계 DB 관련 메소드 정의
*/

public class SalesDao {

	// DB연결된 상태(세션)을 담은 객체
	Connection conn = null;

	// 쿼리문에 사용하는 state객체
	PreparedStatement ps = null;

	Statement st = null;
	ResultSet rs = null;

	// 쿼리문 결과 (1행) 담을 PosDto 객체
	PosDto posDto = null;

//	 멤버십 전체 정보를 불러오는 메소드
	public Vector<PosDto> searchAll() {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosDto> list = new Vector<PosDto>();

		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = "select * from membership\r\n";
			ps = conn.prepareStatement(query);

			// 쿼리문 실행
			rs = ps.executeQuery();
			// 결과 저장
			while (rs.next()) {

				posDto = new PosDto();

				posDto.setMembershipId(rs.getString(1));
				posDto.setMemberName(rs.getString(2));
				posDto.setPhone(rs.getString(3));
				posDto.setPoint(rs.getInt(4));

				list.add(posDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				DBManager.dbClose(rs, ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 리턴
		return list;
	}

//	name만 입력하고 멤버십 조회하는 메소드
	public Vector<PosDto> search(String name) {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		
		Vector<PosDto> list = new Vector<PosDto>();
		
	
		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = "select membership_id, name, phone, point\r\n" + 
					"from membership\r\n" + 
					"where name = ?";
			ps = conn.prepareStatement(query);

			ps.setString(1, name);
			
			// 쿼리문 실행
			rs = ps.executeQuery();
			// 결과 저장
		
			while (rs.next()) {

				posDto = new PosDto();

				posDto.setMembershipId(rs.getString(1));
				posDto.setMemberName(rs.getString(2));
				posDto.setPhone(rs.getString(3));
				posDto.setPoint(rs.getInt(4));

				list.add(posDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				DBManager.dbClose(rs, ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 리턴
		return list;
	}
//	phone만 입력하고 조회하는 메소드
	public Vector<PosDto> search1(String cellphone) {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		
		Vector<PosDto> list = new Vector<PosDto>();
		
	
		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = "select membership_id, name, phone, point\r\n" + 
					"from membership\r\n" + 
					"where phone = ?";
			ps = conn.prepareStatement(query);

			ps.setString(1, cellphone);
			
			// 쿼리문 실행
			rs = ps.executeQuery();
			// 결과 저장
		
			while (rs.next()) {

				posDto = new PosDto();

				posDto.setMembershipId(rs.getString(1));
				posDto.setMemberName(rs.getString(2));
				posDto.setPhone(rs.getString(3));
				posDto.setPoint(rs.getInt(4));

				list.add(posDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				DBManager.dbClose(rs, ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 리턴
		return list;
	}
//		name, phone 전부로 조회하는 메소드
	public Vector<PosDto> search2(String name, String cellphone) {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		
		Vector<PosDto> list = new Vector<PosDto>();
		
	
		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = "select membership_id, name, phone, point\r\n" + 
					"from membership\r\n" + 
					"where name = ?" +
					"and phone = ?";
			ps = conn.prepareStatement(query);

			ps.setString(1, name);
			ps.setString(2, cellphone);
			
			// 쿼리문 실행
			rs = ps.executeQuery();
			// 결과 저장
		
			while (rs.next()) {

				posDto = new PosDto();

				posDto.setMembershipId(rs.getString(1));
				posDto.setMemberName(rs.getString(2));
				posDto.setPhone(rs.getString(3));
				posDto.setPoint(rs.getInt(4));

				list.add(posDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				DBManager.dbClose(rs, ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 리턴
		return list;
	}
//		회원정보를 입력하는 메소드	
	public int register(String name, String cellphone) {
		int result = 0;

		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문
			String query = "insert into membership values((MEMBERSHIP_ID_SEQ.NEXTVAL), ?, ?, 0)";

			//	쿼리문 실행
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, cellphone);

			// 3)쿼리문 결과

			System.out.println("쿼리문 실행");
			result = ps.executeUpdate();

			if (result > 0) {
				System.out.println("INSERT 성공");
			} else
				System.out.println("INSERT 실패");

		} catch (SQLException sqle) {
			System.out.println("INSERT");
			sqle.printStackTrace();

		} finally {
			// DB 연결 종료
			try {
				DBManager.dbClose(ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		// 결과 리턴
		return result;
	}

	
	
}
