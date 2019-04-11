package com.kitri.pos;

import java.nio.channels.MembershipKey;
import java.sql.*;
import java.util.Vector;

import com.kitri.pos.PosDto;
import com.kitri.pos.DBManager;

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

	public Vector<PosDto> searchAll() {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosDto> list = new Vector<PosDto>();

		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = "select * from MEMBERSHIP";
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

	
	public Vector<PosDto> search(String name) {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosDto> list = new Vector<PosDto>();
		ViewSalesCustomer viewSalesCustomer = new ViewSalesCustomer();
		name = viewSalesCustomer.name.getText();
		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = "select * from MEMBERSHIP"
					+ "where name = ?";
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
	
}
