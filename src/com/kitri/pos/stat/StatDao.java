package com.kitri.pos.stat;

import java.sql.*;
import java.util.Vector;

import com.kitri.pos.PosDto;
import com.kitri.pos.db.DBManager;

/*
	StatDao : 통계 DB 관련 메소드 정의
*/

public class StatDao {

	// DB연결된 상태(세션)을 담은 객체
	Connection conn = null;

	// 쿼리문에 사용하는 state객체
	PreparedStatement ps = null;

	Statement st = null;
	ResultSet rs = null;

	// 쿼리문 결과 (1행) 담을 PosDto 객체
	PosDto posDto = null;

	// [메소드]

	//////////////////////////////// 상품별 통계 ////////////////////////////////
	// <상품별 매출내역 select> 메소드
	// : 소분류, 년, 월 입력받아 매출합계 랭킹순으로 조회
	public Vector<PosDto> findProductSell(String minor_level, String year, String month) {

		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosDto> list = new Vector<PosDto>();

		int date = Integer.parseInt(year.concat(month).concat("01"));
		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = "select row_number() over(order by p.price*v.sc desc) as 매출순위, p.product_code as 상품코드, p.minor_level as 상품분류, p.product_name as 상품명, p.price as 판매가, p.purchase as 매입가, v.sc as 판매수량,  p.price*v.sc as 매출합계, p.company as 제조사\r\n"
					+ "from products p, (select product_code, sum(sell_count) sc\r\n"
					+ "                                 from history_detail\r\n"
					+ "                                 where to_char(sell_date,'yyyymm') = to_char(to_date(?),'yyyymm')\r\n"
					+ "                                 group by product_code) v\r\n"
					+ "where p.product_code = v.product_code\r\n" + "and p.minor_level = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, date);
			ps.setString(2, minor_level);

			// 쿼리문 실행
			rs = ps.executeQuery();

			// 결과 저장
			while (rs.next()) {
				posDto = new PosDto();

				posDto.setRanking(rs.getInt(1));
				posDto.setProductCode(rs.getString(2));
				posDto.setMinorLevel(rs.getString(3));
				posDto.setProductName(rs.getString(4));
				posDto.setPrice(rs.getInt(5));
				posDto.setPurchase(rs.getInt(6));
				posDto.setSellCount(rs.getInt(7));
				posDto.setStatTotalPrice(rs.getInt(8));
				posDto.setCompany(rs.getString(9));

				list.add(posDto);
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

		// 결과 리턴
		return list;

	}

	// <상품별 매출내역 BEST 5 select> 메소드
	// : 소분류, 년, 월 입력받아 매출합계 랭킹 상위 5위까지 조회
	public Vector<PosDto> findProductSellBestFive(String minor_level, String year, String month) {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosDto> list = new Vector<PosDto>();

		// String으로 입력한 날짜를 '년월' 합쳐서 int로 변환
		int date = Integer.parseInt(year.concat(month).concat("01"));
		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = "select vr.매출순위, vr.상품코드, vr.상품분류, vr.상품명, vr.판매가, vr.매입가, vr.판매수량, vr.매출합계, vr.제조사\r\n"
					+ "from (select row_number() over(order by p.price*v.sc desc) as 매출순위, p.product_code as 상품코드, p.minor_level as 상품분류, p.product_name as 상품명, p.price as 판매가, p.purchase as 매입가, v.sc as 판매수량,  p.price*v.sc as 매출합계, p.company as 제조사\r\n"
					+ "        from products p, (select product_code, sum(sell_count) sc\r\n"
					+ "                                 from history_detail\r\n"
					+ "                                 where to_char(sell_date,'yyyymm') = to_char(to_date(?),'yyyymm')\r\n"
					+ "                                 group by product_code) v\r\n"
					+ "        where p.product_code = v.product_code\r\n" + "        and p.minor_level = ?) vr\r\n"
					+ "where 매출순위 < 6";
			ps = conn.prepareStatement(query);
			ps.setInt(1, date);
			ps.setString(2, minor_level);

			// 쿼리문 실행
			rs = ps.executeQuery();

			// 결과 저장
			while (rs.next()) {
				posDto = new PosDto();

				posDto.setRanking(rs.getInt(1));
				posDto.setProductCode(rs.getString(2));
				posDto.setMinorLevel(rs.getString(3));
				posDto.setProductName(rs.getString(4));
				posDto.setPrice(rs.getInt(5));
				posDto.setPurchase(rs.getInt(6));
				posDto.setSellCount(rs.getInt(7));
				posDto.setStatTotalPrice(rs.getInt(8));
				posDto.setCompany(rs.getString(9));

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

	//////////////////////////////// 기간별 통계 ////////////////////////////////
	// <연도별 매출내역 select> 메소드
	// : 년 입력받아 조회
	
	public Vector<PosDto> findYearSell(int startYear, int endYear) {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosDto> list = new Vector<PosDto>();

		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = "select h.hy as 매출년도, sum(h.htp) as 매출합계, sum(h.hb) as 부가세, sum(h.hcp) as 현금매출, sum(h.hcdp) as 카드매출, count(*) as 고객수\r\n"
					+ "from (select total_price htp, to_char(sell_date, 'yyyy') hy, total_price*0.1 hb, cash_price hcp, card_price hcdp\r\n"
					+ "        from history) h\r\n" + "where hy between ? and ?\r\n" + "group by hy\r\n"
					+ "order by hy";
			ps = conn.prepareStatement(query);
			ps.setInt(1, startYear);
			ps.setInt(2, endYear);

			// 쿼리문 실행
			rs = ps.executeQuery();

			// 결과 저장
			while (rs.next()) {
				posDto = new PosDto();

				posDto.setSellDate(Integer.toString(rs.getInt(1)));
				posDto.setStatTotalPrice(rs.getInt(2));
				posDto.setTotalTax(rs.getInt(3));
				posDto.setCashPrice(rs.getInt(4));
				posDto.setCardPrice(rs.getInt(5));
				posDto.setCustomerCount(rs.getInt(6));

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

	// <월별 매출내역 select> 메소드
	// : 년 입력받아 조회
	public Vector<PosDto> findMonthSell(int year) {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosDto> list = new Vector<PosDto>();

		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = "select h.hy as 매출년월, sum(h.htp) as 매출합계, sum(h.hb) as 부가세, sum(h.hcp) as 현금매출, sum(h.hcdp) as 카드매출, count(*) as 고객수\r\n"
					+ "from (select total_price htp, to_char(sell_date, 'yyyymm') hy, total_price*0.1 hb, cash_price hcp, card_price hcdp\r\n"
					+ "        from history\r\n" + "        where to_char(sell_date,'yyyy')=?) h\r\n"
					+ "group by hy\r\n" + "order by hy";
			ps = conn.prepareStatement(query);
			ps.setInt(1, year);

			// 쿼리문 실행
			rs = ps.executeQuery();

			// 결과 저장
			while (rs.next()) {
				posDto = new PosDto();

				posDto.setSellDate(Integer.toString(rs.getInt(1)));
				posDto.setStatTotalPrice(rs.getInt(2));
				posDto.setTotalTax(rs.getInt(3));
				posDto.setCashPrice(rs.getInt(4));
				posDto.setCardPrice(rs.getInt(5));
				posDto.setCustomerCount(rs.getInt(6));

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

	// <일별 매출내역 select> 메소드
	// : 년, 월, 일 입력받아 조회
	public PosDto findDaySell(String year, String month, String day) {
	
		// String으로 입력한 날짜를 '년월일' 합쳐서 int로 변환
		int date = Integer.parseInt(year.concat(month).concat(day));
		try {
			// DB 연결
			conn = DBManager.getConnection();    
			
			// 쿼리문 세팅
			String query = "select h.hy as 매출일자, sum(h.htp) as 매출합계, sum(h.hb) as 부가세, sum(h.hcp) as 현금매출, sum(h.hcdp) as 카드매출, count(*) as 고객수\r\n" + 
					"from (select total_price htp, to_char(sell_date, 'yyyymmdd') hy, total_price*0.1 hb, cash_price hcp, card_price hcdp\r\n" + 
					"        from history\r\n" + 
					"        where to_char(sell_date,'yyyymmdd')= to_date(?)) h\r\n" + 
					"group by hy\r\n" + 
					"order by hy";                              
			ps = conn.prepareStatement(query);
			ps.setInt(1, date);
			
			// 쿼리문 실행
			rs = ps.executeQuery();                    
			
			// 결과 저장
			while(rs.next()) {	
				posDto = new PosDto();
				
				posDto.setSellDate(Integer.toString(rs.getInt(1)));
				posDto.setStatTotalPrice(rs.getInt(2));
				posDto.setTotalTax(rs.getInt(3));
				posDto.setCashPrice(rs.getInt(4));
				posDto.setCardPrice(rs.getInt(5));
				posDto.setCustomerCount(rs.getInt(6));
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
		return posDto;
	}
}
