
package com.kitri.pos.stock;

import java.sql.*;
import java.util.Vector;
/*
	StockDao : 재고 DB 관련 메소드 정의
*/

import javax.swing.table.DefaultTableModel;

import com.kitri.pos.ForcePos;
import com.kitri.pos.MainFrame;
import com.kitri.pos.db.DBManager;
import com.kitri.pos.db.PosDto;

public class StockDao {
//	StockMonitor m = new StockMonitor();
	boolean flag;
	
	MainFrame mainFrame;
	// DB연결된 상태(세션)을 담은 객체
	Connection conn = null;

	// 쿼리문에 사용하는 state객체
	PreparedStatement ps = null;

	Statement st = null;
	ResultSet rs = null;

	// 쿼리문 결과 (1행) 담을 PosDto 객체
	PosDto posDto = null;

	
	// [메소드]

	// 최초에 모든재고표시, 재고 조회(특정재고검색), 등록(입고), 선택한것 수정(수량만), 수량0인것 일괄삭제(새로고침)
	// 모든재고표시
	public Vector<PosDto> StockAll() {

		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosDto> list = new Vector<PosDto>();

		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = "select product_code, to_char(in_date, 'yy/mm/dd'), product_name, volume, price, real_exp \r\n"
					+ "from stocks\r\n" + "where volume > 0";
			ps = conn.prepareStatement(query);

			// 쿼리문 실행
			rs = ps.executeQuery();

			// 결과 저장
			while (rs.next()) {
				posDto = new PosDto();

				posDto.setProductCode(rs.getString(1));
				posDto.setInDate(rs.getString(2));
				posDto.setProductName(rs.getString(3));
				posDto.setVolume(rs.getInt(4));
				posDto.setPrice(rs.getInt(5));
				posDto.setRealExp(rs.getString(6));

				list.add(posDto);
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(rs, ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		flag = false;
		// 결과 리턴
		return list;

	}

///////////////////조회
	// 원하는 재고 조회 ( 상품코드 또는 상품명으로만)
	// 코드로하는 메소드
	public Vector<PosDto> StockSearchCode(String productCode) {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosDto> list = new Vector<PosDto>();

		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = "select product_code, to_char(in_date, 'yy/mm/dd'), product_name, volume, price, real_exp\r\n"
					+ "from stocks\r\n" + "where product_code = upper(?)" + "and volume > 0";
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);

			// 쿼리문 실행
			rs = ps.executeQuery();

			// 결과 저장
			while (rs.next()) {
				posDto = new PosDto();

				posDto.setProductCode(rs.getString(1));
				posDto.setInDate(rs.getString(2));
				posDto.setProductName(rs.getString(3));
				posDto.setVolume(rs.getInt(4));
				posDto.setPrice(rs.getInt(5));
				posDto.setRealExp(rs.getString(6));

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

	/////////////////// 조회
	// 원하는 재고 조회 ( 상품코드 또는 상품명으로만)
	// 상품명으로하는 메소드
	public Vector<PosDto> StockSearchName(String productName) {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosDto> list = new Vector<PosDto>();

		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = "select product_code, to_char(in_date, 'yy/mm/dd'), product_name, volume, price, real_exp\r\n"
					+ "from stocks\r\n" + "where product_name = ?" + "and volume > 0";

			ps = conn.prepareStatement(query);
			ps.setString(1, productName);

			// 쿼리문 실행
			rs = ps.executeQuery();

			// 결과 저장
			while (rs.next()) {
				posDto = new PosDto();

				posDto.setProductCode(rs.getString(1));
				posDto.setInDate(rs.getString(2));
				posDto.setProductName(rs.getString(3));
				posDto.setVolume(rs.getInt(4));
				posDto.setPrice(rs.getInt(5));
				posDto.setRealExp(rs.getString(6));

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

////////////입고
	// 인자값 3개만 받아서 입력하기 상품코드 입고일자 수량
	// 나머지 3개 데이터는 product테이블 참조해서 채워넣기 가능?

	public int StockIn(String productCode, String inDate, int volume) {
		int r = 0;

		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = "insert into stocks(product_code, in_date, product_name, volume, price, real_exp)\r\n"
					+ "values( upper(?), ?, (select distinct p.product_name \r\n"
					+ "			from products p, stocks s \r\n" + "			where p.product_code = upper(?)), ?, "
					+ "(select distinct p.price \r\n" + " from products p, stocks s \r\n"
					+ "	where p.product_code = upper(?)), " + "(select distinct add_months(s.in_date, p.exp) \r\n"
					+ " from products p, stocks s \r\n" + " where p.product_code = s.product_code"
					+ " and p.product_code = upper(?)) )";

			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			ps.setString(2, inDate);
			ps.setString(3, productCode);
			ps.setInt(4, volume);
			ps.setString(5, productCode);
			ps.setString(6, productCode);

			// 쿼리문 실행
			r = ps.executeUpdate();

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
		return r;
	}

	//////////////// 재고수정
	// 테이블에서 선택한 행의 정보 표시 후 수량만 수정하기
	// 수량만 자유자재로 바꿀수있음. 일자는 yymmdd로 받기
	public void StockChange(int volume, String productCode, String inDate) {

		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅, 조건으로사용하는 상품코드는 겟텍스트로 받자
			String query = "update stocks \r\n" + "set volume = ? \r\n" + "where product_code = ?"
					+ "and in_date = to_date(?, 'yy/mm/dd')";
			ps = conn.prepareStatement(query);
			ps.setInt(1, volume);
			ps.setString(2, productCode);
			ps.setString(3, inDate);

			// 쿼리문 실행
			int r = ps.executeUpdate();

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

	}

	
	public void expCount() {

		conn = DBManager.getConnection();

		try {
			String query = "select product_name, trunc(round((real_exp - sysdate),1 )*24) exp \r\n "
					+ "from stocks \r\n"
					+ "where real_exp - sysdate < 1 \r\n "
					+ "and volume !=0 and rownum = 1 \r\n "
					+ "order by exp";
			st = conn.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				posDto = new PosDto();

				ForcePos.expName.setExpName(rs.getString(1));
				ForcePos.exp.setExp(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(rs, st, conn);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	
}// 클래스 종료