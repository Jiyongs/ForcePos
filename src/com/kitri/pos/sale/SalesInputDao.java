package com.kitri.pos.sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.kitri.pos.ForcePos;
import com.kitri.pos.db.*;

public class SalesInputDao {

	SalesInputService salesInputService;
	PosDto posDto = null;
	Double dis;
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	int i = 1;

	public boolean costomerRef(String phoneNum) {

		conn = DBManager.getConnection();

		try {

			String query = "select Membership_id, name, point from Membership where phone = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, phoneNum);
			rs = ps.executeQuery();

			while (rs.next()) {

				posDto.setMembershipId(rs.getString(1));
				posDto.setMemberName(rs.getString(2));
				posDto.setPoint(rs.getInt(3));

				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(rs, ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return false;

	}

	public void searchByCP(String item) {

		conn = DBManager.getConnection();

		try {
			String query = "select discount_code, cooperate_name, discount_pct from discount where cooperate_name = lower(?)";

			ps = conn.prepareStatement(query);
			ps.setString(1, item);
			rs = ps.executeQuery();

			while (rs.next()) {
				posDto.setDiscountCode(rs.getString(1));
				posDto.setCooperateName(rs.getString(2));
				posDto.setDiscountPct(rs.getFloat(3));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(rs, ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public Vector<PosDto> searchBy(String identifier) {

		Vector<PosDto> salesList = new Vector<PosDto>();

		String query = "";
		try {
			conn = DBManager.getConnection();
			System.out.println(SalesInputService.key);
//		"번호", "상품코드", "상품명", "단가", "수량", "금액", "유통기한"	
			if (SalesInputService.key == true) {
				query = "select product_code, product_name, price, 1, to_char(in_date,'yy/mm/dd') from Stocks where product_code = upper(?) and volume > 0 order by in_date asc";
				System.out.println("code");
			} else if (SalesInputService.key == false) {
				query = "select product_code, product_name, price, 1, to_char(in_date,'yy/mm/dd') from Stocks where product_name = upper(?) and volume > 0 order by in_date asc";
				System.out.println("name");

			}

			ps = conn.prepareStatement(query);

			ps.setString(1, identifier);

			rs = ps.executeQuery();

			while (rs.next()) {

				posDto = new PosDto();
				posDto.setListNum(i);
				posDto.setProductCode(rs.getString(1));
				posDto.setProductName(rs.getString(2));
				posDto.setPrice(rs.getInt(3));
				posDto.setSellCount(1);
				posDto.setPricensellCount(rs.getInt(3) * posDto.getSellCount());
				posDto.setInDate(rs.getString(5));

				salesList.add(posDto);
				i++;

				return salesList;
			}

			int x = salesList.size();
			System.out.println(x);
			System.out.println("조회완료");

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(rs, ps, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public void registerHistory() {
		conn = DBManager.getConnection();

		try {
			String query = "insert into history(sell_id, membership_id, discount_code, sell_date, cash_price, card_price, total_price) "
					+ "values(history_sell_id_seq.nextval, ?, ?, to_date(sysdate, 'yy/mm/dd hh24:mi:ss'), ?, ?, ?)";

			ps = conn.prepareStatement(query);
			ps.setString(1, posDto.getMembershipId());
			ps.setString(2, posDto.getDiscountCode());
			ps.setInt(3, posDto.getCashPrice());
			ps.setInt(4, posDto.getCardPrice());
			ps.setInt(5, posDto.getTotalPrice());

			int r = ps.executeUpdate();

//			System.out.println("변경된 row : " + r);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(rs, ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public void registerHisDetail(int size, Vector<Object> getgoods) {
		conn = DBManager.getConnection();

		try {
			String query = "insert into history_detail(sell_id, product_code, user_code, in_date, sell_date, sell_count, product_name, price) "
					+ "values ((select sell_id from (select sell_id from history order by sell_id desc) where rownum=1), ?, ?, to_date(?,'yy-mm-dd'), (select sell_date from (select sell_date from history order by sell_date desc) where rownum=1), ?, ?, ?)";

			int j = 0;
			ps = conn.prepareStatement(query);

			System.out.println(size);
			for (int i = 0; i < size; i++) {
//					System.out.println(j);
				ps.setString(1, String.valueOf(getgoods.elementAt(j++)));
				ps.setInt(2, ForcePos.usercodeDto.getUserCode());
				ps.setString(3, String.valueOf(getgoods.elementAt(j++)));
				int cnt = Integer.parseInt(String.valueOf(getgoods.elementAt(j++)));
				ps.setInt(4, cnt);
				ps.setString(5, String.valueOf(getgoods.elementAt(j++)));
				ps.setInt(6, Integer.parseInt(String.valueOf(getgoods.elementAt(j++))) * cnt);
//					System.out.println(j);

				int r = ps.executeUpdate();

//					System.out.println("추가된 열개수 : " + r);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void reflectStocks(int size, Vector<Object> getgoods) {
		conn = DBManager.getConnection();

		try {
			String query = "update stocks set volume = volume -? where product_code = ? and in_date = to_date(?, 'yy/mm/dd')";
			ps = conn.prepareStatement(query);
			int j = 0;
			for (int i = 0; i < size; i++) {
				ps.setString(2, String.valueOf(getgoods.elementAt(j++)));
				ps.setString(3, String.valueOf(getgoods.elementAt(j++)));
				ps.setInt(1, Integer.valueOf(String.valueOf(getgoods.elementAt(j++))));
				j += 2;
				int r = ps.executeUpdate();
				System.out.println("추가된 열개수 : " + r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(ps, conn);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

	}

	public void pointUpdate() {

		conn = DBManager.getConnection();

		try {
			String query = "update membership set point = ? where membership_id = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, posDto.getPoint());
			ps.setString(2, posDto.getMembershipId());

			int r = ps.executeUpdate();
			System.out.println("변경된 행의 갯수 : " + r);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(ps, conn);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

	}

	public void SellIdDate() {

		try {
			// DB 연결
			conn = DBManager.getConnection();

			// 쿼리문 세팅
			String query = " select sell_id, sell_date "
					+ "from (select sell_id, sell_date from history order by sell_id desc) "
					+ "where rownum=1";
			ps = conn.prepareStatement(query);

			// 쿼리문 실행
			rs = ps.executeQuery();

			// 결과 저장
			while (rs.next()) {
				

				ForcePos.selldto.setSellId(Integer.toString(rs.getInt(1)));
				ForcePos.selldto.setSellDate(rs.getString(2));

			
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

	}

}// 클래스 끝
