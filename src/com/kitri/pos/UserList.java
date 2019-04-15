package com.kitri.pos;


import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class UserList {

	Vector<UserDto> data;
	Vector<String> userColumn;
	
	
	//====================//
	Administrator administrator;
	UserDao userDao;


	// 기본생성자
	public UserList() {

//		UserDao dao = new UserDao();
//		row = dao.getMemberList(); // 테이블의 값들을 select 
//		cols = new Vector<String>(getColum());
//		jTable = new JTable(model);
//		jTable.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
//		pane = new JScrollPane(jTable);
//		jTable.addMouseListener(this);
//
//		userDao = new UserDao();
//		data = userDao.getMemberList();
//		data = new Vector<UserDto>(data);
//
//		userColumn = new Vector<String>();
//		userColumn.addElement("유저코드");
//		userColumn.addElement("pw");
//		userColumn.addElement("id");
//		userColumn.addElement("aurthority");
//		userColumn.addElement("name");
//
//		
//		int size = data.size();
//		
//		for (int i = 0; i < size; i++) {
//			// 행
//			Vector<String> row = new Vector<String>();
//		
//
//			// 숫자를 문자로 변환 행에 추가
//			row.addElement(data.get(i).getUserCode() +"");
//			row.addElement(data.get(i).getPw());
//			row.addElement(data.get(i).getId());
//			row.addElement(data.get(i).getAuthority());
//			row.addElement(data.get(i).getName());
//
//			administrator.tm = new DefaultTableModel(userColumn, data);
//			administrator.tm.addRow(row);


		}
	}
//}
