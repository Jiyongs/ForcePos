package com.kitri.pos.sales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

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
			
			while(rs.next()) {
				
				posDto.setMembershipId(rs.getString(1));
				posDto.setMemberName(rs.getString(2));
				posDto.setPoint(rs.getInt(3));
				
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public double searchByCP(String item) {
		

		conn = DBManager.getConnection();
		
		try {
			String query = "select discount_pct from discount where cooperate_name = lower(?)";
			
			ps = conn.prepareStatement(query);
			ps.setString(1, item);
			rs = ps.executeQuery();
			
			while(rs.next()) {
			dis = Double.parseDouble(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dis;
		
	}

	public Vector<PosDto> searchBy(String identifier) {
		
		Vector<PosDto> salesList = new Vector<PosDto>();
		
		String query = "";
		try {
			conn = DBManager.getConnection();
			System.out.println(SalesInputService.key);
//		"번호", "상품코드", "상품명", "단가", "수량", "금액", "유통기한"	
			if(SalesInputService.key == true) {
				query = "select product_code, product_name, price, 1 from Stocks where product_code = upper(?)";				
				System.out.println("code");
			}else if(SalesInputService.key == false){
				query = "select product_code, product_name, price, 1 from Stocks where product_name = upper(?)";
				System.out.println("name");
		
			}

			ps = conn.prepareStatement(query);
			
			ps.setString(1,identifier);

			rs = ps.executeQuery();

			
			
			while (rs.next()) {
				
				posDto = new PosDto();
				posDto.setListNum(i);
				posDto.setProductCode(rs.getString(1));
				posDto.setProductName(rs.getString(2));
				posDto.setPrice(rs.getInt(3));
				posDto.setSellCount(1);
				posDto.setPricensellCount(rs.getInt(3) * posDto.getSellCount());				
//				posDto.setRealExp(rs.getString(4));
				
				salesList.add(posDto);
				i++;
				
			}
			
			int x = salesList.size();
			System.out.println(x);
			System.out.println("조회완료");

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				com.kitri.pos.calc.DBManager.dbClose(rs, ps, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return salesList;
	}

}


//tfP1BeforePrice