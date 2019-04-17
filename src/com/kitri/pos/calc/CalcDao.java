package com.kitri.pos.calc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.kitri.pos.sales.PosDto;

public class CalcDao {
	PCalc pCalc = new PCalc();

	
/////////////////////////////////////////////////////////////////

	// 쿼리문 결과 (1행) 담을 Dto 객체
	com.kitri.pos.calc.PosDto posDto = new com.kitri.pos.calc.PosDto();


	
	// DB연결된 상태(세션)을 담은 객체
	Connection conn = null;

	// 쿼리문에 사용하는 state객체
	PreparedStatement ps = null;

	Statement st = null;
	ResultSet rs = null;



	
	

	public int inputComs_Calc() {
		int sumCash = 0;
		try {
			conn = DBManager.getConnection();
			
			String query = "select sum(cash_price)  from history where to_char(sell_date,'yyyy.mm.dd') = to_char(sysdate, 'yyyy.mm.dd')";
			
			st = conn.createStatement();
			rs = st.executeQuery(query);

			
			pCalc = new PCalc();
			
			while(rs.next()) {
				sumCash = rs.getInt(1);
				System.out.println(sumCash);					
			}
			pCalc.tfCashState.setText(Integer.toString(sumCash));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(rs, st, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return sumCash;

	}

//	정산결과값 테이블에 insert
	public void calc_Apply() {

//		int s, int c, int r
		
		
		conn = DBManager.getConnection();
		try {
		
			String query = "insert into money(count_date ,user_code ,coms_calc ,current_money ,total_calc)"
					+ " values(to_date(sysdate,'yyyy.mm.dd hh.mi.ss'),?,?,?,?)";
		
			ps = conn.prepareStatement(query);

			ps.setInt(1,posDto.getUserCode());
			ps.setInt(2,posDto.getComsCalc());
			ps.setInt(3,posDto.getCurrentMoney());
			ps.setInt(4,posDto.getTotalCalc());
			

			int rows = ps.executeUpdate();
			System.out.println("변경된 row  : " + rows);
			if(rows == 0)
				JOptionPane.showMessageDialog(pCalc, "정산처리가 실패했습니다. 다시 시도하십시오", "정산오류", JOptionPane.ERROR_MESSAGE);
				return;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.dbClose(ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	

	}
	
}
